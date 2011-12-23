package Model;

import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import java.io.*;

public class DataBase {
	protected NodeList _item_in_file;
	
	public DataBase () {
	}
	
	protected void loadXml (String path) {
		try{
			// création d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			
			// création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			// lecture du contenu d'un fichier XML avec DOM
			File xml = new File(path);
			Document document = constructeur.parse(xml);
			this._item_in_file = document.getDocumentElement().getElementsByTagName("ItemDisplay");
		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
		}catch(SAXException se){
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}
		
	}
	
	public boolean loadLevel (int number) {
		boolean result = true;
		
		loadXml("levels/level" + number + ".xml");
		System.out.println("Nombre d'item � charger : " + _item_in_file.getLength());
		for (int i = 0; i < _item_in_file.getLength(); ++i) {
			NodeList cur_item = _item_in_file.item(i).getChildNodes();
			int posx = Integer.parseInt(cur_item.item(3).getTextContent());
			int posy = Integer.parseInt(cur_item.item(5).getTextContent());
			int width = Integer.parseInt(((cur_item.item(9).getTextContent().isEmpty()) ? "0" : cur_item.item(9).getTextContent()));
			int height = Integer.parseInt(((cur_item.item(11).getTextContent().isEmpty()) ? "0" : cur_item.item(11).getTextContent()));
			String img = "img/" + cur_item.item(7).getTextContent();
			switch (Integer.parseInt(cur_item.item(1).getTextContent())) {
				case 1 :
					Fenetre._list_static_items.add(new Obstacle(posx, posy, img, width, height));
				break;
				
				case 2 :
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
		
		return result;
	}
	
	public boolean loadSave (int number) {
		boolean result = true;
		
		loadXml("saved_game/level" + number + ".xml");
		System.out.println("Nombre d'item � charger : " + _item_in_file.getLength());
		for (int i = 0; i < _item_in_file.getLength(); ++i) {
			NodeList cur_item = _item_in_file.item(i).getChildNodes();
			int posx = Integer.parseInt(cur_item.item(3).getTextContent());
			int posy = Integer.parseInt(cur_item.item(5).getTextContent());
			int width = Integer.parseInt(((cur_item.item(9).getTextContent().isEmpty()) ? "0" : cur_item.item(9).getTextContent()));
			int height = Integer.parseInt(((cur_item.item(11).getTextContent().isEmpty()) ? "0" : cur_item.item(11).getTextContent()));
			String img = "img/" + cur_item.item(7).getTextContent();
			switch (Integer.parseInt(cur_item.item(1).getTextContent())) {
				case 1 :
					Fenetre._list_static_items.add(new Obstacle(posx, posy, img, width, height));
				break;
				
				case 2 :
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
				
				case 9 :
					Fenetre.oeufEnCours = new Small_egg(posx, posy);
				break;
				
				case 10 :
					Fenetre.oeufEnCours = new Normal_egg(posx, posy);
				break;
				
				case 11 :
					Fenetre.oeufEnCours = new Big_egg(posx, posy);
				break;
				
				default :
					System.out.println("Erreur de chargement de l'item " + i);
					result = false;
				break;
			}
		}
		
		return result;
	}
	
	public boolean savelevel () {
		return false;
	}
}
