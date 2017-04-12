package gioco;

import java.awt.EventQueue;

/**
 * Classe principale che dirige il funzionamento del gioco dall'esterno.
 * @author Werther&Lorenzo
 * @version 1.0
 */
public class Gioco {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMenuPrincipale frame = new GUIMenuPrincipale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
