
public class Gate implements Transport {    // A teleportkaput reprezentáló osztály.
	private Asteroid asteroid;              //Az az aszteroida, amire a Gate példányt elhelyezték.
	private Gate pair;                      //Az adott kapu párja. 

	public void SetPair(Gate g) {           //Beállítja, hogy melyik kapupár a párja.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetPair()");
		pair = g;
		cnt.SetTab(-1);
	}

	public Gate GetPair() {                 //Visszaadja az adott kapu párját
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetPair() : Gate");
		cnt.SetTab(-1);
		return pair;
	}

	public void SetAsteroid(Asteroid a) {   //Beállítja, hogy melyik aszteroidán áll a kapu.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetAsteroid(Asteroid a)");
		asteroid = a;
		cnt.SetTab(-1);
	}

	public Asteroid GetAsteroid() {        //Az asteroid paraméter értékét adja vissza.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetAsteroid(): Asteroid");
		cnt.SetTab(-1);
		return asteroid;
	}

	public void Transport(Creature c) {    //két aszteroida közötti mozgás teleportkapu által
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Transport(Creature c)");
		asteroid.AddCreature(c);
		c.SetAsteroid(asteroid);
		cnt.SetTab(-1);

	}
}

