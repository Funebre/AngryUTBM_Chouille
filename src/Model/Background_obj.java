package Model;

import java.awt.Rectangle;


public class Background_obj extends ItemDisplay {
	public Background_obj () {
		this._solid = false;
		this._type = 2;
	}
	
	public Background_obj (float x, float y, String texture, int width, int height) {
		this._x = x;
		this._y = y;
		this._texture = texture;
		this._width = width;
		this._height = height;
		this._r = new Rectangle(this.getPosX(), this.getPosY(), this._width, this._height);
		this._solid = false;
		this._type = 2;
	}
}
