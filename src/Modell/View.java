package Modell;

import Frame.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class View {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private SettlerNumber settlernumberFrame;
	private Menu menuFrame;
	private GameFrame gameFrame;

	public View() throws IOException {
		menuFrame=new Menu();
		//gameFrame=new GameFrame();
		//settlernumberFrame=new SettlerNumber();
	}
	public void UpdateAll() {
		for(int i=0; i<drawables.size();i++ ){
			drawables.get(i).Update();
		}
		gameFrame.GetControlPanel().Update();
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
				int t = -48+(rnd.nextInt(10)+6)*5;
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

	public void startGame() throws IOException {
		gameFrame = new GameFrame(this);
	}

	public void endGame(boolean result){
		gameFrame.Exit();
		EndGame endgame = new EndGame(result);
	}
	public GameFrame GetGameFrame(){return gameFrame;}
}
