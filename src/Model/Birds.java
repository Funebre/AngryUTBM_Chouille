package Model;

abstract public class Birds extends MovingItems {
	
	protected double _flight_time = 0;
	protected double _stat_time = 0;
	protected int _egg_left = 0;
	protected int _takeoff = 0;
	protected boolean _bouge = false;
	
	public Birds(){
		super();
	}
	
	public Birds(float x, float y,int vitesse, int width, int height, double flight_time, double stat_time, int egg_left, int takeoff, boolean bouge) {
		super(x,y,vitesse,width,height);
		_flight_time = flight_time * 1000;
		_stat_time = stat_time * 1000;
		_egg_left = egg_left;
		_takeoff = takeoff;
		setMoving(bouge);
	}
	
	private void take_off () {
		moveX((float)_vitesse / 1000 * 5);
		moveY((float)-1.2 * _REFRESH_POS / 5);
		_takeoff++;
		
	}
	
	private void land () {
		moveX((float)_vitesse / 1000 * 5);
		moveY((float)1.2 * _REFRESH_POS / 5);
	}
	
	public void move() {
		if (!this._destructing) {
			if(this._takeoff < 60 && getMoving()){
					take_off();
			} else if (_flight_time <= 0){
					land();
			} else if (getMoving()) {
				moveX((float)_vitesse / 1000 * 5);
			}
			
			if (this._takeoff != 0){
				_flight_time -= 5;		
			}
		}
	}
	
	/* Retourne true si l'oiseau est en train d'être détruit
	* @param void
	* @return boolean
	*/
	public boolean isDestructing() {
		return this._destructing;
	}
	
	/* Largue un oeuf
	* @param void
	* @return Eggs
	*/
	public Eggs lay_egg() {
		System.out.println("Lacher oeuf.");
		return null;
	}
	
	/* Retourne true si l'oiseau est en train de bouger
	* @param void
	* @return boolean
	*/
	public boolean getMoving() {
		return _bouge;
	}
	
	/* Donne une nouvelle valeur à la variable _bouge
	* @param boolean newVal
	* @return void
	*/
	public void setMoving(boolean newVal) {
		_bouge = newVal;
	}
	
	abstract public void demol();
}
