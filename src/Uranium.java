
public class Uranium extends Material {

	public void ReactToMine(Asteroid a, Settler s) {
		// Akkor hivodik meg ha asnak az aszteroidan
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("ReactToMine(Asteroid a, Settler s)");
		a.SetMaterial(null); // kiszedi az aszteroidabol az eddig bennelevo anyagot, igy ures lesz
		s.AddMaterial(this); //hozzaadja a kibanyaszott anyagot a settler materialjaihoz
		c.SetTab(-1);
	}
	
	public void ReactToSunclose(Asteroid a) {
		// Akkor hivodik meg, ha az aszteroida napkozelben van
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("ReactToSunclose(Asteroid a)");
		a.Explosion(); // felrobban az aszteroida
		c.SetTab(-1);
	}
	
	public boolean IsEquales(Material m) {
		// ellenorzi, hogy a megadott material ugyanolyan fajta- e, mint az osztaly
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("IsEquales(Material m): true/false");
		c.SetTab(-1);
		return m.getClass()==this.getClass();
	}
	
	
}
