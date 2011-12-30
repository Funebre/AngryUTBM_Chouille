package Model;


public class Normal_walker extends Walkers {
	public Normal_walker (double x, double y) {
		super(x, y, _VITESSE_NORMAL_WALKER, _WIDTH_NORMAL_WALKER, _HEIGHT_NORMAL_WALKER);
		
		constructor();
	}

	public Normal_walker(double posx, double posy, boolean switched) {
		super(posx, posy, _VITESSE_NORMAL_WALKER, _WIDTH_NORMAL_WALKER, _HEIGHT_NORMAL_WALKER);
		
		_arriere = switched;
		
		constructor();
	}
	
	private void constructor() {
		_liste_texture.add("img/droite1.png");
		_liste_texture.add("img/droite2.png");
		_liste_texture.add("img/droite3.png");
		_liste_texture.add("img/droite4.png");
		_liste_texture_back.add("img/gauche1.png");
		_liste_texture_back.add("img/gauche2.png");
		_liste_texture_back.add("img/gauche1.png");
		_liste_texture_back.add("img/gauche4.png");
		
		_texture = "img/droite1.png";
		_type = 3;
	}

	@Override
	public void demol() {
		// TODO Auto-generated method stub
		
	}
}
