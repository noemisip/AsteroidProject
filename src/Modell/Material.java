package Modell;

public abstract class Material { // Absztrakt osztaly, a nyersanyagok ose
	// Absztrakt fuggevenyek, melyek a leszarmazottakban feluldefinialodnak
	public abstract void ReactToSunclose(Asteroid a);
	public abstract void ReactToMine(Asteroid a, Creature c);
	public abstract boolean IsEquales(Material m);
	public abstract String ToString();
}
