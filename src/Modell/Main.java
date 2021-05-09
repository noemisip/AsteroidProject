package Modell;
import Frame.EndGame;

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
	private static int gatecnt =0;

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

	public void SetActiveSettler(Settler s){	//a soronkovetkezo telepes beallitasa
		activeSettler =s;
		Modell.ControlPanel cp = view.GetGameFrame().GetControlPanel();
		cp.SetSettler(s);	//beallitja, hogy melyik az aktiv jatekos
		cp.Update();		//frissiti a ControllPanelon levo szovegeket
	}

	public void SettlerAction(int result){ //a telepes egy muveletet vegez
		Modell.ControlPanel cp = view.GetGameFrame().GetControlPanel();
		switch (result){
			case 1:	//a telepes a kivalasztott aszteroidara utazik
				Modell.Asteroid a = cp.GetAsteroid();
				activeSettler.Move(a);
				break;
			case 2: //a telepes az aszteroida kergebe fur
				activeSettler.Drill();
				break;
			case 3: //a telepes banyaszik az aszteroidan
				activeSettler.Mine();
				break;
			case 4: //a telepes visszahelyez egy kivalasztott tipusu nyersanyagot
				Modell.Material m = cp.GetMaterial();
				activeSettler.RestoreMaterial(m);
				break;
			case 5:	//a telepes egy robotot epit
				activeSettler.CreateRobot();
				break;
			case 6: //a telepes kaput epit
				activeSettler.CreateGate();
				break;
			case 7:	//a telepeslehelyez egy kaput
				Modell.Gate g = cp.GetGate();
				activeSettler.PlaceGate(g);
				break;
			default: break;
		}
		Game.getInstance().Action();
		view.UpdateAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {		//a felhasznalo kattint egy gombon: valamilyen muveletet vegez a telepessel
		JButton source = (JButton) e.getSource();
		SettlerAction((int)source.getClientProperty("id"));
	}

	public void SteppableAction(){
		Space sp = Game.getInstance().GetSpace();
		boolean solarstrom = sp.GetSolarStrom();
		if(solarstrom) view.ShowSolarStrom();
		view.UpdateAll();
	}
	public String GetKey(Object o){ //objektum nevenek megekeresese
		for(Map.Entry<String, Object> t : hash.entrySet()){
			if(t.getValue().equals(o)) return t.getKey();
		}
		return null;
	}

	public static void Load(int i) throws IOException {
		//listak es szamalalok nullazasa
		gatecnt=0;
		hash.clear();
		view.startGame();
		Random rnd = new Random();
		int r = rnd.nextInt(10)+5;
		int acnt=r;							//aszteroidak szama
		for(int j=0; j<r; j++){ 			//random szamu aszteroida letrehozasa (min 5-ot, max 15-ot hoz létre)
			Asteroid a = new Asteroid();
			GAsteroid ga = new GAsteroid();	//aszteroidahoz tartozo grafikus peldany
			AddAsteroid(a, ga, j);
		}
		Game.getInstance().GetSpace().SetNeighbours();	//szomszedsagok veletlenszeru beallitasa

		for(int j=0; j<i; j++){ 						//a megadott szamu telepes letrehozasa
			Settler s = new Settler();
			GSettler gs = new GSettler();
			AddSettler(s, gs);
			view.GetGameFrame().GetControlPanel().SetSettler(s);
		}
		r=rnd.nextInt(3);
		for(int j=0; j<r; j++){ //random szamu robot letrehozasa (max 3)
			Robot robot = new Robot();
			CreateRobot(robot);
			AddCreature(robot);
		}
		r=rnd.nextInt(acnt/2);
		for(int j=0; j<r; j++){ //random szamu ufo letrehozasa (max az aszteroidak szama/2)
			Ufo u = new Ufo();
			GUfo gu = new GUfo();
			gu.SetUfo(u);
			gu.SetView(view);
			view.AddDrawable(gu);
			AddCreature(u);
		}
		Game.getInstance().StartGame(); //jatek kezdete
		view.UpdateAll(); //a creaturek, aszteroidak megjelenitese, nezzet frissitese

	}
	public static void AddSettler(Settler s, GSettler gs){	//uj telepes jon letre
		gs.SetSettler(s);									//grafikus es modellbeli peldany osszrendelese
		view.AddDrawable(gs);								//grafikus peldany hozzaadasa a view listajahoz
		gs.SetView(view);									//view beallitasa
		Game.getInstance().AddSettler(s);					//telepes hozaadasa a jatekterhez
		Space sp = Game.getInstance().GetSpace();			//telepes hozzadasa a spacehez
		sp.AddCreature(s);
		s.SetAsteroid(sp.GetAsteroid());					//telepes elhelyzese az eslo aszteroidan
		sp.GetAsteroid().AddCreature(s);
		String name = "settler"+ Game.getInstance().GetSettlers().size();	//telepes nevenek beallitasa
		gs.SetName(name);
		AddHash(name, s);									//telepes hozzaadasa a hashez
	}
	public static void AddCreature(Creature c){				//uj creature (robot vagy ufo) jon letre
		Game.getInstance().AddSteppable((Steppable)c);		//creature hozaadasa a jatekhoz
		Space sp = Game.getInstance().GetSpace();			//creature hozaadasa a spacehez
		sp.AddCreature(c);
		sp.GetAsteroid().AddCreature(c);					//creature elhelyezese az elso aszteroidan
		c.SetAsteroid(sp.GetAsteroid());
	}
	public static void AddAsteroid(Asteroid a, GAsteroid ga, int i){ //uj aszteroida jon letre
		Random rnd = new Random();
		//koordinatak beallitasa: az aszteroidak nem kerulnek kozel egymashoz
		int gax=rnd.nextInt(18)*50+50;
		int gay=rnd.nextInt(10)*50+100;
		while(!Newkoords(gax,gay)){		//ellenorzi, hogy nem kerulnek-e egymasra az aszteroidak
			gax=rnd.nextInt(18)*50+50;
			gay=rnd.nextInt(10)*50+100;
		}
		ga.SetKoord(gax,gay);			//koordinatak beallitasa
		ga.SetAsteroid(a);
		view.AddDrawable(ga);
		ga.SetView(view);											//view beallitasa
		String name = "asteroid"+(i+1);								//aszteroida nevenek beallitasa
		ga.SetName(name);
		AddHash(name, a);											//aszteroida hozzaadasa a hashez
		Game.getInstance().AddSteppable(a);							//aszteroida hozzaadasa a jatekhoz
		Space sp = Game.getInstance().GetSpace();					//aszteroida hozzaadasa a spacehez
		sp.AddAsteroid(a);
		a.SetSpace(sp);
	}
	public static void AddHash(String key, Object object){
		hash.put(key, object);
	} //uj elem felvetele a hashbe
	public HashMap<String, Object> GetHash(){
		return hash;
	}	//egy objektum megkeresese nev alapjan

	public void AddGate(Gate g){		//uj kapu hozzadasa a hashez
		gatecnt++;
		hash.put("gate"+gatecnt, g);
	}

	public static void CreateRobot(Robot robot){ //letrejon egy robot: letre kell hozza hozni a megfelelo grafikus peldanyt
		GRobot gr = new GRobot();
		gr.SetRobot(robot);
		gr.SetView(view);
		view.AddDrawable(gr);
	}
	public static boolean Newkoords(int x,int y){ //ellenorzi, hogy az uj aszteroida nem fog-e egy masikra racsuszni
		boolean newkoord=true;
		int i=0;
		while(newkoord && i<view.GetDrawables().size()){
			if(view.GetDrawables().get(i).GetX()==x && view.GetDrawables().get(i).GetY()==y) newkoord=false;
			i++;
		}
		return newkoord;
	}
}
