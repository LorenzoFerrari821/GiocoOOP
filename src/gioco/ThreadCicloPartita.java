package gioco;

public class ThreadCicloPartita extends Thread{

	private Partita partita;
	private boolean turnoPersona;

	public ThreadCicloPartita(Partita partita)
	{
		 this.partita = partita;
	}
	
	public void run() 
	{
		  
		int indice = 0;
		
		while(true)
		{
			partita.setTurnoCorrente(partita.getOrdineGioco().get(indice));
			partita.aggiornaDatiGUI();
			
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
	
	public boolean isTurnoPersona() {
		return turnoPersona;
	}

	public void setTurnoPersona(boolean turnoPersona) {
		this.turnoPersona = turnoPersona;
	}
}
