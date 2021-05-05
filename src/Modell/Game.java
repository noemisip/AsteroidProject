package Modell;

import java.util.ArrayList;

public class Game {

	private boolean onGame = true; //azt mutatja, hogy tart-e meg a jatek, alapbol true ertket kap
	private Space space; //a jatekszintere, a vilagur
	private static ArrayList<Steppable> steppable= new ArrayList<Steppable>();; //a jatekban resztvevo leptetheto dolgok listaja
	private static ArrayList<Settler> settlers = new ArrayList<Settler>(); //a jatekban resztvevo leptetheto telepesek listaja
	private static BillOfMaterials bill; //a bazis megepitesehez szukseges nyersanyagok megletet ellenorzi


	private static Game INSTANCE;

	public Game() {
	}

	//mivel a Modell.Game osztaly egy singleton, ezert letrehozzunk az egyetlen peldany, amivel dolgozni fogunk
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
			ArrayList creaturesmaterials=asteroidcreatures.get(i).GetMaterials();
			if(bill.CheckMaterials(creaturesmaterials)) EndGame(true); //Megvan az osszes szukseges material a settlereknel az urbazishoz?
		}

	}
	
	//a jatek befejezesekor meghivodo fuggveny
	public void EndGame(boolean result) {
		//TODO Nyertek vs vesztettek
		onGame=false; //vege a jateknak
	}

	//a jatek kezdetekor meghivodo fuggveny	
	public void StartGame() {
		//játéktér felépítése
		onGame=true; //elindul a jatek
		OnGame();
	}
	
	//egy kor a jatekban: eloszor a telepesek cselekvesei vegzodnek el, majd a Steppeble objektumok lepesei
	public void Round() {
		while (onGame) {
			//settlerek lepesei
			for (Settler s : settlers) {
				Action(s);
			}
			//steppable-k lepesei
			for (Steppable st : steppable) {
				st.Step();
			}
		}
	}

	//egy telepes altal vegrehajthato muveletek 
	public void Action(Settler s) {
		//a tesztekhez meg nem szukseges
		//beolvasás a controllerről
		//settlerek lepesei
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
	
	//addig hivogatja meg a Round() fuggvenyt amig az onGame attributum igaz
	public void OnGame() {
	//nem a vegleges, meg nem kell a tesztek futasahoz
        Round();
	}
	
	//space beallitasa
	public void SetSpace(Space sp){
		space=sp;
	}
	
	//lekeri a settlerek listajat
	public ArrayList<Settler> GetSettlers(){ return settlers;}
	
	//lekeri, hogy vege van-e a jateknak
	public boolean GetOnGame(){ return onGame;}

	public Space GetSpace() {
		return space;
	}

	public ArrayList<Steppable> GetSteppables(){ return steppable;}
}
