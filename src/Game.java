import java.util.ArrayList;

public class Game {

	private boolean onGame = true; //azt mutatja, hogy tart-e meg a jatek, alapbol true ertket kap
	private Space space; //a jatekszintere, a vilagur
	private static ArrayList<Steppable> steppable; //a jatekban resztvevo leptetheto dolgok listaja
	private static ArrayList<Settler> settlers; //a jatekban resztvevo leptetheto telepesek listaja
	private static BillOfMaterials bill; //a bazis megepitesehez szukseges nyersanyagok megletet ellenorzi


	private static Game INSTANCE;

	private Game() {
	}

	//mivel a Game osztaly egy singleton, ezert letrehozzunk az egyetlen peldany, amivel dolgozni fogunk
	public static Game getInstance() {
		steppable = new ArrayList<Steppable>();
		settlers = new ArrayList<Settler>();
		bill=  new BillOfMaterials();

		if(INSTANCE == null) {
			INSTANCE = new Game(); 
		}

		return INSTANCE;
	}
	
	//ellenorzi, hogy a jatekban vannak-e meg eletben telepesek, ha nem, meghivja a jatekot befejezo EndGame() fuggvenyt
	public void CheckSettlers() {
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("CheckSettlers()");
		EndGame(true);
		cnt.SetTab(-1);
	}
	
	//megviszgalja, hogy megvannak-e az urbazis felepitesehez szukseges nyersanyagok, ha igen meghivja a jatekot befejezo EndGame() fuggvenyt
	public void CheckBase(Asteroid a) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("CheckBase(Asteroid a)");
        
		ArrayList<Creature> creatures = new ArrayList<>();
		creatures = a.GetCreatures();
		ArrayList<Material> materials = new ArrayList<>();
		for (Creature c : creatures) { 
			materials = c.GetMaterials(); 
			bill.SetUpBase();
			boolean result = bill.CheckMaterials(materials); 
			if (result)
				EndGame(true);
		}
		
		cnt.SetTab(-1);

	}
	
	//a jatek befejezesekor meghivodo fuggveny
	public void EndGame(boolean result) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("EndGame(booelan result)");
        cnt.SetTab(-1);
			
	}

	//a jatek kezdetekor meghivodo fuggveny	
	public void StartGame() {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("StartGame()");
        cnt.SetTab(-1);

	}
	
	//egy kor a jatekban: eloszor a telepesek cselekvesei vegzodnek el, majd a Steppeble objektumok lepesei
	public void Round() {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("Round()");
        
		while (onGame) {
			for (Settler s : settlers) {
				Action(s);
			}
			for (Steppable st : steppable) {
				st.Step();
			}
		}
		
		cnt.SetTab(-1);
	}

	//egy telepes altal vegrehajthato muveletek 
	public void Action(Settler s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("Action(Settler s)");
        cnt.SetTab(-1);

	}

	//egy telepes halalakor kiveszi a settlers listabol	
	public void RemoveSettler(Settler s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("RemoveSettler(Settler s)");
        settlers.remove(s);
        cnt.SetTab(-1);
	}
	
	//egy telepest hozzaad a settlers listahoz
	public void AddSettler(Settler s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddSettler(Settler s)");
		settlers.add(s);
		cnt.SetTab(-1);
	}
	
	//kiveszi a steppable listahoz a parameterkent megkapott Steppable peldanyt
	public void RemoveSteppable(Steppable s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc(" RemoveSteppable(Steppable s)");
        steppable.remove(s);
		cnt.SetTab(-1);
	}
	
	//hozzáadja a steppable listahoz a parameterkent megkapott Steppable peldanyt
	public void AddSteppable(Steppable s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddSteppable(Steppable s)");
        steppable.add(s);
		cnt.SetTab(-1);		
	}
	
	//addig hivogatja meg a Round() fuggvenyt amig az onGame attributum igaz
	public void OnGame() {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("OnGame()");
        Round();
		cnt.SetTab(-1);		
	}
}
