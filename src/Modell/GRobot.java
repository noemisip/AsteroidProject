package Modell;

import Frame.FieldPanel;
import java.util.ArrayList;

public class GRobot extends Drawable{
    private Robot robot;

    public void Draw() {
        FieldPanel.init("robot_icon.png", this);
    }
    public void Update() {
        Asteroid a = robot.GetAsteroid();
        ArrayList<Steppable> steppables = Game.getInstance().GetSteppables();
        boolean found = false;
        for( Steppable s: steppables ){
            if ( s == robot){
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
        return null;
    }

    public void SetRobot(Robot r){
        robot = r;
    }

}
