import java.util.ArrayList;

public class Game {

	private boolean onGame = true; //azt mutatja, hogy tart-e még a játék, alapból true értket kap
	private Space space; //a játékszítere, a világűr
	private static ArrayList<Steppable> steppable; //a játékban résztvevő léptethető dolgok listája
	private static ArrayList<Settler> settlers; //a játékban résztvevő léptethető telepesek listája
	private static BillOfMaterials bill; //a bázis megépítéséhez szükséges nyersanyagok meglétét ellenőrzi


	private static Game INSTANCE;

	private Game() {
	}

	//mivel a Game osztály egy singleton, ezért létrehozzunk az egyetlen példány, amivel dolgozni fogunk
	public static Game getInstance() {
		steppable = new ArrayList<Steppable>();
		settlers = new ArrayList<Settler>();
		bill=  new BillOfMaterials();

		if(INSTANCE == null) {
			INSTANCE = new Game(); 
		}

		return INSTANCE;
	}
	
	//ellenőrzi, hogy a játékban vannak-e még életben telepesek, ha nem, meghívja a játékot befejező EndGame() függvényt
	public void CheckSettlers() {
		Controller cnt = new Controller();
		cnt.PrintFunc("CheckSettlers()");

		EndGame(true);
	}
	
	//megviszgálja, hogy megvannak-e az űrbázis felépítéséhez szükséges nyersanyagok, ha igen meghívja a játékot befejező EndGame() függvényt
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
	
	//a játék befejezésekor meghívodó függvény
	public void EndGame(boolean result) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("EndGame(booelan result)");
        cnt.SetTab(-1);
			
	}

	//a játék kezdetekor meghívodó függvény	
	public void StartGame() {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("StartGame()");
        cnt.SetTab(-1);

	}
	
	//egy kör a játékban: először a telepesek cselekvései vegződnek el, majd a Steppeble objektumok lépései
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

	//egy telepes által végrehajtható műveletek 
	public void Action(Settler s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("Action(Settler s)");
        cnt.SetTab(-1);

	}

	//egy telepes halÃ¡lakor kiveszi a settlers listÃ¡bÃ³l	
	public void RemoveSettler(Settler s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("RemoveSettler(Settler s)");
        settlers.remove(s);
        cnt.SetTab(-1);
	}
	
	//egy telepest hozzáad a settlers listához
	public void AddSettler(Settler s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddSettler(Settler s)");
		settlers.add(s);
		cnt.SetTab(-1);
	}
	
	//kiveszi a steppable listához a paraméterként megkapott Steppable példányt
	public void RemoveSteppable(Steppable s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc(" RemoveSteppable(Steppable s)");
        steppable.remove(s);
		cnt.SetTab(-1);
	}
	
	//hozzáadja a steppable listához a paraméterként megkapott Steppable példányt
	public void AddSteppable(Steppable s) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddSteppable(Steppable s)");
        steppable.add(s);
		cnt.SetTab(-1);		
	}
	
	//addig hívogatja meg a Round() függvényt amíg az onGame attribútum igaz
	public void OnGame() {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("OnGame()");
        Round();
		cnt.SetTab(-1);		
	}
}
