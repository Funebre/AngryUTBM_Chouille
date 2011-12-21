package Model;

import java.util.LinkedList;
import java.util.List;

public class Walkers extends MovingItems{
	
	final int SLIM = 0;
	final int NORMAL = 1;
	final int FAT = 2;

	protected List<String> _liste_texture_back = new LinkedList<String>();
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

	public void move(Fenetre fen){	//Penser à utiliser peut etre un floor avec des +0,3 en position
		if (!_arriere){
			//_waitForSwitch = true;
			this.moveX((float)this._vitesse / 1000 * 5);
			if (this._timer == this._vitesse) {
				this.statutTexture = (this.statutTexture+1) % (this._liste_texture.size());			
				this._texture = this._liste_texture.get(this.statutTexture);
				this._timer = 0;
			}
		} else {
			//_waitForSwitch = true;
			this.moveX(-(float)this._vitesse / 1000 * 5);
			if (this._timer == this._vitesse) {
				this.statutTexture = (this.statutTexture+1) % (this._liste_texture_back.size());	
				this._texture = this._liste_texture_back.get(this.statutTexture);
				this._timer = 0;
			}
		}
		
		this._timer++;
	}

	public void switchArriereState(){
		//if (_waitForSwitch)
			this._arriere = !this._arriere;
			if (this._arriere) {
				this.statutTexture = 0;
				this._texture = this._liste_texture_back.get(0);
			} else {
				this.statutTexture = 0;
				this._texture = this._liste_texture.get(0);
			}
		//_waitForSwitch = false;
	}
}
