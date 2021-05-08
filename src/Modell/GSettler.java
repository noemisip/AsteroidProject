package Modell;

import Frame.FieldPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GSettler extends Drawable{
    private Settler settler;
    private String name;
    public void Draw(Graphics g, ImageObserver i) throws IOException {
        final BufferedImage dnImg = ImageIO.read(new File("settler_icon.png"));
        g.drawImage(dnImg, this.GetX(), this.GetY(),30,30, i);
        String name = Main.getInstance().GetKey(settler);
        g.drawString(name,this.GetX(), this.GetY());
    }
    public void Update(){
        Asteroid a = settler.GetAsteroid();
        ArrayList<Settler> settlers = Game.getInstance().GetSettlers();
        boolean found = false;
        for( Settler s: settlers ){
            if ( s.equals(settler)){
                found =true;
                view.FindAsteroid(a,this);
                view.GetGameFrame().repaint();
                view.GetGameFrame().GetFieldPanel().repaint();
            }
        }
        if(!found){
            Remove();
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
