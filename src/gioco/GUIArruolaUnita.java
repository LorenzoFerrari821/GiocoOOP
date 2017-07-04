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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUIArruolaUnita extends JFrame {
	
	private Font fontFuturist;
	private JPanel contentPane;
	private JPanel pnlMid;
	private JPanel pnlBot;
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
	
	private String edificio;
	
	GUIArruolaUnita(Partita partita, GUIPartita guiPartita, ValoriDiGioco valoriDiGioco, IconeGrafiche iconeGrafiche, String edificio)
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
		setBounds(0, 0, 700, 470);
		setMinimumSize(new Dimension(700, 470));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new BorderLayout(0,0));
		
		this.partita = partita;
		this.valoriDiGioco = valoriDiGioco;
		this.guiPartita = guiPartita;
		this.iconeGrafiche = iconeGrafiche;
		this.edificio = edificio;
		
		selezionato = null;
		
		pnlMid = new JPanel(new GridLayout(14, 6));
		
		contentPane.add(new JScrollPane(pnlMid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		pnlBot = new JPanel(new BorderLayout(0, 0));
		
		//PANNELLO BOT 1
		pnlBot1 = new JPanel(new GridLayout(1, 2, 0, 0));
		
		lblUDaArruolare = new JLabel("Unità da arruolare: ");
		lblUDaArruolare.setFont(fontFuturist.deriveFont(13f));
		pnlBot1.add(lblUDaArruolare);
		
		txtUDaArruolare = new JTextField("1");
		txtUDaArruolare.getDocument().addDocumentListener(new DocumentListener() {
			
			public void changedUpdate(DocumentEvent e) {
				gestisci();
			}
			public void removeUpdate(DocumentEvent e) {
				gestisci();
			}
			public void insertUpdate(DocumentEvent e) {
				gestisci();
			}

			public void gestisci() {
				boolean valido = true;
				
				if(txtUDaArruolare.getText().toString().length() <= 4)
				{
					if(isNumber(txtUDaArruolare.getText()))
				    {
						if(txtUDaArruolare.getText().toString().length() != 0)
							if(Integer.parseInt(txtUDaArruolare.getText()) > 0 && 
								Integer.parseInt(txtUDaArruolare.getText()) < 1001)
							{
								if(selezionato != null)
								{
									lblOroTot.setText(Integer.toString(Integer.parseInt(txtUDaArruolare.getText()) * valoriDiGioco.getValoriOro().get(selezionato)));
									lblMatTot.setText(Integer.toString(Integer.parseInt(txtUDaArruolare.getText()) * valoriDiGioco.getValoriMat().get(selezionato)));
								}
							}
							else
								valido = false;
				    }
				    else
				    	valido = false;
				}
				else
					valido = false;
				
				if(!valido)
				{
		    		JOptionPane.showMessageDialog(null, "Inserisci un numero valido di unità da arruolare (min 1, max 1000)",
		 				"Informazioni", JOptionPane.DEFAULT_OPTION);
		    	}
			 }
		});
		pnlBot1.add(txtUDaArruolare);
		
		pnlBot.add(pnlBot1, BorderLayout.NORTH);
		
		//PANNELLO BOT 2
		pnlBot2 = new JPanel(new GridLayout(1, 3, 0, 0));
		
		lblCostoTotale = new JLabel("Costo totale: ");
		lblCostoTotale.setFont(fontFuturist.deriveFont(13f));
		pnlBot2.add(lblCostoTotale);
		
		lblOroTot = new JLabel("0");
		lblOroTot.setIcon(iconeGrafiche.newiconOro);
		pnlBot2.add(lblOroTot);
		
		lblMatTot = new JLabel("0");
		lblMatTot.setIcon(iconeGrafiche.newiconMat);
		pnlBot2.add(lblMatTot);
		
		pnlBot.add(pnlBot2, BorderLayout.CENTER);
		
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
				if(arruolaUnitaMilitare(txtUDaArruolare.getText().toString()))
					dispose();
			}
		});
		pnlBot3.add(btnArruola);
		
		pnlBot.add(pnlBot3, BorderLayout.SOUTH);
		
		contentPane.add(pnlBot, BorderLayout.SOUTH);
		
		popolaDiTruppe(edificio, null);
		
		add(contentPane);
	}
	
	/**
	 * Metodo che permette di arruolare unità militari. Ritorna true se l'operazione è avvenuta con successo, false altrimenti
	 * @param numero
	 * @return
	 */
	public boolean arruolaUnitaMilitare(String numero)
	{
		boolean valido = true;
		
		if(isNumber(numero))
		{
			if(numero.length() != 0 && numero.length() <= 4 && Integer.parseInt(numero) > 0 && Integer.parseInt(numero) <= 1000)
			{
				//Controlliamo che il giocatore abbia sufficiente oro e materiali
				if(partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getOro() >= Integer.parseInt(lblOroTot.getText().toString()) && 
				partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getMateriali() >= Integer.parseInt(lblMatTot.getText().toString()))
				{
					//Scalo l'oro e i materiali dal giocatore
					partita.getGiocatore().get(guiPartita.getIndiceProprietario()).setOro(
							partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getOro() - 
							Integer.parseInt(lblOroTot.getText().toString()));
					partita.getGiocatore().get(guiPartita.getIndiceProprietario()).setMateriali(
							partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getMateriali() - 
							Integer.parseInt(lblOroTot.getText().toString()));
					
					//refresh della gui
					guiPartita.aggiornaDatiGUI();
					
					//Registro le nuove unità militari nelle truppe del giocatore (CHE FINISCONO NEL MUNICIPIO)
					for(int i = 0; i < Integer.parseInt(txtUDaArruolare.getText().toString()); i++)
					{
						partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getUnitaMunicipio().add(selezionato);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Risorse per l'acquisto insufficienti",
							"Informazioni", JOptionPane.DEFAULT_OPTION);
					return false;
				}
			}
			else
				valido = false;
		}
		else
			valido = false;
		
		if(!valido)
		{
			JOptionPane.showMessageDialog(null, "Inserisci un numero valido di unità da arruolare (min 1, max 1000)",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
			return false;
		}
		
		return true;
	}
	
	public boolean isNumber(String s)
	{
		char[] sArray = s.toCharArray();
		int n;
		
		for(char c: sArray)
		{
			n = (int)c;
			if(n < 48 || n > 57)
				return false;
		}
		return true;
	}
	
	public void daiInformazioni(String nome)
	{
		if(nome == null)
			JOptionPane.showMessageDialog(null, "Nessuna unità militare selezionata.\nPremi su un'unità per selezionarla",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
		else
			JOptionPane.showMessageDialog(null, "Le unità militari sono la punta di diamante di ogni impero potente.\n"
					+ "Ti permettono di formare armate da schierare in difesa del municipio o per conquistare nuove terre",
					"Informazioni", JOptionPane.DEFAULT_OPTION);
	}
	
	/*
	 * Riempie la pagina in base all'unità militare selezionata
	 */
	public void popolaDiTruppe(String edificio, String evidenzia)
	{
		int aggiunte = 0;
		String nome;
		pnlMid.removeAll();
		
		if(edificio.equals("Caserma"))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Caserma"))
				{
					nome = "Miliziano";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Fanteria"))
				{
					nome = "Soldato";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					nome = "Spadaccino";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte += 2;
				}
				else
				if(obj.equals("Tiro con l'arco"))
				{
					nome = "Arciere";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Cavalleria"))
				{
					nome = "Cavaliere";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Corazze"))
				{
					nome = "Soldato in armatura pesante";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Balestre"))
				{
					nome = "Balestriere";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Tattiche di cavalleria"))
				{
					nome = "Cavaliere pesante";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Polvere da sparo"))
				{
					nome = "Cannone";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Fucili"))
				{
					nome = "Fuciliere";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					nome = "Mitragliere";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					nome = "Granatiere";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Tattiche in campo aperto"))
				{
					nome = "Fuciliere a cavallo";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Balistica"))
				{
					nome = "Artiglieria";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
		}
		else
		if(edificio.equals("Tempio"))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Oracolo"))
				{
					nome = "Druido";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
		}
		else
		if(edificio.equals("Palazzo"))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Età imperiale"))
				{
					nome = "Pretoriano";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals("Società militare"))
				{
					nome = "Berserk";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
		}
		else
		if(edificio.equals("Campo mercenari"))
		{
			nome = "Spadaccino mercenario";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
			nome = "Arciere mercenario";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals("Chiesa"))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Inquisizione"))
				{
					nome = "Cavaliere templare";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
			nome = "Cavaliere crociato";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals("Ospedale"))
		{
			nome = "Medico";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals("Caserma eroi"))
		{
			nome = "Lorenzo";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
			nome = "Werther";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals("Parlamento"))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals("Carabinieri"))
				{
					nome = "Carabinieri";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
				else
				if(obj.equals("Guardie reali"))
				{
					nome = "Guardia reale";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
				else
				if(obj.equals("Legione straniera"))
				{
					nome = "Legione straniera";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
				else
				if(obj.equals("Gardenkorps"))
				{
					nome = "Gardenkorps";
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
			}
		}
		
		pnlMid.setVisible(true);
			
		for(int i = aggiunte; i < 14; i++)
		{
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
		}
	}
	
	public void aggiungiVoce(String nome, ImageIcon icona, String evidenzia)
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
		
		JLabel lblAtk = new JLabel();
		lblAtk.setText(valoriDiGioco.getAtkUnita().get(nome).toString());
		lblAtk.setIcon(iconeGrafiche.newiconAtk);
		pnlMid.add(lblAtk);
		
		JLabel lblDef = new JLabel();
		lblDef.setText(valoriDiGioco.getDefUnita().get(nome).toString());
		lblDef.setIcon(iconeGrafiche.newiconDef);
		pnlMid.add(lblDef);
		
		JLabel lblVel = new JLabel();
		lblVel.setText(valoriDiGioco.getVelUnita().get(nome).toString());
		lblVel.setIcon(iconeGrafiche.newiconVel);
		pnlMid.add(lblVel);
		
		if(nome.equals(evidenzia))
		{
			lblVoce.setOpaque(true);
			lblVoce.setBackground(Color.LIGHT_GRAY);
			lblOro.setOpaque(true);
			lblOro.setBackground(Color.LIGHT_GRAY);
			lblMat.setOpaque(true);
			lblMat.setBackground(Color.LIGHT_GRAY);
			lblAtk.setOpaque(true);
			lblAtk.setBackground(Color.LIGHT_GRAY);
			lblDef.setOpaque(true);
			lblDef.setBackground(Color.LIGHT_GRAY);
			lblVel.setOpaque(true);
			lblVel.setBackground(Color.LIGHT_GRAY);

			//aggiorno costi in oro e materiali in base all'unità selezionata
			if(txtUDaArruolare.getText().toString().length() == 0)
				txtUDaArruolare.setText("1");
			txtUDaArruolare.setText(txtUDaArruolare.getText());
		}
		
		lblVoce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionato != nome)
				{
					selezionato = nome;
					popolaDiTruppe(edificio, nome);
				}
			}
		});
		lblOro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionato != nome)
				{
					selezionato = nome;
					popolaDiTruppe(edificio, nome);
				}
			}
		});
		lblMat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionato != nome)
				{
					selezionato = nome;
					popolaDiTruppe(edificio, nome);
				}
			}
		});
		lblAtk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionato != nome)
				{
					selezionato = nome;
					popolaDiTruppe(edificio, nome);
				}
			}
		});
		lblDef.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionato != nome)
				{
					selezionato = nome;
					popolaDiTruppe(edificio, nome);
				}
			}
		});
		lblVel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionato != nome)
				{
					selezionato = nome;
					popolaDiTruppe(edificio, nome);
				}
			}
		});
	}
}
