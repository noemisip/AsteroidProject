package Modell;

import Frame.FieldPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GSettler extends Drawable{           //telepesek megjeleniteseert felelos osztaly
    private Settler settler;
    private String name;
    public void Draw(Graphics g, ImageObserver i) throws IOException {          //telepes kirajzolasa
        final BufferedImage dnImg = ImageIO.read(new File("settler_icon.png"));      //telepes ikonjanak beallitasa
        g.drawImage(dnImg, this.GetX(), this.GetY(),30,30, i);                      //kirajzolas a megadott koordinatakra
        String name = Main.getInstance().GetKey(settler);
        g.drawString(name,this.GetX(), this.GetY());                               //telepes nevenek megjelenitese
    }
    public void Update(){                                                         //kirajzolas frissitese az aktualis adatoknak megfeleloen
        Asteroid a = settler.GetAsteroid();                                        //lekerjuk a telepes adatait
        ArrayList<Settler> settlers = Game.getInstance().GetSettlers();
        boolean found = false;
        for( Settler s: settlers ){                                                 //vegigmegyunk a telepeseken es amelyik egyezik a telepesunkkel, azt ujrarajzoljuk
            if ( s.equals(settler)){
                found =true;
                view.FindAsteroid(a,this);
                view.GetGameFrame().repaint();
                view.GetGameFrame().GetFieldPanel().repaint();
            }
        }
        if(!found){                                                  //ha nem talaltuk meg,tehat a telepes meghalt akkor nem rajzoljuk ki
            Remove();
        }
    }
    public String GetName(){                                         //settler nevenek getter fuggvenye
        return name;
    }
    public void SetSettler(Settler s){                                //settler beallitasa
        settler = s;
    }
    public void SetName(String name){                                 //nev beallitasa
        this.name = name;
    }
}
