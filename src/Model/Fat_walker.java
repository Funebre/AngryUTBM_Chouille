package Model;


public class Fat_walker extends Walkers{
	public Fat_walker() {
		super(0,0,_VITESSE_FAT_WALKER, _WIDTH_FAT_WALKER, _HEIGHT_FAT_WALKER);
		
		//Images de merde, j'ai que paint... ^^
		this._liste_texture.add("img/grod1.png");
		this._liste_texture.add("img/grod2.png");
		this._liste_texture.add("img/grod3.png");
		this._liste_texture_back.add("img/grog1.png");
		this._liste_texture_back.add("img/grog2.png");
		this._liste_texture_back.add("img/grog3.png");
		this._texture = "img/grod1.png";
	}
	
	public Fat_walker (int x, int y) {
		
		super(x,y,_VITESSE_FAT_WALKER, _WIDTH_FAT_WALKER, _HEIGHT_FAT_WALKER);
					
		//Images de merde, j'ai que paint... ^^
		this._liste_texture.add("img/grod1.png");
		this._liste_texture.add("img/grod2.png");
		this._liste_texture.add("img/grod3.png");
		this._liste_texture_back.add("img/grog1.png");
		this._liste_texture_back.add("img/grog2.png");
		this._liste_texture_back.add("img/grog3.png");
		this._texture = "img/grod1.png";
	}
}
