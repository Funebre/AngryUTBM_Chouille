package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.Keyboard;
import Model.Bouton;
import Model.Fenetre;
import Model.StateFen;

public class MenuPause extends ZoneAff {
	private static final long serialVersionUID = 1913382158330314915L;
	
	private Keyboard _keyListener;
	private Bouton reprendre = new Bouton("", 300, 100, 200, 50);
	
	public MenuPause () {
		super();
		setFocusable(true);
		requestFocus();
		setLayout(null);
		
		add(reprendre);
		reprendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Fenetre.setState(StateFen.Level);
				Fenetre._level.savelevel();
				System.out.println("Bouton Reprendre");
			}
		});
		
		_keyListener = new Keyboard(this);
		addKeyListener(_keyListener);
	}

	@Override
	public void actionESC() {
		Fenetre.setState(StateFen.Level);
		System.out.println("Press ESC");
	}

	@Override
	public void actionSPACE() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionENTER() {
		// TODO Auto-generated method stub
		
	}
}
