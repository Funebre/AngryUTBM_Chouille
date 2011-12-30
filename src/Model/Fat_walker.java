package Model;


public class Fat_walker extends Walkers{
	public Fat_walker (int x, int y) {
		super(x, y, _VITESSE_FAT_WALKER, _WIDTH_FAT_WALKER, _HEIGHT_FAT_WALKER);
		
		constructor();
	}

	public Fat_walker(int posx, int posy, boolean switched) {
		super(posx, posy, _VITESSE_FAT_WALKER, _WIDTH_FAT_WALKER, _HEIGHT_FAT_WALKER);
		
		_arriere = switched;
		
		constructor();
	}

	private void constructor() {
		//Images de merde, j'ai que paint... ^^
		_liste_texture.add("img/grod1.png");
		_liste_texture.add("img/grod2.png");
		_liste_texture.add("img/grod3.png");
		_liste_texture_back.add("img/grog1.png");
		_liste_texture_back.add("img/grog2.png");
		_liste_texture_back.add("img/grog3.png");
		_texture = "img/grod1.png";
		_type = 5;
	}

	@Override
	public void demol() {
		// TODO Auto-generated method stub
		
	}
}
