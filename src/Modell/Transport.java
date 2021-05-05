package Modell;

public interface Transport {
        //absztrakt fuggvenyek
	public abstract void Transport(Creature c);

	public abstract void TransportGate(Gate gate);

	public abstract Asteroid GetAsteroid();

	public abstract void SolarStorm(int c);

	public abstract void RemoveNeighbour(Transport t);
	public abstract Transport GetPair();


}
