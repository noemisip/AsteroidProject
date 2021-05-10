package Modell;

import java.util.ArrayList;
import java.util.Random;

public class Asteroid implements Steppable, Transport {
	private int layer;                      		//Az aszteroida aktualis retegeinek szama.
	private boolean closeToSun=false;             		//Igaz, ha az aszteroida eppen napkozelben van.
	private Space space;                    		//Ebben talalhato meg az aszteroida.
	private ArrayList<Creature> creatures;            	//Az aszteroidan talalhato Modell.Creature osztalybol szarmazo objektumok listaja.
	private Material material;              		//Az aszteroidaban talalhato nyersanyag. Ha ureges, ennek az erteke null.
	private ArrayList<Transport> neighbours;           	//Azok az aszteroidak, amelyekre a telepes eljuthat az adott aszteroidarol

	public Asteroid(Space sp){ //egy parameteres konstruktor
		creatures = new ArrayList<Creature>();		//letrehozza a creaturek listajat
		neighbours= new ArrayList<Transport>();		//letrehozza a szomszedok listajat
		space=sp;					//a prameterkent kapott Spacet beíllitja a space attributumnak
	}
	public Asteroid(){
		creatures = new ArrayList<Creature>();		//letrehozza a creaturek listajat
		neighbours= new ArrayList<Transport>();		//letrehozza a szomszedok listajat
		Random rnd = new Random();
		switch (rnd.nextInt(5)){
			case 1: material = new Ice();
			break;
			case 2: material = new Iron();
				break;
			case 3: material = new Carbon();
				break;
			case 4: material = new Uranium();
				break;
			case 0: material = null;
				break;
			default: material = null;
			break;
		}
		layer= rnd.nextInt(5);
	}
	public void AddCreature(Creature c) {   //Hozzaad egy Modell.Creature objektumot a creatures listahoz.
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
		if (neighbours != null) {
			for(Transport t: neighbours){
				t.RemoveNeighbour(this);
			}
				neighbours=null;
		}
		Game.getInstance().RemoveSteppable(this);
	}

	public void SetCloseToSun(boolean b) {    //Beallitja az aszteroida napkozelseget.
		closeToSun=b;
		if(b && layer==0 && material !=null){		//ha az aszteroida napkozelben van, 0 kerge es nem ureges,
			material.ReactToSunclose(this);		//akkor a benne levo nyersanyag reagal a napkozelsegre
		}

	}

	public void SetMaterial(Material m) {      //Beallitja az aszteroidaban talalhato nyersanyagot.
		material=m;
	}

	public void Step() {
		Random rand = new Random();
		int randsunclose=rand.nextInt(2);
		if(randsunclose==1) {
			SetCloseToSun(true);          // beallitja az aszteroida napkozelseget, veletlenszeruen
		}
		else SetCloseToSun(false);
	}

	public void RemoveNeighbour(Transport t) { //Kivesz egy aszteroidat a neighbours listabol.
		neighbours.remove(t);
	}

	@Override
	public Transport GetPair() {
		return this;
	}

	public void RemoveCreature(Creature c) {  //Kivesz egy Modell.Creature objektumot a creatures listabol.
		creatures.remove(c);
	}

	public void Transport(Creature c) {      //Utaztatja a lenyeket, a leny beallitja magat az uj aszteroidara
		AddCreature(c);
		c.SetAsteroid(this);
	}

	public void TransportGate(Gate g) {      //Utaztatja a kapukat, a kapu beallitja magat az uj aszteroidara
		g.GetAsteroid().RemoveNeighbour(g.GetPair());
		AddNeighbour(g.GetPair());
		g.SetAsteroid(this);
	}

	public void SolarStorm(int count) {              //Az aszteroida napviharba kerul.
		if(layer != 0 || material != null){ 		//ha az aszteroidan nem tudnak elbujni (nem 0 a kereg es nem ureges), akkor a creturek meghalnak

			while(creatures.size()!=0) creatures.get(0).Die();
		}
		if(count>0)
		for(int i=0;i<neighbours.size();i++) neighbours.get(0).GetAsteroid().SolarStorm(count-1); //a kapuk megkergulnek napvihar hatasara

	}

	public void SetLayer(int l){ //beallitja a layer erteket
		layer=l;
	}

	public void SetSpace(Space s){space = s;} //a space attributumot beallito fuggveny

}

