package gioco;

/**
 * Thread che rimane in esecuzione durante la partita e scandisce i turni di tutti i giocatori
 * @author Werther e Lorenzo
 *
 */
public class ThreadCicloPartita extends Thread{

	private Partita partita;
	private boolean turnoPersona;

	/**
	 * inizializza il riferimento a Partita
	 * @param partita riferimento a Partita
	 */
	ThreadCicloPartita(Partita partita)
	{
		 this.partita = partita;
	}
	
	/**
	 * Avvia il thread e comincia a scandire i turni
	 */
	public void run() 
	{
		  
		int indice = 0;
		
		while(true)
		{
			partita.setTurnoCorrente(partita.getOrdineGioco().get(indice));
			partita.calcolaRisorse(true);
			partita.getGuiPartita().aggiornaDatiGUI();
			
			refreshMovimentiEAttacchi();
			
			if(partita.getGiocatore().get(partita.getTurnoCorrente()).getProprietario().equals("cpu")) //il giocatore è ia
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {}
			}
			else //il giocatore è umano
			{
				turnoPersona = true;
				while(turnoPersona)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
				}
			}
			if(indice < 3)
				indice++;
			else
				indice = 0;
			
		}
		
	}
	
	/**
	 * Riporta a true la possibilità di attacco e movimento del giocatore di turno corrente
	 */
	public void refreshMovimentiEAttacchi()
	{
		for(GruppoMilitare g : partita.getGiocatore().get(partita.getTurnoCorrente()).getGruppiInAttacco())
		{
			g.setAttaccoPossibile(true);
			g.setMovimentoPossibile(true);
		}
	}
	
	public boolean isTurnoPersona() {
		return turnoPersona;
	}

	public void setTurnoPersona(boolean turnoPersona) {
		this.turnoPersona = turnoPersona;
	}
}
