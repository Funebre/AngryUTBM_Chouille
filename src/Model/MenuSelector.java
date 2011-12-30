package Model;

import View.MenuFail;
import View.MenuHigh;
import View.MenuPause;
import View.MenuPrinc;
import View.MenuLevel;
import View.MenuVict;
import View.ZoneAff;

public class MenuSelector {
	private MenuPrinc _menuPrinc;
	private MenuPause _menuPause;
	private MenuHigh _menuHigh;
	private MenuLevel _menuLevel;
	private MenuVict _menuVict;
	private MenuFail _menuFail;
	
	public MenuSelector () {
		_menuPrinc = new MenuPrinc();
		_menuPause = new MenuPause();
		_menuHigh = new MenuHigh();
		_menuLevel = new MenuLevel();
		_menuVict = new MenuVict();
		_menuFail = new MenuFail();
	}

	public ZoneAff selectMenu() {
		ZoneAff curMenu;
		
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
				Fenetre._level.saveHighScore();
			break;
			
			case Fail :
				curMenu = _menuFail;
			break;
			
			default :
				curMenu = _menuPrinc;
			break;
		}
		
		return curMenu;
	}
}
