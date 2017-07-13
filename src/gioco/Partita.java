package gioco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * Cuore pulsante della partita. Contiene tutti i parametri e i metodi principali per lo svolgimento della partita.
 * @author Werther e Lorenzo
 *
 */
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
	private List<CavaDiRisorse> caveScenario;
	
	private ValoriDiGioco valoriDiGioco;
	private String nomeGiocatore;
	private int civiltaGiocatore;
	
	private Scenario scenario;
	
	/**
	 * Inizializza tutte le variabili per iniziare una partita
	 * @param situazioneDiGioco Stringa situazione di gioco
	 * @param nomeGiocatore Nome del giocatore fisico
	 * @param tutorial Tutorial si/no
	 * @param difficolta Difficoltà scelta dal giocatore
	 * @param mappa Mappa scelta dal giocatore
	 * @param civilta Civiltà scelta dal giocatore
	 * @param guiPartita riferimento a GUIPartita
	 * @param valoriDiGioco riferimento a ValoriDiGioco
	 */
	Partita(String situazioneDiGioco, String nomeGiocatore, int tutorial, int difficolta, 
			int mappa, int civilta)
	{
		this.guiPartita = guiPartita;
		gruppiMilitariSchierati = new ArrayList<GruppoMilitare>();
		valoriDiGioco = new ValoriDiGioco();
		this.caveScenario = new ArrayList<CavaDiRisorse>();
		this.nomeGiocatore = nomeGiocatore;
		this.civiltaGiocatore = civilta;
		scenario = new Scenario(mappa);
		
		setupGUIPartita();
		
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
	
	/**
	 * Crea una partita da zero
	 * @param nomeGiocatore Nome del giocatore
	 * @param civilta Civiltà scelta dal giocatore
	 */
	public void creaPartita(String nomeGiocatore, int civilta)
	{
		giocatore = new ArrayList<Giocatore>();
		
		for(int i = 0; i < 4; i++)
		{
			if(civilta == i) //giocatore umano
				giocatore.add(i, new Giocatore(civilta, "utente1", nomeGiocatore));
			else //cpu
				giocatore.add(i, new Giocatore(i, "cpu", "CPU"));
			
			giocatore.get(i).setOro(2000);
			giocatore.get(i).setMateriali(2000);
			giocatore.get(i).setPuntiRicerca(500);
		}
		
		//Randomizzo ordine di gioco
		ordineGioco = new ArrayList<Integer>();
		ordineGioco.add(0);
		ordineGioco.add(1);
		ordineGioco.add(2);
		ordineGioco.add(3);
		
		Collections.shuffle(ordineGioco);
		
		//catalogo tutte le cave in gioco
		for(int j = 0; j < 48; j++)
		{
			for(int i = 0; i < 93; i++)
			{
				if(scenario.getScenario()[j][i].length() >= 3)
				{
					if(scenario.getScenario()[j][i].substring(scenario.getScenario()[j][i].length() - 2, 
							scenario.getScenario()[j][i].length()).equals(" x"))
					{
						caveScenario.add(new CavaDiRisorse("x", i, j, -1));
					}
					else
					if(scenario.getScenario()[j][i].substring(scenario.getScenario()[j][i].length() - 2, 
							scenario.getScenario()[j][i].length()).equals(" y"))
					{
						caveScenario.add(new CavaDiRisorse("y", i, j, -1));
					}
					else
					if(scenario.getScenario()[j][i].substring(scenario.getScenario()[j][i].length() - 2, 
							scenario.getScenario()[j][i].length()).equals(" z"))
					{
						caveScenario.add(new CavaDiRisorse("z", i, j, -1));
					}
				}
			}
		}
		
		avviaPartita();
	}

	/**
	 * Crea la GUI Partita
	 */
	public void setupGUIPartita() {

		guiPartita = new GUIPartita(nomeGiocatore, tutorial, difficolta, mappa, civiltaGiocatore, this, valoriDiGioco);
		guiPartita.setVisible(true);
	}
	
	/**
	 * Metodo che scandisce i turni e gestisce il turno con l'ia della cpu (se tocca alla cpu)
	 * altrimenti lascia giocare il giocatore finchè non passa il turno
	 */
	public void avviaPartita()
	{
		threadCicloPartita = new ThreadCicloPartita(this);
		threadCicloPartita.start();
	}
	
	/**
	 * In base a tutti i possedimenti del giocatore corrente, e in base a tutte le cave di materiali, oro e PR calcola
	 * ed eventualmente incrementa le risorse in più che spettano al giocatore all'inizio del turno
	 * @param incrementa Indica se le risorse sono anche da incrementare (true) o solo da mostrare (false)
	 */
	public void calcolaRisorse(boolean incrementa)
	{
		//Variabili di Incremento risorse
		int iOro = 3, iMat = 3, iPr = 3;
		double iEconomia = 1, iMilitare = 1, iRicerca = 1;
		double caveOro = 1, caveMat = 1, cavePr = 1;
		
		/*Utilizzo HashMap per velocizzare la ricerca sui dati*/
		Map<String, Integer> oro = new HashMap<String, Integer>();
		Map<String, Integer> mat = new HashMap<String, Integer>();
		Map<String, Integer> pr = new HashMap<String, Integer>();
		
		Map<String, Double> economia = new HashMap<String, Double>();
		Map<String, Double> militare = new HashMap<String, Double>();
		Map<String, Double> ricerca = new HashMap<String, Double>();
		
		//Età classica
		oro.put(Global.getLabels("i56"), 2);
		mat.put(Global.getLabels("i64"), 4);
		economia.put(Global.getLabels("i67"), 0.2);
		economia.put(Global.getLabels("i68"), 0.2);
		ricerca.put(Global.getLabels("i24"), 0.5);
		pr.put(Global.getLabels("i24"), 5);
		oro.put(Global.getLabels("i57"), 4);
		ricerca.put(Global.getLabels("i25"), 0.3);
		pr.put(Global.getLabels("i25"), 5);
		oro.put(Global.getLabels("i27"), 6);
		
		//Medioevo
		oro.put(Global.getLabels("i58"), 6);
		ricerca.put(Global.getLabels("i73"), 0.5);
		pr.put(Global.getLabels("i73"), 7);
		militare.put(Global.getLabels("i32"), 0.2);
		oro.put(Global.getLabels("i59"), 8);
		ricerca.put(Global.getLabels("i75"), 0.3);
		pr.put(Global.getLabels("i75"), 7);
		economia.put(Global.getLabels("i69"), 0.3);
		economia.put(Global.getLabels("i70"), 0.3);
		mat.put(Global.getLabels("i65"), 8);
		
		
		//Età vittoriana
		mat.put(Global.getLabels("i66"), 10);
		ricerca.put(Global.getLabels("i76"), 0.3);
		pr.put(Global.getLabels("i76"), 10);
		oro.put(Global.getLabels("i60"), 10);
		economia.put(Global.getLabels("i71"), 0.5);
		mat.put(Global.getLabels("i42"), 13);
		oro.put(Global.getLabels("i74"), 16);
		economia.put(Global.getLabels("i72"), 0.3);
		oro.put(Global.getLabels("i61"), 12);
		
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
		
		//calcola incrementi dovuti dalle cave
		for(CavaDiRisorse c: caveScenario)
		{
			if(c.getCiviltaProprietaria() == giocatore.get(turnoCorrente).getCiviltà()) //cava del giocatore
			{
				if(c.getTipo().equals("x"))
					caveOro += 0.2;
				else
				if(c.getTipo().equals("y"))
					caveMat += 0.2;
				else
				if(c.getTipo().equals("z"))
					cavePr += 0.2;
			}
		}
		
		iOro *= caveOro;
		iMat *= caveMat;
		iPr *= cavePr;
		
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
	 * @param gruppoAttacco Gruppo militare in attacco
	 * @param iDef X del difensore
	 * @param jDef Y del difensore
	 * @return 1 se l'attacco è riuscito (vittoria attaccanti), 0 altrimenti
	 */
	public int esercitoAttaccaEsercito(GruppoMilitare gruppoAttacco, int iDef, int jDef)
	{
		int atkTotale = 0;
		int defTotale = 0;
		GruppoMilitare gruppoDifesa = null;
		
		gruppoAttacco.setAttaccoPossibile(false);
		
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
			scenario.getScenario()[gruppoDifesa.getPosY()][gruppoDifesa.getPosX()] =
					scenario.getScenario()[gruppoDifesa.getPosY()][gruppoDifesa.getPosX()]
					.substring(0, scenario.getScenario()[gruppoDifesa.getPosY()][gruppoDifesa.getPosX()].length() - 10);
			
			//se presente una cava adiacente a gruppo difesa e di sua proprietà la rendo libera
			controllaCava(gruppoDifesa.getPosX(), gruppoDifesa.getPosY(), 1);
			
			//se presente una cava adiacente a gruppo attacco libera la rendo di sua proprietà
			controllaCava(gruppoAttacco.getPosX(), gruppoAttacco.getPosY(), 0);
			
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
			scenario.getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()] =
					scenario.getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()]
					.substring(0, scenario.getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()].length() - 10);
			
			//se presente una cava adiacente a gruppo attacco e di sua proprietà la rendo libera
			controllaCava(gruppoAttacco.getPosX(), gruppoAttacco.getPosY(), 1);
			
			//se presente una cava adiacente a gruppo difesa libera la rendo di sua proprietà
			controllaCava(gruppoDifesa.getPosX(), gruppoDifesa.getPosY(), 0);
			
			return 0;
		}
	}
	
	/**
	 * Metodo che controlla se nella casella di posizione i, j è presente un esercito. Se è presente ritorna 0 se l'esercito è romano,
	 * 1 se inglese, 2 se francese, 3 se tedesco, -1 se non è presente alcun esercito
	 * @param i X
	 * @param j Y
	 * @return nazionalità esercito, -1 se non presente
	 */
	public int esercitoPresente(int i, int j)
	{
		String str = scenario.getScenario()[j][i];
		
		if(str.contains(Global.getLabels("s72")))
			return Integer.parseInt(str.substring(str.length()-1, str.length()));
		
		return -1;
	}
	
	/**
	 * Metodo che controlla se nella casella di posizione i, j è presente un municipio. Se è presente ritorna 0 se il municipio è romano,
	 * 1 se inglese, 2 se francese, 3 se tedesco, -1 se non è presente alcun municipio
	 * @param i X
	 * @param j Y
	 * @return nazionalità municipio, -1 se non presente
	 */
	public int municipioPresente(int i, int j)
	{
		String str = scenario.getScenario()[j][i];
		
		if(str.contains(Global.getLabels("i49")))
		{
			if(i > 37 && i < 55 && j > 34 && j < 45) //romani
				return 0;
			if(i > 37 && i < 55 && j > 2 && j < 13) //britanni
				return 1;
			if(i > 6 && i < 24 && j > 18 && j < 29) //galli
				return 2;
			if(i > 68 && i < 86 && j > 18 && j < 29) //sassoni
				return 3;
		}
		
		return -1;
	}
	
	/**
	 * Fa muovere un gruppo militare
	 * @param gruppoMilitare Gruppo militare
	 * @param i X
	 * @param j Y
	 */
	public void gruppoMilitareMuovi(GruppoMilitare gruppoMilitare, int i, int j)
	{
		//se presente una cava adiacente al gruppo militare e di sua proprietà la rendo libera
		controllaCava(gruppoMilitare.getPosX(), gruppoMilitare.getPosY(), 1);
		
		//tolgo il gruppo militare dallo scenario di posizione vecchia
		scenario.getScenario()[gruppoMilitare.getPosY()][gruppoMilitare.getPosX()] = 
				scenario.getScenario()[gruppoMilitare.getPosY()][gruppoMilitare.getPosX()]
				.substring(0, scenario.getScenario()[gruppoMilitare.getPosY()][gruppoMilitare.getPosX()].length() -Integer.parseInt(Global.getLabels("s135")));    //La linghezza è diversa a seconda della lingua
		
		//aggiorno la posizione in gruppo militare
		gruppoMilitare.setPosX(i);
		gruppoMilitare.setPosY(j);
		gruppoMilitare.setMovimentoPossibile(false);
		
		//aggiorno la posizione nello scenario
		scenario.aggiungiEsercito(i, j, giocatore.get(turnoCorrente).getCiviltà());
		
		//controllo se presente un falò lo raccolgo
		controllaFalo(i, j);
		
		//se è presente una cava libera nelle celle adiacenti la gestisco
		controllaCava(i, j, 0);
	}
	
	/**
	 * Metodo che libera la cava in posizione i,j dai possedimenti di cave del giocatore
	 * @param i X
	 * @param j Y
	 */
	public void liberaCava(int i, int j)
	{
		for(CavaDiRisorse c: caveScenario)
		{
			if(c.getX() == i && c.getY() == j)
			{
				c.setCiviltaProprietaria(-1);
			}
		}
	}
	
	/**
	 * Metodo che permette al giocatore del turno corrente di conquistare la cava di posizione i, j
	 * @param i X
	 * @param j Y
	 */
	public void conquistaCava(int i, int j)
	{
		for(CavaDiRisorse c: caveScenario)
		{
			if(c.getX() == i && c.getY() == j)
			{
				if(c.getCiviltaProprietaria() == -1)
					c.setCiviltaProprietaria(giocatore.get(turnoCorrente).getCiviltà());
			}
		}
	}
	
	/**
	 * Metodo che controlla se è presente una cava nelle caselle vicine a i e j, ed eventualmente la controlla o la libera (azione)
	 * @param i X
	 * @param j Y
	 * @param azione 0 controlla, 1 libera
	 */
	public void controllaCava(int i, int j, int azione)
	{
		String cella;
		
		if(j > 0) //controlla parte superiore
		{
			if(scenario.getScenario()[j-1][i].length() >= 3)
			{
				cella = scenario.getScenario()[j-1][i].substring(scenario.getScenario()[j-1][i].length() - 2, 
						scenario.getScenario()[j-1][i].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i, j-1);
					else
						liberaCava(i, j-1);
				}
			}
		}
		if(i > 0) //controlla parte sx
		{
			if(scenario.getScenario()[j][i-1].length() >= 3)
			{
				cella = scenario.getScenario()[j][i-1].substring(scenario.getScenario()[j][i-1].length() - 2, 
						scenario.getScenario()[j][i-1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i-1, j);
					else
						liberaCava(i-1, j);
				}
			}
		}
		if(j < 47) //controlla parte bassa
		{
			if(scenario.getScenario()[j+1][i].length() >= 3)
			{
				cella = scenario.getScenario()[j+1][i].substring(scenario.getScenario()[j+1][i].length() - 2, 
						scenario.getScenario()[j+1][i].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i, j+1);
					else
						liberaCava(i, j+1);
				}
			}
		}
		if(j < 92) //controlla parte dx
		{
			if(scenario.getScenario()[j][i+1].length() >= 3)
			{
				cella = scenario.getScenario()[j][i+1].substring(scenario.getScenario()[j][i+1].length() - 2, 
						scenario.getScenario()[j][i+1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i+1, j);
					else
						liberaCava(i+1, j);
				}
			}
		}
		if(j > 0 && j > 0) //controlla parte sx in alto
		{
			if(scenario.getScenario()[j-1][i-1].length() >= 3)
			{
				cella = scenario.getScenario()[j-1][i-1].substring(scenario.getScenario()[j-1][i-1].length() - 2, 
						scenario.getScenario()[j-1][i-1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i-1, j-1);
					else
						liberaCava(i-1, j-1);
				}
			}
		}
		if(i < 92 && j > 0) //controlla parte dx in alto
		{
			if(scenario.getScenario()[j-1][i+1].length() >= 3)
			{
				cella = scenario.getScenario()[j-1][i+1].substring(scenario.getScenario()[j-1][i+1].length() - 2, 
						scenario.getScenario()[j-1][i+1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i+1, j-1);
					else
						liberaCava(i+1, j-1);
				}
			}
		}
		if(i > 0 && j < 47) //sx in basso
		{
			if(scenario.getScenario()[j+1][i-1].length() >= 3)
			{
				cella = scenario.getScenario()[j+1][i-1].substring(scenario.getScenario()[j+1][i-1].length() - 2, 
						scenario.getScenario()[j+1][i-1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i-1, j+1);
					else
						liberaCava(i-1, j+1);
				}
			}
		}
		if(i < 92 && j < 47) //dx in basso
		{
			if(scenario.getScenario()[j+1][i+1].length() >= 3)
			{
				cella = scenario.getScenario()[j+1][i+1].substring(scenario.getScenario()[j+1][i+1].length() - 2, 
						scenario.getScenario()[j+1][i+1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i+1, j+1);
					else
						liberaCava(i+1, j+1);
				}
			}
		}
	}
	
	/**
	 * Controlla se è presente un falo in una delle caselle adiacenti. Se si lo raccoglie
	 * @param i X
	 * @param j Y
	 */
	public void controllaFalo(int i, int j)
	{
		if(j > 0) //controlla parte superiore
		{
			if(scenario.getScenario()[j-1][i].length() >= 3 && 
					scenario.getScenario()[j-1][i].substring(scenario.getScenario()[j-1][i].length() - 2, 
							scenario.getScenario()[j-1][i].length()).equals(" f")) //Se nella casella superiore c'è un falò
			{
				ottieniFalo(i, j-1);
			}
		}
		if(i > 0) //controlla parte sx
		{
			if(scenario.getScenario()[j][i-1].length() >= 3 && 
					scenario.getScenario()[j][i-1].substring(scenario.getScenario()[j][i-1].length() - 2, 
							scenario.getScenario()[j][i-1].length()).equals(" f")) //Se nella casella sx c'è un falò
			{
				ottieniFalo(i-1, j);
			}
		}
		if(j < 47) //controlla parte bassa
		{
			if(scenario.getScenario()[j+1][i].length() >= 3 && 
					scenario.getScenario()[j+1][i].substring(scenario.getScenario()[j+1][i].length() - 2, 
					scenario.getScenario()[j+1][i].length()).equals(" f")) //Se nella casella giu c'è un falò
			{
				ottieniFalo(i, j+1);
			}
		}
		if(i < 92) //controlla parte dx
		{
			if(scenario.getScenario()[j][i+1].length() >= 3 && 
					scenario.getScenario()[j][i+1].substring(scenario.getScenario()[j][i+1].length() - 2, 
					scenario.getScenario()[j][i+1].length()).equals(" f")) //Se nella casella dx c'è un falò
			{
				ottieniFalo(i+1, j);
			}
		}
		if(i > 0 && j > 0) //sx in alto
		{
			if(scenario.getScenario()[j-1][i-1].length() >= 3 && 
					scenario.getScenario()[j-1][i-1].substring(scenario.getScenario()[j-1][i-1].length() - 2, 
					scenario.getScenario()[j-1][i-1].length()).equals(" f")) //Se nella casella sx c'è un falò
			{
				ottieniFalo(i-1, j-1);
			}
		}
		if(i < 92 && j > 0) //dx in alto
		{
			if(scenario.getScenario()[j-1][i+1].length() >= 3 && 
					scenario.getScenario()[j-1][i+1].substring(scenario.getScenario()[j-1][i+1].length() - 2, 
					scenario.getScenario()[j-1][i+1].length()).equals(" f")) //Se nella casella dx in alto c'è un falò
			{
				ottieniFalo(i+1, j-1);
			}
		}
		if(i > 0 && j < 47) //sx in basso
		{
			if(scenario.getScenario()[j+1][i-1].length() >= 3 && 
					scenario.getScenario()[j+1][i-1].substring(scenario.getScenario()[j+1][i-1].length() - 2, 
					scenario.getScenario()[j+1][i-1].length()).equals(" f")) //Se nella casella sx in basso c'è un falò
			{
				ottieniFalo(i-1, j+1);
			}
		}
		if(i < 92 && j < 47) //dx in basso
		{
			if(scenario.getScenario()[j+1][i+1].length() >= 3 && 
					scenario.getScenario()[j+1][i+1].substring(scenario.getScenario()[j+1][i+1].length() - 2, 
					scenario.getScenario()[j+1][i+1].length()).equals(" f")) //Se nella casella dx in basso c'è un falò
			{
				ottieniFalo(i+1, j+1);
			}
		}
	}
	
	/**
	 * Metodo che permette di ottenere un falò sulla mappa
	 * @param i X
	 * @param j Y
	 */
	public void ottieniFalo(int i, int j)
	{
		int bottino;
		
		//tolgo il falo dallo scenario
		scenario.getScenario()[j][i] = scenario.getScenario()[j][i].substring(0, scenario.getScenario()[j][i].length() - 2);
		
		//calcolo valore falò: 10% di oro o materiali
		if(Math.random() < 0.5) {
			bottino = (int)(giocatore.get(turnoCorrente).getOro() * 0.1);
			giocatore.get(turnoCorrente).setOro(giocatore.get(turnoCorrente).getOro() + bottino);
		}
		else
		{
			bottino = (int)(giocatore.get(turnoCorrente).getMateriali() * 0.1);
			giocatore.get(turnoCorrente).setMateriali(
					giocatore.get(turnoCorrente).getMateriali() + bottino);
		}
	}
	
	/**
	 * Funzione con cui un esercito attacca un municipio nemico
	 * @param gruppoAttacco Gruppo militare in attacco
	 * @param civiltaDifesa Civiltà in difesa
	 * @return 1 se l'attacco è riuscito (vittoria attaccanti), 0 altrimenti, -1 se la città era gia eliminata
	 */
	public int esercitoAttaccaMunicipio(GruppoMilitare gruppoAttacco, int civiltaDifesa)
	{
		int atkTotale = 0;
		int defTotale = 0;
		
		gruppoAttacco.setAttaccoPossibile(false);
		
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
				scenario.getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()] =
						scenario.getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()]
						.substring(0, scenario.getScenario()[gruppoAttacco.getPosY()][gruppoAttacco.getPosX()].length() - 10);
				return 0;
			}
		}
		
		return -1;
	}

	/**
	 * Compra l'elemento e lo piazza sullo scenario
	 * @param elemLblsGioco elemento da piazzare
	 * @param i X
	 * @param j Y
	 */
	public void compraEPiazza(String elemLblsGioco, int i, int j)
	{
		/*Scalo costo in oro*/
		giocatore.get(turnoCorrente).setOro(
		giocatore.get(turnoCorrente).getOro()-valoriDiGioco.getValoriOro().get(elemLblsGioco));
		/*Scalo costo in materiali*/
		giocatore.get(turnoCorrente).setMateriali(
		giocatore.get(turnoCorrente).getMateriali()-valoriDiGioco.getValoriMat().get(elemLblsGioco));

		giocatore.get(turnoCorrente).getStoricoPossedimenti().add(elemLblsGioco);
		posizionaElementoSuScenario(elemLblsGioco, i, j);
	}
	
	/**
	 * Posiziona l'elemento contenuto nella variabile elemLblsGioco sullo scenario
	 * @param elemLblsGioco elemento da posizionare
	 * @param i X
	 * @param j Y
	 */
	public void posizionaElementoSuScenario(String elemLblsGioco, int i, int j)
	{
		/*Posizionamento oggetto sulla plancia di gioco*/
		if(elemLblsGioco.equals(Global.getLabels("i53")) || elemLblsGioco.equals(Global.getLabels("i54")) || elemLblsGioco.equals(Global.getLabels("i55")) ||
				elemLblsGioco.equals(Global.getLabels("i56")) || elemLblsGioco.equals(Global.getLabels("i57")) || 
				elemLblsGioco.equals(Global.getLabels("i58")) || elemLblsGioco.equals(Global.getLabels("i59")) ||
				elemLblsGioco.equals(Global.getLabels("i60")) || elemLblsGioco.equals(Global.getLabels("i61")))
		{ //caso 1x1
			scenario.getScenario()[j][i] += " ";
			elemLblsGioco = elemLblsGioco.replaceAll(" ", "_");
			scenario.getScenario()[j][i] += elemLblsGioco;
		}
		else
		{ //caso 2x2
			elemLblsGioco = elemLblsGioco.replaceAll(" ", "_");
			scenario.getScenario()[j][i] += " ";
			scenario.getScenario()[j][i] += elemLblsGioco;
			scenario.getScenario()[j][i] += "1";

			scenario.getScenario()[j][i+1] += " ";
			scenario.getScenario()[j][i+1] += elemLblsGioco;
			scenario.getScenario()[j][i+1] += "2";

			scenario.getScenario()[j+1][i] += " ";
			scenario.getScenario()[j+1][i] += elemLblsGioco;
			scenario.getScenario()[j+1][i] += "3";

			scenario.getScenario()[j+1][i+1] += " ";
			scenario.getScenario()[j+1][i+1] += elemLblsGioco;
			scenario.getScenario()[j+1][i+1] += "4";
		}
	}
	
	/**
	 * Vende struttura e la rimuove dallo scenario
	 * @param nome Nome edificio
	 * @param i X
	 * @param j Y
	 */
	public void vendiERimuovi(String nome, int i, int j)
	{
		char ultimoChar = nome.charAt(nome.length() - 1);

		if(ultimoChar == '1' || ultimoChar == '2' || ultimoChar == '3' || ultimoChar == '4') {
			nome = nome.substring(0, nome.length() - 1);
		}
		//Rimozione oggetto dai suoi possedimenti
		for(int k = 0; k < giocatore.get(turnoCorrente).getStoricoPossedimenti().size(); k++)
		{
			if(giocatore.get(turnoCorrente).getStoricoPossedimenti().get(k).equals(nome)) {
				giocatore.get(turnoCorrente).getStoricoPossedimenti().remove(k);
			}
		}
		//Accredito della metà dell'oro e di materiali dell'oggetto venduto
		giocatore.get(turnoCorrente).setOro(
				giocatore.get(turnoCorrente).getOro() + (valoriDiGioco.getValoriOro().get(nome) / 2));
		giocatore.get(turnoCorrente).setMateriali(
				giocatore.get(turnoCorrente).getMateriali() + (valoriDiGioco.getValoriMat().get(nome) / 2));

		rimuoviElementoDaScenario(i, j, ultimoChar);
	}
	
	/**
	 * Rimuove elemento dallo scenario
	 * @param i X
	 * @param j Y
	 * @param ultimoChar Ultimo carattere della stringa dell'elemento da rimuovere
	 */
	public void rimuoviElementoDaScenario(int i, int j, char ultimoChar)
	{
		//Rimozione dell'oggetto dallo scenario
		if(ultimoChar == '1')
		{
			scenario.getScenario()[j][i] = scenario.getScenario()[j][i]
					.substring(0, scenario.getScenario()[j][i].lastIndexOf(" "));
			scenario.getScenario()[j][i+1] = scenario.getScenario()[j][i+1]
					.substring(0, scenario.getScenario()[j][i+1].lastIndexOf(" "));
			scenario.getScenario()[j+1][i] = scenario.getScenario()[j+1][i]
					.substring(0, scenario.getScenario()[j+1][i].lastIndexOf(" "));
			scenario.getScenario()[j+1][i+1] = scenario.getScenario()[j+1][i+1]
					.substring(0, scenario.getScenario()[j+1][i+1].lastIndexOf(" "));
		}
		else
			if(ultimoChar == '2')
			{
				scenario.getScenario()[j][i] = scenario.getScenario()[j][i]
						.substring(0, scenario.getScenario()[j][i].lastIndexOf(" "));
				scenario.getScenario()[j][i-1] = scenario.getScenario()[j][i-1]
						.substring(0, scenario.getScenario()[j][i-1].lastIndexOf(" "));
				scenario.getScenario()[j+1][i] = scenario.getScenario()[j+1][i]
						.substring(0, scenario.getScenario()[j+1][i].lastIndexOf(" "));
				scenario.getScenario()[j+1][i-1] = scenario.getScenario()[j+1][i-1]
						.substring(0, scenario.getScenario()[j+1][i-1].lastIndexOf(" "));
			}
			else
				if(ultimoChar == '3')
				{
					scenario.getScenario()[j-1][i] = scenario.getScenario()[j-1][i]
							.substring(0, scenario.getScenario()[j-1][i].lastIndexOf(" "));
					scenario.getScenario()[j-1][i+1] = scenario.getScenario()[j-1][i+1]
							.substring(0, scenario.getScenario()[j-1][i+1].lastIndexOf(" "));
					scenario.getScenario()[j][i] = scenario.getScenario()[j][i]
							.substring(0, scenario.getScenario()[j][i].lastIndexOf(" "));
					scenario.getScenario()[j][i+1] = scenario.getScenario()[j][i+1]
							.substring(0, scenario.getScenario()[j][i+1].lastIndexOf(" "));
				}
				else
					if(ultimoChar == '4')
					{
						scenario.getScenario()[j-1][i-1] = scenario.getScenario()[j-1][i-1]
								.substring(0, scenario.getScenario()[j-1][i-1].lastIndexOf(" "));
						scenario.getScenario()[j-1][i] = scenario.getScenario()[j-1][i]
								.substring(0, scenario.getScenario()[j-1][i].lastIndexOf(" "));
						scenario.getScenario()[j][i-1] = scenario.getScenario()[j][i-1]
								.substring(0, scenario.getScenario()[j][i-1].lastIndexOf(" "));
						scenario.getScenario()[j][i] = scenario.getScenario()[j][i]
								.substring(0, scenario.getScenario()[j][i].lastIndexOf(" "));
					}
					else
					{
						scenario.getScenario()[j][i] = scenario.getScenario()[j][i]
								.substring(0, scenario.getScenario()[j][i].lastIndexOf(" "));
					}
	}
	
	/**
	 * Posiziona elemento sulla plancia di gioco
	 * @param elemLblsGioco Elemento
	 * @param i X
	 * @param j Y
	 * @param ioldLblsGioco vecchia X
	 * @param joldLblsGioco vecchia Y
	 */
	public void posizionaElemento(String elemLblsGioco, int i, int j, int ioldLblsGioco, int joldLblsGioco)
	{
		char ultimoChar = elemLblsGioco.charAt(elemLblsGioco.length() - 1);

		rimuoviElementoDaScenario(ioldLblsGioco, joldLblsGioco, ultimoChar);

		if(ultimoChar == '1' || ultimoChar == '2' || ultimoChar == '3' || ultimoChar == '4')
			elemLblsGioco = elemLblsGioco.substring(0, elemLblsGioco.length() - 1);
		posizionaElementoSuScenario(elemLblsGioco, i, j);
	}
	
	/**
	 * Metodo che controlla se la casella di indice i e j è adiacente a una città e ritorna 0 se vicino a romani, 1 se vicino a britanni,
	 * 2 se vicino a galli, 3 se vicino a sassoni, -1 se non adiacente a nessuna città
	 * @param i X
	 * @param j Y
	 * @return civiltà
	 */
	public int isVicinoACitta(int i, int j)
	{
		int SxX = -1;
		int SuY = -1;
		int DxX = -1;
		int GiuY = -1;
		
		for(int k = 0; k < 4; k++)
		{
			switch(k)
			{
			case 0:
				SxX = 38;
				SuY = 35;
				DxX = 54;
				GiuY = 44;
				break;
			case 1:
				SxX = 38;
				SuY = 3;
				DxX = 54;
				GiuY = 12;
				break;
			case 2:
				SxX = 7;
				SuY = 19;
				DxX = 23;
				GiuY = 28;
				break;
			case 3:
				SxX = 69;
				SuY = 19;
				DxX = 85;
				GiuY = 28;
				break;
			}
			if(i == SxX - 1 && j >= SuY - 1 && j <= GiuY + 1) //lato sx citta
				return k;
			if(j == SuY - 1 && i >= SxX && i <= DxX) //lato superiore citta
				return k;
			if(i == DxX + 1 && j >= SuY - 1 && j <= GiuY + 1) //lato dx citta
				return k;
			if(j == GiuY + 1 && i >= SxX && i <= DxX) //lato inferiore citta
				return k;
		}
		
		return -1;
	}

	/**
	 * Individua l'oggetto su cui è stato cliccato tenendo conto se interno al villaggio o esterno al villaggio
	 * @param i X
	 * @param j Y
	 * @param interno true se interno ad una città, false altrimenti
	 * @return Ritorna il nome dell'oggetto individuato
	 */
	public String individuaOggetto(int i, int j, boolean interno) //i è la x, j è la y
	{
		String strCercata = null;

		StringTokenizer st;
		int stItera = 0;

		st = new StringTokenizer(scenario.getScenario()[j][i]);
		while(st.hasMoreTokens()) {
			if(stItera == 0) //controllo che il pavimento sia ghiaia
				if(interno) //se e solo se l'edificio in questione è interno
					if(!st.nextToken().equals("g"))
					{
						return null;
					}
			stItera++;
			if(stItera > 1)
				strCercata = st.nextToken();
		}
		if(strCercata == null)
			return null;

		strCercata = strCercata.replaceAll("_", " ");

		return strCercata;
	}

	/**
	 * Controlla se è possibile piazzare l'elemento in elemLblsGioco nella cella corrente
	 * @param elemLblsGioco elemento da piazzare
	 * @param i X
	 * @param j Y
	 * @return true se possibile, false altrimenti
	 */
	public boolean isPiazzamentoPossibile(String elemLblsGioco, int i, int j) //i è la x, j è la y
	{
		int stItera;

		if(elemLblsGioco.equals(Global.getLabels("i53")) || elemLblsGioco.equals(Global.getLabels("i54")) || elemLblsGioco.equals(Global.getLabels("i55")) ||
				elemLblsGioco.equals(Global.getLabels("i56")) || elemLblsGioco.equals(Global.getLabels("i57")) || 
				elemLblsGioco.equals(Global.getLabels("i58")) || elemLblsGioco.equals(Global.getLabels("i59")) ||
				elemLblsGioco.equals(Global.getLabels("i60")) || elemLblsGioco.equals(Global.getLabels("i61")) || elemLblsGioco.equals(Global.getLabels("s60")))
		{
			//controlliamo che la lbl di posizione i, j sia disponibile (1x1)
			StringTokenizer st;
			stItera = 0;

			st = new StringTokenizer(scenario.getScenario()[j][i]);
			while(st.hasMoreTokens()) {
				if(stItera == 0) //controllo che il pavimento sia ghiaia
					if(!st.nextToken().equals("g") && !elemLblsGioco.equals(Global.getLabels("s60")))
					{
						return false;
					}
				stItera++;
				if(stItera > 1) //La lbl di posizione i,i è già impegnata
				{
					return false;
				}
			}

		}
		else
		{
			//controlliamo che le lbl di posizione i, j; i+1, j; i, j+1; i+1, j+1 siano disponibili (2x2)
			for(int k = 0; k < 2; k++)
			{
				for(int u = 0; u < 2; u++)
				{
					//controlliamo che la lbl di posizione i+k, j+k sia disponibile (2x2)
					StringTokenizer st;
					stItera = 0;
					
					st = new StringTokenizer(scenario.getScenario()[j+u][i+k]);
					while(st.hasMoreTokens()) {
						if(stItera == 0) //controllo che il pavimento sia ghiaia
							if(!st.nextToken().equals("g"))
								return false;
						stItera++;
						if(stItera > 1) //La lbl di posizione i,i è già impegnata
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Piazza esercito sul terreno di gioco
	 * @param gruppoMilitare Gruppo militare
	 * @param civilta Civiltà
	 * @param i X
	 * @param j Y
	 */
	public void piazzaEsercito(GruppoMilitare gruppoMilitare, int civilta, int i, int j)
	{
		gruppoMilitare.setCivilta(civilta);
		gruppoMilitare.setPosX(i);
		gruppoMilitare.setPosY(j);
		for(String s: gruppoMilitare.getGruppoMilitare())
		{
			giocatore.get(turnoCorrente).getUnitaMunicipio().remove(s);
		}
		gruppiMilitariSchierati.add(gruppoMilitare);
		giocatore.get(turnoCorrente).getGruppiInAttacco().add(gruppoMilitare);
		scenario.aggiungiEsercito(i, j, civilta);
		
		//controllo la presenza di falo nelle caselle adiacenti
		controllaFalo(i, j);
		
		//se è presente una cava libera nelle celle adiacenti la gestisco
		controllaCava(i, j, 0);
	}
	
	/**
	 * Preleva l'esercito e lo inserisce nel municipio
	 * @param i X
	 * @param j Y
	 */
	public void prelevaEsercito(int i, int j)
	{
		int conta = 0, indice = -1;
		
		//prelevo il gruppo giusto
		for(GruppoMilitare g: gruppiMilitariSchierati)
		{
			if(g.getPosX() == i && g.getPosY() == j)
			{
				indice = conta;
				break;
			}
			conta++;
		}
		//Le truppe tornano nel municipio
		for(String s: gruppiMilitariSchierati.get(indice).getGruppoMilitare())
		{
			giocatore.get(turnoCorrente).getUnitaMunicipio().add(s);
		}
		//Il gruppo militare viene sciolto
		gruppiMilitariSchierati.remove(indice);
		
		//tolgo il gruppo militare dallo scenario
		scenario.getScenario()[j][i] = scenario.getScenario()[j][i]
				.substring(0, scenario.getScenario()[j][i].length() - 10);
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

	public List<CavaDiRisorse> getCaveScenario() {
		return caveScenario;
	}

	public void setCaveScenario(List<CavaDiRisorse> caveScenario) {
		this.caveScenario = caveScenario;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
	
}
