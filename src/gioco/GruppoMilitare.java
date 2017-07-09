package gioco;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un gruppo militare con Lista dei componenti, posizione X e Y sullo scenario, civiltà di appartenenza,
 * se puo effettuare un attacco o un movimento (attaccoPossibile e movimentoPossibile)
 * @author Werther e Lorenzo
 *
 */
public class GruppoMilitare {
	private List<String> gruppoMilitare = new ArrayList<String>();
	private int posX;
	private int posY;
	private int civilta;
	
	private boolean movimentoPossibile;
	private boolean attaccoPossibile;
	
	/**
	 * Inizializza movimentoPossibile e attaccoPossibile a true
	 */
	GruppoMilitare()
	{
		movimentoPossibile = true;
		attaccoPossibile = true;
	}
	
	public List<String> getGruppoMilitare() {
		return gruppoMilitare;
	}
	public void setGruppoMilitare(List<String> gruppoMilitare) {
		this.gruppoMilitare = gruppoMilitare;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getCivilta() {
		return civilta;
	}
	public void setCivilta(int civilta) {
		this.civilta = civilta;
	}

	public boolean isMovimentoPossibile() {
		return movimentoPossibile;
	}

	public void setMovimentoPossibile(boolean movimentoPossibile) {
		this.movimentoPossibile = movimentoPossibile;
	}

	public boolean isAttaccoPossibile() {
		return attaccoPossibile;
	}

	public void setAttaccoPossibile(boolean attaccoPossibile) {
		this.attaccoPossibile = attaccoPossibile;
	}
	
}

