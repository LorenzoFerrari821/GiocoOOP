package gioco;

public class CavaDiRisorse {
	private String tipo;
	private int x;
	private int y;
	int civiltaProprietaria; //0 romani, 1 britanni, 2 galli, 3 sassoni, -1 cava libera
	
	CavaDiRisorse(String tipo, int x, int y, int civiltaProprietaria)
	{
		this.tipo = tipo;
		this.x = x;
		this.y = y;
		this.civiltaProprietaria = civiltaProprietaria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCiviltaProprietaria() {
		return civiltaProprietaria;
	}

	public void setCiviltaProprietaria(int civiltaProprietaria) {
		this.civiltaProprietaria = civiltaProprietaria;
	}
	
}
