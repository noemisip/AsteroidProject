
public class Gate implements Transport {    // A teleportkaput reprezentalo osztaly.
	private Asteroid asteroid;              //Az az aszteroida, amire a Gate peldanyt elhelyeztek.
	private Gate pair;                      //Az adott kapu parja.

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
}

