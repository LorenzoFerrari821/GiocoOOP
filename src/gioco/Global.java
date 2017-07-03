package gioco;
//Variabili globali
public class Global { 
	private static float livVolume=0;  //Volume standard

	public static float getLivVolume() {
		return livVolume;
	}

	public static void setLivVolume(float l) {
		livVolume = l;
		Music.controlVolume();
	}
}
