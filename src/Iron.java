
public class Iron extends Material {
	// Akkor hivodik meg ha asnak az aszteroidan
	public void ReactToMine(Asteroid a, Settler s) {
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("ReactToMine(Asteroid a, Settler s)");
		a.SetMaterial(null); // kiszedi az aszteroidabol az eddig bennelevo anyagot, igy ures lesz
		s.AddMaterial(this); //hozzaadja a kibanyaszott anyagot a settler materialjaihoz
	}
	
	public void ReactToSunclose(Asteroid a) {
		// Akkor hivodik meg, ha az aszteroida napkozelben van
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc(" ReactToSunclose(Asteroid a)");
	}
	
	public boolean IsEquales(Material m) {
		// ellenorzi, hogy a megadott material ugyanolyan fajta- e, mint az osztaly
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("IsEquales(Material m)  : true/false");
		c.SetTab(-1);
		return m.getClass()==this.getClass();
	}
	
}
