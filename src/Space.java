import java.util.ArrayList; 

public class Space implements Steppable {
	
	private ArrayList<Asteroid> asteroids; // az űrben található aszteroidák listája
	private ArrayList<Creature> creatures; // az űrben tartozkodó Creature-ök listája

	public Space(){
		asteroids= new ArrayList<Asteroid>();
		creatures = new ArrayList<Creature>();
	}
	//hozzáadja a paraméterként megkapott Astreoid példányt az asteroids listához
	public void AddAsteroid(Asteroid a) {
		Controller c = new Controller();
		c.PrintFunc("AddAsteroid(Asteroid a)");
		asteroids.add(a); 
	}

	//kiveszi a paraméterként megkapott Astreoid példányt az asteroids listából
	public void RemoveAsteroid(Asteroid a) {
		Controller c = new Controller();
		c.PrintFunc("RemoveAsteroid(Asteroid a)");
		asteroids.remove(a);
	}

	//hozzáadja a paraméterként megkapott Creature példányt az creatures listához
	public void AddCreature(Creature c) {
		Controller cnt = new Controller();
		cnt.PrintFunc("AddCreature(Creature c)");
		creatures.add(c);
	}

	//kiveszi a paraméterként megkapott Creature példányt az creatures listából
	public void RemoveCreature(Creature c) {
		Controller cnt = new Controller();
		cnt.PrintFunc("RemoveCreature(Creature c)");
		creatures.remove(c);
	}

	//minden körben eldöbti, hogy lesz-e napvihar, azaz random meghívja az aszteroidák SolarStorm() függvényét	
	public void Step() {
		Controller c = new Controller();
		c.PrintFunc("Step()");
		for (Asteroid a : asteroids) {
			a.SolarStorm();
		}
	}
}
