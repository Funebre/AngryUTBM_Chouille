package View;

import Model.Fenetre;
import Model.StateFen;

public class MenuHigh extends ZoneAff {
	private static final long serialVersionUID = -8417133885786590651L;
	
	@Override
	public void actionESC() {
		Fenetre._state = StateFen.MenuPrinc;
	}

	@Override
	public void actionSPACE() {
		
	}

	@Override
	public void actionENTER() {
		
	}
	
}
