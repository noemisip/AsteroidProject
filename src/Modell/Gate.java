package Modell;

import java.util.ArrayList;
import java.util.Random;

public class Gate implements Transport,Steppable,AI {    // A teleportkaput reprezentalo osztaly.
	private Asteroid asteroid;              //Az az aszteroida, amire a Modell.Gate peldanyt elhelyeztek.
	private Gate pair;                      //Az adott kapu parja.
	private boolean kerge;                   //kapu kergesege

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
		asteroid.AddCreature(c);       //hozzaadja a lenyt az aszteroidahoz
		c.SetAsteroid(asteroid);        //beallitja a leny aszteroidajat az ujra
	}

	public void SetKerge(boolean b) {        //atallitja a kapu kergeseget a parameterben megadottal
		kerge=b;
	}
	public void WhereToMove() {       //a kapu ha kerge elofordulhat hogy kovetkezo lepeskent atmegy egy masik aszteroidara
		ArrayList<Transport> asteroids=this.asteroid.GetNeighbours(); //aszteroida szomszedai kozul kivalaszt random modon egyet,ahova mennyi fog a kapu
		Random rand = new Random();
		int rand_int = rand.nextInt(asteroids.size());
		Asteroid nextAsteroid;
		nextAsteroid = asteroids.get(rand_int).GetAsteroid();         
		Move(nextAsteroid);          //a kapu atmozog a parameterkent megadott aszteroidara
	}
	public int NextStep() {                  //eldonti, mi tortenjen a kovetkezo lepesben
		Random rand = new Random();
		int rand_int = rand.nextInt(2);
		return rand_int;
	}
	public void Move(Asteroid a) {  //a kapu mozgasat megvalosito metodus

		ArrayList<Transport> neighbours = asteroid.GetNeighbours();
		Transport tr = null;
		for(Transport t : neighbours){          //vegigmegyunk az aktualis aszteroida szomszedjain es megnezzuk,hogy kapun akar e atmenni es ha igen akkor megnezzuk, hogy az nem e a parja, mert a parjan keresztul nem utazhat
			if(t.GetAsteroid() == a && t.GetPair()!=this) {
				tr=t;
				break;
			}
		}
		if(tr!=null) tr.TransportGate(this);       //atkerul a kapu egy masik aszteroidara
	}
	public void Step() {                    //eldonti,hogy a kovetkezo lepesben mit csinaljon a nextstep fuggvennyel majd vegre is hajtja
		int next = NextStep();
		if(kerge==true && next==1){
			WhereToMove();
		}
	}
	public void SolarStorm(int a){          //napvihar eseten a kapu kerge lesz
		SetKerge(true);
	}


	public void RemoveNeighbour(Transport t){      //kapu eltavolitasa az aktualis aszteroidarol
		asteroid=null;
		pair.SetPair(null);                  //parjat nullra allitjuk
	}

	public void TransportGate(Gate g){                      //kapu-kapun keresztul mozgasaert felelos metodus
		if(pair!=g) {
			Asteroid a = g.GetAsteroid();           
			a.RemoveNeighbour(g.GetPair());             //toroljuk a kaput az eddigi aszteroidarol
			asteroid.AddNeighbour(g.GetPair());         //hozzaadjuk az aszteroidahoz az uj kapu parjat
			g.SetAsteroid(asteroid);                   //beallitjuk a kapu uj aszteroidajat
		}
	}

}

