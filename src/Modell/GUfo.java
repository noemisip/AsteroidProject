package Modell;

import Frame.FieldPanel;

import javax.swing.*;
import java.util.ArrayList;

public class GUfo extends Drawable{
    private Ufo ufo;

    public void Draw(){
        FieldPanel.init("ufo_icon.png", this);
    }
    public void Update(){
        Asteroid asteroid=ufo.GetAsteroid();
        ArrayList<Steppable> sp=Game.getInstance().GetSteppables();
        int index= sp.indexOf(ufo);
        if(index!=-1){
            view.FindAsteroid(asteroid,this);
            Draw();
        }else{
            Remove();
        }
    }
    public String GetName(){
        return " ";
    }
    
    public void SetUfo(Ufo u){
        ufo=u;
    }
}
