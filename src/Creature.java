
import java.util.ArrayList;

public abstract class Creature {
	protected Asteroid asteroid; //az az aszteroida amin a creautre eppen all

	public abstract void Die(); //a creature meghal
	public void Drill() { //a creature fur az aszteroida kopenyen
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("Drill()");

		asteroid.DecreaseLayer();
		c.SetTab(-1);
	}

	public abstract void Move(Asteroid a); //a creature a kivalasztott aszteroidara mozog
	
	public abstract void AsteroidExplosion(); //a creature reagal az aszteroida robbanasara
	
	public void SetAsteroid(Asteroid a) { //a creature asteroid attruibutumának erteket allitja be
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("SetAsteroid(a)");

		asteroid=a;
		c.SetTab(-1);
	}
	
	public abstract ArrayList<Material> GetMaterials(); //visszaadja a creaturenel levo nyersanyagok listajat
}
