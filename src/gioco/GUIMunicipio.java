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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUIMunicipio extends JFrame 
{
	private Font fontFuturist;
	private JPanel contentPane;
	private JPanel pnlMid;
	private JPanel pnlBot;
	
	private RoundedCornerButton btnIndietro;
	private RoundedCornerButton btnCreaEsercito;

	private Partita partita;
	private GUIPartita guiPartita;
	private IconeGrafiche iconeGrafiche;
	private ValoriDiGioco valoriDiGioco;
	
	private List<String> selezionati; //Voci al momento selezionate
	private Clip audio;
	
	private int nUnitaMunicipio;
	
	GUIMunicipio(Partita partita, GUIPartita guiPartita, ValoriDiGioco valoriDiGioco, IconeGrafiche iconeGrafiche)
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
		
		setTitle("Unit� nel municipio");
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
		
		selezionati = new ArrayList<String>(partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getUnitaMunicipio());
		
		nUnitaMunicipio = partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getUnitaMunicipio().size();
		
		pnlMid = new JPanel(new GridLayout(nUnitaMunicipio, 4));
		
		contentPane.add(new JScrollPane(pnlMid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		pnlBot = new JPanel(new GridLayout(1, 2, 0, 0));
		
		btnIndietro = new RoundedCornerButton();
		btnIndietro.setFont(fontFuturist.deriveFont(13f));
		btnIndietro.setText("INDIETRO");
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
		
		btnCreaEsercito = new RoundedCornerButton();
		btnCreaEsercito.setFont(fontFuturist.deriveFont(13f));
		btnCreaEsercito.setText("CREA ESERCITO");
		btnCreaEsercito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				audio.start();
				//creaEsercito();
				dispose();
			}
		});
		pnlBot.add(btnCreaEsercito);
		
		contentPane.add(pnlBot, BorderLayout.SOUTH);
		
		popolaDiTruppe();
		
		add(contentPane);
	}
	
	/*
	 * Riempie la pagina in base alle unit� militari selezionate
	 */
	public void popolaDiTruppe()
	{
		pnlMid.removeAll();
		int indice = 0;
		
		for(String nome: partita.getGiocatore().get(guiPartita.getIndiceProprietario()).getUnitaMunicipio())
		{
			aggiungiVoce(nome, iconeGrafiche.getIconeUMilitari().get(nome), indice);
			indice++;
		}
		
		pnlMid.setVisible(false);
		pnlMid.setVisible(true);
	}
	
	public void aggiungiVoce(String nome, ImageIcon icona, int indiceLista)
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
		
		if(selezionati.get(indiceLista).contains("EVIDENZIA"))
		{
			lblVoce.setOpaque(true);
			lblVoce.setBackground(Color.LIGHT_GRAY);
			lblAtk.setOpaque(true);
			lblAtk.setBackground(Color.LIGHT_GRAY);
			lblDef.setOpaque(true);
			lblDef.setBackground(Color.LIGHT_GRAY);
			lblVel.setOpaque(true);
			lblVel.setBackground(Color.LIGHT_GRAY);
		}
		
		lblVoce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionati.get(indiceLista).contains("EVIDENZIA"))
					selezionati.set(indiceLista, selezionati.get(indiceLista).substring(0, selezionati.get(indiceLista).length() - 9));
				else
					selezionati.set(indiceLista, selezionati.get(indiceLista) + "EVIDENZIA");
				popolaDiTruppe();
			}
		});
		lblAtk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionati.get(indiceLista).contains("EVIDENZIA"))
					selezionati.set(indiceLista, selezionati.get(indiceLista).substring(0, selezionati.get(indiceLista).length() - 9));
				else
					selezionati.set(indiceLista, selezionati.get(indiceLista) + "EVIDENZIA");
				popolaDiTruppe();
			}
		});
		lblDef.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionati.get(indiceLista).contains("EVIDENZIA"))
					selezionati.set(indiceLista, selezionati.get(indiceLista).substring(0, selezionati.get(indiceLista).length() - 9));
				else
					selezionati.set(indiceLista, selezionati.get(indiceLista) + "EVIDENZIA");
				popolaDiTruppe();
			}
		});
		lblVel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(selezionati.get(indiceLista).contains("EVIDENZIA"))
					selezionati.set(indiceLista, selezionati.get(indiceLista).substring(0, selezionati.get(indiceLista).length() - 9));
				else
					selezionati.set(indiceLista, selezionati.get(indiceLista) + "EVIDENZIA");
				popolaDiTruppe();
			}
		});
	}
}
