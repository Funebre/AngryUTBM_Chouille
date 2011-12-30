package Model;

public class Moineau extends Birds {

	public Moineau(int posx, int posy) {
		_type = 8;
		this._egg_left = 1;
	}

	@Override
	public Eggs lay_egg() {
		if(this._flight_time > 0 && this._egg_left > 0) {
			super.lay_egg();
			--this._egg_left;
			return new Normal_egg(this.getPosX(), this.getPosY());
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
