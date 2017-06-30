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
					Thread.sleep(5000);
				} catch (InterruptedException e) {}
			}
			else //il giocatore è umano
			{
				turnoPersona = true;
				while(true)
				{
					if(turnoPersona == false)
						break;
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
