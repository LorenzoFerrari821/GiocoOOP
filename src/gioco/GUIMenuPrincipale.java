package gioco;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

/**
 * Questa classe rappresenta il JFrame relativo al Menù Principale,
 * al suo interno vengono richiamati tutti i sottomenù e vengono gestite
 * le azioni sui bottoni principali. 
 * @author Werther&Lorenzo
 *
 */
public class GUIMenuPrincipale extends JFrame {

	private JPanel contentPane;  
	private JPanel panelsx;
	private JPanel paneldx;
	private RoundedCornerButton btnNuovaPartita;
	private RoundedCornerButton btnNuovaSmall;
	private RoundedCornerButton btnCaricaPartita;
	private RoundedCornerButton btnCaricaSmall;
	private RoundedCornerButton btnMultiplayer;
	private RoundedCornerButton btnMultiplayerSmall;
	private RoundedCornerButton btnOpzioni;
	private RoundedCornerButton btnOpzioniSmall;
	private RoundedCornerButton btnExtra;
	private RoundedCornerButton btnExtraSmall;
	private RoundedCornerButton btnObiettivi;
	private RoundedCornerButton btnObiettiviSmall;
	private RoundedCornerButton btnEsci;
	private RoundedCornerButton btnEsciSmall;
	private JPanel panelTitolo;;
	private JLabel titolo;
	private GUINuovaPartita panelNuovaPartita;
	private GUICaricaPartita panelCaricaPartita;
	private GUIMultiplayer panelMultiplayer;
	private GUIOpzioni panelOpzioni;
	private GUIExtra panelExtra;
	private GUIObiettivi panelObiettivi;
	private Clip audio;
	ImageIcon iconTitolo;
	ImageIcon Immagine;
	Image scaleTitolo;
	Image scaleImmagine;
	private JLabel immagineDx;
	
	/**
	 * Costruttore della classe; molto corposo poichè si occupa di posizionare ogni elemento
	 * all'interno dell'interfaccia, costruisce quindi un contentPane di base e altri pannelli
	 * per la GUI, insieme a tutti gli altri elementi.
	 */
	public GUIMenuPrincipale() throws IllegalAccessException {
		setTitle("Empire Conquerors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1280, 720));   
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);

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
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		contentPane = new JPanel();
		panelsx = new JPanel();
		paneldx = new JPanel();
		panelTitolo = new JPanel();

		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);

		panelsx.setLayout(new GridLayout2(16, 2, 5, 5));
		panelsx.setBackground(Color.DARK_GRAY);

		paneldx.setLayout(new GridLayout(1, 1, 5, 5));
		paneldx.setBackground(Color.decode("0xFABCD"));

		panelTitolo.setLayout(new BorderLayout(4, 4));
		panelTitolo.setBackground(Color.BLACK);

		btnNuovaSmall = new RoundedCornerButton();
		ImageIcon iconbtnNuovaSmall = new ImageIcon("media/btnNuovaSmall.png");
		Image scalebtnNuovaSmall = iconbtnNuovaSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnNuovaSmall.setIcon(iconbtnNuovaSmall);
		btnNuovaSmall.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelNuovaPartita.setVisible(true);
					contentPane.add(panelNuovaPartita,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelNuovaPartita=new GUINuovaPartita();
					contentPane.add(panelNuovaPartita,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnNuovaSmall);

		btnNuovaPartita = new RoundedCornerButton();
		ImageIcon iconbtnNuovaPartita = new ImageIcon("media/btnNuovaPartita.png");
		Image scalebtnNuovaPartita = iconbtnNuovaPartita.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnNuovaPartita.setIcon(iconbtnNuovaPartita);

		btnNuovaPartita.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelNuovaPartita.setVisible(true);
					contentPane.add(panelNuovaPartita,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelNuovaPartita=new GUINuovaPartita();
					contentPane.add(panelNuovaPartita,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnNuovaPartita);

		btnCaricaSmall = new RoundedCornerButton();
		ImageIcon iconbtnCaricaSmall = new ImageIcon("media/btnCaricaSmall.png");
		Image scalebtnCaricaSmall = iconbtnCaricaSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnCaricaSmall.setIcon(iconbtnCaricaSmall);
		btnCaricaSmall.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelCaricaPartita.setVisible(true);
					contentPane.add(panelCaricaPartita,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelCaricaPartita=new GUICaricaPartita();
					panelCaricaPartita.creaComboBox(); //Creiamo la combobox interna della GUIcaricapartita
					contentPane.add(panelCaricaPartita,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnCaricaSmall);

		btnCaricaPartita = new RoundedCornerButton();
		ImageIcon iconbtnCaricaPartita = new ImageIcon("media/btnCaricaPartita.png");
		Image scalebtnCaricaPartita = iconbtnCaricaPartita.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnCaricaPartita.setIcon(iconbtnCaricaPartita);
		btnCaricaPartita.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelCaricaPartita.setVisible(true);
					contentPane.add(panelCaricaPartita,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelCaricaPartita=new GUICaricaPartita();
					contentPane.add(panelCaricaPartita,BorderLayout.CENTER);
					panelCaricaPartita.creaComboBox();   //Creiamo la combobox interna della GUIcaricapartita
				};
			}
		});
		panelsx.add(btnCaricaPartita);

