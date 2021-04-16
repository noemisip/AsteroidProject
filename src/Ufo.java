import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Creature implements Steppable,AI {

    private ArrayList<Material> materials;

    Ufo(){
        materials = new ArrayList<Material>();
    }
    public void AddMaterial( Material m){
        materials.add(m);
    }
    public void Mine(){
        Material m = asteroid.GetMaterial();
        if(asteroid.GetLayer()==0 && m!=null) {
            AddMaterial(m);
            m.ReactToMine(asteroid, this);
        }
    }
    public void Step(){
        int result = NextStep();
        if(result == 0) Mine();
        else if(result==1) WhereToMove();

    }
    public void Move(Asteroid a){
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
   public void WhereToMove(){
       ArrayList<Transport> a = asteroid.GetNeighbours();//lekerdezi annak az aszteroidanak a szomszedait, amin epp all
       Random r = new Random();
       int chosen = r.nextInt(a.size()+ 1);
       if(a.size()==0) Die(); //ha nem tud hova menni (azaz az aszteroidanak, mar nincs szomszedja), akkor meghal
       else Move(a.get(chosen).GetAsteroid());
   }

    public int NextStep(){
        Random r = new Random();
        int random = r.nextInt(2);
        return random;
    }

    public void AsteroidExplosion(){
        Die();
    }

    public ArrayList<Material> GetMaterials(){
        return null;
    }

    @Override
    public Asteroid GetAsteroid() {
        return asteroid;
    }

    public void Drill(){
    }

    public void Die(){
        asteroid.RemoveCreature(this);
        asteroid.GetSpace().RemoveCreature(this);
        Game.getInstance().RemoveSteppable(this);
    }
}
