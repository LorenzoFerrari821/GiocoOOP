package gioco;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Questa classe si occupa di organizzare e definire l'interfaccia grafica
 * del sottomen� 'Nuova Partita', contiene anche i mouseListener dei relativi
 * pulsanti in essa contenuti e le azioni da compiere.
 * @author Werther&Lorenzo
 *
 */
public class GUINuovaPartita extends JPanel {

	private JPanel pnlMenu;
	private GridBagConstraints c;
	private JTextField txtNomeGiocatore;
	private JCheckBox chkTut;
	private JComboBox<String> listMappa;
	private JComboBox<String> listCivilta;
	private JComboBox<String> listDifficolta;
	private RoundedCornerButton btnIndietro;
	private RoundedCornerButton btnAvvia;
	private JLabel lblNomeGiocatore;
	private JLabel lblTutorial;
	private JLabel lblMappa;
	private JLabel lblCivilta;
	private JLabel lblDifficolta;
	private Font fontFuturist;
	private GUIPartita framePartita;
	private ConnectionDB s = null;
	private String Tutorial;
	private Clip audio;
	private int k;
	private Window[] finestreAttive;
	private GUIMenuPrincipale guiMenuPrincipale; //Inserito per avere un riferimento a menu principale
	private GUINuovaPartita guiNuovaPartita;
	
	/**
	 * Costruttore della classe; molto corposo poich� si occupa di posizionare ogni elemento
	 * all'interno dell'interfaccia.
	 */
	GUINuovaPartita(GUIMenuPrincipale guiMenuPrincipale) {	
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

		pnlMenu = new JPanel(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		this.guiMenuPrincipale = guiMenuPrincipale;
		guiNuovaPartita = this;
		
		pnlMenu.setBackground(Color.WHITE);

		c = new GridBagConstraints();

		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;

		lblNomeGiocatore = new JLabel("Nome Giocatore: ");
		lblNomeGiocatore.setFont(lblNomeGiocatore.getFont().deriveFont(22f));
		pnlMenu.add(lblNomeGiocatore, c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		lblTutorial = new JLabel("Tutorial: ");
		lblTutorial.setFont(lblTutorial.getFont().deriveFont(22f));
		pnlMenu.add(lblTutorial, c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		lblDifficolta = new JLabel("Difficolt�: ");
		lblDifficolta.setFont(lblDifficolta.getFont().deriveFont(22f));
		pnlMenu.add(lblDifficolta, c);



		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		lblMappa = new JLabel("Mappa: ");
		lblMappa.setFont(lblMappa.getFont().deriveFont(22f));
		pnlMenu.add(lblMappa, c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		lblCivilta = new JLabel("Civilt�: ");
		lblCivilta.setFont(lblCivilta.getFont().deriveFont(22f));
		pnlMenu.add(lblCivilta, c);

		c.gridy = 0;
		c.gridx = 2;
		c.anchor = GridBagConstraints.LINE_START;

		pnlMenu.add(txtNomeGiocatore = new JTextField(16), c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(chkTut = new JCheckBox(), c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		listDifficolta = new  JComboBox<String>();
		listDifficolta.addItem("Facile");
		listDifficolta.addItem("Medio");
		listDifficolta.addItem("Difficile");
		listDifficolta.addItem("Maestro");
		pnlMenu.add(listDifficolta, c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		listMappa = new  JComboBox<String>();
		listMappa.addItem("Predefinita");
		listMappa.addItem("Generata casualmente");
		listMappa.addItem("Estiva");
		listMappa.addItem("Invernale");
		pnlMenu.add(listMappa, c);

		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		listCivilta = new  JComboBox<String>();
		listCivilta.addItem("Romani");
		listCivilta.addItem("Britanni");
		listCivilta.addItem("Galli");
		listCivilta.addItem("Sassoni");
		pnlMenu.add(listCivilta, c);

		c.gridx = 0;
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		btnIndietro = new RoundedCornerButton("INDIETRO");
		btnIndietro.setFont(fontFuturist.deriveFont(16f));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/suonoindietro.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				setVisible(false);
				guiMenuPrincipale.ripristinaPanelDx((int)guiNuovaPartita.getSize().getWidth(), (int)guiNuovaPartita.getSize().getHeight());
			}
		});
		pnlMenu.add(btnIndietro, c);

		c.gridy -= 2;
		c.gridx += 3;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		btnAvvia = new RoundedCornerButton("AVVIA PARTITA");
		btnAvvia.setFont(fontFuturist.deriveFont(16f));
		btnAvvia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0){
				
				if(btnAvvia.isEnabled())
				{
					btnAvvia.setEnabled(false);
					k=creaNuovaPartita();
					if(k == 1)              //La partita si crea solo se non ci sono stati errori nella creazione del salvataggio (ovvero se k=1)
					{
						String nomeGiocatore;
						int tutorial, difficolta, mappa, civilta;
						nomeGiocatore = txtNomeGiocatore.getText();
						if(chkTut.isSelected())
							tutorial = 1;
						else
							tutorial = 0;
						difficolta = listDifficolta.getSelectedIndex();
						mappa = listMappa.getSelectedIndex();
						civilta = listCivilta.getSelectedIndex();
	
						finestreAttive=Frame.getWindows();      //Ritorna un array con tutte le finestre attive
						finestreAttive[0].setVisible(false);    
						framePartita = new GUIPartita(nomeGiocatore, tutorial, difficolta, mappa, civilta);
						try {
							audio = AudioSystem.getClip();
							audio.open(AudioSystem.getAudioInputStream(new File("media/suonoiniziopartita.wav")));
						} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
							e1.printStackTrace();
						}
						FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(Global.getLivVolume()); 
						Music.stopSound();
						audio.start();
						framePartita.setVisible(true);
					}
				}
			}
		});
		pnlMenu.add(btnAvvia, c);
		
		add(pnlMenu, BorderLayout.CENTER);
	}

	/**
	 * Questo metodo viene invocato nel momento in cui il JButton Avvia Partita �
	 * premuto. Raccoglie la configurazione della partita inserita dall'utente e con
	 * essi crea un salvataggio nel DataBase Salvataggi.
	 */
	private int creaNuovaPartita()	{   
		try {	
			s = new ConnectionDB();           //Connessione al database

			if(txtNomeGiocatore.getText().equals(""))
			{
				JOptionPane.showMessageDialog(pnlMenu, "Il campo Nome Giocatore non pu� essere vuoto.");
				return -1;
			}

			if(chkTut.isSelected())
				Tutorial="Si";
			else
				Tutorial="No";		
			//Adesso creiamo il salvataggio iniziale con i dati che abbiamo raccolto (e alcuni di default)
			k=s.init(txtNomeGiocatore.getText(),Tutorial,listDifficolta.getItemAt(listDifficolta.getSelectedIndex()),listMappa.getItemAt(listMappa.getSelectedIndex()),
					listCivilta.getItemAt(listCivilta.getSelectedIndex()));

		} catch(SQLException  | ClassNotFoundException e ){
			e.printStackTrace();
			JOptionPane.showMessageDialog(pnlMenu, "Si � verificato un errore inaspettato "
					+ "nella creazione della partita.",
					"Errore", JOptionPane.ERROR_MESSAGE);
			if(s!=null)
				s.closeConnection();
			return -1;
		}    finally {
			if(s!=null)
				s.closeConnection();
		}			
		return k;
	}

	public RoundedCornerButton getBtnAvvia() {
		return btnAvvia;
	}

	public void setBtnAvvia(RoundedCornerButton btnAvvia) {
		this.btnAvvia = btnAvvia;
	}
	
}
