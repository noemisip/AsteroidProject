import java.util.ArrayList;
import java.util.Random;

public class Gate implements Transport,Steppable,AI {    // A teleportkaput reprezentalo osztaly.
	private Asteroid asteroid;              //Az az aszteroida, amire a Gate peldanyt elhelyeztek.
	private Gate pair;                      //Az adott kapu parja.
	private boolean kerge;                   //kapu kergesége

	public void SetPair(Gate g) {           //Beallitja, hogy melyik kapupar a parja.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetPair()");
		pair = g;
		cnt.SetTab(-1);
	}

	public Gate GetPair() {                 //Visszaadja az adott kapu parjat
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetPair() : Gate");
		cnt.SetTab(-1);
		return pair;
	}

	public void SetAsteroid(Asteroid a) {   //Beallitja, hogy melyik aszteroidan all a kapu.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetAsteroid(Asteroid a)");
		asteroid = a;
		cnt.SetTab(-1);
	}

	public Asteroid GetAsteroid() {        //Az asteroid parameter erteket adja vissza.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetAsteroid(): Asteroid");
		cnt.SetTab(-1);
		return asteroid;
	}

	public void Transport(Creature c) {    //ket aszteroida kozotti mozgas teleportkapu altal
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Transport(Creature c)");
		asteroid.AddCreature(c);
		c.SetAsteroid(asteroid);
		cnt.SetTab(-1);

	}
	public void SetKerge(boolean b) {
		kerge=b;
	}
	public void WhereToMove() {
		ArrayList<Transport> asteroids=this.asteroid.GetNeighbours();
		Random rand = new Random();
		int rand_int = rand.nextInt(asteroids.size());
		Move(asteroids.get(rand_int));

	}
	public int NextStep() {
		Random rand = new Random();
		int rand_int = rand.nextInt(2);
		return rand_int;
	}
	public void Move(Transport t) {  //t amire utazik
		Gate g = null;
		Asteroid a = null;
		for(int i=0;i<t.GetAsteroid().GetNeighbours().size();i++) {
			if (t.GetAsteroid().GetNeighbours().get(i) == pair.GetPair()) {  //ha benne van a szomszédjai listájában, tehát kapun keresztül utazik meg a getpair kaput ad vissza
				TransportGate(pair.GetPair());
			}
		}


	}
	public void Step() {
		int next = NextStep();
		if(kerge==true && next==1){
			WhereToMove();
		}
	}
	public void SolarStorm(int a){
		SetKerge(true);
	}
	public void RemoveNeighbour(Asteroid a){
		asteroid=null;
		pair.SetPair(null);
	}

	public void TransportGate(Gate g){
		if(pair==g){
			return;
		}
		asteroid.RemoveNeighbour(g.GetPair().GetAsteroid());
		pair.GetPair().GetAsteroid().AddNeighbour(g.GetPair().GetAsteroid());
		g.SetAsteroid(g.GetPair().GetAsteroid());

	}

}

