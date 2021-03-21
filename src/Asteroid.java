import java.util.ArrayList;
import java.util.Scanner;

public class Asteroid implements Steppable, Transport {
	private int layer;                      //Az aszteroida aktualis retegeinek szama.
	private boolean closeToSun;             //Igaz, ha az aszteroida eppen napkozelben van.
	private Space space;                    //Ebben talalhato meg az aszteroida.
	private ArrayList<Creature> creatures;             //Az aszteroidan talalhato Creature osztalybol szarmazo objektumok listaja.
	private Material material;              //Az aszteroidaban talalhato nyersanyag. Ha ureges, ennek az erteke null.
	private ArrayList<Transport> neighbours;           //Azok az aszteroidak, amelyekre a telepes eljuthat az adott aszteroidarol

	public Asteroid(){
		Controller cnt = new Controller();
		cnt.SetTab(1);
		creatures = new ArrayList<Creature>();
		neighbours= new ArrayList<Transport>();
		cnt.SetTab(-1);
	}
	public void AddCreature(Creature c) {   //Hozzaad egy Creature objektumot a creatures listahoz.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("AddCreature(Creature c)");
		creatures.add(c);
		cnt.SetTab(-1);
	}

	public void AddNeighbour(Transport t) { //Hozzaad egy aszteroidat a neighbours listahoz.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("AddNeighbour(Transport t)");
		neighbours.add(t);
		cnt.SetTab(-1);
	}

	public Asteroid GetAsteroid() {          //Az aszteroida objektum
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetAsteroid() : Asteroid");
		cnt.SetTab(-1);
		return this;
	}

	public boolean GetCloseToSun() {           //Igazzal ter vissza, ha az aszteroida napkozelben van.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetCloseToSun()");
		cnt.SetTab(-1);
		return closeToSun;
	}

	public ArrayList<Creature> GetCreatures() {     //Visszaadja az aszteroidán levo lenyeket.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetCreatures() : ArrayList<Creature>");
		cnt.SetTab(-1);
		return creatures;
	}

	public int GetLayer() {                   //Visszaadja az aszteroidan levo aktualis retegek szamat.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetLayer() : int");
		cnt.PrintFunc("How many layers does the asteroid have?");
		int  b = new Scanner(System.in).nextInt();
		cnt.SetTab(-1);
		return b;
	}

	public Material GetMaterial() {          //Visszaadja az aszteroidaban talalhato nyersanyagot.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetMaterial() : material");
		cnt.SetTab(-1);
		return material;
	}

	public ArrayList<Transport> GetNeighbours() {  //Visszaadja azon aszteroidak tombjet, ahova mehet a leny
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetNeighbours() : ArrayList<Transport>");
		cnt.SetTab(-1);
		return neighbours;
	}

	public Space GetSpace() {                //Visszaadja az urt.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetSpace() : Space");
		cnt.SetTab(-1);
		return space;
	}

	public void DecreaseLayer() {             //Csokkenti az aszteroida retegeinek a szamat.
		Controller cnt = new Controller();
		cnt.SetTab(1);

		cnt.PrintFunc("DecreaseLayer()");
		cnt.PrintFunc("How many layers does the asteroid have?");
		int  b = new Scanner(System.in).nextInt();
		if(b>0) setLayer(b-1);


		cnt.SetTab(-1);
	}

	public void Explosion() {                //Felrobban az aszteroida.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Explosion()");

		while(creatures.size()!=0) creatures.get(0).AsteroidExplosion();
		cnt.SetTab(-1);
	}

	public void SetCloseToSun(boolean b) {    //Beallitja az aszteroida napkozelseget.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetCloseToSun(boolen b)");

		closeToSun=b;
		cnt.SetTab(-1);
	}

	public void SetMaterial(Material m) {      //Beallitja az aszteroidaban talalhato nyersanyagot.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetMaterial(Material m)");

		material=m;
		cnt.SetTab(-1);
	}

	public void Step() {
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Step()");

		cnt.PrintFunc("the asteroid is in sunclose?(0-No, 1-Yes):");
		int  a = new Scanner(System.in).nextInt();
		if(a==0){
			SetCloseToSun(false);              // beallitja az aszteroida napkozelseget
		}else {SetCloseToSun(true);}
		cnt.PrintFunc("How many layers does the Asteroid have?:");
		int  b = new Scanner(System.in).nextInt();
		if(closeToSun && b==0){
			material.ReactToSunclose(this);
		}
		cnt.SetTab(-1);

	}

	public void RemoveNeighbour(Asteroid t) { //Kivesz egy aszteroidat a neighbours listabol.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveNeighbour(Asteroid t)");
		neighbours.remove(t);
		cnt.SetTab(-1);
	}

	public void RemoveCreature(Creature c) {  //Kivesz egy Creature objektumot a creatures listabol.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveCreature(Creature c)");

		creatures.remove(c);
		cnt.SetTab(-1);
	}

	public void Transport(Creature c) {      //Utaztatja a lenyeket, a leny beallitja magat az uj aszteroidara
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Transport(Creature c)");

		AddCreature(c);
		c.SetAsteroid(this);
		cnt.SetTab(-1);
	}

	public void SolarStorm() {              //Az aszteroida napviharba kerul.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SolarStorm()");

		if(layer != 0 || material != null){ //Ha a rajta tartozkodo utazok nem tudnak elbujni, akkor meghalnak.

			while(creatures.size()!=0) creatures.get(0).Die();
		}
		cnt.SetTab(-1);
	}

	public void SetSpace(Space sp) {
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetSpace(Space sp)");
		space=sp;
		cnt.SetTab(-1);
	}
	public void setLayer(int l){
		layer=l;
	}
}

