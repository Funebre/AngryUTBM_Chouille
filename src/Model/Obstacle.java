package Model;


public class Obstacle extends ItemDisplay {
	public Obstacle () {
		
	}
	
	public Obstacle (int x, int y, String texture, int width, int height) {
		super (x, y, width, height, texture);
		this._solid = true;
	}
}
