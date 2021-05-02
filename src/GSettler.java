import java.util.ArrayList;

public class GSettler extends Drawable{
    private Settler settler;
    private String name;
    public void Draw(){

    }
    public void Update(){
        Asteroid a = settler.GetAsteroid();
        ArrayList<Steppable> steppables = Game.getInstance().GetSteppables();
        boolean found = false;
        for( Steppable s: steppables ){
            if ( s == settler){
                found =true;
                view.FindAsteroid(a,this);
                Draw();
            }
        }
        if( found == false){
            this.Remove();
        }
    }
    public String GetName(){
        return name;
    }

    public void SetSettler(Settler s){
        settler = s;
    }
    public void SetName(String name){
        this.name = name;
    }
}
