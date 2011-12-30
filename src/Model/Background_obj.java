package Model;

public class Background_obj extends ItemDisplay {
	public Background_obj () {
		this._solid = false;
		this._type = 2;
	}
	
	public Background_obj (float x, float y, String texture, int width, int height) {
		super(x, y, width, height);
		this._texture = texture;
		this._solid = false;
		this._type = 2;
	}
}
