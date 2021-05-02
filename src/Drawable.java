public abstract class Drawable {
    private double x;
    private double y;

    public abstract void Draw();
    public abstract void Update();
    public void SetKoord( double x, double y){
        this.x = x;
        this.y = y;
    }
    public void Remove(){

    }
    public abstract String GetName();
    public double GetX(){
        return x;
    }
    public double GetY(){
        return y;
    }


}
