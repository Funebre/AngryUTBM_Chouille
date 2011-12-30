package Threading;

import Model.Fenetre;
import Model.StateFen;

public class Print_menu implements Runnable {

	@Override
	public void run() {
		while (Fenetre._state != StateFen.Level) {
			
			// Verifie l'etat du menu toute les demi-seconde
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
