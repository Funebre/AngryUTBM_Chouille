package Model;

import javax.swing.JPanel;

import View.MenuHigh;
import View.MenuPause;
import View.MenuPrinc;
import View.MenuLevel;
import View.MenuVict;

public class MenuSelector {
	private MenuPrinc _menuPrinc;
	private MenuPause _menuPause;
	private MenuHigh _menuHigh;
	private MenuLevel _menuLevel;
	private MenuVict _menuVict;
	
	public MenuSelector () {
		_menuPrinc = new MenuPrinc();
		_menuPause = new MenuPause();
		_menuHigh = new MenuHigh();
		_menuLevel = new MenuLevel();
		_menuVict = new MenuVict();
	}

	public JPanel selectMenu() {
		JPanel curMenu;
		
		switch (Fenetre._state) {
			case MenuPause :
				curMenu = _menuPause;
			break;
		
			case MenuHigh :
				curMenu = _menuHigh;
			break;
			
			case LevelLoad :
				curMenu = _menuLevel;
			break;
			
			case Victory :
				curMenu = _menuVict;
			break;
			
			default :
				curMenu = _menuPrinc;
			break;
		}
		
		return curMenu;
	}
}
