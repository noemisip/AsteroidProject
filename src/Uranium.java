public class Uranium extends Material {

	public void ReactToMine(Asteroid a, Settler s) {
		// Akkor hívódik meg ha ásnak az aszteroidán
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("ReactToMine(Asteroid a, Settler s)");
		a.SetMaterial(null); // kiszedi az aszteroidából az eddig bennelévő anyagot, így üres lesz
		s.AddMaterial(this); //hozzáadja a kibányászott anyagot a settler materialjaihoz
		c.SetTab(-1);
	}
	
	public void ReactToSunclose(Asteroid a) {
		// Akkor hívódik meg, ha az aszteroida napközelben van
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("ReactToSunclose(Asteroid a)");
		a.Explosion(); // felrobban az aszteroida
		c.SetTab(-1);
	}
	
	public boolean IsEquales(Material m) {
		// ellenőrzi, hogy a megadott material ugyanolyan fajta- e, mint az osztály
		Controller c = new Controller();
		c.SetTab(1);
		c.PrintFunc("IsEquales(Material m): true/false");
		c.SetTab(-1);
		return m.getClass()==this.getClass();
	}
	
	
}
