public class GAsteroid extends Drawable{
    private Asteroid asteroid;
    private String text;
    private String name;

    public void Draw(){

    }
    public void Update(){
        int layer= asteroid.GetLayer();
        Material m=asteroid.GetMaterial();
        ArrayList<Steppable> sp=Game.getInstance().GetSteppables();
        int index= sp.indexOf(asteroid);
        if(index!=-1){

            Draw();
        }else{
            Remove();
        }
    }
    public String GetName(){
        return name;
    }

    public void SetName(String n){
        name=n;
    }

    public void SetAsteroid(Asteroid a){
        asteroid = a;
    }
}
