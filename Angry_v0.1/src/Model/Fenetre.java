package Model;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.Keyboard;

import View.Affichage;

public class Fenetre extends JFrame implements Constantes, Runnable{
	/**
	 * Obligatoire pour une fenêtre
	 */
	private static final long serialVersionUID = 1L;

	static JFrame fenetre = new JFrame();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuedition = new JMenu("Edition");
	private JMenuItem go = new JMenuItem("Lancer");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem stop = new JMenuItem("Quitter");
	private Thread game;
	private Thread position;
	private Bouton jouer = new Bouton("Jouer", 300, 100, 200, 50);
	private Bouton charger = new Bouton("Charger partie", 300, 175, 200, 50);
	private Bouton exit = new Bouton("Exit", 300, 250, 200, 50);
	private String path = "levels/";
	
	static public List<Walkers> _list_walkers = new LinkedList<Walkers>();
	static public List<Birds> _list_birds = new LinkedList<Birds>();
	static public List<ItemDisplay> _list_static_items = new LinkedList<ItemDisplay>();
	static public Eggs oeufEnCours = null;
	static public boolean anime = false;
	
	private Affichage image;
	private Menu menu = new Menu();
	private MenuNiveau menuchargement = new MenuNiveau();
	
		
	public static void main(String[] args){
		
		Fenetre fenster = new Fenetre();
	}
	
		
	class GoPlay implements Runnable {
		public void run() {
			setContentPane(image);
			setSize(800,600);
			setVisible(true);
			printObj();
		}
	}
	
	class GoPos implements Runnable {
		public void run() {
			calc_pos();
		}
	}
	
	public Fenetre() {
		DataBase level = new DataBase();
		level.loadLevel(1);
		
		image = new Affichage();
		
		this.menu.add(jouer);
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anime = true;
				game = new Thread(new GoPlay());
				game.start();
				position = new Thread(new GoPos());
				position.start();
			}
		});
		this.menu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		this.menu.add(charger);
		charger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Lister liste = new Lister();
				File rep = new File(path);
				liste.listerRepertoire(rep,menuchargement);
				setContentPane(menuchargement);
				setVisible(true);
			}
		});

		this.menuedition.add(go);
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anime = true;
				game = new Thread(new GoPlay());
				game.start();
			}
		});
		
		this.menuedition.add(pause);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agr0) {
				anime = false;
			}
		});
		
		this.menuedition.add(stop);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		this.menuBar.add(menuedition);
		
		for(int i = 0; i < _list_walkers.size(); i++)
			image.add_elem_walker(_list_walkers.get(i));
		
		for(int i = 0; i < _list_birds.size(); i++)
			image.add_elem_birds(_list_birds.get(i));
		
		for(int i = 0; i < _list_static_items.size(); i++)
			image.add_elem_static(_list_static_items.get(i));
		
		this.setTitle("Test d'affichage");
		this.setSize(800,400);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setJMenuBar(menuBar);
		this.setContentPane(menu);

		this.setVisible(true);
			
	}
	
	void printObj () {
		while(anime){
			//Suppression des oiseaux ayant collisionné
			if(_list_birds.isEmpty() == false){
				for(int i = 0; i < _list_birds.size(); i++) {
					if (_list_birds.get(i).isDestructed()) {
						_list_birds.remove(i);
						//image.rm_elem_birds(i);
					}
				}
			}
			
			this.repaint();
			
			try {
				Thread.sleep(_REFRESH_AFF);
			} catch (InterruptedException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
			}
		}
	}
	
	public void calc_pos () {
		while(anime){
			for (int i = 0; i < _list_walkers.size(); i++) {
				if (_list_walkers.get(i).collide_static() || this.outScreen(_list_walkers.get(i)))
					_list_walkers.get(i).switchArriereState();
				
				_list_walkers.get(i).move(this);
			}
			
						
			//Deplacement des oiseaux
			for(int i = 0; i < _list_birds.size(); i++){
				if (_list_birds.get(i).collide_static() || _list_birds.get(i).collide_dynamic() || this.outScreen(_list_birds.get(i)) || _list_birds.get(i).isDestructing())
					_list_birds.get(i).demol();
				_list_birds.get(i).move();
			}
			
			if(oeufEnCours != null) {
				int c = oeufEnCours.collide();
				if (c != -1){
					_list_walkers.remove(c);
					
					oeufEnCours = null;
				} else if (oeufEnCours.collide_static()){
					oeufEnCours = null;
				} else {
					oeufEnCours.moveY(1);
				}
			}
			
			
			try {
				Thread.sleep(_REFRESH_POS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean outScreen(ItemDisplay item) {
		if (item.getPosX() + item.getWidth() >= this.getWidth()) {
			return true;
		} else if (item.getPosX() <= 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public void run() {
		
	}        
}




