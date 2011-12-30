package Model;

import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import java.io.*;
import java.util.LinkedList;

public class DataBase {
	protected NodeList _item_in_file;
	protected NodeList _currentScore;
	protected int _currentLvl;
	protected int _maxLvl;
	
	public DataBase () {
		try{
			// creation d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			
			// creation d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			// lecture du contenu d'un fichier XML avec DOM
			File xml_score = new File("config/highscore.xml");
			if (xml_score.exists()) {
				Document doc_score = constructeur.parse(xml_score);
			
				_currentScore = doc_score.getDocumentElement().getElementsByTagName("Score");
			} else {
				_currentScore = null;
			}
			
			InputStream ips = new FileInputStream("config/lvl_max.config"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			_maxLvl = Integer.parseInt(br.readLine());
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch(ParserConfigurationException pce) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel de fabrique.newDocumentBuilder();");
		} catch(SAXException se) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel de construteur.parse(xml)");
		} catch(IOException ioe) {
			System.out.println("Erreur d'entree/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}
	}
	
	public boolean loadLevel (int number) {
		_currentLvl = number;
		boolean result = true;
		String img = null;
		int width = 0;
		int height = 0;
		
		clearLvl();
		
		loadXml("levels/level" + number + ".xml");
		System.out.println("Nombre d'item a charger : " + _item_in_file.getLength());
		
		for (int i = 0; i < _item_in_file.getLength(); ++i) {
			NodeList cur_item = _item_in_file.item(i).getChildNodes();
			int posx = Integer.parseInt(cur_item.item(3).getTextContent());
			int posy = Integer.parseInt(cur_item.item(5).getTextContent());
			switch (Integer.parseInt(cur_item.item(1).getTextContent())) {
				case 1 :
					width = Integer.parseInt(((cur_item.item(7).getTextContent().isEmpty()) ? "0" : cur_item.item(7).getTextContent()));
					height = Integer.parseInt(((cur_item.item(9).getTextContent().isEmpty()) ? "0" : cur_item.item(9).getTextContent()));
					img = "img/" + cur_item.item(11).getTextContent();
					
					Fenetre._list_static_items.add(new Obstacle(posx, posy, img, width, height));
				break;
				
				case 2 :
					width = Integer.parseInt(((cur_item.item(7).getTextContent().isEmpty()) ? "0" : cur_item.item(7).getTextContent()));
					height = Integer.parseInt(((cur_item.item(9).getTextContent().isEmpty()) ? "0" : cur_item.item(9).getTextContent()));
					img = "img/" + cur_item.item(11).getTextContent();
					
					Fenetre._list_static_items.add(new Background_obj(posx, posy, img, width, height));
				break;
				
				case 3 :
					Fenetre._list_walkers.add(new Normal_walker(posx, posy));
				break;
				
				case 4 :
					Fenetre._list_walkers.add(new Slim_walker(posx, posy));
				break;
				
				case 5 :
					Fenetre._list_walkers.add(new Fat_walker(posx, posy));
				break;
				
				case 6 :
					Fenetre._list_birds.add(new Colibri(posx, posy));
				break;
				
				case 7 :
					Fenetre._list_birds.add(new Pigeon(posx, posy));
				break;
				
				case 8 :
					Fenetre._list_birds.add(new Moineau(posx, posy));
				break;
				
				default :
					System.out.println("Erreur de chargement de l'item " + i);
					result = false;
				break;
			}
		}
		
		//Fenetre._score.setHigh(loadHighScore(_currentLvl));
		
		return result;
	}
	
	public boolean loadSave (int number) {
		_currentLvl = number;
		boolean result = true;
		
		int posx = 0;
		int posy = 0;
		String img = null;
		int width = 0;
		int height = 0;
		boolean switched;
		int stat = 0;
		int takeoff = 0;
		
		clearLvl();
		
		loadXml("saved_game/level" + number + ".xml");
		System.out.println("Nombre d'item a charger : " + _item_in_file.getLength());
		
		for (int i = 0; i < _item_in_file.getLength(); ++i) {
			NodeList cur_item = _item_in_file.item(i).getChildNodes();
			
			posx = Integer.parseInt(cur_item.item(3).getTextContent());
			posy = Integer.parseInt(cur_item.item(5).getTextContent());
			
			switch (Integer.parseInt(cur_item.item(1).getTextContent())) {
				case 1 :
					img = "img/" + cur_item.item(11).getTextContent();
					width = Integer.parseInt(((cur_item.item(7).getTextContent().isEmpty()) ? "0" : cur_item.item(7).getTextContent()));
					height = Integer.parseInt(((cur_item.item(9).getTextContent().isEmpty()) ? "0" : cur_item.item(9).getTextContent()));
					
					Fenetre._list_static_items.add(new Obstacle(posx, posy, img, width, height));
				break;
				
				case 2 :
					img = "img/" + cur_item.item(11).getTextContent();
					width = Integer.parseInt(((cur_item.item(7).getTextContent().isEmpty()) ? "0" : cur_item.item(7).getTextContent()));
					height = Integer.parseInt(((cur_item.item(9).getTextContent().isEmpty()) ? "0" : cur_item.item(9).getTextContent()));
					
					Fenetre._list_static_items.add(new Background_obj(posx, posy, img, width, height));
				break;
				
				case 3 :
					switched = Boolean.parseBoolean(cur_item.item(7).getTextContent());
					Fenetre._list_walkers.add(new Normal_walker(posx, posy, switched));
				break;
				
				case 4 :
					switched = Boolean.parseBoolean(cur_item.item(7).getTextContent());
					Fenetre._list_walkers.add(new Slim_walker(posx, posy, switched));
				break;
				
				case 5 :
					switched = Boolean.parseBoolean(cur_item.item(7).getTextContent());
					Fenetre._list_walkers.add(new Fat_walker(posx, posy, switched));
				break;
				
				case 6 :
					// width stock le nombre d'oeuf restant
					width = Integer.parseInt(cur_item.item(7).getTextContent());
					// height stock le temps de vol restant
					height = Integer.parseInt(cur_item.item(9).getTextContent());
					stat = Integer.parseInt(cur_item.item(11).getTextContent());
					takeoff = Integer.parseInt(cur_item.item(13).getTextContent());
					// switched contient le fait que l'oiseau bouge ou non
					switched = Boolean.parseBoolean(cur_item.item(15).getTextContent());
					
					Fenetre._list_birds.add(new Colibri(posx, posy, width, height, stat, takeoff, switched));
				break;
				
				case 7 :
					// width stock le nombre d'oeuf restant
					width = Integer.parseInt(cur_item.item(7).getTextContent());
					// height stock le temps de vol restant
					height = Integer.parseInt(cur_item.item(9).getTextContent());
					stat = Integer.parseInt(cur_item.item(11).getTextContent());
					takeoff = Integer.parseInt(cur_item.item(13).getTextContent());
					// switched contient le fait que l'oiseau bouge ou non
					switched = Boolean.parseBoolean(cur_item.item(15).getTextContent());
					
					Fenetre._list_birds.add(new Pigeon(posx, posy, width, height, stat, takeoff, switched));
				break;
				
				case 8 :
					// width stock le nombre d'oeuf restant
					width = Integer.parseInt(cur_item.item(7).getTextContent());
					// height stock le temps de vol restant
					height = Integer.parseInt(cur_item.item(9).getTextContent());
					stat = Integer.parseInt(cur_item.item(11).getTextContent());
					takeoff = Integer.parseInt(cur_item.item(13).getTextContent());
					// switched contient le fait que l'oiseau bouge ou non
					switched = Boolean.parseBoolean(cur_item.item(15).getTextContent());
					
					Fenetre._list_birds.add(new Moineau(posx, posy, width, height, stat, takeoff, switched));
				break;
				
				case 9 :
					Fenetre.oeufEnCours = new Small_egg(posx, posy);
				break;
				
				case 10 :
					Fenetre.oeufEnCours = new Normal_egg(posx, posy);
				break;
				
				case 11 :
					Fenetre.oeufEnCours = new Big_egg(posx, posy);
				break;
				
				case 12 :
					//Fenetre._score.setCurrent(posx);
				break;
				
				default :
					System.out.println("Erreur de chargement de l'item " + i);
					result = false;
				break;
			}
		}

		//Fenetre._score.setHigh(loadHighScore(_currentLvl));
		// On supprime la sauvegarde maintenant qu'on a charge le niveau
		File save = new File("saved_game/level" + number + ".xml");
		save.delete();
		
		return result;
	}
	
	public boolean savelevel () {
		String NomFichier = "saved_game/level" + _currentLvl + ".xml";
		
		try {
			PrintWriter out  = new PrintWriter(new BufferedWriter(new FileWriter(NomFichier)));
			
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<game>");
			
			for (int i = 0; i < Fenetre._list_static_items.size(); ++i) {
				out.println("\t<ItemDisplay>");
				out.println("\t\t<type>" + Fenetre._list_static_items.get(i).getType() + "</type>");
				out.println("\t\t<posx>" + Fenetre._list_static_items.get(i).getPosX() + "</posx>");
				out.println("\t\t<posy>" + Fenetre._list_static_items.get(i).getPosY() + "</posy>");
				out.println("\t\t<witdh>" + Fenetre._list_static_items.get(i).getWidth() + "</width>");
				out.println("\t\t<height>" + Fenetre._list_static_items.get(i).getHeight() + "</height>");
				out.println("\t\t<img>" + Fenetre._list_static_items.get(i).getTexture() + "</img>");
				out.println("\t</ItemDisplay>");
			}
			
			for (int i = 0; i < Fenetre._list_walkers.size(); ++i) {
				out.println("\t<ItemDisplay>");
				out.println("\t\t<type>" + Fenetre._list_walkers.get(i).getType() + "</type>");
				out.println("\t\t<posx>" + Fenetre._list_walkers.get(i).getPosX() + "</posx>");
				out.println("\t\t<posy>" + Fenetre._list_walkers.get(i).getPosY() + "</posy>");
				out.println("\t\t<switched>" + Fenetre._list_walkers.get(i).getSwitched() + "</switched>");
				out.println("\t</ItemDisplay>");
			}
			
			for (int i = 0; i < Fenetre._list_birds.size(); ++i) {
				out.println("\t<ItemDisplay>");
				out.println("\t\t<type>" + Fenetre._list_birds.get(i).getType() + "</type>");
				out.println("\t\t<posx>" + Fenetre._list_birds.get(i).getPosX() + "</posx>");
				out.println("\t\t<posy>" + Fenetre._list_birds.get(i).getPosY() + "</posy>");
				out.println("\t\t<egg>" + Fenetre._list_birds.get(i).getEggLeft() + "</egg>");
				out.println("\t\t<flight>" + Fenetre._list_birds.get(i).getFlightTime() + "</flight>");
				out.println("\t\t<stat>" + Fenetre._list_birds.get(i).getStatTime() + "</stat>");
				out.println("\t\t<takeoff>" + Fenetre._list_birds.get(i).getTakeOff() + "</takeoff>");
				out.println("\t\t<moving>" + Fenetre._list_birds.get(i).getMoving() + "</moving>");
				out.println("\t</ItemDisplay>");
			}
			
			if (Fenetre.oeufEnCours != null) {
				out.println("\t<ItemDisplay>");
				out.println("\t\t<type>" + Fenetre.oeufEnCours.getType() + "</type>");
				out.println("\t\t<posx>" + Fenetre.oeufEnCours.getPosX() + "</posx>");
				out.println("\t\t<posy>" + Fenetre.oeufEnCours.getPosY() + "</posy>");
				out.println("\t</ItemDisplay>");
			}
			
			//out.println("\t<ItemDisplay>");
			//out.println("\t\t<type>12</type>");
			//out.println("\t\t<currentScore>" + Fenetre._score.getCurrent() + "</currentScore>");
			//out.println("\t\t<notuse>0</notuse>");
			//out.println("\t</ItemDisplay>");
			
			out.println("</game>");
			out.close();
			
			System.out.println("Sauvegarde le niveau a l'instant t");
			
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean saveExist(int lvl) {
		File fichier = new File("saved_game/level" + lvl + ".xml");
		return fichier.exists();
	}
	
	public boolean nextLvlExist() {
		File fichier = new File("levels/level" + (_currentLvl + 1) + ".xml");
		return fichier.exists();
	}

	/* Charge le lvl suivant
	* @param void
	* @return void
	*/
	public void loadNextLvl() {
		clearLvl();
		
		loadLevel (_currentLvl + 1);
	}
	
	/* Donne le highscore d'un level
	* @param int lvl
	* @return int highscore
	*/
	public int loadHighScore(int lvl) {
		int highScore = 0;
		int curLvl;
		NodeList cur_item;
		
		for (int i = 0; i < _currentScore.getLength(); ++i) {
			cur_item = _currentScore.item(i).getChildNodes();
			curLvl = Integer.parseInt(cur_item.item(1).getTextContent());
			
			if (curLvl == lvl) {
				highScore = Integer.parseInt(cur_item.item(3).getTextContent());
				i = _currentScore.getLength();
			}
		}
		
		return highScore;
	}
	
	public void saveHighScore() {
		updateMaxLvl();
		
		/*if (Fenetre._score.getHigh() < Fenetre._score.getCurrent()) {
			String NomFichier = "config/highscore.xml";
			int curLvl;
			int curHigh;
			NodeList cur_item;
			
			try {
				PrintWriter out  = new PrintWriter(new BufferedWriter(new FileWriter(NomFichier)));
				
				out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				out.println("<highscore>");
				
				for (int i = 0; i < _currentScore.getLength(); ++i) {
					cur_item = _currentScore.item(i).getChildNodes();
					curLvl = Integer.parseInt(cur_item.item(1).getTextContent());
					curHigh = Integer.parseInt(cur_item.item(3).getTextContent());
					if (curLvl == _currentLvl) {
						out.println("\t<Score>");
						out.println("\t\t<lvl>" + curLvl + "</lvl>");
						out.println("\t\t<highscore>" + Fenetre._score.getCurrent() + "</highscore>");
						out.println("\t</Score>");
					} else {
						out.println("\t<Score>");
						out.println("\t\t<lvl>" + curLvl + "</lvl>");
						out.println("\t\t<highscore>" + curHigh + "</highscore>");
						out.println("\t</Score>");
					}
				}
				
				out.println("</highscore>");
				out.close();
			} catch(Exception e){
				e.printStackTrace();
			}
			
			// Mise � jour de l'objet contenant les highscore
			try{
				// creation d'une fabrique de documents
				DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
				
				// creation d'un constructeur de documents
				DocumentBuilder constructeur = fabrique.newDocumentBuilder();
				
				// lecture du contenu d'un fichier XML avec DOM
				File xml_score = new File("config/highscore.xml");
				Document doc_score = constructeur.parse(xml_score);
				
				_currentScore = doc_score.getDocumentElement().getElementsByTagName("Score");
			} catch(ParserConfigurationException pce) {
				System.out.println("Erreur de configuration du parseur DOM");
				System.out.println("lors de l'appel de fabrique.newDocumentBuilder();");
			} catch(SAXException se) {
				System.out.println("Erreur lors du parsing du document");
				System.out.println("lors de l'appel de construteur.parse(xml)");
			} catch(IOException ioe) {
				System.out.println("Erreur d'entree/sortie");
				System.out.println("lors de l'appel à construteur.parse(xml)");
			}
		}*/
	}
	
	private void loadXml (String path) {
		try{
			// creation d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			
			// creation d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			// lecture du contenu d'un fichier XML avec DOM
			File xml_lvl = new File(path);
			Document doc_lvl = constructeur.parse(xml_lvl);
			
			_item_in_file = doc_lvl.getDocumentElement().getElementsByTagName("ItemDisplay");
		} catch(ParserConfigurationException pce) {
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel de fabrique.newDocumentBuilder();");
		} catch(SAXException se) {
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel de construteur.parse(xml)");
		} catch(IOException ioe) {
			System.out.println("Erreur d'entree/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}
	}
	
	public int getMaxLvl() {
		return _maxLvl;
	}
	
	private void clearLvl() {
		Fenetre._list_walkers = new LinkedList<Walkers>();
		Fenetre._list_birds = new LinkedList<Birds>();
		Fenetre._list_static_items = new LinkedList<ItemDisplay>();
		Fenetre.oeufEnCours = null;
	}
	
	private void updateMaxLvl() {
		if (_currentLvl == _maxLvl) {
			++_maxLvl;
			String NomFichier = "config/lvl_max.config";
			
			try {
				PrintWriter out  = new PrintWriter(new BufferedWriter(new FileWriter(NomFichier)));
				
				out.println(_maxLvl);
				
				out.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
