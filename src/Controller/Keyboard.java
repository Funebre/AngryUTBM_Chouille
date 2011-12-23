package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Birds;
import Model.Fenetre;

public class Keyboard implements KeyListener {
	
	private Birds bird;
	
	public Keyboard(Birds model){
		this.bird = model;
	}

	@Override
	public void keyPressed(KeyEvent evt) {	
		if (evt.getKeyCode() == KeyEvent.VK_SPACE && Fenetre._anime){ 
			if (Fenetre._list_birds.size() != 0) { // Test s'il existe un oiseau
				if (bird._bouge == 1)
					System.out.println("Vol Stationnaire.");
				else
					System.out.println("Mise en mouvement.");
				Fenetre._list_birds.get(0)._bouge = (Fenetre._list_birds.get(0)._bouge+1)%2;
			}
		}
		
		if (evt.getKeyCode() == KeyEvent.VK_ENTER && Fenetre.oeufEnCours == null && Fenetre._anime){
			if (Fenetre._list_birds.size() != 0 ) { // Test s'il existe un oiseau
				System.out.println("Lacher oeuf.");
				Fenetre.oeufEnCours = Fenetre._list_birds.get(0).lay_egg();
				// Boko : je propose de remplacer la ligne du dessus par
				// Fenetre.oeufEnCours = Fenetre._list_birds.get(0).lay_egg()
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
