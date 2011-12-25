package Threading;

import Model.Constantes;
import Model.Fenetre;
import Model.StateFen;

public class Print_aff implements Constantes, Runnable {
	
	public void run() {
		if (Fenetre._list_birds.size() != 0)
			Fenetre._fenster.changeBird(Fenetre._list_birds.get(0));
		while (Fenetre._state == StateFen.Level) {
			this.printObj();
			
			try {
				Thread.sleep(_REFRESH_AFF);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printObj () {
		//Suppression des oiseaux qui ont touché quelque chose
		if(Fenetre._list_birds.isEmpty() == false){
			for(int i = 0; i < Fenetre._list_birds.size(); i++) {
				if (Fenetre._list_birds.get(i).isDestructed()) {
					Fenetre._list_birds.remove(i);
					if (Fenetre._list_birds.size() != 0)
						Fenetre._fenster.changeBird(Fenetre._list_birds.get(0));
				}
			}
		}
		
		Fenetre._fenster.repaint();
	}
}
