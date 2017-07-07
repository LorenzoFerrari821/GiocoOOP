package gioco;

import java.util.ArrayList;
import java.util.List;

public class GruppoMilitare {
	private List<String> gruppoMilitare = new ArrayList<String>();
	private int posX;
	private int posY;
	private int civilta;
	
	private boolean movimentoPossibile;
	
	GruppoMilitare()
	{
		movimentoPossibile = true;
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
}

