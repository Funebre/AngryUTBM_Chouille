package Model;


public class Colibri extends Birds {

	public Colibri(float x, float y) {
		super(x,y,_VITESSE_BIRD_COLIBRI, _WIDTH_BIRD_COLIBRI, _HEIGHT_BIRD_COLIBRI, _FLIGHT_TIME_BIRD_COLIBRI, _STAT_TIME_BIRD_COLIBRI,
				_EGGS_COLIBRI, 0,0);
		
		this._solid = true;
		this._liste_texture.add("img/oiseau1.png");
		this._texture = "img/oiseau1.png"; 
		}
	
	public Small_egg lay_egg() {
		Small_egg egg = new Small_egg(this.getPosX(), this.getPosY());
		Fenetre.oeufEnCours = egg;
		
		return egg;
	}
}
