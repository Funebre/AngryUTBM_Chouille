package Threading;

import Model.Constantes;
import Model.Fenetre;

public class Print_aff implements Constantes, Runnable {
private boolean _stop = false;
	
	public void run() {
		while (!_stop && Fenetre._anime) {
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
				}
			}
		}
		
		Fenetre._fenster.repaint();
	}
}
