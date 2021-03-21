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
		creatures = new ArrayList<Creature>();
		neighbours= new ArrayList<Transport>();
	}
	public void AddCreature(Creature c) {   //Hozz�ad egy Creature objektumot a creatures list�hoz.
	    System.out.println("AddCreature(Creature c)");
		creatures.add(c);
	}
	
	public void AddNeighbour(Transport t) { //Hozz�ad egy aszteroid�t a neighbours list�hoz.
	    System.out.println("AddNeighbour(Transport t)");
	    neighbours.add(t);
	}
	
	public Asteroid GetAsteroid() {          //Az aszteroida objektum
	    System.out.println("GetAsteroid() : Asteroid");
	    return this;
	}
	
	public boolean GetCloseToSun() {           //Igazzal t�r vissza, ha az aszteroida napk�zelben van.
	    System.out.println("GetCloseToSun() : boolean");
	    return closeToSun;
	}
	
	public ArrayList<Creature> GetCreatures() {     //Visszaadja az aszteroid�n l�v� l�nyeket.
	    System.out.println("GetCreatures() : ArrayList<Creature>");
	    return creatures;
	}
	
	public int GetLayer() {                   //Visszaadja az aszteroid�n l�v� aktu�lis r�tegek sz�m�t.
	    System.out.println("GetLayer() : int");
	    return layer;
	}
	
	public Material GetMaterial() {          //Visszaadja az aszteroid�ban tal�lhat� nyersanyagot.
	    System.out.println("GetMaterial() : Material");
	    return material;
	}
	
	public ArrayList<Transport> GetNeighbours() {  //Visszaadja azon aszteroid�k t�mbj�t, ahova mehet a l�ny
	    System.out.println("GetNeighbours() : ArrayList<Transport>");
	    return neighbours;
	}
	
	public Space GetSpace() {                //Visszaadja az �rt.
	    System.out.println("GetSpace() : Space");
	    return space;
	}
	
	public void DecraseLayer() {             //Cs�kkenti az aszteroida r�tegeinek a sz�m�t.
	    System.out.println("DecraseLayer()");
	    if(layer>0){
	        --layer;
			System.out.println("The layer is reduced!");
			}else {
			System.out.println("No more layer, Mine!");
			}
	}
	
	public void Explosion() {                //Felrobban az aszteroida. 
	    System.out.println("Explosion()");

	    for(int i=0; i<creatures.size(); i++){
			creatures.get(i).AsteroidExplosion();
	    }                
	}
	
	public void SetCloseToSun(boolean b) {    //Be�ll�tja az aszteroida napk�zels�g�t.
	    System.out.println("SetCloseToSun(boolen b)");
	    closeToSun=b;
	}
	
	public void SetMaterial(Material m) {      //Be�ll�tja az aszteroid�ban tal�lhat� nyersanyagot.
	    System.out.println("SetMaterial(Material m)");
		material=m;
	}
	
	public void Step() {
	    System.out.println("Step()");
		System.out.println("the asteroid is in sunclose?(0-No, 1-Yes):");
		int  a = new Scanner(System.in).nextInt();
		if(a==0){
		SetCloseToSun(false);              // beállítja az aszteroida napk�zels�g�t
		}else {SetCloseToSun(true);}
		System.out.println("How many layers does the Asteroid have?:");
		int  b = new Scanner(System.in).nextInt();
		if(closeToSun && b==0){
		     material.ReactToSunclose(this);
		}

	}
	
	public void RemoveNeighbour(Asteroid t) { //Kivesz egy aszteroid�t a neighbours list�b�l.
	    System.out.println("RemoveNeighbour(Asteroid t)");
	    neighbours.remove(t);
	}
	
	public void RemoveCreature(Creature c) {  //Kivesz egy Creature objektumot a creatures list�b�l.
	    System.out.println("RemoveCreature(Creature c)");
		creatures.remove(c);
	}
	
	public void Transport(Creature c) {      //Utaztatja a l�nyeket, a l�ny be�ll�tja mag�t az �j aszteroid�ra
	    System.out.println("Transport(Creature c)");
		AddCreature(c);
		c.SetAsteroid(this);
	}
	
	public void SolarStorm() {              //Az aszteroida napviharba ker�l.
	    System.out.println("SolarStorm() ");
		if(layer != 0 || material != null){ //Ha a rajta tart�zkod� utaz�k nem tudnak elb�jni, akkor meghalnak.
			for(Creature creature : creatures){
			    creature.Die();
			}
		}
	}

	public void SetSpace(Space sp) {
		space=sp;
	}
}
