package Model;

import java.util.LinkedList;
import java.util.List;


abstract public class MovingItems extends ItemDisplay {

	protected List<String> _liste_texture = new LinkedList<String>();
	protected int statutTexture = 0;
	protected int _vitesse;
	protected int _timer = 0 ;
	protected boolean _destructed = false;
	protected boolean _destructing = false;
	
	public MovingItems() {
		super();	
	}
	
	public MovingItems(float x, float y, int vitesse, int width, int height) {
		super(x,y,width,height);	
		this._vitesse = vitesse;
	}
	
	public void moveX(float x) {
		this._x += x;
		this._r.setLocation(this.getPosX(), this.getPosY());
	}
	
	public void moveY(float y) {
		this._y += y;
		this._r.setLocation(this.getPosX(), this.getPosY());
	}
	
	public boolean isDestructed () {
		setChanged();
		notifyObservers();
		return this._destructed;	
	}

	//Returns true if the item collides with a static item
	public boolean collide_static() {
		for(int i = 0; i < Fenetre._list_static_items.size(); i++) {
			if (Fenetre._list_static_items.get(i).getSolid() && _r.intersects(Fenetre._list_static_items.get(i).getRect())) //Ici on v�rifie que le rectangle de l'objet dans lequel on est collide avec un des objets de la liste static
				return true;
		}
		
		return false;
	}
	
	//Returns true if the item collides with a moving item
	public boolean collide_dynamic() {
		for(int i = 0; i < Fenetre._list_walkers.size(); i++) {
			if (Fenetre._list_walkers.get(i).getSolid() && _r.intersects(Fenetre._list_walkers.get(i).getRect())) //Ici on v�rifie que le rectangle de l'objet dans lequel on est collide avec un des objets de la liste static
				return true;
		}
		
		return false;
	}
}
