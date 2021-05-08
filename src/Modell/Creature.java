package Modell;

import java.util.ArrayList;

public abstract class Creature {
	protected Asteroid asteroid; //az az aszteroida amin a creautre eppen all

	public abstract void Die(); //a creature meghal
	public abstract void Drill();//a creature fur az aszteroidan
	public abstract void Move(Asteroid a); //a creature a kivalasztott aszteroidara mozog
	public abstract void AsteroidExplosion(); //a creature reagal az aszteroida robbanasara
	public void SetAsteroid(Asteroid a) { //a creature asteroid attruibutumanak erteket allitja be
		asteroid=a;
	}
	public abstract void AddMaterial ( Material m); // materialt ad hozza annak a creaturnek a listajahoz akinek van
	public abstract ArrayList<Material> GetMaterials(); //visszaadja a creaturenel levo nyersanyagok listajat
	public Asteroid GetAsteroid(){ // visszaadja a creature aszteroidajat
		return asteroid;
	};
}
