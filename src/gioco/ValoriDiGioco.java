package gioco;

import java.util.HashMap;
import java.util.Map;

public class ValoriDiGioco {
	private Map<String, Integer> valoriRicerche;
	
	ValoriDiGioco() {
		valoriRicerche = new HashMap<String, Integer>();
		
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
	}

	public Map<String, Integer> getValoriRicerche() {
		return valoriRicerche;
	}

	public void setValoriRicerche(Map<String, Integer> valoriRicerche) {
		this.valoriRicerche = valoriRicerche;
	}
}
