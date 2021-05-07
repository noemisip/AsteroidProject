package Modell;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

public abstract class Drawable {
    protected int x;
    protected int y;
    protected View view;

    public abstract void Draw(Graphics g, ImageObserver i) throws IOException;
    public abstract void Update();

    public void SetKoord( int x, int y){
        this.x = x;
        this.y = y;
    }
    public void Remove(){
        //eltunteti a drawable peldanyt a kepernyorol
        view.RemoveDrawable(this);
    }
    public abstract String GetName();
    public int GetX(){
        return x;
    }
    public int GetY(){
        return y;
    }
    public void SetView(View v){
        view=v;
    }

}
