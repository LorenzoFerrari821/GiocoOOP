package gioco;

import java.util.HashMap;
import java.util.Map;

public class ValoriDiGioco {
	private Map<String, Integer> valoriRicerche;
	private Map<String, Integer> valoriOro;
	private Map<String, Integer> valoriMat;
	
	private Map<String, Integer> atkUnita; //attacco unità
	private Map<String, Integer> defUnita; //difesa unità
	private Map<String, Integer> velUnita; //velocità unità
	
	ValoriDiGioco() {
		valoriRicerche = new HashMap<String, Integer>();
		valoriOro = new HashMap<String, Integer>();
		valoriMat = new HashMap<String, Integer>();
		
		atkUnita = new HashMap<String, Integer>();
		defUnita = new HashMap<String, Integer>();
		velUnita = new HashMap<String, Integer>();
		
		//Inserimento valori ricerche età classica
		valoriRicerche.put("Sentieri", 1);
		valoriRicerche.put("Abitazioni", 2);
		valoriRicerche.put("Caserma", 2);
		valoriRicerche.put("Fucina", 2);
		valoriRicerche.put("Commercio", 3);
		valoriRicerche.put("Fanteria", 3);
		valoriRicerche.put("Oreficeria", 3);
		valoriRicerche.put("Religione", 4);
		valoriRicerche.put("Ville", 4);
		valoriRicerche.put("Tiro con l'arco", 4);
		valoriRicerche.put("Oracolo", 5);
		valoriRicerche.put("Città", 5);
		valoriRicerche.put("Cavalleria", 5);
		valoriRicerche.put("Età imperiale", 6);
		valoriRicerche.put("Società militare", 6);
		
		//Inserimento valori ricerche Medioevo
		valoriRicerche.put("Strade lastricate", 7);
		valoriRicerche.put("Case a più piani", 8);
		valoriRicerche.put("Corazze", 8);
		valoriRicerche.put("Balestre", 8);
		valoriRicerche.put("Biblioteca", 9);
		valoriRicerche.put("Mercenari", 9);
		valoriRicerche.put("Clero", 9);
		valoriRicerche.put("Ospedale", 10);
		valoriRicerche.put("Case a schiera", 10);
		valoriRicerche.put("Tattiche di cavalleria", 10);
		valoriRicerche.put("Inquisizione", 11);
		valoriRicerche.put("Granai", 11);
		valoriRicerche.put("Fermentazione", 12);
		valoriRicerche.put("Banca", 12);
		valoriRicerche.put("Polvere da sparo", 12);
		
		//Inserimento valori ricerche età vittoriana
		valoriRicerche.put("Strade asfaltate", 13);
		valoriRicerche.put("Sistema industriale", 14);
		valoriRicerche.put("Scienza", 14);
		valoriRicerche.put("Case con mansarda", 15);
		valoriRicerche.put("Fucili", 15);
		valoriRicerche.put("Gerarchia militare", 15);
		valoriRicerche.put("Società borghese", 16);
		valoriRicerche.put("Politica", 16);
		valoriRicerche.put("Tattiche in campo aperto", 16);
		valoriRicerche.put("Teatri", 17);
		valoriRicerche.put("Musica lirica", 17);
		valoriRicerche.put("Villette", 17);
		valoriRicerche.put("Balistica", 17);
		valoriRicerche.put("Legione straniera", 18);
		valoriRicerche.put("Guardie reali", 18);
		valoriRicerche.put("Carabinieri", 18);
		valoriRicerche.put("Gardenkorps", 18);
		
		//Inserimento costi età classica
		inserisciCosti("Sentiero", 1, 1);
		inserisciCosti("Casa", 5, 2);
		inserisciCosti("Caserma", 3, 10);
		inserisciCosti("Fucina", 2, 5);
		inserisciCosti("Mercato", 3, 6);
		inserisciCosti("Orefice", 2, 6);
		inserisciCosti("Tempio", 3, 7);
		inserisciCosti("Villa", 8, 4);
		inserisciCosti("Palazzo", 10, 10);
		
		//Inserimento costi medioevo
		inserisciCosti("Strada lastricata", 5, 5);
		inserisciCosti("Casa a più piani", 12, 6);
		inserisciCosti("Biblioteca", 10, 14);
		inserisciCosti("Campo mercenari", 8, 16);
		inserisciCosti("Chiesa", 10, 13);
		inserisciCosti("Ospedale", 12, 14);
		inserisciCosti("Casa a schiera", 14, 6);
		inserisciCosti("Rogo", 18, 18);
		inserisciCosti("Mastro birraio", 12, 19);
		inserisciCosti("Banca", 15, 18);
		inserisciCosti("Granaio", 11, 14);
		
		//Inserimento costi età vittoriana
		inserisciCosti("Strada asfaltata", 10, 10);
		inserisciCosti("Fabbrica", 17, 23);
		inserisciCosti("Laboratorio", 13, 27);
		inserisciCosti("Casa con mansarda", 26, 12);
		inserisciCosti("Caserma eroi", 30, 30);
		inserisciCosti("Centro cittadino", 19, 28);
		inserisciCosti("Parlamento", 18, 30);
		inserisciCosti("Opera", 20, 31);
		inserisciCosti("Teatro", 31, 20);
		inserisciCosti("Villetta", 33, 15);
		
		//Inserimento Unità militari
		inserisciUMilitare("Miliziano", 1, 1, 1, 1, 4);
		inserisciUMilitare("Soldato", 3, 1, 5, 5, 4);
		inserisciUMilitare("Spadaccino", 4, 1, 6, 4, 5);
		inserisciUMilitare("Arciere", 6, 2, 8, 3, 4);
		inserisciUMilitare("Cavaliere", 8, 3, 10, 10, 8);
		inserisciUMilitare("Soldato in armatura pesante", 12, 3, 8, 18, 3);
		inserisciUMilitare("Balestriere", 12, 4, 17, 7, 4);
		inserisciUMilitare("Cavaliere pesante", 16, 6, 21, 21, 7);
		inserisciUMilitare("Cannone", 15, 8, 23, 12, 4);
		inserisciUMilitare("Fuciliere", 16, 5, 25, 25, 4);
		inserisciUMilitare("Mitragliere", 19, 4, 27, 21, 3);
		inserisciUMilitare("Granatiere", 17, 5, 30, 13, 7);
		inserisciUMilitare("Fuciliere a cavallo", 25, 10, 28, 31, 8);
		inserisciUMilitare("Artiglieria", 30, 15, 38, 26, 3);
		
		inserisciUMilitare("Druido", 5, 3, 2, 13, 3);
		
		inserisciUMilitare("Pretoriano", 15, 5, 15, 15, 4);
		
		inserisciUMilitare("Berserk", 15, 5, 19, 6, 5);
		
		inserisciUMilitare("Spadaccino mercenario", 10, 2, 16, 13, 6);
		inserisciUMilitare("Arciere mercenario", 12, 1, 19, 7, 5);
		
		inserisciUMilitare("Cavaliere templare", 20, 5, 25, 23, 8);
		inserisciUMilitare("Cavaliere crociato", 20, 5, 23, 25, 8);
		
		inserisciUMilitare("Medico", 10, 1, 0, 20, 7);
		
		inserisciUMilitare("Lorenzo", 200, 100, 100, 300, 4);
		inserisciUMilitare("Werther", 200, 100, 300, 100, 4);
		
		inserisciUMilitare("Carabinieri", 40, 20, 40, 30, 5);
		inserisciUMilitare("Legione straniera", 40, 20, 40, 30, 5);
		inserisciUMilitare("Guardia reale", 40, 20, 40, 30, 5);
		inserisciUMilitare("Gardenkorps", 40, 20, 40, 30, 5);
	}
	
