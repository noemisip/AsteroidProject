package Modell;

public class Uranium  extends Material {

	private int cnt = 0;

	// Akkor hivodik meg ha asnak az aszteroidan
	public void ReactToMine(Asteroid a, Creature c) {
		a.SetMaterial(null); // kiszedi az aszteroidabol az eddig bennelevo anyagot, igy ures lesz
		c.AddMaterial(this); //hozzaadja a kibanyaszott anyagot a settler materialjaihoz
	}

	public void ReactToSunclose(Asteroid a) {
		// Akkor hivodik meg, ha az aszteroida napkozelben van
		if(cnt < 2)
			cnt++;
		if(cnt == 2)
			a.Explosion(); //ha az aszteroida mar ketszer volt napkozelben, akkor harmadszorra felrobban
	}

	public boolean IsEquales(Material m) {
		// ellenorzi, hogy a megadott material ugyanolyan fajta- e, mint az osztaly
		return m.getClass()==this.getClass();
	}
	
	//megnoveli a cnt attributum erteket a paameterkent megadott ertekkel
	void SetCnt(int cnt) {
		this.cnt = cnt;
	}
	
	//visszaadja a cnt attributum erteket
	int GetCnt() {
		return cnt;
	}

	//ha egy aszteroidaban uran nyersanyag talalhato akkor kiirja a nevet
	public String ToString(){
		return "uranium";
	}

}
