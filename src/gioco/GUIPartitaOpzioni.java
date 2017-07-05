package gioco;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIPartitaOpzioni extends JDialog {
	
	private RoundedCornerButton btnContinua;
	private RoundedCornerButton btnSalva;
	private RoundedCornerButton btnCarica;
	private RoundedCornerButton btnImpostazioni;
	private RoundedCornerButton btnTornaMenu;
	private RoundedCornerButton btnEsci;
	private Window[] finestreAttive;
	private JPanel contentPane;
	private Font fontFuturist;
	private Clip audio;
	GUIPartitaOpzioni guiPartitaOpzioni; //Utilizzato per avere anche negli eventi un riferimento a questo JDialog
	GUIPartita guiPartita; //Per avere un riferimento a partita
	
	GUIPartitaOpzioni (GUIPartita guiPartita) {
		ImageIcon icona = new ImageIcon("media/Icona.png");                  //Carichiamo l'icona personalizzata
		Image scaledicona = icona.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		setIconImage(scaledicona);  

		Toolkit t1 = Toolkit.getDefaultToolkit();                                 //Cursore personalizzato
		Image img = t1.getImage("media/cursore.png");
		Point point = new Point(0,0);
		Cursor cursor = t1.createCustomCursor(img, point, "Cursore Personalizzato");
		setCursor(cursor); 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		
		setLayout(new BorderLayout(0,0)); //impostato un borderlayout
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout2(13, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0));
		btnTornaMenu = new RoundedCornerButton();
		setTitle("Opzioni");
		setMinimumSize(new Dimension(300, 500));   
		setLocationRelativeTo(null);
		guiPartitaOpzioni = this;
		setModal(true);
		this.guiPartita = guiPartita;
		
		contentPane.add(new JLabel(" "));
		
		btnContinua = new RoundedCornerButton("CONTINUA PARTITA");
		btnContinua.setFont(fontFuturist.deriveFont(12f));
		btnContinua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				dispose();
			}
		});
		contentPane.add(btnContinua);
		
		contentPane.add(new JLabel(" "));
		
		btnSalva = new RoundedCornerButton("SALVA PARTITA");
		btnSalva.setFont(fontFuturist.deriveFont(12f));
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();	
			}
		});
		contentPane.add(btnSalva);
		
		contentPane.add(new JLabel(" "));
		
		btnCarica = new RoundedCornerButton("CARICA PARTITA");
		btnCarica.setFont(fontFuturist.deriveFont(12f));
		btnCarica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
			}
		});
		contentPane.add(btnCarica);
		
		contentPane.add(new JLabel(" "));
		
		btnImpostazioni = new RoundedCornerButton("IMPOSTAZIONI");
		btnImpostazioni.setFont(fontFuturist.deriveFont(12f));
		btnImpostazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
			}
		});
		contentPane.add(btnImpostazioni);
		
		contentPane.add(new JLabel(" "));
		
		btnTornaMenu = new RoundedCornerButton("TORNA AL MENÙ");
		btnTornaMenu.setFont(fontFuturist.deriveFont(12f));
		btnTornaMenu.addMouseListener(new MouseAdapter() {
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
				finestreAttive = Frame.getWindows();
				 int scelta =0;
				 scelta = JOptionPane.showConfirmDialog(
						    guiPartitaOpzioni, "Stai per tornare al menù principale, perderai i progressi non salvati."
						    		+ "\nUscire dalla partita?", "Conferma",
						    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				   if(scelta == 0)
				   {
					   finestreAttive[0].setVisible(true);
					   guiPartita.dispose();
					   guiPartitaOpzioni.dispose();
					   File musica= new File("media/MusicaMenu.wav");
						Music.playSound(musica);
				   }
			}
		});
		contentPane.add(btnTornaMenu);
		
		contentPane.add(new JLabel(" "));
		
		btnEsci = new RoundedCornerButton("ESCI");
		btnEsci.setFont(fontFuturist.deriveFont(12f));
		btnEsci.addMouseListener(new MouseAdapter() {
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
				int scelta =0;
				scelta = JOptionPane.showConfirmDialog(
						    guiPartitaOpzioni, "Stai per uscire dal gioco, perderai i progressi non salvati.\nUscire?", "Conferma",
						    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(scelta == 0)
					System.exit(0);
			}
		});
		contentPane.add(btnEsci);
		
		add(contentPane, BorderLayout.CENTER);
	}
}
