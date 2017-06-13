package gioco;

public class Scenario {
	
	private String[][] scenario;
	
	Scenario() {
		scenario = new String[48][93];
		
		for(int i = 0; i < 48; i++)
		{
			for(int j = 0; j < 93; j++)
			{
				//aggiungo terreni di base
				if(j < 31 && i < 16) //primo quadrato alto sx
					scenario[i][j] = "n"; //aggiungo neve
				if(j >= 31 && j < 62 && i < 16) //secondo riquadro
					scenario[i][j] = "v"; //aggiungo erba
				if(j >= 62 && i < 16) // terzo riquadro
					scenario[i][j] = "n"; //aggiungo neve
				if(j < 31 && i >= 16 && i < 32) // quarto riquadro
					scenario[i][j] = "v";  //aggiungo erba
				if(j >= 31 && j < 62 && i >= 16 && i < 32) //quinto riquadro
					scenario[i][j] = "t"; //aggiungo terra
				if(j >= 62 && i >= 16 && i < 32) //sesto riquadro
					scenario[i][j] = "v"; //aggiungo erba
				if(j < 31 && i >= 32) //settimo riquadro
					scenario[i][j] = "d"; //aggiungo deserto
				if(j >= 31 && j < 62 && i >= 32) //ottavo riquadro
					scenario[i][j] = "v"; //aggiungo erba
				if(j >= 62 && i >= 32) //ottavo riquadro
					scenario[i][j] = "d"; //aggiungo deserto
				
				//aggiungo pavimentazione villaggi
				if(i > 2 && i < 13 && j > 37 && j < 55) //villaggio top
					scenario[i][j] = "g";
				if(i > 18 && i < 29 && j > 6 && j < 24) //villaggio sx
					scenario[i][j] = "g";
				if(i > 18 && i < 29 && j > 68 && j < 86) //villaggio dx
					scenario[i][j] = "g";
				if(i > 34 && i < 45 && j > 37 && j < 55) //villaggio giu
					scenario[i][j] = "g";
				
				//aggiungo elementi scenario
				if(i == 0 && j == 0)
					scenario[i][j] += ".c";
				if(i == 0 && j == 24)
					scenario[i][j] += ".x";
				if(i == 0 && j == 38)
					scenario[i][j] += ".y";
				switch(i) {
					case 0:
						if(j == 0)
							scenario[i][j] += ".c";
						if(j == 24)
							scenario[i][j] += ".x";
						if(j == 38)
							scenario[i][j] += ".y";
						break;
					case 1:
						if(j == 8)
							scenario[i][j] += ".tr";
						if(j == 79)
							scenario[i][j] += ".f";
						if(j == 92)
							scenario[i][j] += ".c";
						break;
					case 2:
						if(j == 19)
							scenario[i][j] += ".f";
						if(j == 65)
							scenario[i][j] += ".at";
						break;
					case 3:
						if(j == 4)
							scenario[i][j] += ".x";
						break;
					case 4:
						if(j == 72)
							scenario[i][j] += ".pg";
						if(j == 86)
							scenario[i][j] += ".x";
						break;
					case 5:
						if(j == 24)
							scenario[i][j] += ".y";
						break;
					case 7:
						if(j == 5)
							scenario[i][j] += ".t1";
						if(j == 12)
							scenario[i][j] += ".z";
						break;
					case 8:
						if(j == 67)
							scenario[i][j] += ".t";
						if(j == 73)
							scenario[i][j] += ".z";
						if(j == 83)
							scenario[i][j] += ".ap";
						break;
					case 10:
						if(j == 15)
							scenario[i][j] += ".pm";
						break;
					case 11:
						if(j == 3)
							scenario[i][j] += ".f";
						if(j == 21)
							scenario[i][j] += ".f";
						if(j == 86)
							scenario[i][j] += ".y";
						if(j == 90)
							scenario[i][j] += ".f";
						break;
					case 12:
						if(j == 70)
							scenario[i][j] += ".f";
						break;
					case 13:
						if(j == 9)
							scenario[i][j] += ".am";
						if(j == 26)
							scenario[i][j] += ".pg";
						if(j == 63)
							scenario[i][j] += ".pg";
						break;
					case 14:
						if(j == 18)
							scenario[i][j] += ".c";
						if(j == 82)
							scenario[i][j] += ".pm";
						if(j == 92)
							scenario[i][j] += ".t3";
						break;
					case 15:
						if(j == 88)
							scenario[i][j] += ".t1";
						break;
					case 17:
						if(j == 44)
							scenario[i][j] += ".z";
						break;
					case 18:
						if(j == 40)
							scenario[i][j] += ".f";
						if(j == 56)
							scenario[i][j] += ".f";
						break;
					case 19:
						if(j == 36)
							scenario[i][j] += ".x";
						if(j == 47)
							scenario[i][j] += ".am";
						break;
					case 20:
						if(j == 0)
							scenario[i][j] += ".x";
						if(j == 33)
							scenario[i][j] += ".am";
						if(j == 49)
							scenario[i][j] += ".f";
						if(j == 92)
							scenario[i][j] += ".x";
						break;
					case 21:
						if(j == 42)
							scenario[i][j] += ".c";
						if(j == 59)
							scenario[i][j] += ".z";
						break;
					case 22:
						if(j == 54)
							scenario[i][j] += ".y";
						break;
					case 23:
						if(j == 32)
							scenario[i][j] += ".f";
						if(j == 38)
							scenario[i][j] += ".x";
						if(j == 45)
							scenario[i][j] += ".y";
						break;
					case 24:
						if(j == 48)
							scenario[i][j] += ".y";
						break;
					case 25:
						if(j == 41)
							scenario[i][j] += ".at";
						if(j == 57)
							scenario[i][j] += ".am";
						break;
					case 26:
						if(j == 33)
							scenario[i][j] += ".pg";
						break;
					case 27:
						if(j == 0)
							scenario[i][j] += ".y";
						if(j == 43)
							scenario[i][j] += ".y";
						if(j == 47)
							scenario[i][j] += ".pg";
						if(j == 54)
							scenario[i][j] += ".z";
						if(j == 92)
							scenario[i][j] += ".y";
						break;
					case 28:
						if(j == 37)
							scenario[i][j] += ".z";
						break;
					case 29:
						if(j == 48)
							scenario[i][j] += ".x";
						if(j == 57)
							scenario[i][j] += ".f";
						break;
					case 30:
						if(j == 33)
							scenario[i][j] += ".c";
						if(j == 43)
							scenario[i][j] += ".f";
						if(j == 53)
							scenario[i][j] += ".f";
						break;
				}
				
				
				
			}
		}
		//aggiungo municipi iniziali
		//su
		scenario[6][38] += ".municipio1";
		scenario[6][39] += ".municipio2";
		scenario[7][38] += ".municipio3";
		scenario[7][39] += ".municipio4";
		//sx
		scenario[19][9] += ".municipio1";
		scenario[19][10] += ".municipio2";
		scenario[20][9] += ".municipio3";
		scenario[20][10] += ".municipio4";
		//dx
		scenario[27][82] += ".municipio1";
		scenario[27][83] += ".municipio2";
		scenario[28][82] += ".municipio3";
		scenario[28][83] += ".municipio4";
		//giu
		scenario[40][53] += ".municipio1";
		scenario[40][54] += ".municipio1";
		scenario[41][53] += ".municipio1";
		scenario[41][54] += ".municipio1";
		
		for(int i = 0; i < 48; i++)
		{
			for(int j = 0; j < 93; j++)
			{
				System.out.print(scenario[i][j]);
			}
			System.out.println("");
		}
	}
}
