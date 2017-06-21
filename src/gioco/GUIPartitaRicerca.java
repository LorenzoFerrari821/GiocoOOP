package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIPartitaRicerca extends JFrame {
	
	private Font fontFuturist;
	private GridBagConstraints c;
	private JPanel contentPane;
	private ImageIcon iconTick;
	private ImageIcon iconCross;
	private JLabel lblTick;
	private JLabel lblCross;
	private ImageIcon newiconCosto;
	private int gridXEClassica;
	private int gridXMedioevo;
	private int gridXEVittoriana;
	
	private ValoriDiGioco valoriDiGioco; //Salvato qui per facilitarne l'accesso dalle funzioni di questa classe diverse dal costruttore
	private Giocatore giocatore;  //Salvato qui per facilitarne l'accesso dalle funzioni di questa classe diverse dal costruttore
	
	GUIPartitaRicerca(Giocatore giocatore, ValoriDiGioco valoriDiGioco) {
		
		int valX = 1; //dichiarato qui perchè serve solo per il corretto posizionamento orizzontale grafico delle ricerche
		
		try {
		    //Creo un font custom
		    fontFuturist = Font.createFont(Font.TRUETYPE_FONT, new File("media\\font_futurist_fixed.ttf")).deriveFont(12f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //registro il font
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("media\\font_futurist_fixed.ttf")));
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Ricerca");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 750, 500);
		setMinimumSize(new Dimension(650, 450));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new GridBagLayout());
		
		//Setting icone tick e cross
		iconTick = new ImageIcon("media/asset_grafici/icone/tick.png");
		Image scaleTick = iconTick.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconTick = new ImageIcon(scaleTick);
		lblTick = new JLabel();
		lblTick.setIcon(newiconTick);
		lblTick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		iconCross = new ImageIcon("media/asset_grafici/icone/cross.png");
		Image scaleCross = iconCross.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCross = new ImageIcon(scaleCross);
		lblCross = new JLabel();
		lblCross.setIcon(newiconCross);
		lblCross.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Setting icona costo
		ImageIcon iconCosto = new ImageIcon("media/asset_grafici/icone/ricerca.png");
		Image scaleCosto = iconCosto.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconCosto = new ImageIcon(scaleCosto);
		
		c = new GridBagConstraints();
		c.anchor = new GridBagConstraints().CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		
		contentPane.add(new JLabel("      "), c);
		
		c.gridx++;
		
		this.valoriDiGioco = valoriDiGioco;
		this.giocatore = giocatore;
		
		//Ricerca sentieri
		ImageIcon iconSentieri = new ImageIcon("media/asset_grafici/icone/1etaclassica/sentieri.png");
		Image scaleSentieri = iconSentieri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconSentieri = new ImageIcon(scaleSentieri);
		
		gridXEClassica = valX;
		inserisci1(valX, 7, "Sentieri", newiconSentieri);
		
		from1to3();
		
		//Ricerca abitazioni, caserma, fucina
		ImageIcon iconAbitazioni = new ImageIcon("media/asset_grafici/icone/1etaclassica/casa.png");
		Image scaleAbitazioni = iconAbitazioni.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconAbitazioni = new ImageIcon(scaleAbitazioni);
		ImageIcon iconCaserma = new ImageIcon("media/asset_grafici/icone/1etaclassica/caserma.png");
		Image scaleCaserma = iconCaserma.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCaserma = new ImageIcon(scaleCaserma);
		ImageIcon iconFucina = new ImageIcon("media/asset_grafici/icone/1etaclassica/fucina.png");
		Image scaleFucina = iconFucina.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconFucina = new ImageIcon(scaleFucina);
		
		valX += 4;
		inserisci3(valX, 4, "Abitazioni", "Caserma", "Fucina", newiconAbitazioni, newiconCaserma, newiconFucina);
		
		ImageIcon iconCommercio = new ImageIcon("media/asset_grafici/icone/1etaclassica/mercato.png");
		Image scaleCommercio = iconCommercio.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCommercio = new ImageIcon(scaleCommercio);
		ImageIcon iconFanteria = new ImageIcon("media/asset_grafici/icone/1etaclassica/fanteria.png");
		Image scaleFanteria = iconFanteria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconFanteria = new ImageIcon(scaleFanteria);
		
		if(giocatore.getCiviltà() != 1) //se la civiltà è diversa da romani
		{
			from3to2();
			
			//Ricerca commercio, fanteria
			valX += 4;
			inserisci2(valX, 4, "Commercio", "Fanteria", newiconCommercio, newiconFanteria);
			
			from2to3();
		}
		else
		{
			from3to3();
			
			//Ricerca commercio, fanteria, oreficeria
			ImageIcon iconOreficeria = new ImageIcon("media/asset_grafici/icone/1etaclassica/oreficeria.png");
			Image scaleOreficeria = iconOreficeria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconOreficeria = new ImageIcon(scaleOreficeria);
			
			valX += 4;
			inserisci3(valX, 4, "Commercio", "Fanteria", "Oreficeria", newiconCommercio, newiconFanteria, newiconOreficeria);
			
			from3to3();
		}
		
		//Ricerca religione, ville, tiro con l'arco
		ImageIcon iconReligione = new ImageIcon("media/asset_grafici/icone/1etaclassica/religione.png");
		Image scaleReligione = iconReligione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconReligione = new ImageIcon(scaleReligione);
		ImageIcon iconVille = new ImageIcon("media/asset_grafici/icone/1etaclassica/villa.png");
		Image scaleVille = iconVille.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVille = new ImageIcon(scaleVille);
		ImageIcon iconTiroConArco = new ImageIcon("media/asset_grafici/icone/1etaclassica/tiroconarco.png");
		Image scaleTiroConArco = iconTiroConArco.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconTiroConArco = new ImageIcon(scaleTiroConArco);
		
		valX += 4;
		inserisci3(valX, 4, "Religione", "Ville", "Tiro con l'arco", newiconReligione, newiconVille, newiconTiroConArco);
		
		if(giocatore.getCiviltà() != 1) //se la civiltà del giocatore è diversa da romani
		{
			from3to3();
			//Ricerca oracolo,città, cavalleria
			ImageIcon iconOracolo = new ImageIcon("media/asset_grafici/icone/1etaclassica/oracolo.png");
			Image scaleOracolo = iconOracolo.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconOracolo = new ImageIcon(scaleOracolo);
			ImageIcon iconCitta = new ImageIcon("media/asset_grafici/icone/1etaclassica/palazzo.png");
			Image scaleCitta = iconCitta.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconCitta = new ImageIcon(scaleCitta);
			ImageIcon iconCavalleria = new ImageIcon("media/asset_grafici/icone/1etaclassica/cavalleria.png");
			Image scaleCavalleria = iconCavalleria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconCavalleria = new ImageIcon(scaleCavalleria);
			
			valX += 4;
			inserisci3(valX, 4, "Oracolo", "Città", "Cavalleria", newiconOracolo, newiconCitta, newiconCavalleria);
			
			from3to1();
			
			if(giocatore.getCiviltà() == 4) //Se utilizziamo i sassoni
			{
				//Ricerca società militare
				ImageIcon iconSocMil = new ImageIcon("media/asset_grafici/icone/1etaclassica/societamilitare.png");
				Image scaleSocMil = iconSocMil.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
				ImageIcon newiconSocMil = new ImageIcon(scaleSocMil);
				
				valX += 4;
				inserisci1(valX, 7, "Società militare", newiconSocMil);
				
				from1to1();
			}
		}
		else //caso dei romani
		{
			from3to2();
			
			//Ricerca città e cavalleria
			ImageIcon iconCitta = new ImageIcon("media/asset_grafici/icone/1etaclassica/palazzo.png");
			Image scaleCitta = iconCitta.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconCitta = new ImageIcon(scaleCitta);
			ImageIcon iconCavalleria = new ImageIcon("media/asset_grafici/icone/1etaclassica/cavalleria.png");
			Image scaleCavalleria = iconCavalleria.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconCavalleria = new ImageIcon(scaleCavalleria);
			
			valX += 4;
			inserisci2(valX, 4, "Città", "Cavalleria", newiconCitta, newiconCavalleria);
			
			from2to1();

			//Ricerca età imperiale
			ImageIcon iconEtaImp = new ImageIcon("media/asset_grafici/icone/1etaclassica/etaimperiale.png");
			Image scaleEtaImp = iconEtaImp.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconEtaImp = new ImageIcon(scaleEtaImp);
			
			valX += 4;
			inserisci1(valX, 7, "Età imperiale", newiconEtaImp);
			
			from1to1();
		}
		
		//Ricerca strade lastricate
		ImageIcon iconLastr = new ImageIcon("media/asset_grafici/icone/2medioevo/lastricato.png");
		Image scaleLastr = iconLastr.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconLastr = new ImageIcon(scaleLastr);
		
		valX += 4;
		gridXMedioevo = valX;
		inserisci1(valX, 7, "Strade lastricate", newiconLastr);
		
		from1to3();
		
		//Ricerca case a più piani, corazze, balestre
		ImageIcon iconCasaPP = new ImageIcon("media/asset_grafici/icone/2medioevo/casapiupiani.png");
		Image scaleCasaPP = iconCasaPP.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCasaPP = new ImageIcon(scaleCasaPP);
		ImageIcon iconCorazze = new ImageIcon("media/asset_grafici/icone/2medioevo/corazze.png");
		Image scaleCorazze = iconCorazze.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCorazze = new ImageIcon(scaleCorazze);
		ImageIcon iconBalestre = new ImageIcon("media/asset_grafici/icone/2medioevo/balestre.png");
		Image scaleBalestre = iconBalestre.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconBalestre = new ImageIcon(scaleBalestre);
		
		valX += 4;
		inserisci3(valX, 4, "Case a più piani", "Corazze", "Balestre", newiconCasaPP, newiconCorazze, newiconBalestre);
		
		from3to3();
		
		//Ricerca biblioteca, mercenari, clero
		ImageIcon iconBiblioteca = new ImageIcon("media/asset_grafici/icone/2medioevo/biblioteca.png");
		Image scaleBiblioteca = iconBiblioteca.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconBiblioteca = new ImageIcon(scaleBiblioteca);
		ImageIcon iconMercenari = new ImageIcon("media/asset_grafici/icone/2medioevo/mercenari.png");
		Image scaleMercenari = iconMercenari.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconMercenari = new ImageIcon(scaleMercenari);
		ImageIcon iconClero = new ImageIcon("media/asset_grafici/icone/2medioevo/chiesa.png");
		Image scaleClero = iconClero.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconClero = new ImageIcon(scaleClero);
		
		valX += 4;
		inserisci3(valX, 4, "Biblioteca", "Mercenari", "Clero", newiconBiblioteca, newiconMercenari, newiconClero);
		
		from3to3();
		
		//Ricerca Ospedale, case a schiera, tattiche di cavalleria
		ImageIcon iconOspedale = new ImageIcon("media/asset_grafici/icone/2medioevo/ospedale.png");
		Image scaleOspedale = iconOspedale.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOspedale = new ImageIcon(scaleOspedale);
		ImageIcon iconCaseASchiera = new ImageIcon("media/asset_grafici/icone/2medioevo/casaschiera.png");
		Image scaleCaseASchiera = iconCaseASchiera.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCaseASchiera = new ImageIcon(scaleCaseASchiera);
		ImageIcon iconTatCal = new ImageIcon("media/asset_grafici/icone/2medioevo/tattichedicavalleria.png");
		Image scaleTatCal = iconTatCal.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconTatCal = new ImageIcon(scaleTatCal);
		
		valX += 4;
		inserisci3(valX, 4, "Ospedale", "Case a schiera", "Tattiche di cavalleria", newiconOspedale, newiconCaseASchiera, newiconTatCal);
		
		if(giocatore.getCiviltà() == 1) //se il giocatore ha civiltà romana
		{
			from3to2();
			
			//Ricerca inquisizione, granai
			ImageIcon iconInquisizione = new ImageIcon("media/asset_grafici/icone/2medioevo/inquisizione.png");
			Image scaleInquisizione = iconInquisizione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconInquisizione = new ImageIcon(scaleInquisizione);
			ImageIcon iconGranai = new ImageIcon("media/asset_grafici/icone/2medioevo/granai.png");
			Image scaleGranai = iconGranai.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconGranai = new ImageIcon(scaleGranai);
			
			valX += 4;
			inserisci2(valX, 4, "Inquisizione", "Granai", newiconInquisizione, newiconGranai);
			
			from2to2();
			
			//Ricerca fermentazione, polvere da sparo
			ImageIcon iconFermentazione = new ImageIcon("media/asset_grafici/icone/2medioevo/fermentazione.png");
			Image scaleFermentazione = iconFermentazione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconFermentazione = new ImageIcon(scaleFermentazione);
			ImageIcon iconPolvereDaSparo = new ImageIcon("media/asset_grafici/icone/2medioevo/polveredasparo.png");
			Image scalePolvereDaSparo = iconPolvereDaSparo.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconPolvereDaSparo = new ImageIcon(scalePolvereDaSparo);
			
			valX += 4;
			inserisci2(valX, 4, "Fermentazione", "Polvere da sparo", newiconFermentazione, newiconPolvereDaSparo);
			
			from2to1();
		}
		else
		{
			from3to1();
			
			//Ricerca granai
			ImageIcon iconGranai = new ImageIcon("media/asset_grafici/icone/2medioevo/granai.png");
			Image scaleGranai = iconGranai.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconGranai = new ImageIcon(scaleGranai);
			
			valX += 4;
			inserisci1(valX, 7, "Granai", newiconGranai);
			
			from1to3();
			
			//Ricerca fermentazione, banca, polvere da sparo
			ImageIcon iconFermentazione = new ImageIcon("media/asset_grafici/icone/2medioevo/fermentazione.png");
			Image scaleFermentazione = iconFermentazione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconFermentazione = new ImageIcon(scaleFermentazione);
			ImageIcon iconBanca = new ImageIcon("media/asset_grafici/icone/2medioevo/banca.png");
			Image scaleBanca = iconBanca.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconBanca = new ImageIcon(scaleBanca);
			ImageIcon iconPolvereDaSparo = new ImageIcon("media/asset_grafici/icone/2medioevo/polveredasparo.png");
			Image scalePolvereDaSparo = iconPolvereDaSparo.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconPolvereDaSparo = new ImageIcon(scalePolvereDaSparo);
			
			valX += 4;
			inserisci3(valX, 4, "Fermentazione", "Banca", "Polvere da sparo", newiconFermentazione, newiconBanca, newiconPolvereDaSparo);
			
			from3to1();
		}
		
		//Ricerca strade asfaltate
		ImageIcon iconStrAsf = new ImageIcon("media/asset_grafici/icone/3etavittoriana/asfalto.png");
		Image scaleStrAsf = iconStrAsf.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconStrAsf = new ImageIcon(scaleStrAsf);
		
		valX += 4;
		gridXEVittoriana = valX;
		inserisci1(valX, 7, "Strade asfaltate", newiconStrAsf);
		
		from1to2();
		
		//Ricerca sistema industriale, scienza
		ImageIcon iconSisInd = new ImageIcon("media/asset_grafici/icone/3etavittoriana/sistemaindustriale.png");
		Image scaleSisInd = iconSisInd.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconSisInd = new ImageIcon(scaleSisInd);
		ImageIcon iconScienza = new ImageIcon("media/asset_grafici/icone/3etavittoriana/scienza.png");
		Image scaleScienza = iconScienza.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconScienza = new ImageIcon(scaleScienza);
		
		valX += 4;
		inserisci2(valX, 4, "Sistema industriale", "Scienza", newiconSisInd, newiconScienza);
		
		from2to3();
		
		//Ricerca case con mansarda, fucili, gerarchia militare
		ImageIcon iconCasaMan = new ImageIcon("media/asset_grafici/icone/3etavittoriana/casamansarda.png");
		Image scaleCasaMan = iconCasaMan.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCasaMan = new ImageIcon(scaleCasaMan);
		ImageIcon iconFucili = new ImageIcon("media/asset_grafici/icone/3etavittoriana/fucili.png");
		Image scaleFucili = iconFucili.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconFucili = new ImageIcon(scaleFucili);
		ImageIcon iconGerMil = new ImageIcon("media/asset_grafici/icone/3etavittoriana/gerarchiamilitare.png");
		Image scaleGerMil = iconGerMil.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconGerMil = new ImageIcon(scaleGerMil);
		
		valX += 4;
		inserisci3(valX, 4, "Case con mansarda", "Fucili", "Gerarchia militare", newiconCasaMan, newiconFucili, newiconGerMil);
		
		from3to3();
		
		//Ricerca Società borghese, politica, tattiche in campo aperto
		ImageIcon iconSocBor = new ImageIcon("media/asset_grafici/icone/3etavittoriana/centrocittadino.png");
		Image scaleSocBor = iconSocBor.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconSocBor = new ImageIcon(scaleSocBor);
		ImageIcon iconPolitica = new ImageIcon("media/asset_grafici/icone/3etavittoriana/politica.png");
		Image scalePolitica = iconPolitica.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconPolitica = new ImageIcon(scalePolitica);
		ImageIcon iconTatCap = new ImageIcon("media/asset_grafici/icone/3etavittoriana/fuciliacavallo.png");
		Image scaleTatCap = iconTatCap.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconTatCap = new ImageIcon(scaleTatCap);
		
		valX += 4;
		inserisci3(valX, 4, "Società borghese", "Politica", "Tattiche in campo aperto", newiconSocBor, newiconPolitica, newiconTatCap);
		
		from3to3();
		
		if(giocatore.getCiviltà() == 1 || giocatore.getCiviltà() == 4) //se civiltà è romana o sassone
		{
			//Ricerca musica lirica, villette, balistica
			ImageIcon iconMusLir = new ImageIcon("media/asset_grafici/icone/3etavittoriana/opera.png");
			Image scaleMusLir = iconMusLir.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconMusLir = new ImageIcon(scaleMusLir);
			ImageIcon iconVillette = new ImageIcon("media/asset_grafici/icone/3etavittoriana/villette.png");
			Image scaleVillette = iconVillette.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconVillette = new ImageIcon(scaleVillette);
			ImageIcon iconBalistica = new ImageIcon("media/asset_grafici/icone/3etavittoriana/artiglieria.png");
			Image scaleBalistica = iconBalistica.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconBalistica = new ImageIcon(scaleBalistica);
			
			valX += 4;
			inserisci3(valX, 4, "Musica lirica", "Villette", "Balistica", newiconMusLir, newiconVillette, newiconBalistica);
			
			from3to1();
		}
		else
		{
			//Ricerca teatri, villette, balistica
			ImageIcon iconTeatri = new ImageIcon("media/asset_grafici/icone/3etavittoriana/teatro.png");
			Image scaleTeatri = iconTeatri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconTeatri = new ImageIcon(scaleTeatri);
			ImageIcon iconVillette = new ImageIcon("media/asset_grafici/icone/3etavittoriana/villette.png");
			Image scaleVillette = iconVillette.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconVillette = new ImageIcon(scaleVillette);
			ImageIcon iconBalistica = new ImageIcon("media/asset_grafici/icone/3etavittoriana/artiglieria.png");
			Image scaleBalistica = iconBalistica.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconBalistica = new ImageIcon(scaleBalistica);
			
			valX += 4;
			inserisci3(valX, 4, "Teatri", "Villette", "Balistica", newiconTeatri, newiconVillette, newiconBalistica);
			
			from3to1();
		}
		
		
		switch(giocatore.getCiviltà())
		{
		case 1: //romani
			//Ricerca Carabinieri
			ImageIcon iconCarabinieri = new ImageIcon("media/asset_grafici/icone/3etavittoriana/carabinieri.png");
			Image scaleCarabinieri = iconCarabinieri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconCarabinieri = new ImageIcon(scaleCarabinieri);
			
			valX += 4;
			inserisci1(valX, 7, "Carabinieri", newiconCarabinieri);
			break;
		case 2: //britanni
			//Ricerca guardie reali britanniche
			ImageIcon iconGuaRea = new ImageIcon("media/asset_grafici/icone/3etavittoriana/guardiareale.png");
			Image scaleGuaRea = iconGuaRea.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconGuaRea = new ImageIcon(scaleGuaRea);
			
			valX += 4;
			inserisci1(valX, 7, "Guardie reali", newiconGuaRea);
			break;
		case 3: //galli
			//Ricerca galli
			ImageIcon iconLegione = new ImageIcon("media/asset_grafici/icone/3etavittoriana/legionestraniera.png");
			Image scaleLegione = iconLegione.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconLegione = new ImageIcon(scaleLegione);
			
			valX += 4;
			inserisci1(valX, 7, "Legione straniera", newiconLegione);
			break;
		case 4: //sassoni
			//Ricerca gardenkorps
			ImageIcon iconGK = new ImageIcon("media/asset_grafici/icone/3etavittoriana/gardenkorps.png");
			Image scaleGK = iconGK.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
			ImageIcon newiconGK = new ImageIcon(scaleGK);
			
			valX += 4;
			inserisci1(valX, 7, "Gardenkorps", newiconGK);
			break;
		}
		
		c.gridx++;
		contentPane.add(new JLabel("      "), c);
		
		dipingiBackground(gridXEClassica, gridXMedioevo, gridXEVittoriana);
		
		add(new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
	
	public void dipingiBackground(int gridXEClassica, int gridXMedioevo, int gridXEVittoriana)
	{
		c.gridy = 1;
		c.gridx = gridXEClassica;
		JLabel lblECla = new JLabel("ETA CLASSICA");
		lblECla.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblECla, c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		
		c.gridy--;
		c.gridx = gridXMedioevo;
		JLabel lblMed = new JLabel("MEDIOEVO");
		lblMed.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblMed, c);
		c.gridy++;
		
		c.gridx = gridXEVittoriana;
		JLabel lblEVit = new JLabel("ETA VITTTORIANA");
		lblEVit.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblEVit, c);
		c.gridy++;
	}
	
	public void from1to1()
	{
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		c.gridy--;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
	}
	
	public void from1to2()
	{
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		c.gridy--;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		ImageIcon icon3su = new ImageIcon("media/asset_grafici/icone/frameRicerca/3su.png");
		Image scale3su = icon3su.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3su = new ImageIcon(scale3su);
		c.gridx++;
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(newicon3su);
		contentPane.add(lbl3su, c);
		
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		c.gridy--;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy--;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy--;
		ImageIcon icon2dxgiu = new ImageIcon("media/asset_grafici/icone/frameRicerca/2dxgiu.png");
		Image scale2dxgiu = icon2dxgiu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2dxgiu = new ImageIcon(scale2dxgiu);
		JLabel lbl2dxgiu = new JLabel();
		lbl2dxgiu.setIcon(newicon2dxgiu);
		contentPane.add(lbl2dxgiu, c);
	}
	
	public void from1to3() 
	{	
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		c.gridy--;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		ImageIcon icon4 = new ImageIcon("media/asset_grafici/icone/frameRicerca/4.png");
		Image scale4 = icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon4 = new ImageIcon(scale4);
		c.gridx++;
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(newicon4);
		contentPane.add(lbl4, c);
		
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		c.gridy--;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy--;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy--;
		ImageIcon icon2dxgiu = new ImageIcon("media/asset_grafici/icone/frameRicerca/2dxgiu.png");
		Image scale2dxgiu = icon2dxgiu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2dxgiu = new ImageIcon(scale2dxgiu);
		JLabel lbl2dxgiu = new JLabel();
		lbl2dxgiu.setIcon(newicon2dxgiu);
		contentPane.add(lbl2dxgiu, c);
		
		c.gridy += 4;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		ImageIcon icon2sudx = new ImageIcon("media/asset_grafici/icone/frameRicerca/2sudx.png");
		Image scale2sudx = icon2sudx.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2sudx = new ImageIcon(scale2sudx);
		JLabel lbl2sudx = new JLabel();
		lbl2sudx.setIcon(newicon2sudx);
		contentPane.add(lbl2sudx, c);
	}
	
	public void from2to1()
	{
		c.gridy -= 4;
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		ImageIcon icon2giusx = new ImageIcon("media/asset_grafici/icone/frameRicerca/2giusx.png");
		Image scale2giusx = icon2giusx.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2giusx = new ImageIcon(scale2giusx);
		JLabel lbl2giusx = new JLabel();
		lbl2giusx.setIcon(newicon2giusx);
		contentPane.add(lbl2giusx, c);
		
		c.gridy++;
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		ImageIcon icon3su = new ImageIcon("media/asset_grafici/icone/frameRicerca/3su.png");
		Image scale3su = icon3su.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3su = new ImageIcon(scale3su);
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void from2to2()
	{
		c.gridy -= 4;
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		ImageIcon icon3giu = new ImageIcon("media/asset_grafici/icone/frameRicerca/3giu.png");
		Image scale3giu = icon3giu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3giu = new ImageIcon(scale3giu);
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		ImageIcon icon3su = new ImageIcon("media/asset_grafici/icone/frameRicerca/3su.png");
		Image scale3su = icon3su.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3su = new ImageIcon(scale3su);
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void from3to1()
	{
		c.gridy -= 7;
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		ImageIcon icon2giusx = new ImageIcon("media/asset_grafici/icone/frameRicerca/2giusx.png");
		Image scale2giusx = icon2giusx.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2giusx = new ImageIcon(scale2giusx);
		JLabel lbl2giusx = new JLabel();
		lbl2giusx.setIcon(newicon2giusx);
		contentPane.add(lbl2giusx, c);
		
		c.gridy++;
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		ImageIcon icon4 = new ImageIcon("media/asset_grafici/icone/frameRicerca/4.png");
		Image scale4 = icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon4 = new ImageIcon(scale4);
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy++;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz3 = new JLabel();
		lblOriz3.setIcon(newiconOriz);
		contentPane.add(lblOriz3, c);
		
		c.gridx++;
		ImageIcon icon2sxsu = new ImageIcon("media/asset_grafici/icone/frameRicerca/2sxsu.png");
		Image scale2sxsu = icon2sxsu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2sxsu = new ImageIcon(scale2sxsu);
		JLabel lbl2sxsu = new JLabel();
		lbl2sxsu.setIcon(newicon2sxsu);
		contentPane.add(lbl2sxsu, c);
	}
	
	public void from3to2()
	{
		c.gridy -= 7;
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		ImageIcon icon3giu = new ImageIcon("media/asset_grafici/icone/frameRicerca/3giu.png");
		Image scale3giu = icon3giu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3giu = new ImageIcon(scale3giu);
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		ImageIcon icon3su = new ImageIcon("media/asset_grafici/icone/frameRicerca/3su.png");
		Image scale3su = icon3su.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3su = new ImageIcon(scale3su);
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void from2to3()
	{
		c.gridy -= 4;
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		ImageIcon icon3giu = new ImageIcon("media/asset_grafici/icone/frameRicerca/3giu.png");
		Image scale3giu = icon3giu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3giu = new ImageIcon(scale3giu);
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		ImageIcon icon4 = new ImageIcon("media/asset_grafici/icone/frameRicerca/4.png");
		Image scale4 = icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon4 = new ImageIcon(scale4);
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy++;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		ImageIcon icon2sudx = new ImageIcon("media/asset_grafici/icone/frameRicerca/2sudx.png");
		Image scale2sudx = icon2sudx.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon2sudx = new ImageIcon(scale2sudx);
		JLabel lbl2sudx = new JLabel();
		lbl2sudx.setIcon(newicon2sudx);
		contentPane.add(lbl2sudx, c);
	}
	
	public void from3to3()
	{
		c.gridy -= 7;
		ImageIcon iconOriz = new ImageIcon("media/asset_grafici/icone/frameRicerca/orizzontale.png");
		Image scaleOriz = iconOriz.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconOriz = new ImageIcon(scaleOriz);
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		ImageIcon icon3giu = new ImageIcon("media/asset_grafici/icone/frameRicerca/3giu.png");
		Image scale3giu = icon3giu.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3giu = new ImageIcon(scale3giu);
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		ImageIcon iconVert = new ImageIcon("media/asset_grafici/icone/frameRicerca/verticale.png");
		Image scaleVert = iconVert.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconVert = new ImageIcon(scaleVert);
		JLabel lblVert = new JLabel();
		lblVert.setIcon(newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		ImageIcon icon4 = new ImageIcon("media/asset_grafici/icone/frameRicerca/4.png");
		Image scale4 = icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon4 = new ImageIcon(scale4);
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy++;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz3 = new JLabel();
		lblOriz3.setIcon(newiconOriz);
		contentPane.add(lblOriz3, c);
		
		c.gridx++;
		ImageIcon icon3su = new ImageIcon("media/asset_grafici/icone/frameRicerca/3su.png");
		Image scale3su = icon3su.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newicon3su = new ImageIcon(scale3su);
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void inserisci2(int posx, int posy, String nome1, String nome2,
			ImageIcon icona1, ImageIcon icona2) {
		
		inserisci1(posx, posy, nome1, icona1);
		inserisci1(posx, posy+3, nome2, icona2);
	}
	
	public void inserisci3(int posx, int posy, String nome1, String nome2, String nome3, ImageIcon icona1, ImageIcon icona2,
			ImageIcon icona3) {
		inserisci1(posx, posy, nome1, icona1);
		inserisci1(posx, posy+3, nome2, icona2);
		inserisci1(posx, posy+6, nome3, icona3);
	}
	
	public void inserisci4(int posx, int posy, String nome1, String nome2, String nome3, String nome4, 
			ImageIcon icona1, ImageIcon icona2, ImageIcon icona3, ImageIcon icona4) {
		inserisci1(posx, posy, nome1, icona1);
		inserisci1(posx, posy+3, nome2, icona2);
		inserisci1(posx, posy+6, nome3, icona3);
		inserisci1(posx, posy+9, nome4, icona4);
	}
	
	public void inserisci1(int posx, int posy, String nome, ImageIcon icona) {

		//Aggiungo il nome della ricerca
		c.gridx = posx;
		c.gridy = posy;
		
		JLabel lblNome = new JLabel("  " + nome + "  ");
		lblNome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblNome, c);
		
		//Aggiungo l'icona della ricerca
		c.gridx++;
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(icona);
		lblIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblIcon, c);
		
		//Aggiungo il costo in punti ricerca
		c.gridx = posx;
		c.gridy++;
		JLabel lblCosto = new JLabel("  " + Integer.toString(valoriDiGioco.getValoriRicerche().get(nome)) + "  ");
		lblCosto.setIcon(newiconCosto);
		lblCosto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblCosto, c);
		
		
		//Aggiungo lo stato corrente della ricerca (tick o cross)
		c.gridx++;
		if(giocatore.getRicercheEffettuate().contains(nome)) {
			JLabel lblcpyTick = new JLabel();  //creata una copia della lblTick per poter utilizzare l'icona tick più volte
			lblcpyTick.setIcon(lblTick.getIcon());
			lblcpyTick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			contentPane.add(lblcpyTick, c);
		}
		else {
			JLabel lblcpyCross = new JLabel(); //creata una copia della lblCross per poter utilizzare l'icona cross più volte
			lblcpyCross.setIcon(lblCross.getIcon());
			lblcpyCross.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			contentPane.add(lblcpyCross, c);
		}
	}
}