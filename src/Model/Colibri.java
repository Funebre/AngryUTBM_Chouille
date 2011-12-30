package Model;


public class Colibri extends Birds {
	/* Crée un oiseau de type Colibri
	* @param float x
	* @param float y
	* @return void
	*/
	public Colibri(float x, float y) {
		super(x,y,_VITESSE_BIRD_COLIBRI, _WIDTH_BIRD_COLIBRI, _HEIGHT_BIRD_COLIBRI, _FLIGHT_TIME_BIRD_COLIBRI, _STAT_TIME_BIRD_COLIBRI,
				_EGGS_COLIBRI, 0, false);
		
		this._solid = true;
		this._liste_texture.add("img/oiseau1.png");
		this._texture = "img/oiseau1.png";
		this._type = 6;
		this._egg_left = 2;
	}
	
	@Override
	public Eggs lay_egg() {
		if(_flight_time > 0 && _egg_left > 0) {
			super.lay_egg();
			--this._egg_left;
			return new Small_egg(this.getPosX(), this.getPosY());
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
