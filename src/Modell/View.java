package Modell;

import Frame.*;
import Frame.Menu;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//a felhasznaloi nezetert felelos osztaly
public class View {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>(); //a megjelenitett dolgok listaja
	private SettlerNumber settlernumberFrame; //a jatekosok szamat bekero ablakhoz tartozo attributum
	private Menu menuFrame; //menu ablakhoz tartozo attributum
	private GameFrame gameFrame; //a jatekot megjelenito ablakhoz tartozo attributum

	//a konstruktor meghivja a Menu osztaly kostruktorat
	public View() throws IOException {
		Menu();
	}
	
	//vegigmegy a drawables listan es meghivja az Update fuggvenyuket, illetve a gameFrame controlPanel-jenek az Update fuggvenyet is
	public void UpdateAll() {
		for(int i=0; i<drawables.size();i++ ){
			drawables.get(i).Update();
		}
		gameFrame.GetControlPanel().Update();
	}

	//a drawables lista getter fuggvenye
	public ArrayList<Drawable> GetDrawables() {
		return drawables;
	}

	//ha napvihar van, megjeleniti a kepernyon
	public void ShowSolarStrom() {
		gameFrame.GetFieldPanel().SetSolarstorm();
	}
	
	//a parameterkent kapott aszteroida neve alapjan megkeressuk azt a drawable peldanyt a listaban amelyiknek a neve megegyezik az aszteroida nevevel, 
	//es ennek koordinatait, és beállítjuk a parameterbeli Drawable koordinatainak
	public void FindAsteroid(Asteroid a, Drawable d) {
		String name = Main.getInstance().GetKey(a);
		for(Drawable i:drawables) {
			if(i.GetName()!= null && i.GetName().equals(name)) {
				Random rnd = new Random();
				int t = -48+(rnd.nextInt(10)+6)*5;
				d.SetKoord(i.GetX()+t, i.GetY()-40);
			}
		}
	}

	//a parameterkent megadott drawable-t tavolitja el a listabol	
	public void RemoveDrawable(Drawable d) {
		drawables.remove(d);
	}
	
	//a parameterkent megadott drawable-t adja hozza a listahoz
	public void AddDrawable(Drawable d) {
		drawables.add(d);
	}

	public void startGame() throws IOException {
		gameFrame = new GameFrame(this);
		drawables.clear();
	}

	public void endGame(boolean result){
		gameFrame.Exit();
		EndGame endgame = new EndGame(result);
	}
	public GameFrame GetGameFrame(){return gameFrame;}
	public void Menu() throws IOException {
		menuFrame=new Menu();
	}
}
