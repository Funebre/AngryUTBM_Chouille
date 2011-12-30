package Model;


abstract public class Eggs extends MovingItems {
	public Eggs(double x, double y, int vitesse, int width, int height) {
		super(x, y, vitesse, width, height);
	}
	
	public int collide() {
		return -1;
	}
}
