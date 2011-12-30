package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
	public static StateFen _state = StateFen.MenuPrinc;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuedition = new JMenu("Edition");
	private JMenuItem go = new JMenuItem("Lancer");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem stop = new JMenuItem("Quitter");
	
	static public List<Walkers> _list_walkers = new LinkedList<Walkers>();
	static public List<Birds> _list_birds = new LinkedList<Birds>();
	static public List<ItemDisplay> _list_static_items = new LinkedList<ItemDisplay>();
	static public Eggs oeufEnCours = null;
	//static public HighScore _score;
	static public DataBase _level = new DataBase();
	
	private Affichage _panelAff;
	private MenuSelector _menuSelect;
	
	public static void main(String[] args){
		Fenetre._fenster = new Fenetre();
		
		if (Fenetre._fenster != null) {
			Fenetre._working = true;
			Fenetre._fenster.startThreads();
		}
	}

	public static void setState(StateFen newState) {
		_state = newState;
	}

	public Fenetre() {
		_working = true;
		
		_panelAff = new Affichage();
		_menuSelect = new MenuSelector();

		menuedition.add(go);
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fenetre._state = StateFen.Level;
			}
		});
		
		menuedition.add(pause);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent agr0) {
				Fenetre._state = StateFen.MenuPause;
			}
		});
		
		menuedition.add(stop);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_working = false;
			}
		});
		
		menuBar.add(menuedition);
		
		setTitle("AngryUTBM");
		setSize(800,600);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setJMenuBar(menuBar);
	}
	
	public boolean outScreen(ItemDisplay item) {
		if (item.getPosX() + item.getWidth() >= this.getWidth()) {
			return true;
		} else if (item.getPosX() <= 0) {
			return true;
		}
		
		return false;
	}
	
	public void changeBird (Birds newBird) {
		_panelAff.changeBird(newBird);
	}

	public void updateMenu() {
		setPanel(_menuSelect.selectMenu());
	}
	
	private void startThreads () {
		while (_working) {
			if (_state == StateFen.Level) {
				if ((_threadAff == null && _threadCalcPos == null) || _threadAff.getState().toString() == "TERMINATED") {
					// Cree les threads d'affichage du niveau et de calcul des positions
					_threadAff = new Thread(new Print_aff());
					_threadCalcPos = new Thread(new Calc_pos());
					
					// Demarre les threads
					_threadAff.start();
					_threadCalcPos.start();
					
					// Affiche le panel du niveau dans la fenetre
					setPanel(_panelAff);
					
					System.out.println("Cree le Thread affichage et calcul de position");
				}
			} else {
				if (_threadMenu == null || _threadMenu.getState().toString() == "TERMINATED") {
					// Cree le thread pour le menu
					_threadMenu = new Thread(new Print_menu());
					
					// Demarre le thread
					_threadMenu.start();
					
					// Affiche le panel du menu
					setPanel(_menuSelect.selectMenu());

					System.out.println("Cree le Thread menu");
				}
			}
			
			// Verifie l'etat des threads toutes les demi-secondes
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Quitte le jeu");
		System.exit(0);
	}
	
	private void setPanel(JPanel curPanel) {
		setContentPane(curPanel);
		setVisible(true);
	}
}