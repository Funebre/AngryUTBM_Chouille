package Model;

public class Moineau extends Birds {
	public Moineau(int posx, int posy) {
		super(posx, posy, _VITESSE_BIRD_MOINEAU, _WIDTH_BIRD_MOINEAU, _HEIGHT_BIRD_MOINEAU, _FLIGHT_TIME_BIRD_MOINEAU, _STAT_TIME_BIRD_MOINEAU, _EGGS_MOINEAU, 0, false);
		
		_type = 8;
	}

	public Moineau(double posx, double posy, int egg, int flight, int stat, int takeoff, boolean moving) {
		super(posx, posy, _VITESSE_BIRD_MOINEAU, _WIDTH_BIRD_MOINEAU, _HEIGHT_BIRD_MOINEAU, flight, stat, egg, takeoff, moving);

		_type = 8;
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