		btnMultiplayerSmall = new RoundedCornerButton();
		ImageIcon iconbtnMultiplayerSmall = new ImageIcon("media/btnMultiplayerSmall.png");
		Image scalebtnMultiplayerSmall = iconbtnMultiplayerSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnMultiplayerSmall.setIcon(iconbtnMultiplayerSmall);
		btnMultiplayerSmall.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelMultiplayer.setVisible(true);
					contentPane.add(panelMultiplayer,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelMultiplayer=new GUIMultiplayer();
					contentPane.add(panelMultiplayer,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnMultiplayerSmall);

		btnMultiplayer = new RoundedCornerButton();
		ImageIcon iconbtnMultiplayer = new ImageIcon("media/btnMultiplayer.png");
		Image scalebtnMultiplayer = iconbtnMultiplayer.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnMultiplayer.setIcon(iconbtnMultiplayer);

		btnMultiplayer.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelMultiplayer.setVisible(true);
					contentPane.add(panelMultiplayer,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelMultiplayer=new GUIMultiplayer();
					contentPane.add(panelMultiplayer,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnMultiplayer);


		btnOpzioniSmall = new RoundedCornerButton();
		ImageIcon iconbtnOpzioniSmall = new ImageIcon("media/btnOpzioniSmall.png");
		Image scalebtnOpzioniSmall = iconbtnOpzioniSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnOpzioniSmall.setIcon(iconbtnOpzioniSmall);
		btnOpzioniSmall.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelOpzioni.setVisible(true);
					contentPane.add(panelOpzioni,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelOpzioni=new GUIOpzioni();
					contentPane.add(panelOpzioni,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnOpzioniSmall);

		btnOpzioni = new RoundedCornerButton();
		ImageIcon iconbtnOpzioni = new ImageIcon("media/btnOpzioni.png");
		Image scalebtnOpzioni = iconbtnOpzioni.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnOpzioni.setIcon(iconbtnOpzioni);
		btnOpzioni.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelOpzioni.setVisible(true);
					contentPane.add(panelOpzioni,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelOpzioni=new GUIOpzioni();
					contentPane.add(panelOpzioni,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnOpzioni);


		btnExtraSmall = new RoundedCornerButton();
		ImageIcon iconbtnExtraSmall = new ImageIcon("media/btnExtraSmall.png");
		Image scalebtnExtraSmall = iconbtnExtraSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnExtraSmall.setIcon(iconbtnExtraSmall);
		btnExtraSmall.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelExtra.setVisible(true);
					contentPane.add(panelExtra, BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelExtra = new GUIExtra();
					contentPane.add(panelExtra, BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnExtraSmall);

		btnExtra = new RoundedCornerButton();
		ImageIcon iconbtnExtra = new ImageIcon("media/btnExtra.png");
		Image scalebtnExtra = iconbtnExtra.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnExtra.setIcon(iconbtnExtra);
		btnExtra.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelExtra.setVisible(true);
					contentPane.add(panelExtra, BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelExtra = new GUIExtra();
					contentPane.add(panelExtra, BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnExtra);


		btnObiettiviSmall = new RoundedCornerButton();
		ImageIcon iconbtnObiettiviSmall = new ImageIcon("media/btnObiettiviSmall.png");
		Image scalebtnObiettiviSmall = iconbtnObiettiviSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnObiettiviSmall.setIcon(iconbtnObiettiviSmall);
		btnObiettiviSmall.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelObiettivi.setVisible(true);
					contentPane.add(panelObiettivi,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelObiettivi = new GUIObiettivi();
					contentPane.add(panelObiettivi,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnObiettiviSmall);

		btnObiettivi = new RoundedCornerButton();
		ImageIcon iconbtnObiettivi = new ImageIcon("media/btnObiettivi.png");
		Image scalebtnObiettivi = iconbtnObiettivi.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnObiettivi.setIcon(iconbtnObiettivi);
		btnObiettivi.addMouseListener(new MouseAdapter() {
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
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelObiettivi.setVisible(true);
					contentPane.add(panelObiettivi,BorderLayout.CENTER);
				}catch(NullPointerException e){
					panelObiettivi = new GUIObiettivi();
					contentPane.add(panelObiettivi,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnObiettivi);


		btnEsciSmall = new RoundedCornerButton();
		ImageIcon iconbtnEsciSmall = new ImageIcon("media/btnEsciSmall.png");
		Image scalebtnEsciSmall = iconbtnEsciSmall.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		btnEsciSmall.setIcon(iconbtnEsciSmall);
		btnEsciSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		panelsx.add(btnEsciSmall);

		btnEsci = new RoundedCornerButton();
		ImageIcon iconbtnEsci = new ImageIcon("media/btnEsci.png");
		Image scalebtnEsci = iconbtnEsci.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnEsci.setIcon(iconbtnEsci);

		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Music.stopSound();
				dispose();
			}
		});
		panelsx.add(btnEsci);

		for(int i = 0; i < 16; i++)
		{
			panelsx.add(new JLabel(" "));
		}

		contentPane.add(panelsx, BorderLayout.LINE_START);
		contentPane.add(paneldx, BorderLayout.CENTER);
		contentPane.add(panelTitolo, BorderLayout.NORTH);
		
		

		Immagine = new ImageIcon("media/Immagine.jpg");
		scaleImmagine = Immagine.getImage().getScaledInstance(1000, 550,Image.SCALE_DEFAULT);
		immagineDx = new JLabel(new ImageIcon(scaleImmagine));
		paneldx.add(immagineDx, BorderLayout.CENTER);
		
		
		iconTitolo = new ImageIcon("media/titolo.png");
		scaleTitolo = iconTitolo.getImage().getScaledInstance(1000, 150,Image.SCALE_DEFAULT);
		titolo = new JLabel(new ImageIcon(scaleTitolo));
		panelTitolo.add(titolo, BorderLayout.CENTER);
	}

	/**
	 * Questo metodo quando invocato si assicura che tutti i JPanel nella zona a destra
	 * siano non visibili. Viene invocato per permettere l'apertura di uno specifico JPanel
	 * che, prima di poter essere reso visibile, necessita che lo spazio che andrà ad occupare
	 * sia vuoto.
	 */
	public void allPanelsdxNotVisibles()
	{
		try {
			panelNuovaPartita.setVisible(false);
		}catch(NullPointerException e){};
		try {
			panelCaricaPartita.setVisible(false);
		}catch(NullPointerException e){};
		try {
			panelMultiplayer.setVisible(false);
		}catch(NullPointerException e){};
		try {
			panelOpzioni.setVisible(false);
		}catch(NullPointerException e){};
		try {
			panelExtra.setVisible(false);
		}catch(NullPointerException e){};
		try {
			panelObiettivi.setVisible(false);
		}catch(NullPointerException e){};
	}
}
