package Model;

public class Pigeon extends Birds {

	public Pigeon(int posx, int posy) {
		_type = 7;
		this._egg_left = 3;
	}

	@Override
	public Eggs lay_egg() {
		if(this._flight_time > 0 && this._egg_left > 0) {
			super.lay_egg();
			--this._egg_left;
			return new Big_egg(this.getPosX(), this.getPosY());
		} else {
			return null;
		}
	}
	
	@Override
	public void demol() {
		if (!this._destructing)
			this._destructing = true;
		else {
			this.statutTexture++;
			
			if (this.statutTexture == 5)
				this._destructed = true;
		}
	}

}
