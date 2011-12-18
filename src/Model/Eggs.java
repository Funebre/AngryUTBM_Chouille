package Model;


public class Eggs extends MovingItems {
	public Eggs() {
		super();
	}
	
	public Eggs(int x, int y, int vitesse, int width, int height) {
		super(x,y,vitesse,width,height);
	}
	
	public int collide() {
		return -1;
	}
}
