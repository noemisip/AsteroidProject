public abstract class Drawable {
    protected double x;
    protected double y;
    protected View view;

    public abstract void Draw();
    public abstract void Update();
    public void SetKoord( double x, double y){
        this.x = x;
        this.y = y;
    }
    public void Remove(){
        //eltunteti a drawable peldanyt a kepernyorol
        view.RemoveDrawables(this);
    }
    public abstract String GetName();
    public double GetX(){
        return x;
    }
    public double GetY(){
        return y;
    }


}
