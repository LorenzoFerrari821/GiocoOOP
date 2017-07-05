package gioco;
//Variabili globali
public class Global { 
	private static float livVolume=0;  //Volume standard
	private static int tastiSchermata=0;  //Movimento col mouse

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
