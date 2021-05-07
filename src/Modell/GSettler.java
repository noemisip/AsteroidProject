package Modell;

import Frame.FieldPanel;

import java.util.ArrayList;

public class GSettler extends Drawable{
    private Settler settler;
    private String name;
    public void Draw(){
        FieldPanel.init("settler_icon.png", this);
    }
    public void Update(){
        Asteroid a = settler.GetAsteroid();
        ArrayList<Settler> settlers = Game.getInstance().GetSettlers();
        boolean found = false;
        for( Settler s: settlers ){
            if ( s.equals(settler)){
                found =true;
                view.FindAsteroid(a,this);
                Draw();
            }
        }
        if(!found){
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
