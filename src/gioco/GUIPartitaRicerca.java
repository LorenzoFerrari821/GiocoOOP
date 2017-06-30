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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIPartitaRicerca extends JFrame {
	
	private Font fontFuturist;
	private GridBagConstraints c;
	private JPanel pnlFooter;
	private JPanel contentPane;
	private JLabel lblTick;
	private JLabel lblCross;
	private int gridXEClassica;
	private int gridXMedioevo;
	private int gridXEVittoriana;
	private RoundedCornerButton btnIndietro;
	private IconeGrafiche iconeGrafiche;
	
	private ValoriDiGioco valoriDiGioco; //Salvato qui per facilitarne l'accesso dalle funzioni di questa classe diverse dal costruttore
	private Partita partita;  //Salvato qui per facilitarne l'accesso dalle funzioni di questa classe diverse dal costruttore
	private GUIPartita guiPartita;
	
	private String proprietario;
	
	GUIPartitaRicerca(Partita partita, GUIPartita guiPartita, ValoriDiGioco valoriDiGioco, String proprietario) {
		
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
		setBounds(0, 0, 1000, 550);
		setMinimumSize(new Dimension(650, 450));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new GridBagLayout());
		pnlFooter = new JPanel(new BorderLayout(0,0));
		
		this.valoriDiGioco = valoriDiGioco;
		this.partita = partita;
		this.guiPartita = guiPartita;
		this.proprietario = proprietario;
		
		iconeGrafiche = new IconeGrafiche();
		
		creaGUI();
		btnIndietro = new RoundedCornerButton();
		btnIndietro.setFont(fontFuturist.deriveFont(13f));
		btnIndietro.setText("INDIETRO");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		pnlFooter.add(btnIndietro, BorderLayout.EAST);
		
		add(new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		add(pnlFooter, BorderLayout.SOUTH);
	}
	
	public void creaGUI()
	{
		int valX = 1; //dichiarato qui perchè serve solo per il corretto posizionamento orizzontale grafico delle ricerche
		
		//Setting icone tick e cross
		lblTick = new JLabel();
		lblTick.setIcon(iconeGrafiche.newiconTick);
		lblTick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblCross = new JLabel();
		lblCross.setIcon(iconeGrafiche.newiconCross);
		lblCross.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		c = new GridBagConstraints();
		c.anchor = new GridBagConstraints().CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		
		contentPane.add(new JLabel("      "), c);
		
		c.gridx++;
		
		//Ricerca sentieri
		gridXEClassica = valX;
		inserisci1(valX, 7, "Sentieri", iconeGrafiche.newiconSentieri);
		
		from1to3();
		
		//Ricerca abitazioni, caserma, fucina
		valX += 4;
		inserisci3(valX, 4, "Abitazioni", "Caserma", "Fucina", iconeGrafiche.newiconAbitazioni, 
				iconeGrafiche.newiconCaserma, iconeGrafiche.newiconFucina);
		
		if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà() != 1) //se la civiltà è diversa da romani
		{
			from3to2();
			
			//Ricerca commercio, fanteria
			valX += 4;
			inserisci2(valX, 4, "Commercio", "Fanteria", iconeGrafiche.newiconCommercio, iconeGrafiche.newiconFanteria);
			
			from2to3();
		}
		else
		{
			from3to3();
			
			//Ricerca commercio, fanteria, oreficeria
			valX += 4;
			inserisci3(valX, 4, "Commercio", "Fanteria", "Oreficeria", iconeGrafiche.newiconCommercio, 
					iconeGrafiche.newiconFanteria, iconeGrafiche.newiconOreficeria);
			
			from3to3();
		}
		
		//Ricerca religione, ville, tiro con l'arco
		valX += 4;
		inserisci3(valX, 4, "Religione", "Ville", "Tiro con l'arco", iconeGrafiche.newiconReligione,
				iconeGrafiche.newiconVille, iconeGrafiche.newiconTiroConArco);
		
		if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà() != 1) //se la civiltà del giocatore è diversa da romani
		{
			from3to3();
			//Ricerca oracolo,città, cavalleria
			valX += 4;
			inserisci3(valX, 4, "Oracolo", "Città", "Cavalleria", iconeGrafiche.newiconOracolo,
					iconeGrafiche.newiconCitta, iconeGrafiche.newiconCavalleria);
			
			from3to1();
			
			if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà() == 4) //Se utilizziamo i sassoni
			{
				//Ricerca società militare
				valX += 4;
				inserisci1(valX, 7, "Società militare", iconeGrafiche.newiconSocMil);
				
				from1to1();
			}
		}
		else //caso dei romani
		{
			from3to2();
			
			//Ricerca città e cavalleria
			valX += 4;
			inserisci2(valX, 4, "Città", "Cavalleria", iconeGrafiche.newiconCitta, iconeGrafiche.newiconCavalleria);
			
			from2to1();

			//Ricerca età imperiale
			valX += 4;
			inserisci1(valX, 7, "Età imperiale", iconeGrafiche.newiconEtaImp);
			
			from1to1();
		}
		
		//Ricerca strade lastricate
		valX += 4;
		gridXMedioevo = valX;
		inserisci1(valX, 7, "Strade lastricate", iconeGrafiche.newiconLastr);
		
		from1to3();
		
		//Ricerca case a più piani, corazze, balestre
		valX += 4;
		inserisci3(valX, 4, "Case a più piani", "Corazze", "Balestre", iconeGrafiche.newiconCasaPP,
				iconeGrafiche.newiconCorazze, iconeGrafiche.newiconBalestre);
		
		from3to3();
		
		//Ricerca biblioteca, mercenari, clero
		valX += 4;
		inserisci3(valX, 4, "Biblioteca", "Mercenari", "Clero", iconeGrafiche.newiconBiblioteca, 
				iconeGrafiche.newiconMercenari, iconeGrafiche.newiconClero);
		
		from3to3();
		
		//Ricerca Ospedale, case a schiera, tattiche di cavalleria
		valX += 4;
		inserisci3(valX, 4, "Ospedale", "Case a schiera", "Tattiche di cavalleria", iconeGrafiche.newiconOspedale, 
				iconeGrafiche.newiconCaseASchiera, iconeGrafiche.newiconTatCal);
		
		if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà() == 1) //se il giocatore ha civiltà romana
		{
			from3to2();
			
			//Ricerca inquisizione, granai
			valX += 4;
			inserisci2(valX, 4, "Inquisizione", "Granai", iconeGrafiche.newiconInquisizione, iconeGrafiche.newiconGranai);
			
			from2to2();
			
			//Ricerca fermentazione, polvere da sparo
			valX += 4;
			inserisci2(valX, 4, "Fermentazione", "Polvere da sparo", iconeGrafiche.newiconFermentazione,
					iconeGrafiche.newiconPolvereDaSparo);
			
			from2to1();
		}
		else
		{
			from3to1();
			
			//Ricerca granai
			valX += 4;
			inserisci1(valX, 7, "Granai", iconeGrafiche.newiconGranai);
			
			from1to3();
			
			//Ricerca fermentazione, banca, polvere da sparo
			valX += 4;
			inserisci3(valX, 4, "Fermentazione", "Banca", "Polvere da sparo", iconeGrafiche.newiconFermentazione, 
					iconeGrafiche.newiconBanca, iconeGrafiche.newiconPolvereDaSparo);
			
			from3to1();
		}
		
		//Ricerca strade asfaltate
		valX += 4;
		gridXEVittoriana = valX;
		inserisci1(valX, 7, "Strade asfaltate", iconeGrafiche.newiconStrAsf);
		
		from1to2();
		
		//Ricerca sistema industriale, scienza
		valX += 4;
		inserisci2(valX, 4, "Sistema industriale", "Scienza", iconeGrafiche.newiconSisInd, iconeGrafiche.newiconScienza);
		
		from2to3();
		
		//Ricerca case con mansarda, fucili, gerarchia militare
		valX += 4;
		inserisci3(valX, 4, "Case con mansarda", "Fucili", "Gerarchia militare", iconeGrafiche.newiconCasaMan,
				iconeGrafiche.newiconFucili, iconeGrafiche.newiconGerMil);
		
		from3to3();
		
		//Ricerca Società borghese, politica, tattiche in campo aperto
		valX += 4;
		inserisci3(valX, 4, "Società borghese", "Politica", "Tattiche in campo aperto", iconeGrafiche.newiconSocBor,
				iconeGrafiche.newiconPolitica, iconeGrafiche.newiconTatCap);
		
		from3to3();
		
		if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà() == 1 ||
				partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà() == 4) //se civiltà è romana o sassone
		{
			//Ricerca musica lirica, villette, balistica
			valX += 4;
			inserisci3(valX, 4, "Musica lirica", "Villette", "Balistica", iconeGrafiche.newiconMusLir, 
					iconeGrafiche.newiconVillette, iconeGrafiche.newiconBalistica);
			
			from3to1();
		}
		else
		{
			//Ricerca teatri, villette, balistica
			valX += 4;
			inserisci3(valX, 4, "Teatri", "Villette", "Balistica", iconeGrafiche.newiconTeatri,
					iconeGrafiche.newiconVillette, iconeGrafiche.newiconBalistica);
			
			from3to1();
		}
		
		
		switch(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getCiviltà())
		{
		case 1: //romani
			//Ricerca Carabinieri
			valX += 4;
			inserisci1(valX, 7, "Carabinieri", iconeGrafiche.newiconCarabinieri);
			break;
		case 2: //britanni
			//Ricerca guardie reali britanniche
			valX += 4;
			inserisci1(valX, 7, "Guardie reali", iconeGrafiche.newiconGuaRea);
			break;
		case 3: //galli
			//Ricerca galli
			valX += 4;
			inserisci1(valX, 7, "Legione straniera", iconeGrafiche.newiconLegione);
			break;
		case 4: //sassoni
			//Ricerca gardenkorps
			valX += 4;
			inserisci1(valX, 7, "Gardenkorps", iconeGrafiche.newiconGK);
			break;
		}
		
		c.gridx++;
		contentPane.add(new JLabel("      "), c);
		
		dipingiBackground(gridXEClassica, gridXMedioevo, gridXEVittoriana);
	}
	
	public void dipingiBackground(int gridXEClassica, int gridXMedioevo, int gridXEVittoriana)
	{
		c.gridy = 1;
		c.gridx = gridXEClassica;
		JLabel lblECla = new JLabel("ETÀ CLASSICA");
		lblECla.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblECla, c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		
		c.gridy -= 2;
		c.gridx = gridXMedioevo;
		JLabel lblMed = new JLabel("MEDIOEVO");
		lblMed.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblMed, c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		
		c.gridy -= 2;
		c.gridx = gridXEVittoriana;
		JLabel lblEVit = new JLabel("ETÀ VITTTORIANA");
		lblEVit.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblEVit, c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
		c.gridy++;
		contentPane.add(new JLabel("  "), c);
	}
	
	public void from1to1()
	{
		c.gridx++;
		c.gridy--;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
	}
	
	public void from1to2()
	{
		c.gridx++;
		c.gridy--;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(iconeGrafiche.newicon3su);
		contentPane.add(lbl3su, c);
		
		c.gridy--;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy--;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy--;
		JLabel lbl2dxgiu = new JLabel();
		lbl2dxgiu.setIcon(iconeGrafiche.newicon2dxgiu);
		contentPane.add(lbl2dxgiu, c);
	}
	
	public void from1to3() 
	{	
		c.gridx++;
		c.gridy--;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(iconeGrafiche.newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy--;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy--;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy--;
		JLabel lbl2dxgiu = new JLabel();
		lbl2dxgiu.setIcon(iconeGrafiche.newicon2dxgiu);
		contentPane.add(lbl2dxgiu, c);
		
		c.gridy += 4;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		JLabel lbl2sudx = new JLabel();
		lbl2sudx.setIcon(iconeGrafiche.newicon2sudx);
		contentPane.add(lbl2sudx, c);
	}
	
	public void from2to1()
	{
		c.gridy -= 4;
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl2giusx = new JLabel();
		lbl2giusx.setIcon(iconeGrafiche.newicon2giusx);
		contentPane.add(lbl2giusx, c);
		
		c.gridy++;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(iconeGrafiche.newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void from2to2()
	{
		c.gridy -= 4;
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(iconeGrafiche.newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(iconeGrafiche.newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void from3to1()
	{
		c.gridy -= 7;
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl2giusx = new JLabel();
		lbl2giusx.setIcon(iconeGrafiche.newicon2giusx);
		contentPane.add(lbl2giusx, c);
		
		c.gridy++;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(iconeGrafiche.newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy++;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz3 = new JLabel();
		lblOriz3.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz3, c);
		
		c.gridx++;
		JLabel lbl2sxsu = new JLabel();
		lbl2sxsu.setIcon(iconeGrafiche.newicon2sxsu);
		contentPane.add(lbl2sxsu, c);
	}
	
	public void from3to2()
	{
		c.gridy -= 7;
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(iconeGrafiche.newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(iconeGrafiche.newicon3su);
		contentPane.add(lbl3su, c);
	}
	
	public void from2to3()
	{
		c.gridy -= 4;
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(iconeGrafiche.newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(iconeGrafiche.newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy++;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		JLabel lbl2sudx = new JLabel();
		lbl2sudx.setIcon(iconeGrafiche.newicon2sudx);
		contentPane.add(lbl2sudx, c);
	}
	
	public void from3to3()
	{
		c.gridy -= 7;
		c.gridx++;
		JLabel lblOriz = new JLabel();
		lblOriz.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz, c);
		
		c.gridx++;
		JLabel lbl3giu = new JLabel();
		lbl3giu.setIcon(iconeGrafiche.newicon3giu);
		contentPane.add(lbl3giu, c);
		
		c.gridy++;
		JLabel lblVert = new JLabel();
		lblVert.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert, c);
		
		c.gridy++;
		JLabel lblVert2 = new JLabel();
		lblVert2.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert2, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz2 = new JLabel();
		lblOriz2.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz2, c);
		
		c.gridx++;
		JLabel lbl4 = new JLabel();
		lbl4.setIcon(iconeGrafiche.newicon4);
		contentPane.add(lbl4, c);
		
		c.gridy++;
		JLabel lblVert3 = new JLabel();
		lblVert3.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert3, c);
		
		c.gridy++;
		JLabel lblVert4 = new JLabel();
		lblVert4.setIcon(iconeGrafiche.newiconVert);
		contentPane.add(lblVert4, c);
		
		c.gridy++;
		c.gridx--;
		JLabel lblOriz3 = new JLabel();
		lblOriz3.setIcon(iconeGrafiche.newiconOriz);
		contentPane.add(lblOriz3, c);
		
		c.gridx++;
		JLabel lbl3su = new JLabel();
		lbl3su.setIcon(iconeGrafiche.newicon3su);
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
		
		JLabel lblcpyTick = new JLabel();  //creata una copia della lblTick per poter utilizzare l'icona tick più volte
		lblcpyTick.setIcon(lblTick.getIcon());
		lblcpyTick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lblcpyCross = new JLabel(); //creata una copia della lblCross per poter utilizzare l'icona cross più volte
		lblcpyCross.setIcon(lblCross.getIcon());
		lblcpyCross.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Aggiungo l'icona della ricerca
		c.gridx++;
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(icona);
		lblIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(!partita.getGiocatore().get(partita.getTurnoCorrente()).getProprietario().equals(proprietario)) {
					/*controllo che il turno non sia di un altro giocatore, se è di un altro giocatore non è permesso
					 * ricercare nulla e mostro un avviso che lo comunica.
					 */
					JOptionPane.showMessageDialog(null, "Puoi ricercare una nuova tecnologia solamente durante il tuo turno di gioco",
							"Attenzione", JOptionPane.DEFAULT_OPTION);
					
				}
				else
				{
					if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate().contains(nome)) //se già ricercata
					{
						JOptionPane.showMessageDialog(null, "La tecnologia selezionata è già stata ricercata",
								"Attenzione", JOptionPane.DEFAULT_OPTION);
					}
					else
					{
						if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getPuntiRicerca() < 
								valoriDiGioco.getValoriRicerche().get(nome)) //se i PR del giocatore sono insufficienti
						{
							JOptionPane.showMessageDialog(null, "Punti ricerca insufficienti",
									"Attenzione", JOptionPane.DEFAULT_OPTION);
						}
						else
						{
							int scelta =0;
							Integer rimanente = partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getPuntiRicerca() - 
									valoriDiGioco.getValoriRicerche().get(nome);
							scelta = JOptionPane.showConfirmDialog(
									    null, "Vuoi ricercare "+nome+" per "+valoriDiGioco.getValoriRicerche().get(nome).toString()+
									    " punti ricerca?\n(Rimarranno "+rimanente.toString()+" punti ricerca).", "Conferma",
									    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(scelta == 0) //se si
							{
								partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).setPuntiRicerca(rimanente);
								partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate().add(nome);
								contentPane.remove(lblcpyCross);
								c.gridx = posx+1;
								c.gridy = posy+1;
								contentPane.add(lblcpyTick, c);
								guiPartita.aggiornaDatiGUI();
								contentPane.setVisible(false); //fa una sorta di refresh
								contentPane.setVisible(true);
							}
						}
					}
				}
			}
		});
		contentPane.add(lblIcon, c);
		
		//Aggiungo il costo in punti ricerca
		c.gridx = posx;
		c.gridy++;
		JLabel lblCosto = new JLabel("  " + Integer.toString(valoriDiGioco.getValoriRicerche().get(nome)) + "  ");
		lblCosto.setIcon(iconeGrafiche.newiconCosto);
		lblCosto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblCosto, c);
		
		
		//Aggiungo lo stato corrente della ricerca (tick o cross)
		c.gridx++;
		
		if(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate().contains(nome)) 
		{
			contentPane.add(lblcpyTick, c);
		}
		else
		{
			contentPane.add(lblcpyCross, c);
		}
	}
}