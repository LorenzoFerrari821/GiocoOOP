package gioco;

public class Scenario {

	private String[][] scenario;

	Scenario(int n) {
		scenario = new String[48][93];

		if(n==0){ //Mappa predefinita
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
					switch(i) {
					case 0:
						if(j == 0)
							scenario[i][j] += " c";
						if(j == 39)
							scenario[i][j] += " x";
						if(j == 53)
							scenario[i][j] += " y";
						break;
					case 1:
						if(j == 8)
							scenario[i][j] += " tr";
						if(j == 79)
							scenario[i][j] += " f";
						if(j == 92)
							scenario[i][j] += " c";
						break;
					case 2:
						if(j == 19)
							scenario[i][j] += " f";
						if(j == 65)
							scenario[i][j] += " at";
						break;
					case 3:
						if(j == 4)
							scenario[i][j] += " x";
						break;
					case 4:
						if(j == 72)
							scenario[i][j] += " pg";
						if(j == 86)
							scenario[i][j] += " x";
						break;
					case 5:
						if(j == 24)
							scenario[i][j] += " y";
						break;
					case 7:
						if(j == 5)
							scenario[i][j] += " t1";
						if(j == 12)
							scenario[i][j] += " z";
						break;
					case 8:
						if(j == 67)
							scenario[i][j] += " tr";
						if(j == 73)
							scenario[i][j] += " z";
						if(j == 83)
							scenario[i][j] += " ap";
						break;
					case 10:
						if(j == 15)
							scenario[i][j] += " pm";
						break;
					case 11:
						if(j == 3)
							scenario[i][j] += " f";
						if(j == 21)
							scenario[i][j] += " f";
						if(j == 86)
							scenario[i][j] += " y";
						if(j == 90)
							scenario[i][j] += " f";
						break;
					case 12:
						if(j == 70)
							scenario[i][j] += " f";
						break;
					case 13:
						if(j == 9)
							scenario[i][j] += " am";
						if(j == 26)
							scenario[i][j] += " pg";
						if(j == 63)
							scenario[i][j] += " pg";
						break;
					case 14:
						if(j == 18)
							scenario[i][j] += " c";
						if(j == 82)
							scenario[i][j] += " pm";
						if(j == 92)
							scenario[i][j] += " t3";
						break;
					case 15:
						if(j == 88)
							scenario[i][j] += " t1";
						break;
					case 17:
						if(j == 44)
							scenario[i][j] += " z";
						break;
					case 18:
						if(j == 40)
							scenario[i][j] += " f";
						if(j == 56)
							scenario[i][j] += " f";
						break;
					case 19:
						if(j == 36)
							scenario[i][j] += " x";
						if(j == 47)
							scenario[i][j] += " ap";
						break;
					case 20:
						if(j == 0)
							scenario[i][j] += " x";
						if(j == 33)
							scenario[i][j] += " am";
						if(j == 49)
							scenario[i][j] += " f";
						if(j == 92)
							scenario[i][j] += " x";
						break;
					case 21:
						if(j == 42)
							scenario[i][j] += " c";
						if(j == 59)
							scenario[i][j] += " z";
						break;
					case 22:
						if(j == 54)
							scenario[i][j] += " y";
						break;
					case 23:
						if(j == 32)
							scenario[i][j] += " f";
						if(j == 38)
							scenario[i][j] += " x";
						if(j == 45)
							scenario[i][j] += " y";
						break;
					case 24:
						if(j == 48)
							scenario[i][j] += " y";
						break;
					case 25:
						if(j == 41)
							scenario[i][j] += " at";
						if(j == 57)
							scenario[i][j] += " ap";
						break;
					case 26:
						if(j == 33)
							scenario[i][j] += " pg";
						break;
					case 27:
						if(j == 0)
							scenario[i][j] += " y";
						if(j == 43)
							scenario[i][j] += " y";
						if(j == 47)
							scenario[i][j] += " pg";
						if(j == 54)
							scenario[i][j] += " z";
						if(j == 92)
							scenario[i][j] += " y";
						break;
					case 28:
						if(j == 37)
							scenario[i][j] += " z";
						break;
					case 29:
						if(j == 48)
							scenario[i][j] += " x";
						if(j == 57)
							scenario[i][j] += " f";
						break;
					case 30:
						if(j == 33)
							scenario[i][j] += " c";
						if(j == 43)
							scenario[i][j] += " f";
						if(j == 53)
							scenario[i][j] += " f";
						break;
					case 32:
						if(j == 75)
							scenario[i][j] += " at";
						break;
					case 33:
						if(j == 28)
							scenario[i][j] += " f";
						break;
					case 34:
						if(j == 3)
							scenario[i][j] += " at";
						if(j == 66)
							scenario[i][j] += " f";
						if(j == 84)
							scenario[i][j] += " c";
						break;
					case 35:
						if(j == 11)
							scenario[i][j] += " y";
						if(j == 25)
							scenario[i][j] += " tr";
						if(j == 72)
							scenario[i][j] += " tr";
						break;
					case 36:
						if(j == 8)
							scenario[i][j] += " am";
						break;
					case 37:
						if(j == 15)
							scenario[i][j] += " c";
						if(j == 66)
							scenario[i][j] += " c";
						if(j == 77)
							scenario[i][j] += " y";
						if(j == 85)
							scenario[i][j] += " f";
						break;
					case 38:
						if(j == 5)
							scenario[i][j] += " pm";
						break;
					case 39:
						if(j == 20)
							scenario[i][j] += " z";
						break;
					case 40:
						if(j == 1)
							scenario[i][j] += " f";
						if(j == 26)
							scenario[i][j] += " ap";
						if(j == 63)
							scenario[i][j] += " t4";
						break;
					case 41:
						if(j == 7)
							scenario[i][j] += " pg";
						if(j == 89)
							scenario[i][j] += " z";
						break;
					case 42:
						if(j == 73)
							scenario[i][j] += " f";
						if(j == 81)
							scenario[i][j] += " at";
						break;
					case 43:
						if(j == 13)
							scenario[i][j] += " ap";
						if(j == 21)
							scenario[i][j] += " t2";
						break;
					case 44:
						if(j == 4)
							scenario[i][j] += " x";
						if(j == 70)
							scenario[i][j] += " x";
						break;
					case 45:
						if(j == 26)
							scenario[i][j] += " c";
						if(j == 79)
							scenario[i][j] += " pg";
						if(j == 84)
							scenario[i][j] += " ap";
						break;
					case 46:
						if(j == 21)
							scenario[i][j] += " f";
						if(j == 66)
							scenario[i][j] += " a1";
						if(j == 90)
							scenario[i][j] += " pm";
						break;
					case 47:
						if(j == 39)
							scenario[i][j] += " x";
						if(j == 53)
							scenario[i][j] += " y";
						break;
					}



				}
			}
		}
		if(n==3){  //Mappa invernale
			for(int i = 0; i < 48; i++){
				for(int j = 0; j < 93; j++){
					//aggiungo terreni di base
					if(j < 31 && i < 16) //primo quadrato alto sx
						scenario[i][j] = "gh"; //aggiungo ghiaccio
					if(j >= 31 && j < 62 && i < 16) //secondo riquadro
						scenario[i][j] = "n"; //aggiungo neve
					if(j >= 62 && i < 16) // terzo riquadro
						scenario[i][j] = "gh"; //aggiungo ghiaccio
					if(j < 31 && i >= 16 && i < 32) // quarto riquadro
						scenario[i][j] = "n";  //aggiungo neve
					if(j >= 31 && j < 62 && i >= 16 && i < 32) //quinto riquadro
						scenario[i][j] = "gh"; //aggiungo ghiaccio
					if(j >= 62 && i >= 16 && i < 32) //sesto riquadro
						scenario[i][j] = "n"; //aggiungo neve
					if(j < 31 && i >= 32) //settimo riquadro
						scenario[i][j] = "n"; //aggiungo neve
					if(j >= 31 && j < 62 && i >= 32) //ottavo riquadro
						scenario[i][j] = "gh"; //aggiungo ghiaccio
					if(j >= 62 && i >= 32) //ottavo riquadro
						scenario[i][j] = "n"; //aggiungo neve

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
					switch(i) {
					case 0:
						if(j == 0)
							scenario[i][j] += " c";
						if(j == 39)
							scenario[i][j] += " x";
						if(j == 53)
							scenario[i][j] += " y";
						break;
					case 1:
						if(j == 8)
							scenario[i][j] += " tr";
						if(j == 79)
							scenario[i][j] += " f";
						if(j == 92)
							scenario[i][j] += " c";
						break;
					case 2:
						if(j == 19)
							scenario[i][j] += " f";
						if(j == 65)
							scenario[i][j] += " at";
						break;
					case 3:
						if(j == 4)
							scenario[i][j] += " x";
						break;
					case 4:
						if(j == 72)
							scenario[i][j] += " pg";
						if(j == 86)
							scenario[i][j] += " x";
						break;
					case 5:
						if(j == 24)
							scenario[i][j] += " y";
						break;
					case 7:
						if(j == 5)
							scenario[i][j] += " pg";
						if(j == 12)
							scenario[i][j] += " z";
						break;
					case 8:
						if(j == 67)
							scenario[i][j] += " tr";
						if(j == 73)
							scenario[i][j] += " z";
						if(j == 83)
							scenario[i][j] += " ap";
						break;
					case 10:
						if(j == 15)
							scenario[i][j] += " pm";
						break;
					case 11:
						if(j == 4)
							scenario[i][j] += " f";
						if(j == 19)
							scenario[i][j] += " f";
						if(j == 72)
							scenario[i][j] += " y";
						if(j == 90)
							scenario[i][j] += " f";
						break;
					case 12:
						if(j == 69)
							scenario[i][j] += " f";
						break;
					case 13:
						if(j == 8)
							scenario[i][j] += " am";
						if(j == 27)
							scenario[i][j] += " pg";
						if(j == 64)
							scenario[i][j] += " pg";
						break;
					case 14:
						if(j == 17)
							scenario[i][j] += " c";
						if(j == 83)
							scenario[i][j] += " pm";
						if(j == 92)
							scenario[i][j] += " am";
						break;
					case 15:
						if(j == 87)
							scenario[i][j] += " am";
						break;
					case 17:
						if(j == 41)
							scenario[i][j] += " z";
						break;
					case 18:
						if(j == 52)
							scenario[i][j] += " f";
						if(j == 60)
							scenario[i][j] += " f";
						break;
					case 19:
						if(j == 32)
							scenario[i][j] += " x";
						if(j == 49)
							scenario[i][j] += " ap";
						break;
					case 20:
						if(j == 0)
							scenario[i][j] += " x";
						if(j == 39)
							scenario[i][j] += " am";
						if(j == 54)
							scenario[i][j] += " f";
						if(j == 92)
							scenario[i][j] += " x";
						break;
					case 21:
						if(j == 37)
							scenario[i][j] += " c";
						if(j == 60)
							scenario[i][j] += " z";
						break;
					case 22:
						if(j == 53)
							scenario[i][j] += " y";
						break;
					case 23:
						if(j == 33)
							scenario[i][j] += " f";
						if(j == 41)
							scenario[i][j] += " x";
						if(j == 57)
							scenario[i][j] += " y";
						break;
					case 24:
						if(j == 47)
							scenario[i][j] += " y";
						break;
					case 25:
						if(j == 40)
							scenario[i][j] += " at";
						if(j == 61)
							scenario[i][j] += " ap";
						break;
					case 26:
						if(j == 34)
							scenario[i][j] += " pg";
						break;
					case 27:
						if(j == 0)
							scenario[i][j] += " y";
						if(j == 42)
							scenario[i][j] += " y";
						if(j == 49)
							scenario[i][j] += " pg";
						if(j == 56)
							scenario[i][j] += " z";
						if(j == 92)
							scenario[i][j] += " y";
						break;
					case 28:
						if(j == 55)
							scenario[i][j] += " z";
						break;
					case 29:
						if(j == 37)
							scenario[i][j] += " x";
						if(j == 60)
							scenario[i][j] += " f";
						break;
					case 30:
						if(j == 35)
							scenario[i][j] += " c";
						if(j == 44)
							scenario[i][j] += " f";
						if(j == 53)
							scenario[i][j] += " f";
						break;
					case 32:
						if(j == 75)
							scenario[i][j] += " at";
						break;
					case 33:
						if(j == 92)
							scenario[i][j] += " f";
						break;
					case 34:
						if(j == 3)
							scenario[i][j] += " at";
						if(j == 66)
							scenario[i][j] += " f";
						if(j == 84)
							scenario[i][j] += " c";
						break;
					case 35:
						if(j == 11)
							scenario[i][j] += " y";
						if(j == 0)
							scenario[i][j] += " tr";
						if(j == 25)
							scenario[i][j] += " tr";
						break;
					case 36:
						if(j == 30)
							scenario[i][j] += " am";
						break;
					case 37:
						if(j == 12)
							scenario[i][j] += " c";
						if(j == 63)
							scenario[i][j] += " c";
						if(j == 75)
							scenario[i][j] += " y";
						if(j == 89)
							scenario[i][j] += " f";
						break;
					case 38:
						if(j == 4)
							scenario[i][j] += " pm";
						break;
					case 39:
						if(j == 27)
							scenario[i][j] += " z";
						break;
					case 40:
						if(j == 19)
							scenario[i][j] += " f";
						if(j == 68)
							scenario[i][j] += " ap";
						if(j == 73)
							scenario[i][j] += " am";
						break;
					case 41:
						if(j == 9)
							scenario[i][j] += " pg";
						if(j == 91)
							scenario[i][j] += " z";
						break;
					case 42:
						if(j == 77)
							scenario[i][j] += " f";
						if(j == 21)
							scenario[i][j] += " at";
						break;
					case 43:
						if(j == 21)
							scenario[i][j] += " ap";
						if(j == 13)
							scenario[i][j] += " pg";
						break;
					case 44:
						if(j == 70)
							scenario[i][j] += " x";
						if(j == 4)
							scenario[i][j] += " x";
						break;
					case 45:
						if(j == 79)
							scenario[i][j] += " c";
						if(j == 26)
							scenario[i][j] += " pg";
						if(j ==8)
							scenario[i][j] += " ap";
						break;
					case 46:
						if(j == 22)
							scenario[i][j] += " f";
						if(j == 90)
							scenario[i][j] += " am";
						if(j == 66)
							scenario[i][j] += " pm";
						break;
					case 47:
						if(j == 39)
							scenario[i][j] += " x";
						if(j == 53)
							scenario[i][j] += " y";
						break;
					}
				}
			}
		}

		if(n==2){  //Mappa desertica
			for(int i = 0; i < 48; i++){
				for(int j = 0; j < 93; j++){
					//aggiungo terreni di base
					if(j < 31 && i < 16) //primo quadrato alto sx
						scenario[i][j] = "t"; //aggiungo terra
					if(j >= 31 && j < 62 && i < 16) //secondo riquadro
						scenario[i][j] = "d"; //aggiungo deserto
					if(j >= 62 && i < 16) // terzo riquadro
						scenario[i][j] = "t"; //aggiungo terra
					if(j < 31 && i >= 16 && i < 32) // quarto riquadro
						scenario[i][j] = "d";  //aggiungo deserto
					if(j >= 31 && j < 62 && i >= 16 && i < 32) //quinto riquadro
						scenario[i][j] = "v"; //aggiungo erba
					if(j >= 62 && i >= 16 && i < 32) //sesto riquadro
						scenario[i][j] = "d"; //aggiungo deserto
					if(j < 31 && i >= 32) //settimo riquadro
						scenario[i][j] = "v"; //aggiungo erba
					if(j >= 31 && j < 62 && i >= 32) //ottavo riquadro
						scenario[i][j] = "d"; //aggiungo deserto
					if(j >= 62 && i >= 32) //ottavo riquadro
						scenario[i][j] = "v"; //aggiungo erba
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
					switch(i) {
					case 0:
						if(j == 18)
							scenario[i][j] += " pg";
						if(j == 39)
							scenario[i][j] += " x";
						if(j == 53)
							scenario[i][j] += " y";
						break;
					case 1:
						if(j == 30)
							scenario[i][j] += " pm";
						if(j == 63)
							scenario[i][j] += " f";
						if(j == 88)
							scenario[i][j] += " pg";
						break;
					case 2:
						if(j == 65)
							scenario[i][j] += " f";
						if(j == 18)
							scenario[i][j] += " pp";
						break;
					case 3:
						if(j == 88)
							scenario[i][j] += " x";
						break;
					case 4:
						if(j == 87)
							scenario[i][j] += " pg";
						if(j == 73)
							scenario[i][j] += " x";
						break;
					case 5:
						if(j == 25)
							scenario[i][j] += " y";
						break;
					case 7:
						if(j == 13)
							scenario[i][j] += " pg";
						if(j == 14)
							scenario[i][j] += " z";
						break;
					case 8:
						if(j == 73)
							scenario[i][j] += " pm";
						if(j == 82)
							scenario[i][j] += " z";
						if(j == 68)
							scenario[i][j] += " pg";
						break;
					case 10:
						if(j == 16)
							scenario[i][j] += " pm";
						break;
					case 11:
						if(j == 19)
							scenario[i][j] += " f";
						if(j == 72)
							scenario[i][j] += " f";
						if(j == 90)
							scenario[i][j] += " y";
						if(j == 8)
							scenario[i][j] += " f";
						break;
					case 12:
						if(j == 92)
							scenario[i][j] += " f";
						break;
					case 13:
						if(j == 64)
							scenario[i][j] += " pg";
						if(j == 8)
							scenario[i][j] += " pp";
						if(j == 27)
							scenario[i][j] += " pm";
						break;
					case 14:
						if(j == 85)
							scenario[i][j] += " pg";
						if(j == 18)
							scenario[i][j] += " pm";
						if(j == 81)
							scenario[i][j] += " pp";
						break;
					case 15:
						if(j == 30)
							scenario[i][j] += " pg";
						break;
					case 17:
						if(j == 41)
							scenario[i][j] += " z";
						break;
					case 18:
						if(j == 32)
							scenario[i][j] += " f";
						if(j == 61)
							scenario[i][j] += " f";
						break;
					case 19:
						if(j == 50)
							scenario[i][j] += " x";
						if(j == 35)
							scenario[i][j] += " pp";
						break;
					case 20:
						if(j == 0)
							scenario[i][j] += " x";
						if(j == 55)
							scenario[i][j] += " am";
						if(j == 40)
							scenario[i][j] += " f";
						if(j == 92)
							scenario[i][j] += " x";
						break;
					case 21:
						if(j == 59)
							scenario[i][j] += " c";
						if(j == 38)
							scenario[i][j] += " z";
						break;
					case 22:
						if(j == 48)
							scenario[i][j] += " y";
						break;
					case 23:
						if(j == 57)
							scenario[i][j] += " f";
						if(j == 42)
							scenario[i][j] += " x";
						if(j == 33)
							scenario[i][j] += " y";
						break;
					case 24:
						if(j == 54)
							scenario[i][j] += " y";
						break;
					case 25:
						if(j == 61)
							scenario[i][j] += " at";
						if(j == 37)
							scenario[i][j] += " ap";
						break;
					case 26:
						if(j == 33)
							scenario[i][j] += " pg";
						break;
					case 27:
						if(j == 0)
							scenario[i][j] += " y";
						if(j == 59)
							scenario[i][j] += " y";
						if(j == 50)
							scenario[i][j] += " pg";
						if(j == 42)
							scenario[i][j] += " z";
						if(j == 92)
							scenario[i][j] += " y";
						break;
					case 28:
						if(j == 56)
							scenario[i][j] += " z";
						break;
					case 29:
						if(j == 60)
							scenario[i][j] += " x";
						if(j == 37)
							scenario[i][j] += " f";
						break;
					case 30:
						if(j == 53)
							scenario[i][j] += " c";
						if(j == 35)
							scenario[i][j] += " f";
						if(j == 44)
							scenario[i][j] += " f";
						break;
					case 32:
						if(j == 78)
							scenario[i][j] += " at";
						break;
					case 33:
						if(j == 91)
							scenario[i][j] += " f";
						break;
					case 34:
						if(j == 66)
							scenario[i][j] += " at";
						if(j == 83)
							scenario[i][j] += " f";
						if(j == 2)
							scenario[i][j] += " c";
						break;
					case 35:
						if(j == 1)
							scenario[i][j] += " y";
						if(j == 12)
							scenario[i][j] += " tr";
						if(j == 24)
							scenario[i][j] += " tr";
						break;
					case 36:
						if(j == 29)
							scenario[i][j] += " am";
						break;
					case 37:
						if(j == 75)
							scenario[i][j] += " c";
						if(j == 89)
							scenario[i][j] += " c";
						if(j == 12)
							scenario[i][j] += " y";
						if(j == 63)
							scenario[i][j] += " f";
						break;
					case 38:
						if(j == 88)
							scenario[i][j] += " pm";
						break;
					case 39:
						if(j == 21)
							scenario[i][j] += " z";
						break;
					case 40:
						if(j == 73)
							scenario[i][j] += " f";
						if(j == 19)
							scenario[i][j] += " ap";
						if(j == 68)
							scenario[i][j] += " am";
						break;
					case 41:
						if(j == 8)
							scenario[i][j] += " pg";
						if(j == 90)
							scenario[i][j] += " z";
						break;
					case 42:
						if(j == 22)
							scenario[i][j] += " f";
						if(j == 76)
							scenario[i][j] += " at";
						break;
					case 43:
						if(j == 14)
							scenario[i][j] += " ap";
						if(j == 25)
							scenario[i][j] += " pg";
						break;
					case 44:
						if(j == 69)
							scenario[i][j] += " x";
						if(j == 3)
							scenario[i][j] += " x";
						break;
					case 45:
						if(j == 91)
							scenario[i][j] += " c";
						if(j == 7)
							scenario[i][j] += " pg";
						if(j ==27)
							scenario[i][j] += " ap";
						break;
					case 46:
						if(j == 90)
							scenario[i][j] += " f";
						if(j == 65)
							scenario[i][j] += " am";
						if(j == 16)
							scenario[i][j] += " pm";
						break;
					case 47:
						if(j == 39)
							scenario[i][j] += " x";
						if(j == 53)
							scenario[i][j] += " y";
						break;
					}
				}
			}
		}
		//aggiungo municipi iniziali
		//su
		scenario[6][38] += " ecmunicipio1"; //età classica municipio 1
		scenario[6][39] += " ecmunicipio2";
		scenario[7][38] += " ecmunicipio3";
		scenario[7][39] += " ecmunicipio4";
		//sx
		scenario[19][9] += " ecmunicipio1";
		scenario[19][10] += " ecmunicipio2";
		scenario[20][9] += " ecmunicipio3";
		scenario[20][10] += " ecmunicipio4";
		//dx
		scenario[27][82] += " ecmunicipio1";
		scenario[27][83] += " ecmunicipio2";
		scenario[28][82] += " ecmunicipio3";
		scenario[28][83] += " ecmunicipio4";
		//giu
		scenario[40][53] += " ecmunicipio1";
		scenario[40][54] += " ecmunicipio2";
		scenario[41][53] += " ecmunicipio3";
		scenario[41][54] += " ecmunicipio4";

	}

	public String[][] getScenario() {
		return scenario;
	}

	public void setScenario(String[][] scenario) {
		this.scenario = scenario;
	}
}
