package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUIArruolaUnita extends JFrame {
	
	private Font fontFuturist;
	private JPanel contentPane;
	private JPanel pnlMid;
	private JPanel pnlBot1;
	private JPanel pnlBot2;
	private JPanel pnlBot3;
	
	private JLabel lblUDaArruolare;
	private JTextField txtUDaArruolare;
	private JLabel lblCostoTotale;
	private JLabel lblOroTot;
	private JLabel lblMatTot;
	
	private RoundedCornerButton btnIndietro;
	private RoundedCornerButton btnInfo;
	private RoundedCornerButton btnArruola;

	private Partita partita;
	private GUIPartita guiPartita;
	private IconeGrafiche iconeGrafiche;
	private ValoriDiGioco valoriDiGioco;
	
	private String selezionato; //Opzione al momento selezionata
	private Clip audio;
	
	GUIArruolaUnita(Partita partita, GUIPartita guiPartita, ValoriDiGioco valoriDiGioco, IconeGrafiche iconeGrafiche)
	{
		
		ImageIcon icona = new ImageIcon("media/Icona.png");                  //Carichiamo l'icona personalizzata
		Image scaledicona = icona.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		setIconImage(scaledicona);  

		Toolkit t1 = Toolkit.getDefaultToolkit();                                 //Cursore personalizzato
		Image img = t1.getImage("media/cursore.png");
		Point point = new Point(0,0);
		Cursor cursor = t1.createCustomCursor(img, point, "Cursore Personalizzato");
		setCursor(cursor); 
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
		
		setTitle("Arruola unità militari");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 650, 450);
		setMinimumSize(new Dimension(650, 450));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new BorderLayout(0,0));
		
		this.partita = partita;
		this.valoriDiGioco = valoriDiGioco;
		this.guiPartita = guiPartita;
		this.iconeGrafiche = iconeGrafiche;
		
		selezionato = null;
		
		pnlMid = new JPanel(new GridLayout(14, 6));
		
		contentPane.add(new JScrollPane(pnlMid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		//PANNELLO BOT 1
		pnlBot1 = new JPanel(new GridLayout(1, 2, 0, 0));
		
		lblUDaArruolare = new JLabel("Unità da arruolare: ");
		lblUDaArruolare.setFont(fontFuturist.deriveFont(13f));
		pnlBot1.add(lblUDaArruolare);
		
		txtUDaArruolare = new JTextField("1");
		pnlBot1.add(txtUDaArruolare);
		
		contentPane.add(pnlBot1, BorderLayout.SOUTH);
		
		//PANNELLO BOT 2
		pnlBot2 = new JPanel(new GridLayout(1, 3, 0, 0));
		
		lblCostoTotale = new JLabel("Costo totale: ");
		lblCostoTotale.setFont(fontFuturist.deriveFont(13f));
		pnlBot2.add(lblCostoTotale);
		
		lblOroTot = new JLabel(Integer.toString(Integer.parseInt(txtUDaArruolare.getText()) * valoriDiGioco.getValoriOro().get(selezionato)));
		lblOroTot.setIcon(iconeGrafiche.newiconOro);
		pnlBot2.add(lblOroTot);
		
		lblMatTot = new JLabel(Integer.toString(Integer.parseInt(txtUDaArruolare.getText()) * valoriDiGioco.getValoriMat().get(selezionato)));
		lblMatTot.setIcon(iconeGrafiche.newiconMat);
		pnlBot2.add(lblMatTot);
		
		contentPane.add(pnlBot2, BorderLayout.SOUTH);
		
		//PANNELLO BOT 3
		pnlBot3 = new JPanel(new GridLayout2(1, 3, 0, 0));
		
		btnIndietro = new RoundedCornerButton();
		btnIndietro.setFont(fontFuturist.deriveFont(13f));
		btnIndietro.setText("INDIETRO");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/suonoindietro.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				audio.start();
				dispose();
			}
		});
		pnlBot3.add(btnIndietro);
		
		btnInfo = new RoundedCornerButton();
		btnInfo.setFont(fontFuturist.deriveFont(13f));
		btnInfo.setText("INFORMAZIONI");
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				audio.start();
				daiInformazioni(selezionato);
			}
		});
		pnlBot3.add(btnInfo);
		
		btnArruola = new RoundedCornerButton();
		btnArruola.setFont(fontFuturist.deriveFont(13f));
		btnArruola.setText("ARRUOLA");
		btnArruola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				audio.start();
				//arruolaUnitaMilitare();
				dispose();
			}
		});
		pnlBot3.add(btnArruola);
		
		contentPane.add(pnlBot3, BorderLayout.SOUTH);
		
		add(contentPane);
	}
	
	public void daiInformazioni(String nome)
	{
		if(nome == null)
			JOptionPane.showMessageDialog(null, "Nessuna costruzione selezionata.\nPremi su una voce del negozio per selezionarla",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Sentiero") || nome.equals("Strada lastricata") || nome.equals("Strada asfaltata"))
			JOptionPane.showMessageDialog(null, nome+" viene utilizzata per collegare tutti gli edifici cittadini al municipio.\n"
					+ "Un edificio non collegato al municipio non fornisce bonus risorse e non può funzionare",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Casa") || nome.equals("Villa") || nome.equals("Casa a più piani") || nome.equals("Casa a schiera")
				|| nome.equals("Casa con mansarda") || nome.equals("Villetta"))
			JOptionPane.showMessageDialog(null, nome+" fornisce oro all'inizio di ogni turno.\nUn buon numero "
					+ "di abitazioni è necessario per un impero di successo",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Caserma") || nome.equals("Campo mercenari") || nome.equals("Caserma eroi"))
			JOptionPane.showMessageDialog(null, nome+" è un edificio utilizzato per reclutare potenti unità da "
					+ "schierare in battaglia.\nUn esercito potente è il segreto di ogni grande conquistatore",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Fucina") || nome.equals("Granaio") || nome.equals("Fabbrica") || nome.equals("Parlamento"))
			JOptionPane.showMessageDialog(null, nome+" è un edificio che fornisce Materiali all'inizio di ogni turno.\n"
					+ "Essenziali per non rischiare mai di esaurire le scorte",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Mercato") || nome.equals("Orefice") || nome.equals("Mastro birraio") || nome.equals("Banca")
				|| nome.equals("Centro cittadino") || nome.equals("Teatro"))
			JOptionPane.showMessageDialog(null, nome+" fornisce un bonus all'economia.\nOgni moneta "
					+ "guadagnata verrà moltiplicata per il bonus totale",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Tempio"))
			JOptionPane.showMessageDialog(null, nome+" fornisce punti ricerca aggiuntivi ad ogni turno.\n"
					+ "Francesi e inglesi inoltre possono reclutare i Druidi attraverso il tempio",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Palazzo"))
			JOptionPane.showMessageDialog(null, nome+" fornisce più oro per turno.\nAttraverso il Palazzo i romani possono "
					+ "reclutare i Pretoriani, i sassoni i Berserk",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Biblioteca") || nome.equals("Rogo") || nome.equals("Laboratorio"))
			JOptionPane.showMessageDialog(null, nome+" fornisce punti ricerca aggiuntivi ad ogni turno.\n"
					+ "Il progresso è il fulcro di ogni impero solido",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Campo mercenari"))
			JOptionPane.showMessageDialog(null, nome+" fornisce un bonus alla forza militare.\n"
					+ "Permette inoltre di reclutare Spadaccini mercenari e Arcieri mercenari",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Chiesa"))
			JOptionPane.showMessageDialog(null, nome+" permette di reclutare Cavalieri templari per i romani e Cavalieri crociati"
					+ "per francesi, inglesi e sassoni",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Ospedale"))
			JOptionPane.showMessageDialog(null, nome+" permette di reclutare Medico",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
		if(nome.equals("Opera"))
			JOptionPane.showMessageDialog(null, nome+" fornisce oro aggiuntivo ad ogni turno.\nIl popolo nei giorni di "
					+ "festa adora ascoltare buona musica",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
	}
	
	/*
	 * Riempie la pagina negozio in base all'età selezionata
	 */
	public void popolaNegozio(String eta, String evidenzia)
	{
		int aggiunte = 0;
		
		if(eta == "Classica")
		{
			pnlMid.removeAll();
			
			
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Sentieri"))
				{
					aggiungiVoce("Sentiero", iconeGrafiche.newiconSentieri, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Abitazioni"))
				{
					aggiungiVoce("Casa", iconeGrafiche.newiconAbitazioni, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Caserma"))
				{
					aggiungiVoce("Caserma", iconeGrafiche.newiconCaserma, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Fucina"))
				{
					aggiungiVoce("Fucina", iconeGrafiche.newiconFucina, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Commercio"))
				{
					aggiungiVoce("Mercato", iconeGrafiche.newiconCommercio, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Oreficeria"))
				{
					aggiungiVoce("Orefice", iconeGrafiche.newiconOreficeria, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Religione"))
				{
					aggiungiVoce("Tempio", iconeGrafiche.newiconReligione, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Ville"))
				{
					aggiungiVoce("Villa", iconeGrafiche.newiconVille, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Città"))
				{
					aggiungiVoce("Palazzo", iconeGrafiche.newiconCitta, eta, evidenzia);
					aggiunte++;
				}
			}
			
			for(int i = aggiunte; i < 11; i++)
			{
				pnlMid.add(new JLabel(" "));
				pnlMid.add(new JLabel(" "));
				pnlMid.add(new JLabel(" "));
			}
			
			pnlMid.setVisible(false);
			pnlMid.setVisible(true);
		}
		
		if(eta == "Medioevo")
		{
			pnlMid.removeAll();
			
			
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Strade lastricate"))
				{
					aggiungiVoce("Strada lastricata", iconeGrafiche.newiconLastr, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Case a più piani"))
				{
					aggiungiVoce("Casa a più piani", iconeGrafiche.newiconCasaPP, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Biblioteca"))
				{
					aggiungiVoce("Biblioteca", iconeGrafiche.newiconBiblioteca, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Mercenari"))
				{
					aggiungiVoce("Campo mercenari", iconeGrafiche.newiconMercenari, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Clero"))
				{
					aggiungiVoce("Chiesa", iconeGrafiche.newiconClero, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Ospedale"))
				{
					aggiungiVoce("Ospedale", iconeGrafiche.newiconOspedale, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Case a schiera"))
				{
					aggiungiVoce("Casa a schiera", iconeGrafiche.newiconCaseASchiera, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Inquisizione"))
				{
					aggiungiVoce("Rogo", iconeGrafiche.newiconInquisizione, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Granai"))
				{
					aggiungiVoce("Granaio", iconeGrafiche.newiconGranai, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Fermentazione"))
				{
					aggiungiVoce("Mastro birraio", iconeGrafiche.newiconFermentazione, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Banca"))
				{
					aggiungiVoce("Banca", iconeGrafiche.newiconBanca, eta, evidenzia);
					aggiunte++;
				}
			}
			
			for(int i = aggiunte; i < 11; i++)
			{
				pnlMid.add(new JLabel(" "));
				pnlMid.add(new JLabel(" "));
				pnlMid.add(new JLabel(" "));
			}
			
			pnlMid.setVisible(false);
			pnlMid.setVisible(true);
		}
		
		if(eta == "Vittoriana")
		{
			pnlMid.removeAll();
			
			
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Strade asfaltate"))
				{
					aggiungiVoce("Strada asfaltata", iconeGrafiche.newiconStrAsf, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Sistema industriale"))
				{
					aggiungiVoce("Fabbrica", iconeGrafiche.newiconSisInd, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Scienza"))
				{
					aggiungiVoce("Laboratorio", iconeGrafiche.newiconScienza, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Case con mansarda"))
				{
					aggiungiVoce("Casa con mansarda", iconeGrafiche.newiconCasaMan, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Gerarchia militare"))
				{
					aggiungiVoce("Caserma eroi", iconeGrafiche.newiconGerMil, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Musica lirica"))
				{
					aggiungiVoce("Opera", iconeGrafiche.newiconMusLir, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Società borghese"))
				{
					aggiungiVoce("Centro cittadino", iconeGrafiche.newiconSocBor, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Politica"))
				{
					aggiungiVoce("Parlamento", iconeGrafiche.newiconPolitica, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Teatri"))
				{
					aggiungiVoce("Teatro", iconeGrafiche.newiconTeatri, eta, evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Villette"))
				{
					aggiungiVoce("Villetta", iconeGrafiche.newiconVillette, eta, evidenzia);
					aggiunte++;
				}
			}
			
			for(int i = aggiunte; i < 11; i++)
			{
				pnlMid.add(new JLabel(" "));
				pnlMid.add(new JLabel(" "));
				pnlMid.add(new JLabel(" "));
			}
			
			pnlMid.setVisible(false);
			pnlMid.setVisible(true);
		}
	}
	
	public void aggiungiVoce(String nome, ImageIcon icona, String eta, String evidenzia)
	{
		JLabel lblVoce = new JLabel(nome);
		lblVoce.setIcon(icona);
		pnlMid.add(lblVoce);
		
		JLabel lblOro = new JLabel();
		lblOro.setText(valoriDiGioco.getValoriOro().get(nome).toString());
		lblOro.setIcon(iconeGrafiche.newiconOro);
		pnlMid.add(lblOro);
		
		JLabel lblMat = new JLabel();
		lblMat.setText(valoriDiGioco.getValoriMat().get(nome).toString());
		lblMat.setIcon(iconeGrafiche.newiconMat);
		pnlMid.add(lblMat);
		
		if(nome.equals(evidenzia))
		{
			lblVoce.setOpaque(true);
			lblVoce.setBackground(Color.LIGHT_GRAY);
			lblOro.setOpaque(true);
			lblOro.setBackground(Color.LIGHT_GRAY);
			lblMat.setOpaque(true);
			lblMat.setBackground(Color.LIGHT_GRAY);
		}
		
		lblVoce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				popolaNegozio(eta, nome);
				selezionato = nome;
			}
		});
		lblOro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				popolaNegozio(eta, nome);
				selezionato = nome;
			}
		});
		lblMat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				popolaNegozio(eta, nome);
				selezionato = nome;
			}
		});
	}
}
