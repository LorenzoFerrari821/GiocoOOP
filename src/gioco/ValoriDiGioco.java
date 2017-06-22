package gioco;

import java.util.HashMap;
import java.util.Map;

public class ValoriDiGioco {
	private Map<String, Integer> valoriRicerche;
	private Map<String, Integer> valoriOro;
	private Map<String, Integer> valoriMat;
	
	ValoriDiGioco() {
		valoriRicerche = new HashMap<String, Integer>();
		
		//Inserimento valori ricerche et� classica
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
		valoriRicerche.put("Citt�", 5);
		valoriRicerche.put("Cavalleria", 5);
		valoriRicerche.put("Et� imperiale", 6);
		valoriRicerche.put("Societ� militare", 6);
		
		//Inserimento valori ricerche Medioevo
		valoriRicerche.put("Strade lastricate", 7);
		valoriRicerche.put("Case a pi� piani", 8);
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
		
		//Inserimento valori ricerche et� vittoriana
		valoriRicerche.put("Strade asfaltate", 13);
		valoriRicerche.put("Sistema industriale", 14);
		valoriRicerche.put("Scienza", 14);
		valoriRicerche.put("Case con mansarda", 15);
		valoriRicerche.put("Fucili", 15);
		valoriRicerche.put("Gerarchia militare", 15);
		valoriRicerche.put("Societ� borghese", 16);
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
		
		valoriOro = new HashMap<String, Integer>();
		valoriMat = new HashMap<String, Integer>();
		
		//Inserimento costi et� classica
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
		inserisciCosti("Casa a pi� piani", 12, 6);
		inserisciCosti("Biblioteca", 10, 14);
		inserisciCosti("Campo mercenari", 8, 16);
		inserisciCosti("Chiesa", 10, 13);
		inserisciCosti("Ospedale", 12, 14);
		inserisciCosti("Casa a schiera", 14, 6);
		inserisciCosti("Rogo", 18, 18);
		inserisciCosti("Mastro birraio", 12, 19);
		inserisciCosti("Banca", 15, 18);
		inserisciCosti("Granaio", 11, 14);
		
		//Inserimento costi et� vittoriana
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

	public Map<String, Integer> getValoriRicerche() {
		return valoriRicerche;
	}

	public void setValoriRicerche(Map<String, Integer> valoriRicerche) {
		this.valoriRicerche = valoriRicerche;
	}
}
