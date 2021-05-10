package Modell;

public class Carbon extends Material {
	// Akkor hivodik meg ha asnak az aszteroidan
	public void ReactToMine(Asteroid a, Creature c) {
		a.SetMaterial(null); // kiszedi az aszteroidabol az eddig bennelevo anyagot, igy ures lesz
		c.AddMaterial(this); //hozzaadja a kibanyaszott anyagot a settler materialjaihoz
	}
	
	public void ReactToSunclose(Asteroid a) {
		// Akkor hivodik meg, ha az aszteroida napkozelben van
	}
	
	public boolean IsEquales(Material m) {
		// ellenorzi, hogy a megadott material ugyanolyan fajta- e, mint az osztaly
		return m.getClass()==this.getClass();
	}
	
	//ha egy aszteroidaban szen talalhato, akkor kiirja a nevet
	public String ToString(){
		return "carbon";
	}
	
}
