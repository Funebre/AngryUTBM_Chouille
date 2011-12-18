package Model;

import java.awt.event.KeyListener;

import Controller.Keyboard;


public class Birds extends MovingItems {
	
	protected double _flight_time = 0;
	protected double _stat_time = 0;
	protected int _egg_left = 0;
	protected int _takeoff = 0;
	public int _bouge = 0;
	
	public Birds(){
		super();
	}
	
	public Birds(float x, float y,int vitesse, int width, int height, double flight_time, double stat_time, int egg_left, int takeoff, int bouge){
		super(x,y,vitesse,width,height);
		_flight_time = flight_time * 1000;
		_stat_time = stat_time * 1000;
		_egg_left = egg_left;
		_takeoff = takeoff;
		_bouge = bouge;
	
	}
	
	void take_off (){
		this.moveX((float)this._vitesse / 1000 * 5);
		this.moveY((float)-1.2 * _REFRESH_POS / 5);
		_takeoff++;
		
	}
	
	void land (){
		this.moveX((float)this._vitesse / 1000 * 5);
		this.moveY((float)1.2 * _REFRESH_POS / 5);
	}
	
	public void move(){
		if (!this._destructing) {
			if(this._takeoff < 60 && this._bouge != 0){
					take_off();
			} else if (_flight_time <= 0){
					land();
			} else if (this._bouge != 0) {
				this.moveX((float)this._vitesse / 1000 * 5);
			}
			
			if (this._takeoff != 0){
				_flight_time -= 5;		
			}
		}
	}
	
	public void demol() {
		if (!this._destructing)
			this._destructing = true;
		else {
			this.statutTexture++;
			
			if (this.statutTexture == 5)
				this._destructed = true;
		}
	}
	
	public Eggs lay_egg() {
		return new Eggs();
		
	}

	public boolean isDestructing() {
		return this._destructing;
	}
}
