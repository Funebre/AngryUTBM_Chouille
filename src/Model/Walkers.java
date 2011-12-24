package Model;

import java.util.ArrayList;

abstract public class Walkers extends MovingItems{
	
	final int SLIM = 0;
	final int NORMAL = 1;
	final int FAT = 2;

	protected ArrayList<String> _liste_texture_back = new ArrayList<String>();
	protected boolean _arriere = false;
	protected boolean _waitForSwitch = false;
	
	
	public Walkers()
	{
		super();
		this._solid = true;
	}
	
	public Walkers(float x, float y, int vitesse, int width, int height)
	{
		super(x,y,vitesse, width, height);
		this._solid = true;
	}

	public void move(){
		if (!_arriere){
			this.moveX((float)this._vitesse / 1000 * 5);
			
			if (this._timer == this._vitesse) {
				this.statutTexture = (this.statutTexture+1) % (this._liste_texture.size());			
				this._texture = this._liste_texture.get(this.statutTexture);
				this._timer = 0;
			}
		} else {
			this.moveX(-(float)this._vitesse / 1000 * 5);
			
			if (this._timer == this._vitesse) {
				this.statutTexture = (this.statutTexture+1) % (this._liste_texture_back.size());	
				this._texture = this._liste_texture_back.get(this.statutTexture);
				this._timer = 0;
			}
		}
		
		++this._timer;
	}

	public void switchArriereState(){
		this._arriere = !this._arriere;
		
		if (this._arriere) {
			this.statutTexture = 0;
			this._texture = this._liste_texture_back.get(0);
		} else {
			this.statutTexture = 0;
			this._texture = this._liste_texture.get(0);
		}
	}
}
