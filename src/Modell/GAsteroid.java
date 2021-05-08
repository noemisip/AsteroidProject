package Modell;

import Frame.FieldPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GAsteroid extends Drawable{
    private Asteroid asteroid;
    private String text;
    private String name;

    public void Draw(Graphics g, ImageObserver i) throws IOException {
        final BufferedImage dnImg = ImageIO.read(new File("asteroid.png"));
        g.drawImage(dnImg, this.GetX(), this.GetY(),40,40, i);
        g.setColor(Color.white);
        String name = Main.getInstance().GetKey(asteroid);
        g.drawString(name,this.GetX(), this.GetY());
        g.drawString(Integer.toString(asteroid.GetLayer()),this.GetX(), this.GetY()+50);
        g.drawString(Boolean.toString(asteroid.GetCloseToSun()),this.GetX()+10, this.GetY()+50);
        if( asteroid.GetMaterial() != null){
            g.drawString(asteroid.GetMaterial().ToString(), this.GetX()+40 ,this.GetY()+50);
        } else
            g.drawString("-", this.GetX()+40 ,this.GetY()+50);
    }
    public void Update(){
        int layer= asteroid.GetLayer();
        Material m=asteroid.GetMaterial();
        ArrayList<Steppable> sp=Game.getInstance().GetSteppables();
        int index= sp.indexOf(asteroid);
        if(index!=-1){
            view.GetGameFrame().repaint();
            view.GetGameFrame().GetFieldPanel().repaint();
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
