package Modell;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

//a játékban szereplo objektumok kirajzolasaert felelos osztaly
public abstract class Drawable {
    protected int x; //x koordinata
    protected int y; //y koordinata
    protected View view; //view attributum

    public abstract void Draw(Graphics g, ImageObserver i) throws IOException; //absztrakt fuggveny, ami kirajzolja a megadott x, y koordinatakba az objektumot, lasd GAsteroid, GUfo, GSettler, GRobot
    public abstract void Update(); //absztrakt fuggveny, ami frissiti az attributumokat az aktualis allapotoknak megfeleloen

    //beallitja a megadott x es y ertekeket a koordinataknak
    public void SetKoord( int x, int y){
        this.x = x;
        this.y = y;
    }
    
     //eltunteti a drawable peldanyt a kepernyorol        
    public void Remove(){
        view.RemoveDrawable(this);
        view.GetGameFrame().repaint();
        view.GetGameFrame().GetFieldPanel().repaint();
    }
    
    public abstract String GetName(); //absztrakt fuggveny visszaadja a leszarmazottak nevet
    
    //az x koordinata getter fuggvenye
    public int GetX(){
        return x;
    }
    //az y koordinata getter fuggvenye
    public int GetY(){
        return y;
    }
    
    //a view attributumot beallito fuggveny
    public void SetView(View v){
        view=v;
    }

}
