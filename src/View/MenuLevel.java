package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.Bouton;
import Model.Fenetre;
import Model.StateFen;


public class MenuLevel extends ZoneAff {
	private static final long serialVersionUID = -3449213442064345585L;
	private String path = "levels/";
	private Image img;
	
	public MenuLevel () {
		super();

		try {
			img = ImageIO.read(new File("img/bgmenu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);
		
		String [] listefichiers;
		File repertoire = new File(path);

		int i;
		listefichiers = repertoire.list();
		
		for (i = 0; i < listefichiers.length; ++i){
			final int lvl = i + 1;
			
			int index = listefichiers[i].indexOf(".");
			String temp = listefichiers[i].substring(index);
			listefichiers[i] = listefichiers[i].replaceAll(temp, "");
			File entree = new File(listefichiers[i]);
			
			int i1 = i%4;
			int i2 = i/5;
			
			if (entree.getName().contentEquals("level" + (i + 1)) && i <= Fenetre._level.getMaxLvl()) {
				Bouton temp1 = new Bouton("Niveau " + (i + 1), 125+i1*130, 10+i2*70, 100,50);
				temp1.setOpaque(true);
				temp1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Fenetre._level.loadLevel(lvl);
						Fenetre.setState(StateFen.Level);
						System.out.println("Début du level " + lvl);
					}
				});
				add(temp1);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		
	}
	
	@Override
	public void actionESC() {
		// TODO Auto-generated method stub

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
