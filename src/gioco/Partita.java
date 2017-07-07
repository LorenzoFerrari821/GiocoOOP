package gioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private List<GruppoMilitare> gruppiMilitariSchierati;
	
	private ValoriDiGioco valoriDiGioco;
	
	Partita(String situazioneDiGioco, String nomeGiocatore, int tutorial, int difficolta, 
			int mappa, int civilta, GUIPartita guiPartita, ValoriDiGioco valoriDiGioco)
	{
		this.guiPartita = guiPartita;
		gruppiMilitariSchierati = new ArrayList<GruppoMilitare>();
		this.valoriDiGioco = valoriDiGioco;
		
		if(situazioneDiGioco != null) //non è una nuova partita
		{
			this.situazioneDiGioco = situazioneDiGioco;
			this.tutorial = tutorial;
			this.difficolta = difficolta;
			this.mappa = mappa;
		}
		else //E' una nuova partita, bisogna crearla
		{
			this.situazioneDiGioco = "setup " + nomeGiocatore + " " + Integer.toString(tutorial) + " " + Integer.toString(difficolta) + " "
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
			
			giocatore.get(i).setOro(2000000);
			giocatore.get(i).setMateriali(2000000000);
			giocatore.get(i).setPuntiRicerca(50000);
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
	
	/*
	 * In base a tutti i possedimenti del giocatore corrente, e in base a tutte le cave di materiali, oro e PR calcola
	 * ed eventualmente incrementa le risorse in più che spettano al giocatore all'inizio del turno
	 */
	public void calcolaRisorse(boolean incrementa)
	{
		//Variabili di Incremento risorse
		int iOro = 3, iMat = 3, iPr = 3;
		double iEconomia = 1, iMilitare = 1, iRicerca = 1;
		
		/*Utilizzo HashMap per velocizzare la ricerca sui dati*/
		Map<String, Integer> oro = new HashMap<String, Integer>();
		Map<String, Integer> mat = new HashMap<String, Integer>();
		Map<String, Integer> pr = new HashMap<String, Integer>();
		
		Map<String, Double> economia = new HashMap<String, Double>();
		Map<String, Double> militare = new HashMap<String, Double>();
		Map<String, Double> ricerca = new HashMap<String, Double>();
		
		//Età classica
		oro.put("Casa", 2);
		mat.put("Fucina", 4);
		economia.put("Mercato", 0.2);
		economia.put("Orefice", 0.2);
		ricerca.put("Tempio", 0.5);
		pr.put("Tempio", 5);
		oro.put("Villa", 4);
		ricerca.put("Oracolo", 0.3);
		pr.put("Oracolo", 5);
		oro.put("Palazzo", 6);
		
		//Medioevo
		oro.put("Casa a più piani", 6);
		ricerca.put("Biblioteca", 0.5);
		pr.put("Biblioteca", 7);
		militare.put("Campo mercenari", 0.2);
		oro.put("Casa a schiera", 8);
		ricerca.put("Roghi", 0.3);
		pr.put("Roghi", 7);
		economia.put("Mastro birraio", 0.3);
		economia.put("Banca", 0.3);
		mat.put("Granaio", 8);
		
		
		//Età vittoriana
		mat.put("Fabbriche", 10);
		ricerca.put("Laboratorio", 0.3);
		pr.put("Laboratorio", 10);
		oro.put("Casa con mansarda", 10);
		economia.put("Centro cittadino", 0.5);
		mat.put("Parlamento", 13);
		oro.put("Opera", 16);
		economia.put("Teatro", 0.3);
		oro.put("Villetta", 12);
		
		for(String s : giocatore.get(turnoCorrente).getStoricoPossedimenti())
		{
			if(oro.containsKey(s))
				iOro += oro.get(s);
			
			if(mat.containsKey(s))
				iMat += mat.get(s);
			
			if(pr.containsKey(s))
				iPr += pr.get(s);
			
			if(economia.containsKey(s))
				iEconomia += economia.get(s);
			
			if(militare.containsKey(s))
				iMilitare += militare.get(s);
			
			if(ricerca.containsKey(s))
				iRicerca += ricerca.get(s);
		}
		
		iOro = (int)((double)iOro * iEconomia);
		iMat = (int)((double)iMat * iEconomia);
		iPr = (int)((double)iPr * iRicerca);
		
		giocatore.get(turnoCorrente).setBonusEconomia(iEconomia);
		giocatore.get(turnoCorrente).setBonusMilitare(iMilitare);
		giocatore.get(turnoCorrente).setBonusRicerca(iRicerca);
		
		giocatore.get(turnoCorrente).setOroXTurno(iOro);
		giocatore.get(turnoCorrente).setMatXTurno(iMat);
		giocatore.get(turnoCorrente).setPrXTurno(iPr);
		
		if(incrementa) //Incrementa le risorse del giocatore
		{
			giocatore.get(turnoCorrente).setOro(giocatore.get(turnoCorrente).getOro() + iOro);
			giocatore.get(turnoCorrente).setMateriali(giocatore.get(turnoCorrente).getMateriali() + iMat);
			giocatore.get(turnoCorrente).setPuntiRicerca(giocatore.get(turnoCorrente).getPuntiRicerca() + iPr);
		}
	}
	/**
	 * Funzione con cui un esercito attacca un altro esercito nemico
	 * @param gruppoAttacco
	 * @param iDef
	 * @param jDef
	 * @return 1 se l'attacco è riuscito (vittoria attaccanti), 0 altrimenti
	 */
	public int esercitoAttaccaEsercito(GruppoMilitare gruppoAttacco, int iDef, int jDef)
	{
		int atkTotale = 0;
		int defTotale = 0;
		GruppoMilitare gruppoDifesa = null;
		
		//trovo il gruppo in difesa
		for(GruppoMilitare g: gruppiMilitariSchierati)
		{
			if(g.getPosX() == iDef && g.getPosY() == jDef)
				gruppoDifesa = g;
		}
		
		//Calcolo attacco totale gruppo in attacco
		for(String s: gruppoAttacco.getGruppoMilitare())
		{
			atkTotale += valoriDiGioco.getAtkUnita().get(s);
		}
		
		//Calcolo difesa totale gruppo in difesa
		for(String s: gruppoDifesa.getGruppoMilitare())
		{
			defTotale += valoriDiGioco.getDefUnita().get(s);
		}
		
		if(atkTotale > defTotale) //vince l'attacco
		{
			int conta = 0;
			
			//rimuovo gruppo militare difesa da giocatore
			for(GruppoMilitare g: giocatore.get(gruppoDifesa.getCivilta()).getGruppiInAttacco())
			{
				if(g.getPosX() == gruppoDifesa.getPosX() && g.getPosY() == gruppoDifesa.getPosY())
					break;
				conta++;
			}
			giocatore.get(gruppoDifesa.getCivilta()).getGruppiInAttacco().remove(conta);
			
			conta = 0;
			///rimuovo gruppo militare difesa dai gruppi militari in partita
			for(GruppoMilitare g: gruppiMilitariSchierati)
			{
				if(g.getPosX() == gruppoDifesa.getPosX() && g.getPosY() == gruppoDifesa.getPosY())
					break;
				conta++;
			}
			gruppiMilitariSchierati.remove(conta);
			
			//rimuovo gruppo militare difesa dallo scenario
			guiPartita.getScenario().getScenario()[gruppoDifesa.getPosY()][gruppoDifesa.getPosX()] =
					guiPartita.getScenario().getScenario()[gruppoDifesa.getPosY()][gruppoDifesa.getPosX()]
					.substring(0, guiPartita.getScenario().getScenario()[gruppoDifesa.getPosY()][gruppoDifesa.getPosX()].length() - 10);
			return 1;
			
		}
		else //vince la difesa
		{
			int conta = 0;
			
			//rimuovo gruppo militare attacco da giocatore
			for(GruppoMilitare g: giocatore.get(gruppoAttacco.getCivilta()).getGruppiInAttacco())
			{
				if(g.getPosX() == gruppoAttacco.getPosX() && g.getPosY() == gruppoAttacco.getPosY())
					break;
				conta++;
			}
			giocatore.get(gruppoAttacco.getCivilta()).getGruppiInAttacco().remove(conta);
			
			conta = 0;
			///rimuovo gruppo militare attacco dai gruppi militari in partita
			for(GruppoMilitare g: gruppiMilitariSchierati)
			{
				if(g.getPosX() == gruppoAttacco.getPosX() && g.getPosY() == gruppoAttacco.getPosY())
					break;
				conta++;
			}
			gruppiMilitariSchierati.remove(conta);
			
			//rimuovo gruppo militare attacco dallo scenario
			guiPartita.getScenario().getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()] =
					guiPartita.getScenario().getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()]
					.substring(0, guiPartita.getScenario().getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()].length() - 10);
			return 0;
		}
	}
	
	/**
	 * Funzione con cui un esercito attacca un municipio nemico
	 * @param gruppoAttacco
	 * @param civiltaDifesa
	 * @return 1 se l'attacco è riuscito (vittoria attaccanti), 0 altrimenti, -1 se la città era gia eliminata
	 */
	public int esercitoAttaccaMunicipio(GruppoMilitare gruppoAttacco, int civiltaDifesa)
	{
		int atkTotale = 0;
		int defTotale = 0;
		if(giocatore.get(civiltaDifesa).getSconfitteMunicipioSubite() < 5)
		{
			
			//Calcolo attacco totale gruppo in attacco
			for(String s: gruppoAttacco.getGruppoMilitare())
			{
				atkTotale += valoriDiGioco.getAtkUnita().get(s);
			}
			
			//Calcolo difesa totale gruppo in difesa
			for(String s: giocatore.get(civiltaDifesa).getUnitaMunicipio())
			{
				defTotale += valoriDiGioco.getDefUnita().get(s);
			}
			
			if(atkTotale > defTotale) //vince l'attacco
			{
				int conta = 0;
				
					int oroPreso, matPresi;
					
					//incrementa sconfitte difensore
					giocatore.get(civiltaDifesa).setSconfitteMunicipioSubite(giocatore.get(civiltaDifesa).getSconfitteMunicipioSubite() + 1);
					
					//ruba dal difensore il 20% di oro e materiali
					oroPreso = giocatore.get(civiltaDifesa).getOro() / 5;
					matPresi = giocatore.get(civiltaDifesa).getMateriali() / 5;
					giocatore.get(civiltaDifesa).setOro(giocatore.get(civiltaDifesa).getOro() / 5);
					giocatore.get(civiltaDifesa).setMateriali(giocatore.get(civiltaDifesa).getMateriali() / 5);
					
					giocatore.get(gruppoAttacco.getCivilta()).setOro(giocatore.get(gruppoAttacco.getCivilta()).getOro() + oroPreso);
					giocatore.get(gruppoAttacco.getCivilta()).setMateriali(giocatore.get(gruppoAttacco.getCivilta()).getMateriali() + matPresi);
					
					//Se le sconfitte del municipio in difesa ammontano a 5, il giocatore in difesa ha perso
					if(giocatore.get(civiltaDifesa).getSconfitteMunicipioSubite() == 5)
						giocatore.get(civiltaDifesa).setInPartita(false);
					return 1;
			}
			else //vince la difesa
			{
				int conta = 0;
				
				//rimuovo gruppo militare attacco da giocatore
				for(GruppoMilitare g: giocatore.get(gruppoAttacco.getCivilta()).getGruppiInAttacco())
				{
					if(g.getPosX() == gruppoAttacco.getPosX() && g.getPosY() == gruppoAttacco.getPosY())
						break;
					conta++;
				}
				giocatore.get(gruppoAttacco.getCivilta()).getGruppiInAttacco().remove(conta);
				
				conta = 0;
				///rimuovo gruppo militare attacco dai gruppi militari in partita
				for(GruppoMilitare g: gruppiMilitariSchierati)
				{
					if(g.getPosX() == gruppoAttacco.getPosX() && g.getPosY() == gruppoAttacco.getPosY())
						break;
					conta++;
				}
				gruppiMilitariSchierati.remove(conta);
				
				//rimuovo gruppo militare attacco dallo scenario
				guiPartita.getScenario().getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()] =
						guiPartita.getScenario().getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()]
						.substring(0, guiPartita.getScenario().getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()].length() - 10);
				return 0;
			}
		}
		
		return -1;
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
	public String getSituazioneDiGioco() {
		return situazioneDiGioco;
	}
	public void setSituazioneDiGioco(String situazioneDiGioco) {
		this.situazioneDiGioco = situazioneDiGioco;
	}
	
	public List<GruppoMilitare> getGruppiMilitariSchierati() {
		return gruppiMilitariSchierati;
	}

	public void setGruppiMilitariSchierati(List<GruppoMilitare> gruppiMilitariSchierati) {
		this.gruppiMilitariSchierati = gruppiMilitariSchierati;
	}
	
}
