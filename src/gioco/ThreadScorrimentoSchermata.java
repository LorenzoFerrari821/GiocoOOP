package gioco;

/**
 * Thread che si occupa dello scorrimento dello scenario (attuato con mouse, WASD o frecce direzionale in base alle impostazioni scelte)
 * @author Werther e Lorenzo
 *
 */
public class ThreadScorrimentoSchermata extends Thread {

	private GUIPartita guiPartita;
	private int direzione;
	private volatile boolean scorri;

	/**
	 * Inizializza le variabili di riferimento e indica la direzione verso cui scorrere lo scenario
	 * @param guiPartita riferimento a GUIPartita
	 * @param direzione Direzione di movimento scenario
	 */
	ThreadScorrimentoSchermata(GUIPartita guiPartita, int direzione) { //1 su, 2 dx, 3 giu, 4 sx
		this.guiPartita = guiPartita;
		this.direzione = direzione;
		scorri = true;
	}
	
	/**
	 * Avvia il Thread e comincia a muovere lo scenario finchè il tasto di riferimento rimane premuto (finchè scorri è true)
	 */
	public void run() {
		while(scorri) {
			switch(direzione) {
				case 1: //scorriamo la schermata verso l'alto
					if(guiPartita.getPosSchermataY() > 0) {
						guiPartita.setPosSchermataY(guiPartita.getPosSchermataY()-1);
						guiPartita.aggiornaSchermata();
					}
					else
						scorri = false;
					break;
				case 2: //scorriamo la schermata a dx
					if(guiPartita.getPosSchermataX() < 62) { //scorriamo la schermata verso destra
						guiPartita.setPosSchermataX(guiPartita.getPosSchermataX()+1);
						guiPartita.aggiornaSchermata();
					}
					else
						scorri = false;
					break;
				case 3: //scorriamo la schermata in giu
					if(guiPartita.getPosSchermataY() < 32) {  //se vero allora possiamo scorrere la schermata verso il basso
						guiPartita.setPosSchermataY(guiPartita.getPosSchermataY()+1);
						guiPartita.aggiornaSchermata();
					}
					else
						scorri = false;
					break;
				case 4: //scorriamo la schermata a sx
					if(guiPartita.getPosSchermataX() > 0) { //scorriamo la schermata verso sinistra
						guiPartita.setPosSchermataX(guiPartita.getPosSchermataX()-1);
						guiPartita.aggiornaSchermata();
					}
					else
						scorri = false;
					break;
			}
			if(scorri)
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {}
		}
	}
	
	public boolean isScorri() {
		return scorri;
	}

	public void setScorri(boolean scorri) {
		this.scorri = scorri;
	}
}
