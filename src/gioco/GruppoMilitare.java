package gioco;

import java.util.ArrayList;
import java.util.List;

public class GruppoMilitare {
	private List<UnitaMilitare> gruppoMilitare = new ArrayList<UnitaMilitare>();
	private int posX;
	private int posY;
	
	public List<UnitaMilitare> getGruppoMilitare() {
		return gruppoMilitare;
	}
	
	public void setGruppoMilitare(List<UnitaMilitare> gruppoMilitare) {
		this.gruppoMilitare = gruppoMilitare;
	}
}

