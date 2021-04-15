public interface Transport {
	//Abstract fuggveny, az aszteroidak kozotti kozlekedes lebonyolitasara
	public abstract void Transport(Creature c);
	//Abstract fuggveny, hogy a Transportok aszteroidajat lekerjuk
	public abstract Asteroid GetAsteroid();

	void TransportGate(Gate gate);
}
