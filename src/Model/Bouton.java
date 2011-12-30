package Model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;


public class Bouton extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	private boolean _clicked = false;
	
	public Bouton(String name, int X, int Y, int width, int height) {
		super(name);
		
		setBackground(Color.WHITE);
		setBounds(X,Y,width,height);
		addMouseListener(this);
		setOpaque(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		_clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!(e.getY() < 0 || e.getX() < 0 || e.getY() > this.getHeight() || e.getX() > this.getWidth()) && _clicked) {
			
		}
		_clicked = false;
	}

}
