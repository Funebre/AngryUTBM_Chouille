package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Threading.Print_aff;
import Threading.Print_menu;
import Threading.Calc_pos;
import View.Affichage;

public class Fenetre extends JFrame implements Constantes {
	/**
	 * Obligatoire pour une fenêtre
	 */
	private static final long serialVersionUID = 1L;
	
	private static Thread _threadAff = null;
	private static Thread _threadCalcPos = null;
	private static Thread _threadMenu = null;
	public static Fenetre _fenster = null;
	public static boolean _working = false;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuedition = new JMenu("Edition");
	private JMenuItem go = new JMenuItem("Lancer");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem stop = new JMenuItem("Quitter");
	private Bouton jouer = new Bouton("Jouer", 300, 100, 200, 50);
	private Bouton charger = new Bouton("Charger partie", 300, 175, 200, 50);
	private Bouton exit = new Bouton("Exit", 300, 250, 200, 50);
	private String path = "levels/";
	
	static public List<Walkers> _list_walkers = new LinkedList<Walkers>();
	static public List<Birds> _list_birds = new LinkedList<Birds>();
	static public List<ItemDisplay> _list_static_items = new LinkedList<ItemDisplay>();
	static public Eggs oeufEnCours = null;
	static public boolean _anime = false;
	
	private Affichage _panelAff;
	private Menu _panelMenu = new Menu();
	private MenuNiveau _panelNiveau = new MenuNiveau();

	public static void main(String[] args){
		Fenetre._fenster = new Fenetre();
		
		if (Fenetre._fenster != null) {
			Fenetre._working = true;
			Fenetre._fenster.startThreads();
		}
	}
	
	public void startThreads () {
		while (_working) {
			if (_anime) {
				if (_threadAff == null && _threadCalcPos == null) {
					// Crée les threads d'affichage du niveau et de calcul des positions
					_threadAff = new Thread(new Print_aff());
					_threadCalcPos = new Thread(new Calc_pos());
					
					// Démarre les threads
					_threadAff.start();
					_threadCalcPos.start();
					
					// Affiche le panel du niveau dans la fenêtre et fixe la taille de la fenêtre
					setContentPane(_panelAff);
					setVisible(true);
					setSize(800,600);
					
					System.out.println("Cree le Thread affichage et calcul de position");
				}
			} else {
				if (_threadMenu == null) {
					// Crée le thread pour le menu
					_threadMenu = new Thread(new Print_menu());
					
					// Démarre le thread
					_threadMenu.start();
					
					// Affiche le panel du menu
					setContentPane(_panelMenu);
					setVisible(true);

					System.out.println("Cree le Thread menu");
				}
			}
			
			// Vérifie l'état des threads toutes les demi-secondes
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Quitte le jeu");
		System.exit(0);
	}
	
	public Fenetre() {
		_working = true;
		DataBase level = new DataBase();
		level.loadLevel(1);
		
		_panelAff = new Affichage();
		
		this._panelMenu.add(jouer);
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_anime = true;
				System.out.println("Bouton Jouer");
			}
		});
		this._panelMenu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fenetre._working = false;
				System.out.println("Bouton Exit");
			}
		});
		
		this._panelMenu.add(charger);
		charger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Lister liste = new Lister();
				File rep = new File(path);
				liste.listerRepertoire(rep,_panelNiveau);
				setContentPane(_panelNiveau);
				setVisible(true);
			}
		});

		this.menuedition.add(go);
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_anime = true;
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
				_working = false;
			}
		});
		
		
		this.menuBar.add(menuedition);
		
		this.setTitle("AngryUTBM");
		this.setSize(800,400);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		this.setJMenuBar(menuBar);
			
	}
	
	public boolean outScreen(ItemDisplay item) {
		if (item.getPosX() + item.getWidth() >= this.getWidth()) {
			return true;
		} else if (item.getPosX() <= 0) {
			return true;
		}
		
		return false;
	}   
}
	



