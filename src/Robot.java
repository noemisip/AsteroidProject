
import java.util.ArrayList;

public class Robot extends Creature implements Steppable {

	public void WhereToMove() { //A robot eldonti, hogy melyik aszteroidara mozogjon
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("WhereToMove()");

		ArrayList<Transport> a = asteroid.GetNeighbours(); //lekerdezi annak az aszteroidanak a szomszedait, amin epp all
		if(a.size()==0) Die(); //ha nem tud hova menni (azaz az aszteroidanak, mar nincs szomszedja), akkor meghal
		else Move(a.get(0).GetAsteroid()); //a robot mozog
		c.SetTab(-1);
	}

	public int NextStep() {//a robot eldonti, hogy a kovetkezo lepesben mit csinaljon
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("NextStep(): 0");
		c.SetTab(-1);
		return 0; //az egyszeruseg kedveert most csak 0-val ter vissza
	}

	public void Step() { //a robot vegrehajtja a kivalasztott muveletet
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Step()");

		int result = NextStep();
		if(result == 0) Drill();
		else if(result==1) WhereToMove();
		c.SetTab(-1);
	}
	
	public void Die() { // a robot meghal
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Die()");

		asteroid.RemoveCreature(this); //a robot kitorlodik az aszterodia creture listajabol
		asteroid.GetSpace().RemoveCreature(this); //a robot megkerdezi az aszteroidat, hogy melyik spaceben van és kitorlodik az space creture listajabol
		Game.getInstance().RemoveSteppable(this); //a robot kitorlodik az space steppable listajabol

		c.SetTab(-1);
	}
	
	public void Move(Asteroid a) { //a robot a kiavlasztott aszteroidara mozog
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Move(a)");

		asteroid.GetNeighbours(); //a robot megkapja, hogy melyik transport objektum fogja atvinni a masik aszteroidára
		a.Transport(this); //meghivja az objektum transport fuggvenyet
		asteroid.RemoveCreature(this); //a robot kitorlodik az aszterodia creture listajabol
		c.SetTab(-1);
	}
	
	public void AsteroidExplosion() { //a robot reagal az aszteroida felrobbanasara
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("AsteroidExplosion()");

		WhereToMove();
		c.SetTab(-1);
	}
	
	public ArrayList<Material> GetMaterials() { //a robot megmondja, hogy milyen nyersanyagok vannak nala
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("GetMaterials() : null");
		c.SetTab(-1);
		return null; // a robotnál nincs nyersanyag
	}
}
