package gioco;

import java.util.ArrayList;
import java.util.List;

public class GruppoMilitare {
	private List<String> gruppoMilitare = new ArrayList<String>();
	private int posX;
	private int posY;
	
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
	
	
}

