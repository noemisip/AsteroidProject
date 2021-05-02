import java.util.ArrayList;

public class View {

	private ArrayList<Drawable> drawables;
	private ControlPanel controlPanel;
	
	public void UpdateAll() {
		for(Drawable d:drawables) {
			d.Update();
		}
	}
	
	public void ShowSolarStrom() {
		if(Game.getInstance().GetSpace().GetSolarStrom()) {
			
		}
	}

	public ControlPanel GetControlPanel() {
		return this.controlPanel;
	}
	
	public void FindAsteroid(Asteroid a, Drawable d) {
		String name = Main.getInstance().GetKey(a);
		for(Drawable i:drawables) {
			if(i.GetName().equals(name)) {
				i.SetKoord(d.GetX(), d.GetY()); 
			}
		}
	}

	public void RemoveDrawable(Drawable d) {
		drawables.remove(d);
	}
	
	public void AddDrawable(Drawable d) {
		drawables.add(d);
	}
	
}
