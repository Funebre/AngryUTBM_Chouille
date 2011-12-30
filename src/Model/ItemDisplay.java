package Model;

import java.awt.Rectangle;
import java.util.Observable;

abstract public class ItemDisplay extends Observable implements Constantes {
	protected double _x;
	protected double _y;
	protected int _width;
	protected int _height;
	protected Rectangle _r;
	protected int _type;
	
	public String _texture;
	protected boolean _solid;
	
	public ItemDisplay(){
		super();
	}

	public ItemDisplay(double x, double y, int width, int height){
		super();
		_x = x;
		_y = y;
		_width = width;
		_height = height;
		
		_r = new Rectangle(getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	public void setPosX(double x){
		_x = x;
	}
	
	public int getPosX(){
		return (int) Math.floor(_x);
	}
	
	public void setPosY(double y){
		_y = y;
	}
	
	public int getPosY(){
		return (int) Math.floor(_y);
	}
	
	public void setWidth(int x){
		_width = x;
	}
	
	public int getWidth(){
		return _width;
	}
	
	public void setHeight(int y){
		this._height = y;
	}
	
	public int getHeight(){
		return _height;
	}
	
	public void addTexture(String path){
		_texture = path;
	}
	
	public String getTexture(){
		return _texture;
	}
	
	public Rectangle getRect(){
		return _r;
	}
	
	public boolean getSolid () {
		return _solid;
	}
	
	public int getType() {
		return _type;
	}
}
