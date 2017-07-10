package gioco;

import java.util.HashMap;
import java.util.Map;

/**
 * Contiene tutti i valori utilizzati dal gioco (valori in oro, materiali, PR di costruzioni e attacco, difesa e velocità truppe)
 * @author Werther e Lorenzo
 */
public class ValoriDiGioco {
	private Map<String, Integer> valoriRicerche;
	private Map<String, Integer> valoriOro;
	private Map<String, Integer> valoriMat;
	
	private Map<String, Integer> atkUnita; //attacco unità
	private Map<String, Integer> defUnita; //difesa unità
	private Map<String, Integer> velUnita; //velocità unità
	
	/**
	 * Inizializza tutte le variabili da utilizzare con i valori di gioco
	 */
	ValoriDiGioco() {
		valoriRicerche = new HashMap<String, Integer>();
		valoriOro = new HashMap<String, Integer>();
		valoriMat = new HashMap<String, Integer>();
		
		atkUnita = new HashMap<String, Integer>();
		defUnita = new HashMap<String, Integer>();
		velUnita = new HashMap<String, Integer>();
		
		//Inserimento valori ricerche età classica
		valoriRicerche.put(Global.getLabels("i77"), 1);
		valoriRicerche.put(Global.getLabels("i78"), 2);
		valoriRicerche.put(Global.getLabels("i0"), 2);
		valoriRicerche.put(Global.getLabels("i64"), 2);
		valoriRicerche.put(Global.getLabels("i79"), 3);
		valoriRicerche.put(Global.getLabels("i48"), 3);
		valoriRicerche.put(Global.getLabels("i80"), 3);
		valoriRicerche.put(Global.getLabels("i81"), 4);
		valoriRicerche.put(Global.getLabels("i82"), 4);
		valoriRicerche.put(Global.getLabels("i4"), 4);
		valoriRicerche.put(Global.getLabels("i25"), 5);
		valoriRicerche.put(Global.getLabels("i83"), 5);
		valoriRicerche.put(Global.getLabels("i6"), 5);
		valoriRicerche.put(Global.getLabels("i28"), 6);
		valoriRicerche.put(Global.getLabels("i30"), 6);
		
		//Inserimento valori ricerche Medioevo
		valoriRicerche.put(Global.getLabels("i84"), 7);
		valoriRicerche.put(Global.getLabels("i85"), 8);
		valoriRicerche.put(Global.getLabels("i8"), 8);
		valoriRicerche.put(Global.getLabels("i10"), 8);
		valoriRicerche.put(Global.getLabels("i73"), 9);
		valoriRicerche.put(Global.getLabels("i86"), 9);
		valoriRicerche.put(Global.getLabels("i87"), 9);
		valoriRicerche.put(Global.getLabels("i39"), 10);
		valoriRicerche.put(Global.getLabels("i88"), 10);
		valoriRicerche.put(Global.getLabels("i12"), 10);
		valoriRicerche.put(Global.getLabels("i89"), 11);
		valoriRicerche.put(Global.getLabels("i90"), 11);
		valoriRicerche.put(Global.getLabels("i91"), 12);
		valoriRicerche.put(Global.getLabels("i70"), 12);
		valoriRicerche.put(Global.getLabels("i14"), 12);
		
		//Inserimento valori ricerche età vittoriana
		valoriRicerche.put(Global.getLabels("i92"), 13);
		valoriRicerche.put(Global.getLabels("i93"), 14);
		valoriRicerche.put(Global.getLabels("i94"), 14);
		valoriRicerche.put(Global.getLabels("i95"), 15);
		valoriRicerche.put(Global.getLabels("i16"), 15);
		valoriRicerche.put(Global.getLabels("i96"), 15);
		valoriRicerche.put(Global.getLabels("i98"), 16);
		valoriRicerche.put(Global.getLabels("i99"), 16);
		valoriRicerche.put(Global.getLabels("i20"), 16);
		valoriRicerche.put(Global.getLabels("i100"), 17);
		valoriRicerche.put(Global.getLabels("i97"), 17);
		valoriRicerche.put(Global.getLabels("i101"), 17);
		valoriRicerche.put(Global.getLabels("i22"), 17);
		valoriRicerche.put(Global.getLabels("i46"), 18);
		valoriRicerche.put(Global.getLabels("i44"), 18);
		valoriRicerche.put(Global.getLabels("i43"), 18);
		valoriRicerche.put(Global.getLabels("i47"), 18);
		
		//Inserimento costi età classica
		inserisciCosti(Global.getLabels("i53"), 1, 1);
		inserisciCosti(Global.getLabels("i56"), 5, 2);
		inserisciCosti(Global.getLabels("i0"), 3, 10);
		inserisciCosti(Global.getLabels("i64"), 2, 5);
		inserisciCosti(Global.getLabels("i67"), 3, 6);
		inserisciCosti(Global.getLabels("i68"), 2, 6);
		inserisciCosti(Global.getLabels("i24"), 3, 7);
		inserisciCosti(Global.getLabels("i57"), 8, 4);
		inserisciCosti(Global.getLabels("i27"), 10, 10);
		
		//Inserimento costi medioevo
		inserisciCosti(Global.getLabels("i62"), 5, 5);
		inserisciCosti(Global.getLabels("i58"), 12, 6);
		inserisciCosti(Global.getLabels("i73"), 10, 14);
		inserisciCosti(Global.getLabels("i32"), 8, 16);
		inserisciCosti(Global.getLabels("i35"), 10, 13);
		inserisciCosti(Global.getLabels("i39"), 12, 14);
		inserisciCosti(Global.getLabels("i59"), 14, 6);
		inserisciCosti(Global.getLabels("i75"), 18, 18);
		inserisciCosti(Global.getLabels("i69"), 12, 19);
		inserisciCosti(Global.getLabels("i70"), 15, 18);
		inserisciCosti(Global.getLabels("i65"), 11, 14);
		
		//Inserimento costi età vittoriana
		inserisciCosti(Global.getLabels("i63"), 10, 10);
		inserisciCosti(Global.getLabels("i66"), 17, 23);
		inserisciCosti(Global.getLabels("i76"), 13, 27);
		inserisciCosti(Global.getLabels("i60"), 26, 12);
		inserisciCosti(Global.getLabels("i41"), 30, 30);
		inserisciCosti(Global.getLabels("i71"), 19, 28);
		inserisciCosti(Global.getLabels("i42"), 18, 30);
		inserisciCosti(Global.getLabels("i74"), 20, 31);
		inserisciCosti(Global.getLabels("i72"), 31, 20);
		inserisciCosti(Global.getLabels("i61"), 33, 15);
		
		//Inserimento Unità militari
		inserisciUMilitare(Global.getLabels("i1"), 1, 1, 1, 1, 4);
		inserisciUMilitare(Global.getLabels("i2"), 3, 1, 5, 5, 4);
		inserisciUMilitare(Global.getLabels("i3"), 4, 1, 6, 4, 5);
		inserisciUMilitare(Global.getLabels("i5"), 6, 2, 8, 3, 4);
		inserisciUMilitare(Global.getLabels("i7"), 8, 3, 10, 10, 8);
		inserisciUMilitare(Global.getLabels("i9"), 12, 3, 8, 18, 3);
		inserisciUMilitare(Global.getLabels("i11"), 12, 4, 17, 7, 4);
		inserisciUMilitare(Global.getLabels("i13"), 16, 6, 21, 21, 7);
		inserisciUMilitare(Global.getLabels("i15"), 15, 8, 23, 12, 4);
		inserisciUMilitare(Global.getLabels("i17"), 16, 5, 25, 25, 4);
		inserisciUMilitare(Global.getLabels("i18"), 19, 4, 27, 21, 3);
		inserisciUMilitare(Global.getLabels("i19"), 17, 5, 30, 13, 7);
		inserisciUMilitare(Global.getLabels("i21"), 25, 10, 28, 31, 8);
		inserisciUMilitare(Global.getLabels("i23"), 30, 15, 38, 26, 3);
		
		inserisciUMilitare(Global.getLabels("i26"), 5, 3, 2, 13, 3);
		
		inserisciUMilitare(Global.getLabels("i29"), 15, 5, 15, 15, 4);
		
		inserisciUMilitare(Global.getLabels("i31"), 15, 5, 19, 6, 5);
		
		inserisciUMilitare(Global.getLabels("i33"), 10, 2, 16, 13, 6);
		inserisciUMilitare(Global.getLabels("i34"), 12, 1, 19, 7, 5);
		
		inserisciUMilitare(Global.getLabels("i37"), 20, 5, 25, 23, 8);
		inserisciUMilitare(Global.getLabels("i38"), 20, 5, 23, 25, 8);
		
		inserisciUMilitare(Global.getLabels("i40"), 10, 1, 0, 20, 7);
		
		inserisciUMilitare("Lorenzo", 200, 100, 100, 300, 4);
		inserisciUMilitare("Werther", 200, 100, 300, 100, 4);
		
		inserisciUMilitare(Global.getLabels("i43"), 40, 20, 40, 30, 5);
		inserisciUMilitare(Global.getLabels("i46"), 40, 20, 40, 30, 5);
		inserisciUMilitare(Global.getLabels("i45"), 40, 20, 40, 30, 5);
		inserisciUMilitare(Global.getLabels("i47"), 40, 20, 40, 30, 5);
	}

	/**
	 * Inserisce i costi. Utilizzato per compattare le righe di codice
	 * @param nome Nome costruzione
	 * @param oro Valore in oro
	 * @param mat Valore in materiali
	 */
	public void inserisciCosti(String nome, int oro, int mat)
	{
		valoriOro.put(nome, oro);
		valoriMat.put(nome, mat);
	}
	
	/**
	 * Inserisce costi in oro, materiali, attacco, difesa e velocità di un'unità militare. Utilizzato per compattare le righe di codice
	 * @param nome Nome dell'unità militare
	 * @param oro Valore in oro
	 * @param mat Valore in materiali
	 * @param atk Valore di attacco
	 * @param def Valore di difesa
	 * @param vel Valore di velocità
	 */
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
}
