package Modell;

import Frame.GameFrame;

import java.io.IOException;
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
	public static void main(String[] args) throws IOException {
		view=new View();
		/*GameFrame menu = new GameFrame();
		//Frame.EndGame jaja=new Frame.EndGame(false);
		//SettlerNumber j=new SettlerNumber();
		GRobot r = new GRobot();
		r.SetKoord(700,300);
		r.Draw();
		GSettler s = new GSettler();
		s.SetKoord(710,300);
		s.Draw();
		GUfo u = new GUfo();
		u.SetKoord(720,300);
		u.Draw();
		GAsteroid a = new GAsteroid();
		a.SetKoord( 710, 340);
		a.Draw();*/
	}

	public static View GetView() {
		return view;
	}

	//	public void SettlerAction(Modell.Settler s){
//		Modell.ControlPanel cp = view.GetControlPanel();
//		cp.SetSettler(s);
//		cp.Update();
//		int result = cp.UserInput();
//		switch (result){
//			case 1:
//				Modell.Asteroid a = cp.GetAsteroid();
//				s.Move(a);
//				break;
//			case 2:
//				s.Drill();
//				break;
//			case 3:
//				s.Mine();
//				break;
//			case 4:
//				Modell.Material m = cp.GetMaterial();
//				s.RestoreMaterial(m);
//				break;
//			case 5:
//				s.CreateRobot();
//				break;
//			case 6:
//				s.CreateGate();
//				break;
//			case 7:
//				Modell.Gate g = cp.GetGate();
//				s.PlaceGate(g);
//				break;
//			default: break;
//		}
//		view.UpdateAll();
//
//	}
	public void SteppableAction(){
		Space sp = Game.getInstance().GetSpace();
		boolean solarstrom = sp.GetSolarStrom();
		if(solarstrom) view.ShowSolarStrom();
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
		for(int j=0; j<5; j++){ //create aasteroids
			Asteroid a = new Asteroid();
			GAsteroid ga = new GAsteroid();
			int gax=rnd.nextInt(550)+350;
			int gay=rnd.nextInt(600)+50;
			ga.SetKoord(gax,gay);
			ga.SetAsteroid(a);
			view.AddDrawable(ga);
			AddAsteroid(a, ga, j);
		}
		for(int j=0; j<3; j++){
			Settler s = new Settler();
			GSettler gs = new GSettler();
			gs.SetSettler(s);
			view.AddDrawable(gs);
			AddSettler(s, gs);
		}
		r=rnd.nextInt();
		for(int j=0; j<4; j++){
			Robot robot = new Robot();
			GRobot gr = new GRobot();
			gr.SetRobot(robot);
			view.AddDrawable(gr);
			AddCreature(robot);
		}
		r=rnd.nextInt();
		for(int j=0; j<5; j++){
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
		String name = "settler"+ Game.getInstance().GetSettlers().size();
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
