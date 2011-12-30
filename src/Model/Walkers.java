package Model;

import java.util.ArrayList;

abstract public class Walkers extends MovingItems{
	
	final int SLIM = 0;
	final int NORMAL = 1;
	final int FAT = 2;

	protected ArrayList<String> _liste_texture_back = new ArrayList<String>();
	protected boolean _arriere = false;
	
	public Walkers(double x, double y, int vitesse, int width, int height)
	{
		super(x,y,vitesse, width, height);
		this._solid = true;
	}

	public void move(){
		if (!_arriere){
			moveX((float)_vitesse / 1000 * 5);
			
			if (_timer == _vitesse) {
				statutTexture = (statutTexture+1) % (_liste_texture.size());			
				_texture = _liste_texture.get(statutTexture);
				_timer = 0;
			}
		} else {
			moveX(-(float)_vitesse / 1000 * 5);
			
			if (_timer == _vitesse) {
				statutTexture = (statutTexture+1) % (_liste_texture_back.size());	
				_texture = _liste_texture_back.get(statutTexture);
				_timer = 0;
			}
		}
		
		++this._timer;
	}

	public void switchArriereState(){
		_arriere = !_arriere;
		
		if (_arriere) {
			statutTexture = 0;
			_texture = _liste_texture_back.get(0);
		} else {
			statutTexture = 0;
			_texture = _liste_texture.get(0);
		}
	}
	
	public boolean getSwitched() {
		return _arriere;
	}
}
