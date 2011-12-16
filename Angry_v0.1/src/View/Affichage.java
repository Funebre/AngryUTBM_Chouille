package View;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.Keyboard;
import Model.Birds;
import Model.Fenetre;
import Model.ItemDisplay;
import Model.Walkers;


public class Affichage extends JPanel implements Observer{
	/**
	 * Obligatoire mais des recherches à faire pour savoir pk
	 */
	private static final long serialVersionUID = 1L;
	private Keyboard keyListener;
	
	// Liste de tous les objets � afficher.
	//protected ArrayList<Walkers> _list_walkers_display = new ArrayList<Walkers>();
	//protected ArrayList<Birds> _list_birds_display = new ArrayList<Birds>();
	//protected ArrayList<ItemDisplay> _list_static_items_display = new ArrayList<ItemDisplay>();
	
	public Affichage() {
		super();
		this.setFocusable(true);
		this.requestFocus();
		
		keyListener = new Keyboard(Fenetre._list_birds.get(0));
		this.addKeyListener(keyListener);
	}
	
	

    public void paintComponent(Graphics g){	
		this.requestFocus();
		
    	for(int i = 0; i < Fenetre._list_static_items.size(); i++) {
			try {
				ItemDisplay stock = Fenetre._list_static_items.get(i);
				Image img = ImageIO.read(new File(stock._texture));
				g.drawImage(img, stock.getPosX(), stock.getPosY(), stock.getWidth(), stock.getHeight(), this);
			}catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
			}
		}
    	
		if(Fenetre._list_birds.isEmpty() == false){
			for(int i = 0; i < Fenetre._list_birds.size(); i++) {
				try {
			    	Birds stock = Fenetre._list_birds.get(i);
			    	
			    	
			    	if (!stock.isDestructed()) {
			    		Image img = ImageIO.read(new File(stock._texture));
			    		g.drawImage(img, stock.getPosX(), stock.getPosY(), stock.getWidth(), stock.getHeight(), this);
			    	}
			    	
			    		
				} catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
				}
		 }
		}
		
		if(Fenetre._list_walkers.isEmpty() == false) {
			for(int i = 0; i < Fenetre._list_walkers.size(); i++){
				try {
					Walkers stock = Fenetre._list_walkers.get(i);
					if (!stock.isDestructed()) {
						Image img = ImageIO.read(new File(stock._texture));
						g.drawImage(img, stock.getPosX(), stock.getPosY(), stock.getWidth(), stock.getHeight(), this);
					}
				}catch (IOException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
					}
			}
		}
		
		if(Fenetre.oeufEnCours != null) {
			Image img;
			try {
				img = ImageIO.read(new File(Fenetre.oeufEnCours._texture));
				g.drawImage(img, Fenetre.oeufEnCours.getPosX(), Fenetre.oeufEnCours.getPosY(), Fenetre.oeufEnCours.getWidth(), Fenetre.oeufEnCours.getHeight(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
    }               

    public void add_elem_birds(Birds item){
    	//_list_birds_display.add(item);
    }
    public void rm_elem_birds(int pos){
    	//_list_birds_display.remove(pos);
    }
    public void rm_elem_walkers(int pos){
    	//_list_walkers_display.remove(pos);
    }
    public ArrayList<Birds> get_elem_birds(){
    	//return _list_birds_display;
    	return null;
    }
    public ArrayList<Walkers> get_elem_walkers(){
    	//return _list_walkers_display;
    	return null;
    }
    public void add_elem_walker(Walkers item){
    	//_list_walkers_display.add(item);
    }
    public void add_elem_static(ItemDisplay item){
    	//_list_static_items_display.add(item);
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		
		this.removeKeyListener(keyListener);
    	keyListener = new Keyboard(Fenetre._list_birds.get(0));
		this.addKeyListener(keyListener);
		
	}


}