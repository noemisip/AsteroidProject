
public class Gate implements Transport {    // A teleportkaput reprezent�l� oszt�ly.
	private Asteroid asteroid;              //Az az aszteroida, amire a Gate p�ld�nyt elhelyezt�k.
	private Gate pair;                      //Az adott kapu p�rja. 

	public void SetPair(Gate g) {           //Be�ll�tja, hogy melyik kapup�r a p�rja.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetPair()");
		pair = g;
		cnt.SetTab(-1);
	}

	public Gate GetPair() {                 //Visszaadja az adott kapu p�rj�t
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetPair() : Gate");
		cnt.SetTab(-1);
		return pair;
	}

	public void SetAsteroid(Asteroid a) {   //Be�ll�tja, hogy melyik aszteroid�n �ll a kapu.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("SetAsteroid(Asteroid a)");
		asteroid = a;
		cnt.SetTab(-1);
	}

	public Asteroid GetAsteroid() {        //Az asteroid param�ter �rt�k�t adja vissza.
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("GetAsteroid(): Asteroid");
		cnt.SetTab(-1);
		return asteroid;
	}

	public void Transport(Creature c) {    //k�t aszteroida k�z�tti mozg�s teleportkapu �ltal
		Controller cnt = new Controller();
		cnt.SetTab(1);
		cnt.PrintFunc("Transport(Creature c)");
		asteroid.AddCreature(c);
		c.SetAsteroid(asteroid);
		cnt.SetTab(-1);

	}
}

