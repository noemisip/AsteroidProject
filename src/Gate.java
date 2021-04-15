import java.util.ArrayList;
import java.util.Random;

public class Gate implements Transport,Steppable,AI {    // A teleportkaput reprezentalo osztaly.
	private Asteroid asteroid;              //Az az aszteroida, amire a Gate peldanyt elhelyeztek.
	private Gate pair;                      //Az adott kapu parja.
	private boolean kerge;                   //kapu kergesége

	public void SetPair(Gate g) {           //Beallitja, hogy melyik kapupar a parja.
		pair = g;
	}

	public Gate GetPair() {                 //Visszaadja az adott kapu parjat
		return pair;
	}

	public void SetAsteroid(Asteroid a) {   //Beallitja, hogy melyik aszteroidan all a kapu.
		asteroid = a;
	}

	public Asteroid GetAsteroid() {        //Az asteroid parameter erteket adja vissza.
		return asteroid;
	}

	public void Transport(Creature c) {    //ket aszteroida kozotti mozgas teleportkapu altal
		asteroid.AddCreature(c);
		c.SetAsteroid(asteroid);
	}

	public void SetKerge(boolean b) {
		kerge=b;
	}
	public void WhereToMove() {
		ArrayList<Transport> asteroids=this.asteroid.GetNeighbours();
		Random rand = new Random();
		int rand_int = rand.nextInt(asteroids.size());
		Asteroid nexAsteroid = asteroids.get(rand_int).GetAsteroid();
		Move(nexAsteroid);
	}
	public int NextStep() {
		Random rand = new Random();
		int rand_int = rand.nextInt(2);
		return rand_int;
	}
	public void Move(Asteroid a) {  //t amire utazik
//		for(int i=0;i<a.GetNeighbours().size();i++) {
//			if (a.GetAsteroid().GetNeighbours().get(i) == pair.GetPair()) {  //ha benne van a szomszédjai listájában, tehát kapun keresztül utazik meg a getpair kaput ad vissza
//				TransportGate(pair.GetPair());
//			}
//		}
		ArrayList<Transport> neighbours = asteroid.GetNeighbours();
		Transport tr = null;
		for(Transport t : neighbours){
			if(t.GetAsteroid() == a) {
				tr=t;
				break;
			}
		}
		tr.TransportGate(this);
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
		if(pair!=g) {
			Asteroid a = g.GetPair().GetAsteroid();
			a.RemoveNeighbour(g);
			asteroid.AddNeighbour(g.GetPair());
			g.SetAsteroid(asteroid);
		}
	}

}

