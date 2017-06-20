package gioco;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
	private String proprietario; //possibili valori: utente1 (server), utente2 (client), cpu. Se si gioca in singolo utente1 o cpu
	private String nomeGiocatore;
	private boolean tutorial;
	private int difficolta; //da 1 a 4
	private int mappa; //1 predefinita, 2 casuale, 3 estiva, 4 invernale
	private int civiltà; //1 romani, 2 britanni, 3 galli, 4 sassoni
	private int oro;
	private int materiali;
	private int puntiRicerca;
	
	private int bonusEconomia;
	private int bonusMilitare;
	private int bonusRicerca;
	
	private List<UnitaMilitare> unitaMunicipio; //unità attualmente nel municipio
	private List<GruppoMilitare> armateInAttacco; //gruppi militari attualmente all'esterno del municipio
	
	private List<String> ricercheEffettuate; //contiene tutte le ricerche effettuate dal giocatore
	private List<String> storicoPossedimenti; //contiene tutti i possedimenti (case etc) del giocatore
	
	Giocatore() {
		unitaMunicipio = new ArrayList<UnitaMilitare>();
		armateInAttacco = new ArrayList<GruppoMilitare>();
		ricercheEffettuate = new ArrayList<String>();
		storicoPossedimenti = new ArrayList<String>();
	}
	
	public int getCiviltà() {
		return civiltà;
	}
	public void setCiviltà(int civiltà) {
		this.civiltà = civiltà;
	}
	public int getPuntiRicerca() {
		return puntiRicerca;
	}
	public void setPuntiRicerca(int puntiRicerca) {
		this.puntiRicerca = puntiRicerca;
	}
	public List<String> getRicercheEffettuate() {
		return ricercheEffettuate;
	}
	public void setRicercheEffettuate(List<String> ricercheEffettuate) {
		this.ricercheEffettuate = ricercheEffettuate;
	}
}
