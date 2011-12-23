package Model;


public class Normal_walker extends Walkers {
	public Normal_walker() {
		
		super(0,0,_VITESSE_FAT_WALKER, _WIDTH_FAT_WALKER, _HEIGHT_FAT_WALKER);
		
		//Images de merde, j'ai que paint... ^^
		this._liste_texture.add("img/droite1.png");
		this._liste_texture.add("img/droite2.png");
		this._liste_texture.add("img/droite3.png");
		this._liste_texture.add("img/droite4.png");
		this._liste_texture_back.add("img/gauche1.png");
		this._liste_texture_back.add("img/gauche2.png");
		this._liste_texture_back.add("img/gauche1.png");
		this._liste_texture_back.add("img/gauche4.png");

		this._texture = "img/droite1.png";
		this._type = 3;
	}
	
	public Normal_walker (float x, float y) {
		super(x,y,_VITESSE_NORMAL_WALKER, _WIDTH_NORMAL_WALKER, _HEIGHT_NORMAL_WALKER);
		
		this._liste_texture.add("img/droite1.png");
		this._liste_texture.add("img/droite2.png");
		this._liste_texture.add("img/droite3.png");
		this._liste_texture.add("img/droite4.png");
		this._liste_texture_back.add("img/gauche1.png");
		this._liste_texture_back.add("img/gauche2.png");
		this._liste_texture_back.add("img/gauche1.png");
		this._liste_texture_back.add("img/gauche4.png");
		
		this._texture = "img/droite1.png";
		this._type = 3;
	}
}
