package Model;


public class Slim_walker extends Walkers {
	public Slim_walker() {
		
		super(0,0,_VITESSE_FAT_WALKER, _WIDTH_FAT_WALKER, _HEIGHT_FAT_WALKER);
		
		//Images de merde, j'ai que paint... ^^
		this._liste_texture.add("img/imgd.png");
		this._liste_texture.add("img/imgd2.png");
		this._liste_texture.add("img/imgd3.png");
		this._liste_texture_back.add("img/imgg.png");
		this._liste_texture_back.add("img/imgg2.png");
		this._liste_texture_back.add("img/imgg3.png");
		
		this._texture = "img/imgd.png";
	}
	
	public Slim_walker (float x, float y) {
		super(x,y,_VITESSE_SLIM_WALKER, _WIDTH_SLIM_WALKER, _HEIGHT_SLIM_WALKER);
		
		//Images de merde, j'ai que paint... ^^
		this._liste_texture.add("img/imgd.png");
		this._liste_texture.add("img/imgd2.png");
		this._liste_texture.add("img/imgd3.png");
		this._liste_texture_back.add("img/imgg.png");
		this._liste_texture_back.add("img/imgg2.png");
		this._liste_texture_back.add("img/imgg3.png");
		this._texture = "img/imgd.png";
	}
}