import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;

public class Main {
	private static HashMap<String, Object> hash = new HashMap();
	private static View view;
	public static void main(String[] args) {

	}
	public void SettlerAction(Settler s){
		ControlPanel cp = view.GetControlPanel();
		cp.SetSettler(s);
		cp.Update();
		int result = cp.UserInput();
		switch (result){
			case 1:
				s.Move(a);
				break;
			case 2:
				s.Drill();
				break;
			case 3:
				s.Mine();
				break;
			case 4:
				s.RestoreMaterial(m);
				break;
			case 5:
				s.CreateRobot();
				break;
			case 6:
				s.CreateGate();
				break;
			case 7:
				s.PlaceGate(g);
				break;
			default: break;
		}
		view.UpdateAll();

	}
	public void SteppableAction(){
		Space sp = Game.getInstance().GetSpace();
		boolean solarstrom = sp.GetSolarStrom();
		if(solarstrom) view.ShowSolarStorm();
		view.UpdateAll();
	}
	public String GetKey(Object o){
		for(Map.Entry<String, Object> t : hash.entrySet()){
			if(t.getValue().equals(o)) return t.getKey();
		}
		return null;
	}
	public void Load(int i){}
	public void AddSettler(Settler s){
		Game.getInstance().AddSettler(s);
		Space sp = Game.getInstance().GetSpace();
		sp.AddCreature(s);
		sp.GetAsteroid().AddCreature(s);
		String name = "settler"+Game.getInstance().GetSettlers().size();
		AddHash();
	}
	public void AddCreature(Creature c){
		Game.getInstance().AddSteppable((Steppable)c);
		Space sp = Game.getInstance().GetSpace();
		sp.AddCreature(c);
		sp.GetAsteroid().AddCreature(c);
	}
	public void AddAsteroid(Asteroid a){}
	public void AddHash(String key, Object object){
		hash.put(key, object);
	}
}
