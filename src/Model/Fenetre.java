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

import Threading.Print_aff;
import Threading.Print_menu;
import Threading.Calc_pos;
import View.Affichage;

public class Fenetre extends JFrame implements Constantes {
	/**
	 * Obligatoire pour une fenêtre
	 */
	private static final long serialVersionUID = 1L;
	
	private static Thread _aff = null;
	private static Thread _calcPos = null;
	private static Thread _menu = null;
	public static Fenetre _fenster = null;
	public static boolean _working = false;
	
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
	static public boolean _anime = false;
	
	private Affichage image;
	private Menu menu = new Menu();
	private MenuNiveau menuchargement = new MenuNiveau();

	public static void main(String[] args){
		Fenetre._fenster = new Fenetre();
		
		if (Fenetre._fenster != null) {
			Fenetre._working = true;
			Fenetre._fenster.startThread();
		}
	}
	
	public void startThread () {
		while (_working) {
			if (_anime) {
				if (_aff == null && _calcPos == null) {
					_aff = new Thread(new Print_aff());
					_aff.start();
					_calcPos = new Thread(new Calc_pos());
					_calcPos.start();
					
					setContentPane(image);
					setSize(800,600);

					System.out.println("Cree le Thread affichage et calcul de position");
					setVisible(true);
				}
			} else {
				if (_menu == null) {
					_menu = new Thread(new Print_menu());
					setContentPane(menu);

					System.out.println("Cree le Thread menu");
					setVisible(true);
				}
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	/*
	class GoPlay implements Runnable {
		public void run() {
			printObj();
		}
	}
	
	class GoPos implements Runnable {
		public void run() {
			calc_pos();
		}
	}
	*/
	
	public Fenetre() {
		_working = true;
		DataBase level = new DataBase();
		level.loadLevel(1);
		
		image = new Affichage();
		
		this.menu.add(jouer);
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_anime = true;
				System.out.println("Bouton Jouer");
				/*game = new Thread(new GoPlay());
				game.start();
				position = new Thread(new GoPos());
				position.start();
				*/
			}
		});
		this.menu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.exit(0);
				Fenetre._working = false;
				System.out.println("Bouton Exit");
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
				_anime = true;
				/*game = new Thread(new GoPlay());
				game.start();*/
			}
		});
		
		this.menuedition.add(pause);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agr0) {
				_anime = false;
			}
		});
		
		this.menuedition.add(stop);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		this.menuBar.add(menuedition);
		
		this.setTitle("AngryUTBM");
		this.setSize(800,400);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setJMenuBar(menuBar);
		//this.setContentPane(menu);

		//this.setVisible(true);
			
	}
	
	public void printObj () {
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
	}
	
	public void calc_pos () {
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
	}

	private boolean outScreen(ItemDisplay item) {
		if (item.getPosX() + item.getWidth() >= this.getWidth()) {
			return true;
		} else if (item.getPosX() <= 0) {
			return true;
		}
		
		return false;
	}   
}
	



