package Modell;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;

public class Game {

	private static boolean onGame = true; //azt mutatja, hogy tart-e meg a jatek, alapbol true ertket kap
	private static Space space = new Space(); //a jatekszintere, a vilagur
	private static ArrayList<Steppable> steppable= new ArrayList<Steppable>();; //a jatekban resztvevo leptetheto dolgok listaja
	private static ArrayList<Settler> settlers = new ArrayList<Settler>(); //a jatekban resztvevo leptetheto telepesek listaja
	private static BillOfMaterials bill; //a bazis megepitesehez szukseges nyersanyagok megletet ellenorzi
	private static int cnt;


	private static Game INSTANCE;

	private Game() { }

	//mivel a Game osztaly egy singleton, ezert letrehozzunk az egyetlen peldany, amivel dolgozni fogunk
	public static Game getInstance() {
		bill=  new BillOfMaterials();
		if(INSTANCE == null) {
			INSTANCE = new Game(); 
		}

		return INSTANCE;
	}
	
	//ellenorzi, hogy a jatekban vannak-e meg eletben telepesek, ha nem, meghivja a jatekot befejezo EndGame() fuggvenyt
	public void CheckSettlers() {
		if(settlers.size()==0)
		EndGame(false);
	}
	
	//megviszgalja, hogy megvannak-e az urbazis felepitesehez szukseges nyersanyagok, ha igen meghivja a jatekot befejezo EndGame() fuggvenyt
	public void CheckBase(Asteroid a) {
		ArrayList<Creature> asteroidcreatures= a.GetCreatures();
		bill.SetUpBase(); //feltolti a BillOfMaterial materialListjét az urbazis epitesehez szukseges alapanyagokkal
		for(int i=0;i<asteroidcreatures.size();i++){
			ArrayList<Material> creaturesmaterials=asteroidcreatures.get(i).GetMaterials();
			if(bill.CheckMaterials(creaturesmaterials)) EndGame(true); //Megvan az osszes szukseges material a settlereknel az urbazishoz?
		}

	}
	
	//a jatek befejezesekor meghivodo fuggveny
	public void EndGame(boolean result) {
		onGame=false; //vege a jateknak
		space = new Space();
		steppable.clear();
		settlers.clear();
		Main.GetView().endGame(result);
	}

	//a jatek kezdetekor meghivodo fuggveny	
	public void StartGame() {
		//jatek elinditasa
		onGame=true; //elindul a jatek
		cnt=0;
		Main.getInstance().SetActiveSettler(settlers.get(cnt));
	}
	
	//egy kor a jatekban: eloszor a telepesek cselekvesei vegzodnek el, majd a Steppeble objektumok lepesei
	public void Round() {
			//steppable-k lepesei
			for(int i=0; i< steppable.size(); i++){
				steppable.get(i).Step();
			}
		space.Step();
		Main.getInstance().SteppableAction();
	}

	//egy telepes altal vegrehajthato muveletek 
	public void Action() {
		cnt++;
		if(cnt==settlers.size()){
			cnt=0;
			Round();
		}
		if(settlers.size()>0) Main.getInstance().SetActiveSettler(settlers.get(cnt));
	}

	//egy telepes halalakor kiveszi a settlers listabol	
	public void RemoveSettler(Settler s) {
        settlers.remove(s);
	}
	
	//egy telepest hozzaad a settlers listahoz
	public void AddSettler(Settler s) {
		settlers.add(s);
	}
	
	//kiveszi a steppable listahoz a parameterkent megkapott Modell.Steppable peldanyt
	public void RemoveSteppable(Steppable s) {
        steppable.remove(s);
	}
	
	//hozzáadja a steppable listahoz a parameterkent megkapott Modell.Steppable peldanyt
	public void AddSteppable(Steppable s) {
        steppable.add(s);
	}
	
	//space beallitasa
	public void SetSpace(Space sp){
		space=sp;
	}
	
	//lekeri a settlerek listajat
	public ArrayList<Settler> GetSettlers(){ return settlers;}
	
	//lekeri, hogy vege van-e a jateknak
	public boolean GetOnGame(){ return onGame;}

	//space attributum getter fuggvenye
	public Space GetSpace() {
		return space;
	}

	//steppables listat visszaado fuggveny
	public ArrayList<Steppable> GetSteppables(){ return steppable;}
}
