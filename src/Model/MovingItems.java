package Model;

import java.util.LinkedList;
import java.util.List;


abstract public class MovingItems extends ItemDisplay {

	protected List<String> _liste_texture = new LinkedList<String>();
	protected List<String> _liste_destructing = new LinkedList<String>();
	protected int statutTexture = 0;
	protected int _vitesse;
	protected int _timer = 0 ;
	protected boolean _destructed = false;
	protected boolean _destructing = false;
	protected int destructing_time = 0;
	
	public MovingItems(double x, double y, int vitesse, int width, int height) {
		super(x, y, width, height);	
		_vitesse = vitesse;
		_solid = true;
	}
	
	public void moveX(float x) {
		_x += x;
		_r.setLocation(getPosX(), getPosY());
	}
	
	public void moveY(float y) {
		_y += y;
		_r.setLocation(getPosX(), getPosY());
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
	
	abstract public void demol();
}
