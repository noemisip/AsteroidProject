import java.util.ArrayList;

public class Game {

	private boolean onGame = true; //azt mutatja, hogy tart-e még a játék, alapból true értéket kap
	private Space space; //a játékszítere, a világűr
	private static ArrayList<Steppable> steppable; //a játékban résztvevő léptethető dolgok listája
	private static ArrayList<Settler> settlers; //a játékban résztvevő telepesek listája
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
			INSTANCE = new Game(); //ide nem StartGame kene?
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
		cnt.PrintFunc("CheckBase(Asteroid a)");

		ArrayList<Creature> creatures = a.GetCreatures();
		ArrayList<Material> materials ;
		for (Creature c : creatures) { 
			materials= c.GetMaterials();
			bill.SetUpBase();
			boolean result = bill.CheckMaterials(materials); 
			if (result)
				EndGame(true);
		}

	}
	
	//a játék befejésekor meghívódó függvény
	public void EndGame(boolean result) {
		Controller cnt = new Controller();
		cnt.PrintFunc("EndGame(booelan result)");
			
	}

	//a játék kezdetekor meghívódó függvény	
	public void StartGame() {
		Controller cnt = new Controller();
		cnt.PrintFunc("StartGame()");

	}
	
	//egy kör a játékban: először a telepesek cselekvései vegződnek el, majd a Steppeble objektumok lépései
	public void Round() {
		Controller c = new Controller();
		c.PrintFunc("Round()");
		while (onGame) {
			for (Settler s : settlers) {
				Action(s);
			}
			for (Steppable st : steppable) {
				st.Step();
			}
		}
	}

	//egy telepes által végrehajtható műveletek (utazás, fúrás, bányászás, visszahelyezés, robot vagy kapu építés)	
	public void Action(Settler s) {
		Controller c = new Controller();
		c.PrintFunc("Action(Settler s)");

	}

	//egy telepes halálakor kiveszi a settlers listából	
	public void RemoveSettler(Settler s) {
		Controller c = new Controller();
		c.PrintFunc("RemoveSettler(Settler s)");
		settlers.remove(s);
	}
	
	//egy telepest hozzáad a settlers listához
	public void AddSettler(Settler s) {
		Controller c = new Controller();
		c.PrintFunc("AddSettler(Settler s)");
		settlers.add(s);
	}
	
	//kiveszi a paraméterként kapott Steppable példányt a steppable listából
	public void RemoveSteppable(Steppable s) {
		Controller c = new Controller();
		c.PrintFunc("RemoveSteppable(Steppable s)");
		steppable.remove(s);
	}
	
	//hozzáadja a steppable listához a paraméterként megkapott Steppable példányt
	public void AddSteppable(Steppable s) {
		Controller c = new Controller();
		c.PrintFunc("AddSteppable(Steppable s)");
		steppable.add(s);
	}
	
	//addig hívogatja meg a Round() függvényt amíg az onGame attribútum igaz
	public void OnGame() {
		Controller c = new Controller();
		c.PrintFunc("OnGame()");
		Round();
	}
}
