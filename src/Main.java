import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
	private static HashMap<String, Object> hash = new HashMap();
	private static View view;
	private static Main INSTANCE;
	public static Main getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Main();
		}
		return INSTANCE;
	}
	private Main(){}
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
	public void Load(int i){
		Random rnd = new Random();
		int r = rnd.nextInt();
		for(int j=0; j<r; j++){ //create aasteroids
			Asteroid a = new Asteroid();
			GAsteroid ga = new GAsteroid();
			ga.SetAsteroid(a);
			view.AddDrawable(ga);
			AddAsteroid(a, ga, j);
		}
		for(int j=0; j<i; j++){
			Settler s = new Settler();
			GSettler gs = new GSettler();
			gs.SetSettler(s);
			view.AddDrawable(gs);
			AddSettler(s, gs);
		}
		r=rnd.nextInt();
		for(int j=0; j<r; j++){
			Robot robot = new Robot();
			GRobot gr = new GRobot();
			gr.SetRobot(robot);
			view.AddDrawable(gr);
			AddCreature(robot);
		}
		r=rnd.nextInt();
		for(int j=0; j<r; j++){
			Ufo u = new Ufo();
			GUfo gu = new GUfo();
			gu.SetUfo(u);
			view.AddDrawable(gu);
			AddCreature(u);
		}
		view.UpdateAll();
		Game.getInstance().StartGame();
	}
	public void AddSettler(Settler s, GSettler gs){
		Game.getInstance().AddSettler(s);
		Space sp = Game.getInstance().GetSpace();
		sp.AddCreature(s);
		sp.GetAsteroid().AddCreature(s);
		String name = "settler"+Game.getInstance().GetSettlers().size();
		gs.SetName(name);
		AddHash(name, s);
	}
	public void AddCreature(Creature c){
		Game.getInstance().AddSteppable((Steppable)c);
		Space sp = Game.getInstance().GetSpace();
		sp.AddCreature(c);
		sp.GetAsteroid().AddCreature(c);
	}
	public void AddAsteroid(Asteroid a, GAsteroid ga, int i){
		String name = "asteroid"+(i+1);
		ga.SetName(name);
		AddHash(name, a);
		Game.getInstance().AddSteppable(a);
		Space sp = Game.getInstance().GetSpace();
		sp.AddAsteroid(a);
	}
	public void AddHash(String key, Object object){
		hash.put(key, object);
	}
}
