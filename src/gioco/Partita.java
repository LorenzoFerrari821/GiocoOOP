package gioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Partita {

	List<Giocatore> giocatore;
	private String situazioneDiGioco;
	List<Integer> ordineGioco;
	private int turnoCorrente; //0, 1, 2, 3

	private boolean tutorial;
	private int difficolta; //da 1 a 4
	private int mappa; //1 predefinita, 2 casuale, 3 estiva, 4 invernale
	
	Partita(String situazioneDiGioco, String nomeGiocatore, int civilta, boolean tutorial, int difficolta, int mappa)
	{
		if(situazioneDiGioco != null) //non è una nuova partita
		{
			this.situazioneDiGioco = situazioneDiGioco;
			this.tutorial = tutorial;
			this.difficolta = difficolta;
			this.mappa = mappa;
		}
		else //E' una nuova partita, bisogna crearla
		{
			this.situazioneDiGioco = "";
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
			if(civilta-1 == i) //giocatore umano
				giocatore.add(i, new Giocatore(civilta, "utente1", nomeGiocatore));
			else //cpu
				giocatore.add(i, new Giocatore(i+1, "cpu", "CPU"));
			
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
		turnoCorrente = ordineGioco.get(0);
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

	public void setTurnoCorrente(int turnoCorrente) {
		this.turnoCorrente = turnoCorrente;
	}
}
