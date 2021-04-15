import java.util.ArrayList;
import java.util.Random;

public class Space implements Steppable {
	
	private ArrayList<Asteroid> asteroids; // az urben talalható aszteroidak listaja
	private ArrayList<Creature> creatures; // az urben tartozkodo Creature-ok listaja 

	public Space(){
		asteroids= new ArrayList<Asteroid>();
		creatures = new ArrayList<Creature>();
	}
	//hozzaadja a parameterkent megkapott Astreoid peldanyt az asteroids listahoz
	public void AddAsteroid(Asteroid a) {
        asteroids.add(a);
	}

	//kiveszi a parameterkent megkapott Astreoid peldanyt az asteroids listabol
	public void RemoveAsteroid(Asteroid a) {
        asteroids.remove(a);
    }

	//hozzaadja a parameterkent megkapott Creature peldanyt az creatures listahoz
	public void AddCreature(Creature c) {
        creatures.add(c);
	}

	//kiveszi a parameterkent megkapott Creature peldanyt az creatures listabol
	public void RemoveCreature(Creature c) {
        creatures.remove(c);
	}

	//minden korben eldonti, hogy lesz-e napvihar, azaz random meghivja az aszteroidak SolarStorm() fuggvenyet	
	public void Step() {
		Random rand = new Random();
		int solarstormhappens = rand.nextInt(1);
		int selectedasteroid=0;
		if(solarstormhappens==1) {
			selectedasteroid=rand.nextInt(asteroids.size()-1);
			asteroids.get(selectedasteroid).SolarStorm(2);
		}
	}
}
