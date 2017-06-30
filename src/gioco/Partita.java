package gioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Partita {

	List<Giocatore> giocatore;
	private String situazioneDiGioco;
	List<Integer> ordineGioco;
	private int turnoCorrente; //0, 1, 2, 3

	private int tutorial;
	private int difficolta; //da 0 a 3
	private int mappa; //0 predefinita, 1 casuale, 2 estiva, 3 invernale
	
	private ThreadCicloPartita threadCicloPartita;
	private GUIPartita guiPartita;
	
	Partita(String situazioneDiGioco, String nomeGiocatore, int tutorial, int difficolta, int mappa, int civilta, GUIPartita guiPartita)
	{
		this.guiPartita = guiPartita;
		
		if(situazioneDiGioco != null) //non è una nuova partita
		{
			this.situazioneDiGioco = situazioneDiGioco;
			this.tutorial = tutorial;
			this.difficolta = difficolta;
			this.mappa = mappa;
		}
		else //E' una nuova partita, bisogna crearla
		{
			situazioneDiGioco = "setup " + nomeGiocatore + " " + Integer.toString(tutorial) + " " + Integer.toString(difficolta) + " "
					+ Integer.toString(mappa) + " " + Integer.toString(civilta);

			this.tutorial = tutorial;
			this.difficolta = difficolta;
			this.mappa = mappa;
			creaPartita(nomeGiocatore, civilta);
		}
		
	}
	
	public void creaPartita(String nomeGiocatore, int civilta)
	{
		giocatore = new ArrayList<Giocatore>();
		
		for(int i = 0; i < 4; i++)
		{
			if(civilta == i) //giocatore umano
				giocatore.add(i, new Giocatore(civilta, "utente1", nomeGiocatore));
			else //cpu
				giocatore.add(i, new Giocatore(i, "cpu", "CPU"));
			
			giocatore.get(i).setOro(20);
			giocatore.get(i).setMateriali(20);
			giocatore.get(i).setPuntiRicerca(5);
		}
		
		//Randomizzo ordine di gioco
		ordineGioco = new ArrayList<Integer>();
		ordineGioco.add(0);
		ordineGioco.add(1);
		ordineGioco.add(2);
		ordineGioco.add(3);
		
		Collections.shuffle(ordineGioco);

		avviaPartita();
	}

	/*
	 * Metodo che scandisce i turni e gestisce il turno con l'ia della cpu (se tocca alla cpu)
	 * altrimenti lascia giocare il giocatore finchè non passa il turno
	 */
	public void avviaPartita()
	{
		threadCicloPartita = new ThreadCicloPartita(this);
		threadCicloPartita.start();
	}
	
	public void aggiornaDatiGUI()
	{
		guiPartita.aggiornaDatiGUI();
	}

	public List<Giocatore> getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(List<Giocatore> giocatore) {
		this.giocatore = giocatore;
	}

	public int getTurnoCorrente() {
		return turnoCorrente;
	}
	public ThreadCicloPartita getThreadCicloPartita() {
		return threadCicloPartita;
	}

	public void setThreadCicloPartita(ThreadCicloPartita threadCicloPartita) {
		this.threadCicloPartita = threadCicloPartita;
	}

	public List<Integer> getOrdineGioco() {
		return ordineGioco;
	}

	public GUIPartita getGuiPartita() {
		return guiPartita;
	}

	public void setGuiPartita(GUIPartita guiPartita) {
		this.guiPartita = guiPartita;
	}

	public void setOrdineGioco(List<Integer> ordineGioco) {
		this.ordineGioco = ordineGioco;
	}

	public void setTurnoCorrente(int turnoCorrente) {
		this.turnoCorrente = turnoCorrente;
	}
}
