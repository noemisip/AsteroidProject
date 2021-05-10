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
    private Robot robot; //jatekban szereplo Robot, amit megjelenit
    //kirajzolas
    public void Draw(Graphics g, ImageObserver i) throws IOException {
        //a robotot kirajzolo kep
        final BufferedImage dnImg = ImageIO.read(new File("robot.png"));
        g.drawImage(dnImg, this.GetX(), this.GetY(),30,30, i); //a kep kirajzolasa a megfelelo pozicioban es meretben
    }
    public void Update() { //pozicio frissites
        Asteroid a = robot.GetAsteroid(); //a jelenlegi aszteroida beallitasa
        ArrayList<Steppable> steppables = Game.getInstance().GetSteppables();
        boolean found = false; 
        for( Steppable s: steppables ){//Ha meg jatekban van a robot
            if ( s.equals(robot)){ //megkeressuk a steppable-k kozott
                found =true;
                view.FindAsteroid(a,this); //koordinatak beallitasa az aszteroidanak megfeleloen
                view.GetGameFrame().repaint(); //GameFrame frissitese
                view.GetGameFrame().GetFieldPanel().repaint(); //FieldPanel frissitese
            }
        }
        if(!found){ //Ha mar nincs a jatekban a robot, akkor nem rajzolja ki
            this.Remove();
        }
    }
    public String GetName(){ //nev lekerese
        return null;
    }

    public void SetRobot(Robot r){ //robot beallitasa
        robot = r;
    }

}
