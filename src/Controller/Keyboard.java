package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import View.ZoneAff;

public class Keyboard implements KeyListener {
	private ZoneAff _listener;
	
	public Keyboard (ZoneAff listener) {
		_listener = listener;
	}

	@Override
	public void keyPressed(KeyEvent evt) {	
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			_listener.actionSPACE();
		}
		
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			_listener.actionENTER();
		}
		
		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
			_listener.actionESC();
			System.out.println("Echap");
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	public void setPanel(ZoneAff Zone) {
		_listener = Zone;
	}
}
