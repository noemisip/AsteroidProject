package Modell;

import Frame.FieldPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GRobot extends Drawable{
    private Robot robot;

    public void Draw(Graphics g, ImageObserver i) throws IOException {

        final BufferedImage dnImg = ImageIO.read(new File("robot.png"));
        g.drawImage(dnImg, this.GetX(), this.GetY(),30,30, i);
    }
    public void Update() {
        Asteroid a = robot.GetAsteroid();
        ArrayList<Steppable> steppables = Game.getInstance().GetSteppables();
        boolean found = false;
        for( Steppable s: steppables ){
            if ( s.equals(robot)){
                found =true;
                view.FindAsteroid(a,this);
                view.GetGameFrame().repaint();
                view.GetGameFrame().GetFieldPanel().repaint();
            }
        }
        if(!found){
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
