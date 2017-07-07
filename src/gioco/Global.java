package gioco;
//Variabili globali

import java.util.Locale;
import java.util.ResourceBundle;

public class Global { 
	private static float livVolume=0;  //Volume standard
	private static int tastiSchermata=0;  //Movimento col mouse
	private static Locale[] supportedLocales = {
			Locale.ITALIAN,
			Locale.ENGLISH,
	};
	private static ResourceBundle labels=ResourceBundle.getBundle("Linguaggi/LabelsBundle",Locale.getDefault());    //Leggi le stringhe dal file a seconda della lingua dell'utente
	

	public static String getLabels(String stringa) {  //Ritorna la stringa associata alla chiave richiesta
		return labels.getString(stringa);
	}

	public static int getTastiSchermata() {
		return tastiSchermata;
	}

	public static void setTastiSchermata(int opzione) {
		tastiSchermata = opzione;
	}

	public static float getLivVolume() {
		return livVolume;
	}

	public static void setLivVolume(float l) {
		livVolume = l;
		Music.controlVolume();
	}
}
