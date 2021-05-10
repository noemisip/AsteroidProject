package Modell;

import java.util.ArrayList;
import java.util.Random;

public class Space implements Steppable {
	
	private ArrayList<Asteroid> asteroids; // az urben talalhato aszteroidak listaja
	private ArrayList<Creature> creatures; // az urben tartozkodo Modell.Creature-ok listaja
	private boolean solarstorm;
	public Space(){ //inicializalas
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

	//hozzaadja a parameterkent megkapott Modell.Creature peldanyt az creatures listahoz
	public void AddCreature(Creature c) {
        creatures.add(c);
	}

	//kiveszi a parameterkent megkapott Modell.Creature peldanyt az creatures listabol
	public void RemoveCreature(Creature c) {
        creatures.remove(c);
	}

	//minden korben eldonti, hogy lesz-e napvihar, azaz random meghivja az aszteroidak SolarStorm() fuggvenyet 	
	public void Step() {
		Random rand = new Random();
		int solarstormhappens = rand.nextInt(2);
		int selectedasteroid=0;
		if(solarstormhappens==1) {
			selectedasteroid=rand.nextInt(asteroids.size()-1);
			asteroids.get(selectedasteroid).SolarStorm(2);
			solarstorm = true;
			//random aszteroidan elinditja a napvihart
			//a kettes parameter felel az aszteroida szuk kornyezeteben megvalosulo napvihar miatt
			//System.out.println(Boolean.toString(solarstorm));
		}
		else{
			solarstorm = false;
			//System.out.println(Boolean.toString(solarstorm));
		}
	}
	
	//a solarstrom attributum getter fuggvenye
	public boolean GetSolarStrom() {
		return solarstorm;
	}
	
	//az asteroid listabol az elso aszteroidat adja vissza
	public Asteroid GetAsteroid(){
		return asteroids.get(0);
	}
	public void SetNeighbours(){ //beallitja az aszteroidak kozott veletlenszeruen a szomszedsagokat
		for(int i =0; i< asteroids.size()-1; i++){
			for(int j=i+1; j<asteroids.size(); j++){
				Random rnd = new Random();
				if(rnd.nextInt()%3==0){
					asteroids.get(i).AddNeighbour(asteroids.get(j));
					asteroids.get(j).AddNeighbour(asteroids.get(i));
				}
			}
		}
	}
}
