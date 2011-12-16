package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;

import javax.swing.JPanel;


public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;
	
	public Menu() {
		try {
			img = ImageIO.read(new File("img/bgmenu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(null);
		
	}
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		
	}
	
}
