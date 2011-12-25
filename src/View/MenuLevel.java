package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.BoutonChargement;


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
			
			int index = listefichiers[i].indexOf(".");
			String temp = listefichiers[i].substring(index);
			listefichiers[i] = listefichiers[i].replaceAll(temp, "");
			
			int i1 = i%4;
			int i2 = i/5;
			
			BoutonChargement temp1 = new BoutonChargement("Niveau " + (i + 1), 125+i1*130, 10+i2*70, 100,50,i+1);
			temp1.setOpaque(true);
			add(temp1);
		}
		
		repaint();
		
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
