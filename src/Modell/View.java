package Modell;

import Frame.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class View {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private SettlerNumber settlernumberFrame;
	private Menu menuFrame;
	private GameFrame gameFrame;

	public View() throws IOException {
		gameFrame=new GameFrame();
		//menuFrame=new Menu();
		//settlernumberFrame=new SettlerNumber();
	}
	public void UpdateAll() {
		for(int i=0; i<drawables.size();i++ ){
			drawables.get(i).Update();
		}
	}

	public ArrayList<Drawable> GetDrawables() {
		return drawables;
	}

	public void ShowSolarStrom() {
		if(Game.getInstance().GetSpace().GetSolarStrom()) {
			
		}
	}
	
	public void FindAsteroid(Asteroid a, Drawable d) {
		String name = Main.getInstance().GetKey(a);
		for(Drawable i:drawables) {
			if(i.GetName()!= null && i.GetName().equals(name)) {
				Random rnd = new Random();
				int t = rnd.nextInt(20)+10;
				d.SetKoord(i.GetX()+t, i.GetY()-40);
			}
		}
	}

	public void RemoveDrawable(Drawable d) {
		drawables.remove(d);
	}
	
	public void AddDrawable(Drawable d) {
		drawables.add(d);
	}

	public ControlPanel GetControlPanel() {
		return null;
	}
}
