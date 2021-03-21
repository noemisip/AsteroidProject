import java.util.ArrayList;

public class Settler extends Creature {
	private ArrayList<Material> materials; //a telepesnél lévő nyersanyagok listája
	private ArrayList<Gate> gates; //a telepesnél lévő kapuk
	private BillOfMaterials bill; //a robot és kapu építéséhez szükséseg nyersanyagok megépítését segítő objektum

	public Settler(){
		materials= new ArrayList<Material>();
		gates = new ArrayList<Gate>();
		bill = new BillOfMaterials();
	}

	public void RestoreMaterial(Material m) { //a telepes visszahelyez egy nyersanyagot az aszteroidára, amin épp áll
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("RestoreMaterial(m)");

		if(asteroid.GetMaterial()==null && asteroid.GetLayer()==0){ //a telepes csak abban az esetben helyezi el a nyersanyagot, ha
			RemoveMaterial(m);										//az aszteroida üreges és nincs rajta  kéreg
			asteroid.SetMaterial(m);
		}
		c.SetTab(-1);
	}

	
	public void Mine() { //e telepes bányászik az aszteroidán
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Mine()");

		Material m = asteroid.GetMaterial();
		if(asteroid.GetLayer()==0 && materials.size()<10 && m!=null) { //ellenőrzi, hogy tud-e bányászni
			m.ReactToMine(asteroid, this);
			Game.getInstance().CheckBase(asteroid); //a játék ellenőrzi, hogy fel tudják-e építeni a telpesek a bázist az aszteroidán
		}
		c.SetTab(-1);
	}

	
	public void CreateRobot() { //a telepes létrehoz egy robotot
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("CreateRobot()");

		bill.SetUpRobot(); //bill attributum material listáját beállítja a robothoz szükségesekre
		if(bill.CheckMaterials(materials)){ //elenőrzi, hogy megvan-e a szükséges mennyiségű nyersanyag
			bill.SetUpRobot();  //bill attributum material listáját beállítja a robothoz szükségesekre
			bill.RemoveMaterials(this); //kitörli a telepestől a felhasznált nyersanyagokat, majd létrehozza a robotot és elhelyezi az aszteroidán
			Robot r = new Robot();
			asteroid.AddCreature(r);
			asteroid.GetSpace().AddCreature(r);
			Game.getInstance().AddSteppable(r);
		}
		c.SetTab(-1);
	}
	
	public void CreateGate() { //a telepes létrehoz egy teleportkapu-párt
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("CreateGate()");

		bill.SetUpGate(); //bill attributum material listáját beállítja a kapuhoz szükségesekre
		if(bill.CheckMaterials(materials)){ //elenőrzi, hogy megvan-e a szükséges mennyiségű nyersanyag
			bill.SetUpGate(); //bill attributum material listáját beállítja a kapuhoz szükségesekre
			bill.RemoveMaterials(this); //kitörli a telepestőla  felhasznált nyersanyagokat, majd létrehozza a két kaput és hozzáadja a listájához
			Gate g1 = new Gate();
			Gate g2 = new Gate();
			g1.SetPair(g2);
			g2.SetPair(g1);
			AddGate(g1);
			AddGate(g2);
		}
		c.SetTab(-1);
	}
	
	public void PlaceGate() { //a telepes lehelyez egy kaput az aszteroidára
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("PlaceGate(a)");
		Gate g2=gates.get(0).GetPair();
		asteroid.AddNeighbour(g2); //az aszteroida új szomszédot kap a kapun keresztül
		gates.get(0).SetAsteroid(asteroid); //beállítja, hogy a kapu melyik aszteroidán helyezkedik el
		RemoveGate(gates.get(0)); //a telepes kitölri a kaput a listjából.
		c.SetTab(-1);
	}

	
	public void AddMaterial(Material m) { //a telepes felvesz egy nyerssanyagot a listájába
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AddMaterial(m)");

		materials.add(m);
		c.SetTab(-1);
	}
	
	public void RemoveMaterial(Material m) { //a telepes kitöröl egy nyerssanyagot a listájából
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("RemoveMaterial(m)");
		c.SetTab(-1);
	}
	
	public void AddGate(Gate g) { //a telepes egy kaput készített és felveszi a listájába
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AddGate(g)");

		gates.add(g);
		c.SetTab(-1);
	}
	
	public ArrayList<Material> GetMaterials() { //a telepes visszaadja a néla lévő nyersanyagok listáját
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Getmaterials() : materials");
		c.SetTab(-1);
		return materials;
	}
	
	public void Die() { //a telepes meghal
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Die()");

		asteroid.RemoveCreature(this);//a telpes kitorlodik az aszterodia creture listajabol
		asteroid.GetSpace().RemoveCreature(this); //a telpes megkerdezi az aszteroidat, hogy melyik spaceben van és kitorlodik az space creture listajabol
		Game.getInstance().RemoveSettler(this); //a telpes kitorlodik az game settelr listajabol
		Game.getInstance().CheckSettlers(); //a játék ellenőrzi, hogy ven-e még életben telepes

		c.SetTab(-1);
	}
	
	public void Move(Asteroid a) { //a telepes a kivalasztott aszteroidara mozog
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Move(a)");
		Asteroid al = asteroid;

		asteroid.GetNeighbours(); //a telepes megkapja, hogy melyik transport objektum fogja átvinni a másik aszteroidára
		a.Transport(this); //meghívja az objektum transport függvényét
		al.RemoveCreature(this); //a telepes kitorlodik az aszterodia creture listajabol
		Game.getInstance().CheckBase(asteroid); //a játék ellenőrzi, hogy fel tudják-e építeni a telpesek a bázist az aszteroidán
		c.SetTab(-1);
	}
	
	public void AsteroidExplosion() { //a telepes reagál az aszteroida felrobbanására
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AsteroidExplosion()");

		Die(); //a telpes meghal a robbanás következtében
		c.SetTab(-1);
	}

	public void RemoveGate(Gate g){
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("RemoveGate(Gate g)");
		gates.remove(g);
		c.SetTab(-1);
	}
}
