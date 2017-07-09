package gioco;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
	private String proprietario; //possibili valori: utente1 (server), utente2 (client), cpu. Se si gioca in singolo utente1 o cpu
	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	private String nomeGiocatore;
	private int civiltà; //0 romani, 1 britanni, 2 galli, 3 sassoni
	private int oro;
	private int materiali;
	private int puntiRicerca;
	private double bonusEconomia;
	private double bonusMilitare;
	private double bonusRicerca;
	private int oroXTurno;
	private int matXTurno;
	private int prXTurno;
	private int sconfitteMunicipioSubite; //con 5 sconfitte il giocatore è eliminato
	private boolean inPartita; //indica se il giocatore sta ancora giocando o se è stato eliminato
	
	private List<String> unitaMunicipio; //unità attualmente nel municipio
	private List<GruppoMilitare> gruppiInAttacco; //gruppi militari attualmente all'esterno del municipio
	
	private List<String> ricercheEffettuate; //contiene tutte le ricerche effettuate dal giocatore
	private List<String> storicoPossedimenti; //contiene tutti i possedimenti (case etc) del giocatore
	
	Giocatore(int civiltà, String proprietario, String nomeGiocatore) {
		unitaMunicipio = new ArrayList<String>();
		gruppiInAttacco = new ArrayList<GruppoMilitare>();
		ricercheEffettuate = new ArrayList<String>();
		storicoPossedimenti = new ArrayList<String>();
		
		oro = 0;
		materiali = 0;
		puntiRicerca = 0;
		
		bonusEconomia = 1;
		bonusMilitare = 1;
		bonusRicerca = 1;
		
		oroXTurno = 0;
		matXTurno = 0;
		prXTurno = 0;
		sconfitteMunicipioSubite = 0;
		inPartita = true;
		
		this.proprietario = proprietario;
		this.nomeGiocatore = nomeGiocatore;
		this.civiltà = civiltà;
	}
	
	public List<String> getStoricoPossedimenti() {
		return storicoPossedimenti;
	}

	public void setStoricoPossedimenti(List<String> storicoPossedimenti) {
		this.storicoPossedimenti = storicoPossedimenti;
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
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}
	
	public List<String> getUnitaMunicipio() {
		return unitaMunicipio;
	}

	public void setUnitaMunicipio(List<String> unitaMunicipio) {
		this.unitaMunicipio = unitaMunicipio;
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

	public int getOro() {
		return oro;
	}

	public void setOro(int oro) {
		this.oro = oro;
	}

	public int getMateriali() {
		return materiali;
	}

	public void setMateriali(int materiali) {
		this.materiali = materiali;
	}

	

	public double getBonusEconomia() {
		return bonusEconomia;
	}

	public void setBonusEconomia(double bonusEconomia) {
		this.bonusEconomia = bonusEconomia;
	}

	public double getBonusMilitare() {
		return bonusMilitare;
	}

	public void setBonusMilitare(double bonusMilitare) {
		this.bonusMilitare = bonusMilitare;
	}

	public double getBonusRicerca() {
		return bonusRicerca;
	}

	public void setBonusRicerca(double bonusRicerca) {
		this.bonusRicerca = bonusRicerca;
	}

	public int getOroXTurno() {
		return oroXTurno;
	}

	public void setOroXTurno(int oroXTurno) {
		this.oroXTurno = oroXTurno;
	}

	public int getMatXTurno() {
		return matXTurno;
	}

	public void setMatXTurno(int matXTurno) {
		this.matXTurno = matXTurno;
	}

	public int getPrXTurno() {
		return prXTurno;
	}

	public void setPrXTurno(int prXTurno) {
		this.prXTurno = prXTurno;
	}

	public List<GruppoMilitare> getGruppiInAttacco() {
		return gruppiInAttacco;
	}

	public void setGruppiInAttacco(List<GruppoMilitare> gruppiInAttacco) {
		this.gruppiInAttacco = gruppiInAttacco;
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

	public int getSconfitteMunicipioSubite() {
		return sconfitteMunicipioSubite;
	}

	public void setSconfitteMunicipioSubite(int sconfitteMunicipioSubite) {
		this.sconfitteMunicipioSubite = sconfitteMunicipioSubite;
	}

	public boolean isInPartita() {
		return inPartita;
	}

	public void setInPartita(boolean inPartita) {
		this.inPartita = inPartita;
	}
	
}
