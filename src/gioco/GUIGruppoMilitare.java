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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUIGruppoMilitare extends JDialog {

	private Font fontFuturist;
	private JPanel contentPane;
	private JPanel pnlMid;
	private JPanel pnlBot;
	private int nRighe; //Numero di righe nel gruppo militare
	
	private RoundedCornerButton btnIndietro;
	private RoundedCornerButton btnAttacca;
	private RoundedCornerButton btnMuovi;
	
	private Partita partita;
	private GUIPartita guiPartita;
	private IconeGrafiche iconeGrafiche;
	private ValoriDiGioco valoriDiGioco;
	
	private Clip audio;
	
	private int nUnitaMilitari;
	
	private GruppoMilitare gruppoMilitare;
	
	GUIGruppoMilitare(Partita partita, GUIPartita guiPartita, ValoriDiGioco valoriDiGioco, IconeGrafiche iconeGrafiche, GruppoMilitare gruppoMilitare)
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
		
		if(gruppoMilitare.getCivilta() == partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getCiviltà()) // alleato
			setTitle(Global.getLabels("s45"));
		else
			setTitle(Global.getLabels("s46"));
		setBounds(0, 0, 775, 510);
		setMinimumSize(new Dimension(775, 510));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new BorderLayout(0,0));
		setModal(true);
		
		this.partita = partita;
		this.valoriDiGioco = valoriDiGioco;
		this.guiPartita = guiPartita;
		this.iconeGrafiche = iconeGrafiche;
		this.gruppoMilitare = gruppoMilitare;
		
		nUnitaMilitari = gruppoMilitare.getGruppoMilitare().size();
		
		nRighe = 10;
		if(nUnitaMilitari > nRighe)
			nRighe = nUnitaMilitari;
		pnlMid = new JPanel(new GridLayout(nRighe, 4));
		
		contentPane.add(new JScrollPane(pnlMid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		pnlBot = new JPanel(new GridLayout(1, 3, 0, 0));
		
		btnIndietro = new RoundedCornerButton();
		btnIndietro.setFont(fontFuturist.deriveFont(13f));
		btnIndietro.setText(Global.getLabels("a0"));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				audio.start();
				dispose();
			}
		});
		pnlBot.add(btnIndietro);
		
		if(gruppoMilitare.getCivilta() == partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getCiviltà())
		{
			btnAttacca = new RoundedCornerButton();
			btnAttacca.setFont(fontFuturist.deriveFont(13f));
			btnAttacca.setText(Global.getLabels("s47"));
			btnAttacca.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					try {
						audio = AudioSystem.getClip();
						audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					audio.start();
					guiPartita.gruppoMilitareAttacca(gruppoMilitare);
					dispose();
				}
			});
			pnlBot.add(btnAttacca);
			
			btnMuovi = new RoundedCornerButton();
			btnMuovi.setFont(fontFuturist.deriveFont(13f));
			btnMuovi.setText(Global.getLabels("s48"));
			btnMuovi.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					try {
						audio = AudioSystem.getClip();
						audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					audio.start();
					guiPartita.gruppoMilitareMuovi(gruppoMilitare);
					dispose();
				}
			});
			pnlBot.add(btnMuovi);
		}
		
		contentPane.add(pnlBot, BorderLayout.SOUTH);
		
		popolaDiTruppe();
		
		add(contentPane);
	}
	
	/*
	 * Riempie la pagina con le unità militari
	 */
	public void popolaDiTruppe()
	{
		int indice = 0;
		
		for(String nome: gruppoMilitare.getGruppoMilitare())
		{
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome));
			indice++;
		}
		
		for(int k = indice; k < nRighe; k++)
		{
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
			pnlMid.add(new JLabel(" "));
		}
		
		pnlMid.setVisible(false);
		pnlMid.setVisible(true);
	}
	
	public void aggiungiVoce(String nome, ImageIcon icona)
	{
		JLabel lblVoce = new JLabel(nome);
		lblVoce.setIcon(icona);
		pnlMid.add(lblVoce);
		
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
	}
}
