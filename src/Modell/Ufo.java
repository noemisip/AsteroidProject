package Modell;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Creature implements Steppable,AI {

    private ArrayList<Material> materials; // az ufo material listája

    Ufo(){ // konstruktor amibe letrehozodik a material lista
        materials = new ArrayList<Material>(); 
    }
    public void AddMaterial( Material m){// a material listahoz hozzadjuk a parameterkent kapott materialt
        materials.add(m);
    }
    public void Mine(){ // az ufo banyaszik
        Material m = asteroid.GetMaterial();
        if(asteroid.GetLayer()==0 && m!=null) { // ha az aszteroidanak 0 a retege es van benne nyersanyag, akkor hozzadjuk az ufo materiallistajahoz
            AddMaterial(m);
            m.ReactToMine(asteroid, this); //a material reagal az asasra
        }
    }
    public void Step(){ // az ufo banyaszik ha 0 a nextstep vagy mozog ha 1
        int result = NextStep();
        if(result == 0) Mine();
        else if(result==1) WhereToMove();

    }
    public void Move(Asteroid a){
        Asteroid al = asteroid;
        ArrayList<Transport> neighbours = asteroid.GetNeighbours(); // a szomszedai listaja
        boolean move = false;
        Transport tr = null;
        for(Transport t : neighbours){
            if(t.GetAsteroid() == a) { // ha a parameterul kapott aszteroida benne van a szomszedaiban, akkor mozog
                move = true;
                tr=t;
                break;
            }
        }
        if(move){
            tr.Transport(this); //meghivja az objektum transport fuggvenyet
            al.RemoveCreature(this); //az ufo kitorlodik az aszterodia creture listajabol
        }
    }
   public void WhereToMove(){
       ArrayList<Transport> a = asteroid.GetNeighbours();//lekerdezi annak az aszteroidanak a szomszedait, amin epp all
       Random r = new Random();
       int chosen = r.nextInt(a.size());
       if(a.size()==0) Die(); //ha nem tud hova menni (azaz az aszteroidanak, mar nincs szomszedja), akkor meghal
       else Move(a.get(chosen).GetAsteroid());
   }

    public int NextStep(){ // random visszater 0-val vagy 1-el
        Random r = new Random();
        int random = r.nextInt(2);
        return random;
    }

    public void AsteroidExplosion(){
        Die(); // az ufo meghal az aszteroida robbanasakor
    }

    public ArrayList<Material> GetMaterials(){
        return null; // nullt ad vissza
    }


    public void Drill(){  } // nem tortenik semmi

    public void Die(){ // az ufo meghal
        asteroid.RemoveCreature(this); // kitorlodik az aszteroida creature listajabol
        asteroid.GetSpace().RemoveCreature(this); // a spacebpl is kitorlodik
        Game.getInstance().RemoveSteppable(this); // a gamebol is kitorlodik
    }
}
