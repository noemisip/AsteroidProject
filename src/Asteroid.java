import java.util.ArrayList;
import java.util.Scanner;

public class Asteroid implements Steppable, Transport {
	private int layer;                      //Az aszteroida aktuális rétegeinek száma.
	private boolean closeToSun;             //Igaz, ha az aszteroida éppen napközelben van.
	private Space space;                    //Ebben található meg az aszteroida.
	private ArrayList<Creature> creatures;             //Az aszteroidán található Creature osztályból származó objektumok listája.
	private Material material;              //Az aszteroidában található nyersanyag. Ha üreges, ennek az értéke null.
	private ArrayList<Transport> neighbours;           //Azok az aszteroidák, amelyekre a telepes eljuthat az adott aszteroidáról

	public Asteroid(){
		Controller cnt = new Controller();
		cnt.SetTab(1);
		creatures = new ArrayList<Creature>();
		neighbours= new ArrayList<Transport>();
		cnt.SetTab(-1);
	}
	public void AddCreature(Creature c) {   //Hozzáad egy Creature objektumot a creatures listához.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("AddCreature(Creature c)");
		creatures.add(c);
		cnt.SetTab(-1);
	}

	public void AddNeighbour(Transport t) { //Hozzáad egy aszteroidát a neighbours listához.
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

	public boolean GetCloseToSun() {           //Igazzal tér vissza, ha az aszteroida napközelben van.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetCloseToSun()");
		cnt.SetTab(-1);
		return closeToSun;
	}

	public ArrayList<Creature> GetCreatures() {     //Visszaadja az aszteroidán lévő lényeket.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetCreatures() : ArrayList<Creature>");
		cnt.SetTab(-1);
		return creatures;
	}

	public int GetLayer() {                   //Visszaadja az aszteroidán lévő aktuális rétegek számát.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetLayer() : int");
		cnt.PrintFunc("How many layers does the asteroid have?");
		int  b = new Scanner(System.in).nextInt();
		cnt.SetTab(-1);
		return b;
	}

	public Material GetMaterial() {          //Visszaadja az aszteroidában található nyersanyagot.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetMaterial() : material");
		cnt.SetTab(-1);
		return material;
	}

	public ArrayList<Transport> GetNeighbours() {  //Visszaadja azon aszteroidák tömbjét, ahova mehet a lény
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetNeighbours() : ArrayList<Transport>");
		cnt.SetTab(-1);
		return neighbours;
	}

	public Space GetSpace() {                //Visszaadja az űrt.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetSpace() : Space");
		cnt.SetTab(-1);
		return space;
	}

	public void DecreaseLayer() {             //Csökkenti az aszteroida rétegeinek a számát.
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

	public void SetCloseToSun(boolean b) {    //Beállítja az aszteroida napközelségét.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetCloseToSun(boolen b)");

		closeToSun=b;
		cnt.SetTab(-1);
	}

	public void SetMaterial(Material m) {      //Beállítja az aszteroidában található nyersanyagot.
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
			SetCloseToSun(false);              // beállítja az aszteroida napközelségét
		}else {SetCloseToSun(true);}
		cnt.PrintFunc("How many layers does the Asteroid have?:");
		int  b = new Scanner(System.in).nextInt();
		if(closeToSun && b==0){
			material.ReactToSunclose(this);
		}
		cnt.SetTab(-1);

	}

	public void RemoveNeighbour(Asteroid t) { //Kivesz egy aszteroidát a neighbours listából.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveNeighbour(Asteroid t)");
		neighbours.remove(t);
		cnt.SetTab(-1);
	}

	public void RemoveCreature(Creature c) {  //Kivesz egy Creature objektumot a creatures listából.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveCreature(Creature c)");

		creatures.remove(c);
		cnt.SetTab(-1);
	}

	public void Transport(Creature c) {      //Utaztatja a lényeket, a lény beállítja magát az új aszteroidára
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Transport(Creature c)");

		AddCreature(c);
		c.SetAsteroid(this);
		cnt.SetTab(-1);
	}

	public void SolarStorm() {              //Az aszteroida napviharba kerül.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SolarStorm()");

		if(layer != 0 || material != null){ //Ha a rajta tartózkodó utazók nem tudnak elbújni, akkor meghalnak.

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

