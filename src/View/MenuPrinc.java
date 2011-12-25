package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.Keyboard;
import Model.Bouton;
import Model.Fenetre;
import Model.StateFen;

public class MenuPrinc extends ZoneAff {
	private static final long serialVersionUID = 2864905028652238985L;
	
	private Keyboard _keyListener;
	private Image img;

	private Bouton jouer = new Bouton("Commencer une partie", 300, 100, 200, 50);
	private Bouton exit = new Bouton("Exit", 300, 250, 200, 50);
	
	public MenuPrinc () {
		super();
		
		try {
			setImg(ImageIO.read(new File("img/bgmenu.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
		
		add(jouer);
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fenetre.setState(StateFen.LevelLoad);
				Fenetre._fenster.updateMenu();
				System.out.println("Bouton Jouer");
			}
		});
		add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fenetre._working = false;
				System.out.println("Bouton Exit");
			}
		});
		
		_keyListener = new Keyboard(this);
		addKeyListener(_keyListener);
		repaint();
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void actionESC() {
		
	}

	@Override
	public void actionSPACE() {
		
	}

	@Override
	public void actionENTER() {
		
	}
}