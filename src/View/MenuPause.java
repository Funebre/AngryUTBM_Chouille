package View;

import Model.Fenetre;
import Model.StateFen;

public class MenuPause extends ZoneAff {
	private static final long serialVersionUID = 1913382158330314915L;
	
	public MenuPause () {
		
	}

	@Override
	public void actionESC() {
		Fenetre.setState(StateFen.Level);
	}

	@Override
	public void actionSPACE() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionENTER() {
		// TODO Auto-generated method stub
		
	}
}
