package gioco;

import java.util.HashMap;
import java.util.Map;

public class ValoriDiGioco {
	private Map<String, Integer> valoriRicerche;
	
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

	}

	public Map<String, Integer> getValoriRicerche() {
		return valoriRicerche;
	}

	public void setValoriRicerche(Map<String, Integer> valoriRicerche) {
		this.valoriRicerche = valoriRicerche;
	}
}
