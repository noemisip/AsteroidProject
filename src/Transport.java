public interface Transport {
	//A Transport el� nem kell abstract?
	//Abstract f�ggv�ny, az aszteroid�k k�z�tti k�zleked�s lebonyol�t�s�ra
	public abstract void Transport(Creature c);
	//Abstract f�ggv�ny, hogy a Transportok aszteroid�j�t lek�rj�k
	public abstract Asteroid GetAsteroid();
}
