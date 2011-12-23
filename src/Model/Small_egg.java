package Model;


public class Small_egg extends Eggs {
	public Small_egg() {
		super();
	}
	
	public Small_egg(int x, int y) {
		super(x,y,_WIDTH_SMALL_EGG,_VITESSE_SMALL_EGG, _HEIGHT_SMALL_EGG);
		_texture = "img/egg.png";
		_type = 9;
	}
	
	// Retourne l'index du walker avec lequel l'oeud collisionne, retourne -1 si l'oeuf ne collisionne pas.
	public int collide() {
		// Les oeufs font disparaitre seulement les Walkers.
		for(int i = 0; i < Fenetre._list_walkers.size(); i++){
			
			if (_r.intersects(Fenetre._list_walkers.get(i).getRect()))
				return i;
			}
		return -1;
	}
}
