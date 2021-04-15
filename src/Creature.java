
import java.util.ArrayList;

public abstract class Creature {
	protected Asteroid asteroid; //az az aszteroida amin a creautre eppen all

	public abstract void Die(); //a creature meghal
	public abstract void Drill();//a creature fur az aszteroida kopenye
	public abstract void Move(Asteroid a); //a creature a kivalasztott aszteroidara mozog
	public abstract void AsteroidExplosion(); //a creature reagal az aszteroida robbanasara
	public void SetAsteroid(Asteroid a) { //a creature asteroid attruibutumának erteket allitja b
		asteroid=a;
	}
	public abstract void AddMaterial ( Material m);
	public abstract ArrayList<Material> GetMaterials(); //visszaadja a creaturenel levo nyersanyagok listajat
}
