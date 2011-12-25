package View;

import javax.swing.JPanel;

abstract public class ZoneAff extends JPanel {
	private static final long serialVersionUID = 7819125376046089685L;
	
	abstract public void actionESC();
	abstract public void actionSPACE();
	abstract public void actionENTER();
	
	public ZoneAff() {
		super();
	}
}
