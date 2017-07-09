package gioco;

import java.awt.EventQueue;
import java.io.File;
import java.util.Locale;
import java.io.*;
import sun.audio.*;

/**
 * Contiene solo il main per lanciare la GUI Menu principale
 * @author Werther e Lorenzo
 * @version 1.0
 */
public class Gioco {
	
	
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
		
				try {	
					GUIMenuPrincipale frame = new GUIMenuPrincipale();
					File musica= new File("media/MusicaMenu.wav");
					Music.playSound(musica);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
