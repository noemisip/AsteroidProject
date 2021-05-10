package Modell;

import Frame.FieldPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GUfo extends Drawable{
    private Ufo ufo; //az az ufo a jatekban, aminek a mejeleniteset vegzi
    //ufo kirajzolasa
    public void Draw(Graphics g, ImageObserver i) throws IOException {
        final BufferedImage dnImg = ImageIO.read(new File("ufo_icon.png")); //ufo kepenek beallitasa
        g.drawImage(dnImg, this.GetX(), this.GetY(),30,30, i); //ufo kepenek kirajzolasa a megfelelo pozicioban es meretben
    }
    public void Update(){ //pozicio frissitese
        Asteroid asteroid=ufo.GetAsteroid(); //jelenlegi aszteroida beallitasa
        ArrayList<Steppable> sp=Game.getInstance().GetSteppables();
        int index= sp.indexOf(ufo);
        if(index!=-1){ //Ha meg jatekban van az ufo
            view.FindAsteroid(asteroid,this); //jelenlegi aszteroida szerinti koordinatak beallitasa
            view.GetGameFrame().repaint(); //GameFrame frissitese
            view.GetGameFrame().GetFieldPanel().repaint(); //FieldPanel frissitese
        }else{ //Ha nincs jatekban az ufo, akkor nem rajzolja ki
            Remove();
        }
    }
    public String GetName(){ //visszaadja a nevet
        return null;
    }

    public void SetUfo(Ufo u){ //ufo bealltasa
        ufo=u;
    }
}
