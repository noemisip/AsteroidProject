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
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddAsteroid(Asteroid a)");
        asteroids.add(a); 
		cnt.SetTab(-1);		
	}

	//kiveszi a paraméterként megkapott Astreoid példányt az asteroids listából
	public void RemoveAsteroid(Asteroid a) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("RemoveAsteroid(Asteroid a)");
        asteroids.remove(a);
        cnt.SetTab(-1);
    }

	//hozzáadja a paraméterként megkapott Creature példányt az creatures listához
	public void AddCreature(Creature c) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddCreature(Creature c)");
        creatures.add(c);
    	cnt.SetTab(-1);
	}

	//kiveszi a paraméterként megkapott Creature példányt az creatures listából
	public void RemoveCreature(Creature c) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("RemoveCreature(Creature c)");
        creatures.remove(c);
    	cnt.SetTab(-1);		
	}

	//minden körben eldönti, hogy lesz-e napvihar, azaz random meghívja az aszteroidák SolarStorm() fÃ¼ggvÃ©nyÃ©t	
	public void Step() {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("Step()");
        
    	for (Asteroid a : asteroids) {
			a.SolarStorm();
		}
    	cnt.SetTab(-1);
	}
}
