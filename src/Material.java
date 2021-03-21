
public abstract class Material { // Absztrakt osztály, a nyersanyagok őse
	// Absztrakt függévények, melyek a leszármazottakban felüldefiniálódnak
	public abstract void ReactToSunclose(Asteroid a);
	public abstract void ReactToMine(Asteroid a, Settler s);
	public abstract boolean IsEquales(Material m);
}
