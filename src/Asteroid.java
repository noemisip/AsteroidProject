import java.util.ArrayList;
import java.util.Scanner;

public class Asteroid implements Steppable, Transport {
	private int layer;                      //Az aszteroida aktu�lis r�tegeinek sz�ma.
	private boolean closeToSun;             //Igaz, ha az aszteroida �ppen napk�zelben van.
	private Space space;                    //Ebben tal�lhat� meg az aszteroida.    
	private ArrayList<Creature> creatures;             //Az aszteroid�n tal�lhat� Creature oszt�lyb�l sz�rmaz� objektumok list�ja.
	private Material material;              //Az aszteroid�ban tal�lhat� nyersanyag. Ha �reges, ennek az �rt�ke null.
	private ArrayList<Transport> neighbours;           //Azok az aszteroid�k, amelyekre a telepes eljuthat az adott aszteroid�r�l

	public Asteroid(){
		Controller cnt = new Controller();
		cnt.SetTab(1);
		creatures = new ArrayList<Creature>();
		neighbours= new ArrayList<Transport>();
		cnt.SetTab(-1);
	}
	public void AddCreature(Creature c) {   //Hozz�ad egy Creature objektumot a creatures list�hoz.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("AddCreature(Creature c)");
		creatures.add(c);
		cnt.SetTab(-1);
	}

	public void AddNeighbour(Transport t) { //Hozz�ad egy aszteroid�t a neighbours list�hoz.
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

	public boolean GetCloseToSun() {           //Igazzal t�r vissza, ha az aszteroida napk�zelben van.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetCloseToSun()");
		cnt.SetTab(-1);
		return closeToSun;
	}

	public ArrayList<Creature> GetCreatures() {     //Visszaadja az aszteroid�n l�v� l�nyeket.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetCreatures() : ArrayList<Creature>");
		cnt.SetTab(-1);
		return creatures;
	}

	public int GetLayer() {                   //Visszaadja az aszteroid�n l�v� aktu�lis r�tegek sz�m�t.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetLayer() : int");
		cnt.PrintFunc("How many layers does the asteroid have?");
		int  b = new Scanner(System.in).nextInt();
		cnt.SetTab(-1);
		return b;
	}

	public Material GetMaterial() {          //Visszaadja az aszteroid�ban tal�lhat� nyersanyagot.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetMaterial() : material");
		cnt.SetTab(-1);
		return material;
	}

	public ArrayList<Transport> GetNeighbours() {  //Visszaadja azon aszteroid�k t�mbj�t, ahova mehet a l�ny
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetNeighbours() : ArrayList<Transport>");
		cnt.SetTab(-1);
		return neighbours;
	}

	public Space GetSpace() {                //Visszaadja az �rt.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetSpace() : Space");
		cnt.SetTab(-1);
		return space;
	}

	public void DecreaseLayer() {             //Cs�kkenti az aszteroida r�tegeinek a sz�m�t.
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

	public void SetCloseToSun(boolean b) {    //Be�ll�tja az aszteroida napk�zels�g�t.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetCloseToSun(boolen b)");

		closeToSun=b;
		cnt.SetTab(-1);
	}

	public void SetMaterial(Material m) {      //Be�ll�tja az aszteroid�ban tal�lhat� nyersanyagot.
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
			SetCloseToSun(false);              // beállítja az aszteroida napk�zels�g�t
		}else {SetCloseToSun(true);}
		cnt.PrintFunc("How many layers does the Asteroid have?:");
		int  b = new Scanner(System.in).nextInt();
		if(closeToSun && b==0){
			material.ReactToSunclose(this);
		}
		cnt.SetTab(-1);

	}

	public void RemoveNeighbour(Asteroid t) { //Kivesz egy aszteroid�t a neighbours list�b�l.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveNeighbour(Asteroid t)");
		neighbours.remove(t);
		cnt.SetTab(-1);
	}

	public void RemoveCreature(Creature c) {  //Kivesz egy Creature objektumot a creatures list�b�l.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("RemoveCreature(Creature c)");

		creatures.remove(c);
		cnt.SetTab(-1);
	}

	public void Transport(Creature c) {      //Utaztatja a l�nyeket, a l�ny be�ll�tja mag�t az �j aszteroid�ra
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Transport(Creature c)");

		AddCreature(c);
		c.SetAsteroid(this);
		cnt.SetTab(-1);
	}

	public void SolarStorm() {              //Az aszteroida napviharba ker�l.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SolarStorm()");

		if(layer != 0 || material != null){ //Ha a rajta tart�zkod� utaz�k nem tudnak elb�jni, akkor meghalnak.

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

