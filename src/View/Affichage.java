package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.Keyboard;
import Model.Birds;
import Model.Fenetre;
import Model.ItemDisplay;
import Model.StateFen;
import Model.Walkers;

public class Affichage extends ZoneAff {
	/**
	* Obligatoire mais des recherches Ã  faire pour savoir pk
	*/
	private static final long serialVersionUID = 1L;
	private Keyboard _keyListener;
	private Birds _currentBird;
	
	public Affichage() {
		super();
		setFocusable(true);
		requestFocus();
		
		_keyListener = new Keyboard(this);
		addKeyListener(_keyListener);
	}

	public void paintComponent(Graphics g) {	
		this.requestFocus();
		
		for(int i = 0; i < Fenetre._list_static_items.size(); i++) {
			try {
				ItemDisplay stock = Fenetre._list_static_items.get(i);
				Image img = ImageIO.read(new File(stock._texture));
				g.drawImage(img, stock.getPosX(), stock.getPosY(), stock.getWidth(), stock.getHeight(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(Fenetre._list_birds.isEmpty() == false){
			for(int i = 0; i < Fenetre._list_birds.size(); i++) {
				try {
					Birds stock = Fenetre._list_birds.get(i);
					
					
					if (!stock.isDestructed()) {
						Image img = ImageIO.read(new File(stock._texture));
						g.drawImage(img, stock.getPosX(), stock.getPosY(), stock.getWidth(), stock.getHeight(), this);
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(Fenetre._list_walkers.isEmpty() == false) {
			for(int i = 0; i < Fenetre._list_walkers.size(); i++){
				try {
					Walkers stock = Fenetre._list_walkers.get(i);
					if (!stock.isDestructed()) {
						Image img = ImageIO.read(new File(stock._texture));
						g.drawImage(img, stock.getPosX(), stock.getPosY(), stock.getWidth(), stock.getHeight(), this);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(Fenetre.oeufEnCours != null) {
			Image img;
			try {
				img = ImageIO.read(new File(Fenetre.oeufEnCours._texture));
				g.drawImage(img, Fenetre.oeufEnCours.getPosX(), Fenetre.oeufEnCours.getPosY(), Fenetre.oeufEnCours.getWidth(), Fenetre.oeufEnCours.getHeight(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void changeBird (Birds newBird) {
		_currentBird = newBird;
	}
	
	@Override
	public void actionESC() {
		Fenetre.setState(StateFen.MenuPause);
	}
	
	@Override
	public void actionSPACE() {
		if (Fenetre._state == StateFen.Level){ 
			if (Fenetre._list_birds.size() != 0) { // Test s'il existe un oiseau
				if (_currentBird.getMoving())
					System.out.println("Vol Stationnaire.");
				else
					System.out.println("Mise en mouvement.");
				_currentBird.setMoving(!_currentBird.getMoving());
			}
		}
	}
	
	@Override
	public void actionENTER() {
		if (Fenetre.oeufEnCours == null && Fenetre._state == StateFen.Level && _currentBird.getMoving()){
			// Test s'il existe un oiseau et s'il existe demande à l'oiseau de lacher un oeuf
			if (Fenetre._list_birds.size() != 0 ) {
				Fenetre.oeufEnCours = _currentBird.lay_egg();
			}
		}
	}
	
}