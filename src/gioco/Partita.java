package gioco;

public class Partita {

	private Giocatore giocatori[];
	private String situazioneDiGioco;
	private int ordineGioco[];
	private int turnoCorrente;

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
		
	}
}
