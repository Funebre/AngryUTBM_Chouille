package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonChargement extends Bouton {

	private DataBase data = new DataBase();
	
	private static final long serialVersionUID = 1L;

	public BoutonChargement(String name, int X, int Y, int width, int height, final int i) {
		super(name, X, Y, width, height);

		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data.loadLevel(i);
				Fenetre.setState(StateFen.Level);
				System.out.println("Début du level " + i);
			}
		});
	}

}
