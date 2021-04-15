
import java.util.ArrayList;
import java.util.Random;

public class Robot extends Creature implements Steppable,AI {

	public void WhereToMove() { //A robot eldonti, hogy melyik aszteroidara mozogjon
		ArrayList<Transport> a = asteroid.GetNeighbours();//lekerdezi annak az aszteroidanak a szomszedait, amin epp all
		Random r = new Random();
		int chosen = r.nextInt(a.size()+ 1);
		if(a.size()==0) Die(); //ha nem tud hova menni (azaz az aszteroidanak, mar nincs szomszedja), akkor meghal
		else Move(a.get(chosen).GetAsteroid()); //a robot mozog a random kivalasztott aszteroidara
	}

	public void Drill(){ // a robot fur
			asteroid.DecreaseLayer();
	}

	public void AddMaterial(Material m){
	}

	public int NextStep() {//a robot eldonti, hogy a kovetkezo lepesben mit csinaljon
		Random r = new Random();
		int random = r.nextInt(2);
		return random;
	}

	public void Step() { //a robot vegrehajtja a kivalasztott muveletet
		int result = NextStep();
		if(result == 0) Drill();
		else if(result==1) WhereToMove();
	}
	
	public void Die() { // a robot meghal

		asteroid.RemoveCreature(this);//a robot kitorlodik az aszterodia creture listajabol
		asteroid.GetSpace().RemoveCreature(this); //a robot megkerdezi az aszteroidat, hogy melyik spaceben van és kitorlodik az space creture listajabol
		Game.getInstance().RemoveSteppable(this); //a robot kitorlodik az space steppable listajabol

	}
	
	public void Move(Asteroid a) { //a robot a kiavlasztott aszteroidara mozog
		ArrayList<Transport> neighbours = asteroid.GetNeighbours(); //a telepes ellenorzi, hogy at tud-e menni arra az aszteroidara
		boolean move = false;
		Transport tr = null;
		for(Transport t : neighbours){
			if(t.GetAsteroid() == a) {
				move = true;
				tr=t;
				break;
			}
		}
		if(move){
			tr.Transport(this); //meghivja az objektum transport fuggvenyet
			asteroid.RemoveCreature(this); //a telepes kitorlodik az aszterodia creture listajabol
			Game.getInstance().CheckBase(asteroid); //a jatek ellenorzi, hogy fel tudják-e epiteni a telpesek a bazist az aszteroida
		}
	}
	
	public void AsteroidExplosion() { //a robot reagal az aszteroida felrobbanasara

		if( asteroid.GetNeighbours().size() == 0) Die();
		else WhereToMove();

	}
	
	public ArrayList<Material> GetMaterials() { //a robot megmondja, hogy milyen nyersanyagok vannak nala
		return null;
	}
}
