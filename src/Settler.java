import java.util.ArrayList;

public class Settler extends Creature {
	private ArrayList<Material> materials; //a telepesnel levo nyersanyagok listája
	private ArrayList<Gate> gates; //a telepesnel levo kapuk
	private BillOfMaterials bill; //a robot és kapu epítesehez szukseseg nyersanyagok megepiteset segito objektum

	public Settler(){
		materials= new ArrayList<Material>();
		gates = new ArrayList<Gate>();
		bill = new BillOfMaterials();
	}

	public void Drill(){

	}
	public void RestoreMaterial(Material m) { //a telepes visszahelyez egy nyersanyagot az aszteroidara, amin epp all
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("RestoreMaterial(m)");

		if(asteroid.GetMaterial()==null && asteroid.GetLayer()==0){ //a telepes csak abban az esetben helyezi el a nyersanyagot, ha
			RemoveMaterial(m);										//az aszteroida ureges és nincs rajta  kereg
			asteroid.SetMaterial(m);
		}
		c.SetTab(-1);
	}

	
	public void Mine() { //e telepes bányászik az aszteroidán
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Mine()");

		Material m = asteroid.GetMaterial();
		if(asteroid.GetLayer()==0 && materials.size()<10 && m!=null) { //ellenorzi, hogy tud-e banyaszni
			m.ReactToMine(asteroid, this);
			Game.getInstance().CheckBase(asteroid); //a jatek ellenorzi, hogy fel tudjak-e epíteni a telpesek a bazist az aszteroidan
		}
		c.SetTab(-1);
	}

	
	public void CreateRobot() { //a telepes letrehoz egy robotot
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("CreateRobot()");

		bill.SetUpRobot(); //bill attributum material listajat beallitja a robothoz szuksegesekre
		if(bill.CheckMaterials(materials)){ //elenorzi, hogy megvan-e a szukseges mennyisegu nyersanyag
			bill.SetUpRobot();  //bill attributum material listajat beallitja a robothoz szuksegesekre
			bill.RemoveMaterials(this); //kitorli a telepestol a felhasznalt nyersanyagokat, majd letrehozza a robotot és elhelyezi az aszteroidan
			Robot r = new Robot();
			asteroid.AddCreature(r);
			asteroid.GetSpace().AddCreature(r);
			Game.getInstance().AddSteppable(r);
		}
		c.SetTab(-1);
	}
	
	public void CreateGate() { //a telepes letrehoz egy teleportkapu-part
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("CreateGate()");

		bill.SetUpGate(); //bill attributum material listajat beallítja a kapuhoz szuksegesekre
		if(bill.CheckMaterials(materials)){ //elenorzi, hogy megvan-e a szukséges mennyisegu nyersanyag
			bill.SetUpGate(); //bill attributum material listajat beallitja a kapuhoz szuksegesekre
			bill.RemoveMaterials(this); //kitorli a telepestol a felhasznalt nyersanyagokat, majd letrehozza a ket kaput és hozzaadja a listajahoz
			Gate g1 = new Gate();
			Gate g2 = new Gate();
			g1.SetPair(g2);
			g2.SetPair(g1);
			AddGate(g1);
			AddGate(g2);
		}
		c.SetTab(-1);
	}
	
	public void PlaceGate() { //a telepes lehelyez egy kaput az aszteroidara
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("PlaceGate(a)");
		Gate g2=gates.get(0).GetPair();
		asteroid.AddNeighbour(g2); //az aszteroida új szomszedot kap a kapun keresztul
		gates.get(0).SetAsteroid(asteroid); //beallitja, hogy a kapu melyik aszteroidan helyezkedik el
		RemoveGate(gates.get(0)); //a telepes kitolri a kaput a listjabol
		c.SetTab(-1);
	}

	
	public void AddMaterial(Material m) { //a telepes felvesz egy nyerssanyagot a listájába
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AddMaterial(m)");

		materials.add(m);
		c.SetTab(-1);
	}
	
	public void RemoveMaterial(Material m) { //a telepes kitorol egy nyerssanyagot a listajabol
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("RemoveMaterial(m)");
		c.SetTab(-1);
	}
	
	public void AddGate(Gate g) { //a telepes egy kaput keszitett es felveszi a listajaba
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AddGate(g)");
		// listája elejéra rakja a gate-t
		gates.add(g);
		c.SetTab(-1);
	}
	
	public ArrayList<Material> GetMaterials() { //a telepes visszaadja a nala levo nyersanyagok listajat
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
		Game.getInstance().RemoveSettler(this); //a telpes kitorlodik az game settler listajabol
		Game.getInstance().CheckSettlers(); //a jatek ellenorzi, hogy ven-e meg eletben telepes

		c.SetTab(-1);
	}
	
	public void Move(Asteroid a) { //a telepes a kivalasztott aszteroidara mozog
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Move(a)");
		Asteroid al = asteroid;

		asteroid.GetNeighbours(); //a telepes megkapja, hogy melyik transport objektum fogja atvinni a masik aszteroidara
		a.Transport(this); //meghivja az objektum transport fuggvenyet
		al.RemoveCreature(this); //a telepes kitorlodik az aszterodia creture listajabol
		Game.getInstance().CheckBase(asteroid); //a jatek ellenorzi, hogy fel tudják-e epiteni a telpesek a bazist az aszteroidan
		c.SetTab(-1);
	}
	
	public void AsteroidExplosion() { //a telepes reagal az aszteroida felrobbanasara
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AsteroidExplosion()");

		Die(); //a telpes meghal a robbanas kovetkezteben
		c.SetTab(-1);
	}

	public void RemoveGate(Gate g){ //a telpes kitorol egy kaput a lsitajabol
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("RemoveGate(Gate g)");
		gates.remove(g);
		c.SetTab(-1);
	}
}
