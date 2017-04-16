package gioco;

import java.awt.EventQueue;
import java.io.File;

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
					File musica= new File("media/Musica.wav");
					//MenuMusic.PlaySound(musica);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
