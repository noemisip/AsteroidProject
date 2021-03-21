
public class Iron extends Material {
	// Akkor hívódik meg ha ásnak az aszteroidán
	public void ReactToMine(Asteroid a, Settler s) {
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("ReactToMine(Asteroid a, Settler s)");
		a.SetMaterial(null); // kiszedi az aszteroidából az eddig bennelévő anyagot, így üres lesz
		s.AddMaterial(this); //hozzáadja a kibányászott anyagot a settler materialjaihoz
	}
	
	public void ReactToSunclose(Asteroid a) {
		// Akkor hívódik meg, ha az aszteroida napközelben van
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc(" ReactToSunclose(Asteroid a)");
	}
	
	public boolean IsEquales(Material m) {
		// ellenőrzi, hogy a megadott material ugyanolyan fajta- e, mint az osztály
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("IsEquales(Material m)  : true/false");
		c.SetTab(-1);
		return m.getClass()==this.getClass();
	}
	
}
