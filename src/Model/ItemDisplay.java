package Model;

import java.awt.Rectangle;
import java.util.Observable;

public class ItemDisplay extends Observable implements Constantes {
	protected float _x;
	protected float _y;
	protected int _width;
	protected int _height;
	protected Rectangle _r;
	protected int _type;
	
	public String _texture;
	protected boolean _solid;
	
	public ItemDisplay(){
		super();
		
	}

	public ItemDisplay(float x, float y, int width, int height){
		super();
		this._x = x;
		this._y = y;
		this._width = width;
		this._height = height;
		
		this._r = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}

	public ItemDisplay(float x, float y, int width, int height, String texture){
		super();
		this._x = x;
		this._y = y;
		this._width = width;
		this._height = height;
		
		this._texture = texture;
		
		this._r = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}
	
	public void setPosX(float x){
		this._x = x;
	}
	
	public int getPosX(){
		return (int) Math.floor(this._x);
	}
	
	public void setPosY(int y){
		this._y = y;
	}
	
	public int getPosY(){
		return (int) Math.floor(this._y);
	}
	
	public void setWidth(int x){
		this._width = x;
	}
	
	public int getWidth(){
		return this._width;
	}
	
	public void setHeight(int y){
		this._height=y;
	}
	
	public int getHeight(){
		return this._height;
	}
	
	public void addTexture(String path){
		this._texture = path;
	}
	
	public String getTexture(){
		return this._texture;
	}
	
	public Rectangle getRect(){
		return this._r;
	}
	
	public boolean getSolid () {
		return this._solid;
	}
}
