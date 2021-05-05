package Modell;

import javax.swing.*;
import java.util.ArrayList;


public class GRobot extends Drawable {
    private Robot robot;
    private static JLabel base;
    private static ImageIcon i = new ImageIcon("robot.png");
    public void Draw(){
        //view.add(base);
        base.setIcon(i);
        base.setLocation(x,y);
    }
    public void Update(){
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
