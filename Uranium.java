
public class Uranium  extends Material {
	
	private int cnt = 0;
	
	// Akkor hivodik meg ha asnak az aszteroidan
	public void ReactToMine(Asteroid a, Settler s) {
		Controller c = new Controller();
		a.SetMaterial(null); // kiszedi az aszteroidabol az eddig bennelevo anyagot, igy ures lesz
		s.AddMaterial(this); //hozzaadja a kibanyaszott anyagot a settler materialjaihoz
	}
	
	public void ReactToSunclose(Asteroid a, Creature c) {
		// Akkor hivodik meg, ha az aszteroida napkozelben van
		Controller c = new Controller();
		if(a.getMaterial().getCnt() < 2)
			a.getMaterial().setCnt(1);
		if(a.getMaterail().getCnt() == 2)
			a.Explosion();
	}
	
	public boolean IsEquales(Material m) {
		// ellenorzi, hogy a megadott material ugyanolyan fajta- e, mint az osztaly
		Controller c = new Controller();
		return m.getClass()==this.getClass();
	}
	
	void setCnt(int cnt) {
		this.cnt -= cnt;
	}
	
	void getCnt() {
		return this.cnt;
	}
	
}
