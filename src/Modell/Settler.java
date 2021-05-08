package Modell;

import java.util.ArrayList;

public class Settler extends Creature {
	private ArrayList<Material> materials; //a telepesnel levo nyersanyagok listája
	private ArrayList<Gate> gates; //a telepesnel levo kapuk
	private BillOfMaterials bill; //a robot és kapu epítesehez szukseseg nyersanyagok megepiteset segito objektum

	public Settler(){ // listak letrehozasa a konstruktorba
		materials= new ArrayList<Material>();
		gates = new ArrayList<Gate>();
		bill = new BillOfMaterials();
	}

	public void Drill(){ //a telepes fur, ha nem 0 az aszteroida
		if ( asteroid.GetLayer() != 0)
		asteroid.DecreaseLayer();
	}

	public void RestoreMaterial(Material m) { //a telepes visszahelyez egy nyersanyagot az aszteroidara, amin epp all
		if(asteroid.GetMaterial()==null && asteroid.GetLayer()==0){ //a telepes csak abban az esetben helyezi el a nyersanyagot, ha
													//az aszteroida ureges és nincs rajta  kereg
			asteroid.SetMaterial(RemoveMaterial(m));
		}
	}

	public void Mine() { //a telepes banyaszik az aszteroidán
		Material m = asteroid.GetMaterial();
		if(asteroid.GetLayer()==0 && materials.size()<10 && m!=null) { //ellenorzi, hogy tud-e banyaszni
			m.ReactToMine(asteroid, this); // a material reagal a banyaszasra
			Game.getInstance().CheckBase(asteroid); //a jatek ellenorzi, hogy fel tudjak-e epíteni a telpesek a bazist az aszteroidan
		}
	}
	
	public void CreateRobot() { //a telepes letrehoz egy robotot
		bill.SetUpRobot(); //bill attributum material listajat beallitja a robothoz szuksegesekre
		if(bill.CheckMaterials(materials)){ //elenorzi, hogy megvan-e a szukseges mennyisegu nyersanyag
			bill.SetUpRobot();  //bill attributum material listajat beallitja a robothoz szuksegesekre
			bill.RemoveMaterials(this); //kitorli a telepestol a felhasznalt nyersanyagokat, majd letrehozza a robotot és elhelyezi az aszteroidan
			Robot r = new Robot(); // letrehoz egy uj robotot
			r.SetAsteroid(asteroid);
			asteroid.AddCreature(r); // hozzadja az aszteroidahoz
			asteroid.GetSpace().AddCreature(r); //hozzadja a spacehez
			Game.getInstance().AddSteppable(r); //hozzadja a gamehez
			Main.CreateRobot(r);
		}
	}
	
	public void CreateGate() { //a telepes letrehoz egy teleportkapu-part
		if(gates.size()<2) {
			bill.SetUpGate(); //bill attributum material listajat beallítja a kapuhoz szuksegesekre
			if (bill.CheckMaterials(materials)) { //elenorzi, hogy megvan-e a szukséges mennyisegu nyersanyag
				bill.SetUpGate(); //bill attributum material listajat beallitja a kapuhoz szuksegesekre
				bill.RemoveMaterials(this); //kitorli a telepestol a felhasznalt nyersanyagokat, majd letrehozza a ket kaput és hozzaadja a listajahoz
				Gate g1 = new Gate(); // letrehoz ket uj gatet
				Gate g2 = new Gate();
				g1.SetPair(g2); // beallitja egymas parjainak oket
				g2.SetPair(g1);
				AddGate(g1); //hozzadja a settler gate listajahoz
				AddGate(g2);
				Main.getInstance().AddGate(g1); //felveszi a gatet a hasmapbe
				Main.getInstance().AddGate(g2);
			}
		}
	}
	
	public void PlaceGate(Gate g) { //a telepes lehelyez egy kaput az aszteroidara
		Gate g2=g.GetPair();
		asteroid.AddNeighbour(g2); //az aszteroida uj szomszedot kap a kapun keresztul
		g.SetAsteroid(asteroid); //beallitja, hogy a kapu melyik aszteroidan helyezkedik el
		RemoveGate(g); //a telepes kitolri a kaput a listjabol
	}

	
	public void AddMaterial(Material m) { //a telepes felvesz egy nyersanyagot a listajaba
		materials.add(m);
	}
	
	public Material RemoveMaterial(Material m) { //a telepes kitorol egy nyerssanyagot a listajabol
		for( int i = 0; i< materials.size(); i++){
			if(m.IsEquales(materials.get(i))) {
				Material temp = materials.get(i);
				materials.remove( materials.get(i));
				return temp;
			}
		}
		return null;
	}
	
	public void AddGate(Gate g) { //a telepes egy kaput keszitett es felveszi a listajaba
		gates.add(0,g);
	}
	
	public ArrayList<Material> GetMaterials() { //a telepes visszaadja a nala levo nyersanyagok listajat
		return materials;
	}
	
	public void Die() { //a telepes meghal
		asteroid.RemoveCreature(this);//a telpes kitorlodik az aszterodia creture listajabol
		asteroid.GetSpace().RemoveCreature(this); //a telpes megkerdezi az aszteroidat, hogy melyik spaceben van és kitorlodik az space creture listajabol
		Game.getInstance().RemoveSettler(this); //a telpes kitorlodik az game settler listajabol
		Game.getInstance().CheckSettlers(); //a jatek ellenorzi, hogy ven-e meg eletben telepes
	}
	
	public void Move(Asteroid a) { //a telepes a kivalasztott aszteroidara mozog
		Asteroid al = asteroid;
		ArrayList<Transport> neighbours = asteroid.GetNeighbours(); //a telepes szomszedainak listaja
		boolean move = false;
		Transport tr = null;
		for(Transport t : neighbours){ // ha a paramterkent kapott aszteroida benne van a neighbours listajaban akkr mozog
			if(t.GetAsteroid() == a) {
				move = true;
				tr=t;
				break;
			}
		}
		if(move){
			tr.Transport(this); //meghivja az objektum transport fuggvenyet
			al.RemoveCreature(this); //a telepes kitorlodik az aszterodia creature listajabol
			Game.getInstance().CheckBase(asteroid); //a jatek ellenorzi, hogy fel tudják-e epiteni a telpesek a bazist az aszteroida
		}
	}

	
	public void AsteroidExplosion() { //a telepes reagal az aszteroida felrobbanasara
		Die(); 
	}

	public void RemoveGate(Gate g){ //a telpes kitorol egy kaput a lsitajabol
		gates.remove(g);
	} //kitorol egy gatet a listabol

	public ArrayList<Gate> GetGateList(){return gates; } //a telepes gate listajat adja vissza
}
