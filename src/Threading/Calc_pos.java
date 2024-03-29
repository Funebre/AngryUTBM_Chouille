package Threading;

import Model.Constantes;
import Model.Fenetre;
import Model.StateFen;

public class Calc_pos implements Constantes, Runnable {
	public void run() {
		while (Fenetre._state == StateFen.Level) {
			this.calc_pos();
			
			try {
				Thread.sleep(_REFRESH_POS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void calc_pos () {
		for (int i = 0; i < Fenetre._list_walkers.size(); i++) {
			if (Fenetre._list_walkers.get(i).collide_static() || Fenetre._fenster.outScreen(Fenetre._list_walkers.get(i)))
				Fenetre._list_walkers.get(i).switchArriereState();
			
			Fenetre._list_walkers.get(i).move();
		}
		
					
		//Deplacement des oiseaux
		for(int i = 0; i < Fenetre._list_birds.size(); i++){
			if (Fenetre._list_birds.get(i).collide_static() || Fenetre._list_birds.get(i).collide_dynamic() || Fenetre._fenster.outScreen(Fenetre._list_birds.get(i)) || Fenetre._list_birds.get(i).isDestructing()){
				Fenetre._list_birds.get(i).demol();
			}
			else{
			Fenetre._list_birds.get(i).move();
			}
		}
		
		if(Fenetre.oeufEnCours != null) {
			int c = Fenetre.oeufEnCours.collide();
			if (c != -1){
				Fenetre._list_walkers.remove(c);
				
				Fenetre.oeufEnCours = null;
			} else if (Fenetre.oeufEnCours.collide_static()){
				Fenetre.oeufEnCours = null;
			} else {
				Fenetre.oeufEnCours.moveY(1);
			}
		}
	}
	
}
