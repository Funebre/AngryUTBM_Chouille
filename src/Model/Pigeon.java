package Model;

public class Pigeon extends Birds {

	public Pigeon(double posx, double posy) {
		super(posx, posy,_VITESSE_BIRD_PIGEON, _WIDTH_BIRD_PIGEON, _HEIGHT_BIRD_PIGEON, _FLIGHT_TIME_BIRD_PIGEON, _STAT_TIME_BIRD_PIGEON, _EGGS_PIGEON, 0, false);
		
		_type = 7;
	}

	public Pigeon(int posx, int posy, int egg, int flight, int stat, int takeoff, boolean moving) {
		super(posx, posy,_VITESSE_BIRD_COLIBRI, _WIDTH_BIRD_COLIBRI, _HEIGHT_BIRD_COLIBRI, flight, stat, egg, takeoff, moving);
		
		_type = 7;
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
