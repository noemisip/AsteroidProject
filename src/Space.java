import java.util.ArrayList; 

public class Space implements Steppable {
	
	private ArrayList<Asteroid> asteroids; // az urben talalható aszteroidak listaja
	private ArrayList<Creature> creatures; // az urben tartozkodo Creature-ok listaja 

	public Space(){
		asteroids= new ArrayList<Asteroid>();
		creatures = new ArrayList<Creature>();
	}
	//hozzaadja a parameterkent megkapott Astreoid peldanyt az asteroids listahoz
	public void AddAsteroid(Asteroid a) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddAsteroid(Asteroid a)");
        asteroids.add(a); 
		cnt.SetTab(-1);		
	}

	//kiveszi a parameterkent megkapott Astreoid peldanyt az asteroids listabol
	public void RemoveAsteroid(Asteroid a) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("RemoveAsteroid(Asteroid a)");
        asteroids.remove(a);
        cnt.SetTab(-1);
    }

	//hozzaadja a parameterkent megkapott Creature peldanyt az creatures listahoz
	public void AddCreature(Creature c) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("AddCreature(Creature c)");
        creatures.add(c);
    	cnt.SetTab(-1);
	}

	//kiveszi a parameterkent megkapott Creature peldanyt az creatures listabol
	public void RemoveCreature(Creature c) {
		Controller cnt = new Controller();
        cnt.SetTab(1);
        cnt.PrintFunc("RemoveCreature(Creature c)");
        creatures.remove(c);
    	cnt.SetTab(-1);		
	}

	//minden korben eldonti, hogy lesz-e napvihar, azaz random meghivja az aszteroidak SolarStorm() fuggvenyet	
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
