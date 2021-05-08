package Modell;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main  implements ActionListener {
	private static HashMap<String, Object> hash = new HashMap();
	private static View view;
	private static Main INSTANCE;
	private Settler activeSettler;

	public static Main getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Main();
		}
		return INSTANCE;
	}
	private Main(){}
	public static void main(String[] args) throws IOException {
		view=new View();
	}

	public static View GetView() {
		return view;
	}

	public void SetActiveSettler(Settler s){
		activeSettler =s;
	}

	public void SettlerAction(){
		Modell.ControlPanel cp = view.GetGameFrame().GetControlPanel();
		cp.SetSettler(activeSettler);
		cp.Update();
		int result = cp.UserInput();
		switch (result){
			case 1:
				Modell.Asteroid a = cp.GetAsteroid();
				activeSettler.Move(a);
				break;
			case 2:
				activeSettler.Drill();
				break;
			case 3:
				activeSettler.Mine();
				break;
			case 4:
				Modell.Material m = cp.GetMaterial();
				activeSettler.RestoreMaterial(m);
				break;
			case 5:
				activeSettler.CreateRobot();
				break;
			case 6:
				activeSettler.CreateGate();
				break;
			case 7:
				Modell.Gate g = cp.GetGate();
				activeSettler.PlaceGate(g);
				break;
			default: break;
		}
		Game.getInstance().Action();
		view.UpdateAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		System.out.println(source.getText());
		SettlerAction();
	}

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

	public static void Load(int i) throws IOException {
		view.startGame();
		Random rnd = new Random();
		int r = rnd.nextInt(10)+5;
		int acnt=r;
		Asteroid first = new Asteroid();
		for(int j=0; j<r; j++){ //random szamu aszteroida letrehozasa (min 5-ot, max 15-ot hoz létre)
			Asteroid a = new Asteroid();
			if(j==0) first =a;
			GAsteroid ga = new GAsteroid();
			int gax=rnd.nextInt(11)*50+500;int gay=rnd.nextInt(10)*50+100;
			while(!newkoords(gax,gay)){
			gax=rnd.nextInt(11)*50+500;
			gay=rnd.nextInt(10)*50+100;
			}
			ga.SetKoord(gax,gay);
			ga.SetAsteroid(a);
			view.AddDrawable(ga);
			AddAsteroid(a, ga, j);
		}
		Game.getInstance().GetSpace().SetNeighbours(); //szomszedsagok veletlenszeru beallitasa
		for(int j=0; j<i; j++){ //a megadott szamu telepes letrehozasa
			Settler s = new Settler();
			s.SetAsteroid(first);
			GSettler gs = new GSettler();
			gs.SetSettler(s);
			view.AddDrawable(gs);
			AddSettler(s, gs);
			view.GetGameFrame().GetControlPanel().SetSettler(s);
		}
		r=rnd.nextInt(3);
		for(int j=0; j<r; j++){ //random szamu robot letrehozasa (max 3)
			Robot robot = new Robot();
			robot.SetAsteroid(first);
			GRobot gr = new GRobot();
			gr.SetRobot(robot);
			gr.SetView(view);
			view.AddDrawable(gr);
			AddCreature(robot);
		}
		r=rnd.nextInt(acnt/2);
		for(int j=0; j<r; j++){ //random szamu ufo letrehozasa (max az aszteroidak szama/2)
			Ufo u = new Ufo();
			u.SetAsteroid(first);
			GUfo gu = new GUfo();
			gu.SetUfo(u);
			gu.SetView(view);
			view.AddDrawable(gu);
			AddCreature(u);
		}
		view.UpdateAll(); //a creaturek, aszteroidak megjelenitese, nezzet frissitese
		Game.getInstance().StartGame(); //jatek kezdete

	}
	public static boolean newkoords(int x,int y){
		boolean newkoord=true;
		int i=0;
		while(newkoord && i<view.GetDrawables().size()){
			if(view.GetDrawables().get(i).GetX()==x && view.GetDrawables().get(i).GetY()==y) newkoord=false;
			i++;
		}
		return newkoord;
	}
	public static void AddSettler(Settler s, GSettler gs){
		gs.SetView(view);
		Game.getInstance().AddSettler(s);
		Space sp = Game.getInstance().GetSpace();
		sp.AddCreature(s);
		sp.GetAsteroid().AddCreature(s);
		String name = "settler"+ Game.getInstance().GetSettlers().size();
		gs.SetName(name);
		AddHash(name, s);
	}
	public static void AddCreature(Creature c){
		Game.getInstance().AddSteppable((Steppable)c);
		Space sp = Game.getInstance().GetSpace();
		sp.AddCreature(c);
		sp.GetAsteroid().AddCreature(c);
	}
	public static void AddAsteroid(Asteroid a, GAsteroid ga, int i){
		ga.SetView(view);
		String name = "asteroid"+(i+1);
		ga.SetName(name);
		AddHash(name, a);
		Game.getInstance().AddSteppable(a);
		Space sp = Game.getInstance().GetSpace();
		sp.AddAsteroid(a);
		a.SetSpace(sp);
	}
	public static void AddHash(String key, Object object){
		hash.put(key, object);
	}
	public HashMap<String, Object> GetHash(){return hash;}

}
