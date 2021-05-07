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
    private Ufo ufo;

    public void Draw(Graphics g, ImageObserver i) throws IOException {
        final BufferedImage dnImg = ImageIO.read(new File("ufo_icon.png"));
        g.drawImage(dnImg, this.GetX(), this.GetY(),30,30, i);
    }
    public void Update(){
        Asteroid asteroid=ufo.GetAsteroid();
        ArrayList<Steppable> sp=Game.getInstance().GetSteppables();
        int index= sp.indexOf(ufo);
        if(index!=-1){
            view.FindAsteroid(asteroid,this);
            view.GetGameFrame().repaint();
            view.GetGameFrame().GetFieldPanel().repaint();
        }else{
            Remove();
        }
    }
    public String GetName(){
        return null;
    }

    public void SetUfo(Ufo u){
        ufo=u;
    }
}
