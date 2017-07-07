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
import javax.swing.JDialog;
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

public class GUIArruolaUnita extends JDialog {
	
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
		
		setTitle(Global.getLabels("s36"));
		setBounds(0, 0, 700, 470);
		setMinimumSize(new Dimension(700, 470));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new BorderLayout(0,0));
		setModal(true);
		
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
		
		lblUDaArruolare = new JLabel(Global.getLabels("s37"));
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
		    		JOptionPane.showMessageDialog(null,Global.getLabels("s38"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
		    	}
			 }
		});
		pnlBot1.add(txtUDaArruolare);
		
		pnlBot.add(pnlBot1, BorderLayout.NORTH);
		
		//PANNELLO BOT 2
		pnlBot2 = new JPanel(new GridLayout(1, 3, 0, 0));
		
		lblCostoTotale = new JLabel(Global.getLabels("s39"));
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
		btnIndietro.setText(Global.getLabels("a0"));
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
		btnInfo.setText(Global.getLabels("a5"));
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
		btnArruola.setText(Global.getLabels("s40"));
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
				if(selezionato != null)
				{
					if(arruolaUnitaMilitare(txtUDaArruolare.getText().toString()))
						dispose();
				}
				else
					JOptionPane.showMessageDialog(null, Global.getLabels("s41"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
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
					JOptionPane.showMessageDialog(null, Global.getLabels("s42"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
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
			JOptionPane.showMessageDialog(null, Global.getLabels("s38"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
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
			JOptionPane.showMessageDialog(null,Global.getLabels("s43") ,Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
		else
			JOptionPane.showMessageDialog(null, Global.getLabels("s44"),Global.getLabels("a5"), JOptionPane.DEFAULT_OPTION);
	}
	
	/*
	 * Riempie la pagina in base all'unità militare selezionata
	 */
	public void popolaDiTruppe(String edificio, String evidenzia)
	{
		int aggiunte = 0;
		String nome;
		pnlMid.removeAll();
		
		if(edificio.equals(Global.getLabels("i0")))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals(Global.getLabels("i0")))
				{
					nome = Global.getLabels("i1");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i48")))
				{
					nome = Global.getLabels("i2");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					nome = Global.getLabels("i3");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte += 2;
				}
				else
				if(obj.equals(Global.getLabels("i4")))
				{
					nome = Global.getLabels("i5");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i6")))
				{
					nome = Global.getLabels("i7");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i8")))
				{
					nome = Global.getLabels("i9");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i10")))
				{
					nome = Global.getLabels("i11");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i12")))
				{
					nome = Global.getLabels("i13");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i14")))
				{
					nome = Global.getLabels("i15");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i16")))
				{
					nome = Global.getLabels("i17");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					nome = Global.getLabels("i18");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					nome = Global.getLabels("i19");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i20")))
				{
					nome = Global.getLabels("i21");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i22")))
				{
					nome = Global.getLabels("i23");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
		}
		else
		if(edificio.equals(Global.getLabels("i24")))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals(Global.getLabels("i25")))
				{
					nome = Global.getLabels("i26");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
		}
		else
		if(edificio.equals(Global.getLabels("i27")))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals(Global.getLabels("i28")))
				{
					nome = Global.getLabels("i29");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
				else
				if(obj.equals(Global.getLabels("i30")))
				{
					nome = Global.getLabels("i31");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
		}
		else
		if(edificio.equals(Global.getLabels("i32")))
		{
			nome = Global.getLabels("i33");
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
			nome = Global.getLabels("i34");
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals(Global.getLabels("i35")))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals(Global.getLabels("i36")))
				{
					nome = Global.getLabels("i37");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
				}
			}
			nome = Global.getLabels("i38");
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals(Global.getLabels("i39")))
		{
			nome = Global.getLabels("i40");
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals(Global.getLabels("i41")))
		{
			nome = "Lorenzo";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
			nome = "Werther";
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
			aggiunte++;
		}
		else
		if(edificio.equals(Global.getLabels("i42")))
		{
			for(String obj: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getRicercheEffettuate()) //per ogni ricerca
			{
				if(obj.equals(Global.getLabels("i43")))
				{
					nome = Global.getLabels("i43");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
				else
				if(obj.equals(Global.getLabels("i44")))
				{
					nome = Global.getLabels("i45");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
				else
				if(obj.equals(Global.getLabels("i46")))
				{
					nome = Global.getLabels("i46");
					aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), evidenzia);
					aggiunte++;
					break;
				}
				else
				if(obj.equals(Global.getLabels("i47")))
				{
					nome = Global.getLabels("i47");
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