	public Map<String, Integer> getValoriOro() {
		return valoriOro;
	}

	public void setValoriOro(Map<String, Integer> valoriOro) {
		this.valoriOro = valoriOro;
	}

	public Map<String, Integer> getValoriMat() {
		return valoriMat;
	}

	public void setValoriMat(Map<String, Integer> valoriMat) {
		this.valoriMat = valoriMat;
	}

	public void inserisciCosti(String nome, int oro, int mat)
	{
		valoriOro.put(nome, oro);
		valoriMat.put(nome, mat);
	}
	
	public void inserisciUMilitare(String nome, int oro, int mat, int atk, int def, int vel)
	{
		valoriOro.put(nome, oro);
		valoriMat.put(nome, mat);
		atkUnita.put(nome, atk);
		defUnita.put(nome, def);
		velUnita.put(nome, vel);
	}

	public Map<String, Integer> getValoriRicerche() {
		return valoriRicerche;
	}

	public void setValoriRicerche(Map<String, Integer> valoriRicerche) {
		this.valoriRicerche = valoriRicerche;
	}

	public Map<String, Integer> getAtkUnita() {
		return atkUnita;
	}

	public void setAtkUnita(Map<String, Integer> atkUnita) {
		this.atkUnita = atkUnita;
	}

	public Map<String, Integer> getDefUnita() {
		return defUnita;
	}

	public void setDefUnita(Map<String, Integer> defUnita) {
		this.defUnita = defUnita;
	}

	public Map<String, Integer> getVelUnita() {
		return velUnita;
	}

	public void setVelUnita(Map<String, Integer> velUnita) {
		this.velUnita = velUnita;
	}
	
}
