import java.util.ArrayList;
import java.util.Random;

public class Asteroid implements Steppable, Transport {
	private int layer;                      //Az aszteroida aktualis retegeinek szama.
	private boolean closeToSun;             //Igaz, ha az aszteroida eppen napkozelben van.
	private Space space;                    //Ebben talalhato meg az aszteroida.
	private ArrayList<Creature> creatures;             //Az aszteroidan talalhato Creature osztalybol szarmazo objektumok listaja.
	private Material material;              //Az aszteroidaban talalhato nyersanyag. Ha ureges, ennek az erteke null.
	private ArrayList<Transport> neighbours;           //Azok az aszteroidak, amelyekre a telepes eljuthat az adott aszteroidarol

	public Asteroid(Space sp){
		creatures = new ArrayList<Creature>();
		neighbours= new ArrayList<Transport>();
		space=sp;
	}
	public Asteroid(){
		creatures = new ArrayList<Creature>();
		neighbours= new ArrayList<Transport>();
	}
	public void AddCreature(Creature c) {   //Hozzaad egy Creature objektumot a creatures listahoz.
		creatures.add(c);
	}

	public void AddNeighbour(Transport t) { //Hozzaad egy aszteroidat a neighbours listahoz
		neighbours.add(t);
	}

	public Asteroid GetAsteroid() {          //Az aszteroida objektum
		return this;
	}

	public boolean GetCloseToSun() {           //Igazzal ter vissza, ha az aszteroida napkozelben van.
		return closeToSun;
	}

	public ArrayList<Creature> GetCreatures() {     //Visszaadja az aszteroidán levo lenyeket.
		return creatures;
	}

	public int GetLayer() {                   //Visszaadja az aszteroidan levo aktualis retegek szamat.
		return layer;
	}

	public Material GetMaterial() {          //Visszaadja az aszteroidaban talalhato nyersanyagot.
		return material;
	}

	public ArrayList<Transport> GetNeighbours() {  //Visszaadja azon aszteroidak tombjet, ahova mehet a leny
		return neighbours;
	}

	public Space GetSpace() {                //Visszaadja az urt.
		return space;
	}

	public void DecreaseLayer() {             //Csokkenti az aszteroida retegeinek a szamat.
		if(layer>0) SetLayer(layer-1);
	}

	public void Explosion() {                //Felrobban az aszteroida.
		while(creatures.size()!=0) creatures.get(0).AsteroidExplosion();
		neighbours=null;
	}

	public void SetCloseToSun(boolean b) {    //Beallitja az aszteroida napkozelseget.
		closeToSun=b;
	}

	public void SetMaterial(Material m) {      //Beallitja az aszteroidaban talalhato nyersanyagot.
		material=m;
	}

	public void Step() {
		Random rand = new Random();
		int randsunclose=rand.nextInt(1);
		if(randsunclose==1) {
			if (closeToSun == true) closeToSun = false;
			else closeToSun = true;           // beallitja az aszteroida napkozelseget
		}
		if(layer==0 && closeToSun==true) material.ReactToSunclose(this);
	}

	public void RemoveNeighbour(Transport t) { //Kivesz egy aszteroidat a neighbours listabol.
		neighbours.remove(t);
	}

	public void RemoveCreature(Creature c) {  //Kivesz egy Creature objektumot a creatures listabol.
		creatures.remove(c);
	}

	public void Transport(Creature c) {      //Utaztatja a lenyeket, a leny beallitja magat az uj aszteroidara
		AddCreature(c);
		c.SetAsteroid(this);
	}

	public void TransportGate(Gate g) {      //Utaztatja a kapukat, a kapu beallitja magat az uj aszteroidara
		AddNeighbour(g.GetPair());
		g.SetAsteroid(this);
	}

	public void SolarStorm(int count) {              //Az aszteroida napviharba kerul.
		if(layer != 0 || material != null){ //Ha a rajta tartozkodo utazok nem tudnak elbujni, akkor meghalnak.

			while(creatures.size()!=0) creatures.get(0).Die();
		}
		if(count>0)
		for(int i=0;i<neighbours.size();i++) neighbours.get(0).GetAsteroid().SolarStorm(count-1);

	}

	public void SetLayer(int l){
		layer=l;
	}

}

