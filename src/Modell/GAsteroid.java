package Modell;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//az aszteroida kirajzolasaert felelos osztaly
public class GAsteroid extends Drawable{
    private Asteroid asteroid; //a megjelenitendo aszteroida
    private String text; //azokat az adatokat tarolja, amiket megjelenitunk egy aszteroidarol
    private String name; //az aszteroida neve

//kirajzolja az aszteroidat    
    public void Draw(Graphics g, ImageObserver i) throws IOException {
        final BufferedImage dnImg = ImageIO.read(new File("asteroid.png")); //az aszteroidahoz tartozo kep
        g.drawImage(dnImg, this.GetX(), this.GetY(),40,40, i); //a megfelelo meretu kep kirajzolasa, egy adott koordinataba
        g.setColor(Color.white);
        String name = Main.getInstance().GetKey(asteroid);
        g.drawString(name,this.GetX(), this.GetY()); //a tulajdonsagok megjelenites
        g.drawString(Integer.toString(asteroid.GetLayer()),this.GetX(), this.GetY()+50); //a tulajdonsagokhoz tartozo adatok lekeredr
        g.drawString(Boolean.toString(asteroid.GetCloseToSun()),this.GetX()+10, this.GetY()+50);
        if( asteroid.GetMaterial() != null){
            g.drawString(asteroid.GetMaterial().ToString(), this.GetX()+40 ,this.GetY()+50);
        } else
            g.drawString("-", this.GetX()+40 ,this.GetY()+50);
    }
    
    //frissiti az adatokat, attol fuggoen hogy az aszteroida meg letezik-e
    public void Update(){
        ArrayList<Steppable> sp=Game.getInstance().GetSteppables();
        int index= sp.indexOf(asteroid);
        if(index!=-1){
            view.GetGameFrame().repaint();
            view.GetGameFrame().GetFieldPanel().repaint();
        }else{
            Remove();
        }
    }
    
    //a name attributum getter fuggvenye
    public String GetName(){
        return name;
    }

    //a name attributumot beallito fuggveny
    public void SetName(String n){
        name=n;
    }

    //asz asteroid attributumot beallito fuggveny
    public void SetAsteroid(Asteroid a){
        asteroid = a;
    }
}
