package Modell;
public interface AI { // interface amit megvalosit az ufo, a gate es a robot
    public abstract int NextStep(); //random 0-t vagy 1-et ad vissza
    public abstract void WhereToMove(); //kivalasztja hova mozogjon az objektum
}
