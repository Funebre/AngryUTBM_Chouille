package Model;


import java.io.File;


public class Lister {
	
	public void listerRepertoire(File repertoire, MenuNiveau menu) {
		String [] listefichiers;

		int i;
		listefichiers=repertoire.list();
		
		for(i=0;i<listefichiers.length;i++){
			
			int index = listefichiers[i].indexOf(".");
			String temp = listefichiers[i].substring(index);
			listefichiers[i] = listefichiers[i].replaceAll(temp, "");
			File entree = new File(listefichiers[i]);

			int i1 = i%5;
			int i2 = i/5;
			
			BoutonChargement temp1 = new BoutonChargement(entree.getName(), 125+i1*110, 10+i2*70, 80,50,i+1);
			temp1.setOpaque(true);
			menu.add(temp1);
		
		}

	}
}



