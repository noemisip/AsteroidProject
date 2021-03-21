public interface Transport {
	//A Transport elé nem kell abstract?
	//Abstract függvény, az aszteroidák közötti közlekedés lebonyolítására
	public abstract void Transport(Creature c);
	//Abstract függvény, hogy a Transportok aszteroidáját lekérjük
	public abstract Asteroid GetAsteroid();
}
