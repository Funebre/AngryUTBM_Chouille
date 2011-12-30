package Model;


public class Slim_walker extends Walkers {
	public Slim_walker (double x, double y) {
		super(x, y, _VITESSE_SLIM_WALKER, _WIDTH_SLIM_WALKER, _HEIGHT_SLIM_WALKER);
		
		constructor();
	}

	public Slim_walker(double posx, double posy, boolean switched) {
		super(posx, posy, _VITESSE_SLIM_WALKER, _WIDTH_SLIM_WALKER, _HEIGHT_SLIM_WALKER);
		
		_arriere = switched;
		
		constructor();
	}
	
	private void constructor() {
		//Images de merde, j'ai que paint... ^^
		_liste_texture.add("img/imgd.png");
		_liste_texture.add("img/imgd2.png");
		_liste_texture.add("img/imgd3.png");
		_liste_texture_back.add("img/imgg.png");
		_liste_texture_back.add("img/imgg2.png");
		_liste_texture_back.add("img/imgg3.png");
		
		_texture = "img/imgd.png";
		_type = 4;
	}

	@Override
	public void demol() {
		// TODO Auto-generated method stub
		
	}
}