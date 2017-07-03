package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GUIPartita extends JFrame{

	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelBottom;
	private JPanel panelCenter;
	private JPanel panelBtnGoRight;
	private JPanel panelMsg;
	private JPanel panelStats;
	private JPanel panelTurno;
	private JPanel panelPulsanti;
	private JLabel lblOro;
	private JLabel lblOrov;
	private JLabel lblMateriali;
	private JLabel lblMaterialiv;
	private JLabel lblPuntiRicerca;
	private JLabel lblPuntiRicercav;
	private JLabel lblTurno;
	private RoundedCornerButton btnGoUp;
	private RoundedCornerButton btnGoRight;
	private RoundedCornerButton btnGoDown;
	private RoundedCornerButton btnGoLeft;
	private JPanel panelBtnGoUp;
	private JPanel panelBtnGoDown;
	private JPanel panelBtnGoLeft;
	private JLabel[][] lblsGiocoFondo;
	private JLabel[][] lblsGioco;
	private String azioneLblsGioco;
	private String elemLblsGioco;
	private int ioldLblsGioco, joldLblsGioco;
	private JPanel panelGioco;
	private Image scalelblPartita;
	private int partitaHeight = 16, partitaWidth = 31;
	private Font fontFuturist;
	private JButton btnApriMsg;
	private JButton btnPassaTurno;
	private JTextField txtMsg;
	private JButton btnCostruisci;
	private JButton btnRicerca;
	private JButton btnInfoPartita;
	private JButton btnImpostazioni;
	private Window[] finestreAttive;
	private GUIPartitaOpzioni frmOpzioni;
	private Scenario scenario;
	private int posSchermataX = 31, posSchermataY = 16;
	private String[][] scenarioCorrente;
	private Clip audio;
	
	private GUIPartita guiPartita; //utilizzato per avere un riferimento a questa classe nelle chiamate a thread esterni
	private GUIPartitaInformazioni frmInfo;
	private GUIPartitaRicerca frmRicerca;
	
	private Partita partita;
	
	private ValoriDiGioco valoriDiGioco;
	private GUIPartitaCostruisci frmCostruisci;
	private IconeGrafiche iconeGrafiche;
	
	private String proprietario; //INDICA IL PROPRIETARIO DELLA GUIPartita, in multiplayer utilizzato per distinguere i due giocatori
	private int indiceProprietario; //INDICA L'INDICE DEL PROPRIETARIO
	
	GUIPartita(String nomeGiocatore, int tutorial, int difficolta, int mappa, int civilta)
	{
		setTitle("Empire Conquerors");
	
		ImageIcon icona = new ImageIcon("media/Icona.png");                  //Carichiamo l'icona personalizzata
		Image scaledicona = icona.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		setIconImage(scaledicona);  
		
		Toolkit t1 = Toolkit.getDefaultToolkit();                                 //Cursore personalizzato
		Image img = t1.getImage("media/cursore.png");
		Point point = new Point(0,0);
		Cursor cursor = t1.createCustomCursor(img, point, "Cursore Personalizzato");
		setCursor(cursor);  
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //blocco di codice per evitare la chiusura immediata alla chiusura del programma
		addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   int scelta =0;
				   scelta = JOptionPane.showConfirmDialog(
						    null, "Stai per tornare al menù principale, perderai i progressi non salvati.\nUscire dalla partita?", "Conferma",
						    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				   finestreAttive = Frame.getWindows();
				   if(scelta == 0)
				   {
					   disattivaThread(); //disattiva tutti i thread prima di chiudere la partita
					   finestreAttive[0].setVisible(true);
					   dispose();
					   File musica= new File("media/MusicaMenu.wav");
						Music.playSound(musica);
				   }
			   }
		});
		setMinimumSize(new Dimension(1280,720));   
		
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null);
		
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
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout2(3, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		panelTop = new JPanel(new GridLayout(1, 3, 0, 0));
		panelBottom = new JPanel(new GridLayout(1, 3, 0, 0));
		panelCenter = new JPanel(new GridLayout2(1, 3, 0, 0));
		panelBtnGoRight = new JPanel();
		panelBtnGoLeft = new JPanel();
		
		iconeGrafiche = new IconeGrafiche();
		scenario = new Scenario();
		proprietario = "utente1";
		indiceProprietario = civilta;
		
		//Posiziono la schermata iniziale a seconda della civiltà scelta dal giocatore
		switch(civilta)
		{
		case 0: //romani
			posSchermataX = 31;
			posSchermataY = 32;
			break;
		case 1: //britanni
			posSchermataX = 31;
			posSchermataY = 0;
			break;
		case 2: //galli
			posSchermataX = 0;
			posSchermataY = 16;
			break;
		case 3: //sassoni
			posSchermataX = 62;
			posSchermataY = 16;
			break;
		}
		
		lblOro = new JLabel();
		lblOro.setFont(fontFuturist.deriveFont(15f));
		lblOro.setIcon(iconeGrafiche.newiconOro);
		lblOro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblOrov = new JLabel("0");
		lblOrov.setFont(fontFuturist.deriveFont(15f));
		lblOrov.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblMateriali = new JLabel();
		lblMateriali.setFont(fontFuturist.deriveFont(15f));
		lblMateriali.setIcon(iconeGrafiche.newiconMat);
		lblMateriali.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblMaterialiv = new JLabel("0");
		lblMaterialiv.setFont(fontFuturist.deriveFont(15f));
		lblMaterialiv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPuntiRicerca = new JLabel();
		lblPuntiRicerca.setFont(fontFuturist.deriveFont(15f));
		lblPuntiRicerca.setIcon(iconeGrafiche.newiconCosto);
		lblPuntiRicerca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPuntiRicercav = new JLabel("0");
		lblPuntiRicercav.setFont(fontFuturist.deriveFont(15f));
		lblPuntiRicercav.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panelStats = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		panelStats.add(new JLabel(" "));
		c.gridx ++;
		panelStats.add(lblOro, c);
		c.gridx ++;
		panelStats.add(lblOrov, c);
		c.gridx ++;
		panelStats.add(new JLabel("   "), c);
		c.gridx ++;
		panelStats.add(lblMateriali, c);
		c.gridx ++;
		panelStats.add(lblMaterialiv, c);
		c.gridx ++;
		panelStats.add(new JLabel("   "), c);
		c.gridx ++;
		panelStats.add(lblPuntiRicerca, c);
		c.gridx ++;
		panelStats.add(lblPuntiRicercav, c);
		
		panelTop.add(panelStats);
		
		btnGoUp = new RoundedCornerButton();
		btnGoRight = new RoundedCornerButton();
		btnGoDown = new RoundedCornerButton();
		btnGoLeft = new RoundedCornerButton();
		guiPartita = this;
		
		ImageIcon iconbtnGoUp = new ImageIcon("media/btnGoUp.png");
		Image scalebtnGoUp = iconbtnGoUp.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoUp = new ImageIcon(scalebtnGoUp);
		
		btnGoUp.setIcon(newiconbtnGoUp);
		
		
		btnGoUp.addMouseListener(new MouseAdapter() {
			ThreadScorrimentoSchermata threadScorrimento;
			@Override
			public void mousePressed(MouseEvent arg0) {
				threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 1);
				threadScorrimento.start();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				threadScorrimento.setScorri(false);
				
			}
		});
		panelBtnGoUp = new JPanel();
		panelBtnGoUp.add(btnGoUp);
		
		panelTop.add(panelBtnGoUp);
		
		panelTurno = new JPanel(new GridLayout2(1, 1, 0, 0));
		lblTurno = new JLabel();
		ImageIcon iconlblTurno = new ImageIcon("media/mappa.png");
		Image scalelblTurno = iconlblTurno.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon newiconlblTurno = new ImageIcon(scalelblTurno);
		lblTurno.setIcon(newiconlblTurno);
		lblTurno.setHorizontalAlignment(SwingConstants.RIGHT);
		panelTop.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelTop.add(lblTurno);
		
		btnGoRight = new RoundedCornerButton();
		ImageIcon iconbtnGoRight = new ImageIcon("media/btnGoRight.png");
		Image scalebtnGoRight = iconbtnGoRight.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoRight = new ImageIcon(scalebtnGoRight);
		
		btnGoRight.setIcon(newiconbtnGoRight);
		btnGoRight.addMouseListener(new MouseAdapter() {
			ThreadScorrimentoSchermata threadScorrimento;
			@Override
			public void mousePressed(MouseEvent arg0) {
				threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 2);
				threadScorrimento.start();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				threadScorrimento.setScorri(false);
			}
		});

		panelBtnGoRight.setLayout(new BoxLayout(panelBtnGoRight, BoxLayout.PAGE_AXIS));
		panelBtnGoRight.add(Box.createVerticalGlue());
		panelBtnGoRight.add(btnGoRight);
		panelBtnGoRight.add(Box.createVerticalGlue());
		
		btnGoLeft = new RoundedCornerButton();
		ImageIcon iconbtnGoLeft = new ImageIcon("media/btnGoLeft.png");
		Image scalebtnGoLeft = iconbtnGoLeft.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoLeft = new ImageIcon(scalebtnGoLeft);
		
		btnGoLeft.setIcon(newiconbtnGoLeft);
		btnGoLeft.addMouseListener(new MouseAdapter() {
			ThreadScorrimentoSchermata threadScorrimento;
			@Override
			public void mousePressed(MouseEvent arg0) {
				threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 4);
				threadScorrimento.start();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				threadScorrimento.setScorri(false);
			}
		});

		panelBtnGoLeft.setLayout(new BoxLayout(panelBtnGoLeft, BoxLayout.PAGE_AXIS));
		panelBtnGoLeft.add(Box.createVerticalGlue());
		panelBtnGoLeft.add(btnGoLeft);
		panelBtnGoLeft.add(Box.createVerticalGlue());
		
		panelCenter.add(panelBtnGoLeft);
		lblsGioco = new JLabel[partitaHeight][partitaWidth];
		panelGioco = new JPanel(new GridLayout(16, 31, 0, 0));
		
		Scenario scenario = new Scenario();
		
		for(int i = 0; i < partitaHeight; i++)
		{
			for(int j = 0; j < partitaWidth; j++)
			{
				lblsGioco[i][j] = new JLabel();
				lblsGioco[i][j].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseReleased(MouseEvent arg0) {
						for(int i = 0; i < partitaHeight; i++)
						{
							for(int j = 0; j < partitaWidth; j++)
							{
								if(lblsGioco[i][j] == arg0.getSource())
									gestoreClickLblGioco(j, i);
							}
						}
					}
				});
				panelGioco.add(lblsGioco[i][j]);
			}	
		}
		
		aggiornaSchermata(); //prende la situazione attuale da Scenario e parsando la matrice di stringhe crea il corrispondente grafico
		
		
		
		panelGioco.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				iconeGrafiche.caricaIconeScenario(lblsGioco[0][0].getSize().width, lblsGioco[0][0].getSize().height+1);
				aggiornaSchermata();
			}
		});
		
		panelCenter.add(panelGioco);
		
		panelCenter.add(panelBtnGoRight);
		
		panelMsg = new JPanel(new GridBagLayout());
		btnApriMsg = new JButton(">");
		txtMsg = new JTextField(20);
		txtMsg.setEditable(false);
		GridBagConstraints d = new GridBagConstraints();
		d.gridx = 0;
		d.gridy = 0;
		panelMsg.add(btnApriMsg, d);
		d.gridx ++;
		panelMsg.add(txtMsg, d);
		d.gridx ++;
		btnPassaTurno = new JButton("Passa");
		btnPassaTurno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/suonoiniziopartita.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				if(!partita.getGiocatore().get(partita.getTurnoCorrente()).getProprietario().equals("cpu")){
					partita.getThreadCicloPartita().setTurnoPersona(false);
				}
			}
		});
		panelMsg.add(btnPassaTurno, d);
		
		panelBottom.add(panelMsg);
		
		btnGoDown = new RoundedCornerButton();
		ImageIcon iconbtnGoDown = new ImageIcon("media/btnGoDown.png");
		Image scalebtnGoDown = iconbtnGoDown.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoDown = new ImageIcon(scalebtnGoDown);
		
		btnGoDown.setIcon(newiconbtnGoDown);
		btnGoDown.addMouseListener(new MouseAdapter() {
			ThreadScorrimentoSchermata threadScorrimento;
			@Override
			public void mousePressed(MouseEvent arg0) {
				threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 3);
				threadScorrimento.start();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				threadScorrimento.setScorri(false);
			}
		});
		
		panelBtnGoDown = new JPanel();
		panelBtnGoDown.add(btnGoDown);
		
		panelBottom.add(panelBtnGoDown);
		
		panelPulsanti = new JPanel(new GridBagLayout());
		btnCostruisci = new JButton();
		ImageIcon iconbtnCostruisci = new ImageIcon("media/btnCostruisci.png");
		Image scalebtnCostruisci = iconbtnCostruisci.getImage().getScaledInstance(30, 33, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnCostruisci = new ImageIcon(scalebtnCostruisci);
		
		btnCostruisci.setIcon(newiconbtnCostruisci);
		btnCostruisci.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				frmCostruisci = new GUIPartitaCostruisci(partita, valoriDiGioco, guiPartita);
				frmCostruisci.setVisible(true);
			}
		});
		btnCostruisci.setSize(30, 33);
		
		btnRicerca = new JButton();
		ImageIcon iconbtnRicerca = new ImageIcon("media/btnRicerca.png");
		Image scalebtnRicerca = iconbtnRicerca.getImage().getScaledInstance(30, 33, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnRicerca = new ImageIcon(scalebtnRicerca);
		
		btnRicerca.setIcon(newiconbtnRicerca);
		
		btnRicerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				frmRicerca = new GUIPartitaRicerca(partita, guiPartita, valoriDiGioco, proprietario);
				frmRicerca.setVisible(true);
			}
		});
		
		btnInfoPartita = new JButton();
		ImageIcon iconbtnInfoPartita = new ImageIcon("media/btnInfoPartita.png");
		Image scalebtnInfoPartita = iconbtnInfoPartita.getImage().getScaledInstance(30, 33, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnInfoPartita = new ImageIcon(scalebtnInfoPartita);
		
		btnInfoPartita.setIcon(newiconbtnInfoPartita);
		btnInfoPartita.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				frmInfo = new GUIPartitaInformazioni(partita);
				frmInfo.setVisible(true);
			}
		});
		
		btnImpostazioni = new JButton();
		ImageIcon iconbtnImpostazioni = new ImageIcon("media/btnImpostazioni.png");
		Image scalebtnImpostazioni = iconbtnImpostazioni.getImage().getScaledInstance(30, 33, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnImpostazioni = new ImageIcon(scalebtnImpostazioni);
		
		btnImpostazioni.setIcon(newiconbtnImpostazioni);
		btnImpostazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/bottonepremuto.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				frmOpzioni = new GUIPartitaOpzioni();
				frmOpzioni.setVisible(true);
			}
		});
		
		GridBagConstraints f = new GridBagConstraints();
		f.gridx = 0;
		f.gridy = 0;
		panelPulsanti.add(btnCostruisci, f);
		f.gridx ++;
		panelPulsanti.add(btnRicerca, f);
		f.gridx ++;
		panelPulsanti.add(btnInfoPartita, f);
		f.gridx ++;
		panelPulsanti.add(btnImpostazioni, f);
		
		panelBottom.add(panelPulsanti);
		
		contentPane.add(panelTop);
		contentPane.add(panelCenter);
		contentPane.add(panelBottom);
		this.add(contentPane);
		
		pack();
		
		setupPartita(nomeGiocatore, tutorial, difficolta, mappa, civilta);
	}
	
	//Aggiorna la schermata in base alla posizione di visualizzazione corrente
	public void aggiornaSchermata() {
		scenarioCorrente = scenario.getScenario();
		StringTokenizer st;
		List<ImageIcon> daAggiungere = new ArrayList<ImageIcon>();
		CompoundIcon cmpIcon;
		
		panelGioco.setVisible(false);
		for(int y = posSchermataY; y < posSchermataY + 16; y++)
		{
			for(int x = posSchermataX; x < posSchermataX + 31; x++)
			{
				st = new StringTokenizer(scenarioCorrente[y][x]);
				daAggiungere.clear();
				while(st.hasMoreTokens()) {
					String str = st.nextToken();
					daAggiungere.add(iconeGrafiche.getIconeScenario().get(str));
				}
				cmpIcon = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, daAggiungere.toArray(new ImageIcon[daAggiungere.size()]));
				
				lblsGioco[y-posSchermataY][x-posSchermataX].setIcon(cmpIcon);
			}
		}
		panelGioco.setVisible(true);
	}

	public int getPosSchermataX() {
		return posSchermataX;
	}

	public void setPosSchermataX(int posSchermataX) {
		this.posSchermataX = posSchermataX;
	}

	public int getPosSchermataY() {
		return posSchermataY;
	}

	public int getIndiceProprietario() {
		return indiceProprietario;
	}

	public void setIndiceProprietario(int indiceProprietario) {
		this.indiceProprietario = indiceProprietario;
	}

	public void setPosSchermataY(int posSchermataY) {
		this.posSchermataY = posSchermataY;
	}
	
	/*Utilizzato per preparare tutte le variabili e le utility alla partita*/
	public void setupPartita(String nomeGiocatore, int tutorial, int difficolta, int mappa, int civilta) {
		valoriDiGioco = new ValoriDiGioco();
		
		partita = new Partita(null, nomeGiocatore, tutorial, difficolta, mappa, civilta, this);
		aggiornaDatiGUI();
	}
	
	public void aggiornaDatiGUI()
	{
		lblOrov.setText(Integer.toString(partita.getGiocatore().get(indiceProprietario).getOro()));
		lblMaterialiv.setText(Integer.toString(partita.getGiocatore().get(indiceProprietario).getMateriali()));
		lblPuntiRicercav.setText(Integer.toString(partita.getGiocatore().get(indiceProprietario).getPuntiRicerca()));
		switch(partita.getTurnoCorrente())
		{
		case 0:
			lblTurno.setIcon(iconeGrafiche.newiconRomani);
			break;
		case 1:
			lblTurno.setIcon(iconeGrafiche.newiconInglesi);
			break;
		case 2:
			lblTurno.setIcon(iconeGrafiche.newiconFrancesi);
			break;
		case 3:
			lblTurno.setIcon(iconeGrafiche.newiconSassoni);
			break;
		}
	}
	
	public void disattivaThread()
	{
		partita.getThreadCicloPartita().stop();
	}
	
	/**
	 * Metodo che permette di comprare un elemento dal negozio
	 */
	public void posizionaECompra(String elemento)
	{
		azioneLblsGioco = "compra"; //Indica che la prossima azione di click su una lblGioco è per comprare
		elemLblsGioco = elemento; //Indica l'elemento da comprare
	}
	
	/**
	 * Metodo che permette di vendere una costruzione
	 */
	public void vendiCostruzione()
	{
		azioneLblsGioco = "vendi"; //Indica che la prossima azione di click è per vendere
	}
	
	
	/**
	 * Metodo che permette di muovere una costruzione
	 */
	public void muoviCostruzione()
	{
		azioneLblsGioco = "muovi";
	}
	
	public void gestoreClickLblGioco(int i, int j) //i è la x, j è la y
	{
		if(azioneLblsGioco == null)
			return;
		if(azioneLblsGioco.equals("compra"))
		{
			if(isPiazzamentoPossibile(i, j)) //compra e piazza
			{
				/*Scalo costo in oro*/
				partita.getGiocatore().get(partita.getTurnoCorrente()).setOro(
						partita.getGiocatore().get(partita.getTurnoCorrente()).getOro()-valoriDiGioco.getValoriOro().get(elemLblsGioco));
				/*Scalo costo in materiali*/
				partita.getGiocatore().get(partita.getTurnoCorrente()).setMateriali(
						partita.getGiocatore().get(partita.getTurnoCorrente()).getMateriali()-valoriDiGioco.getValoriMat().get(elemLblsGioco));
				
				partita.getGiocatore().get(partita.getTurnoCorrente()).getStoricoPossedimenti().add(elemLblsGioco);
				posizionaElementoSuScenario(i, j);
			}
			else //il posto dove vuole piazzarlo è già occupato
			{
				JOptionPane.showMessageDialog(null, "Non è possibile piazzare "+ elemLblsGioco + " in questa posizione.",
						"Informazioni", JOptionPane.DEFAULT_OPTION);
			}
			
			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";
			
			aggiornaSchermata();
			aggiornaDatiGUI();
		}
		else
		if(azioneLblsGioco.equals("vendi"))
		{
			//Controllo che sulla lbl ci sia un elemento vendibile
			String nome = individuaOggetto(i, j); //ritorna l'oggetto contenuto nella lbl che può essere venduto
			
			if(nome == null || nome.contains("municipio"))
			{
				JOptionPane.showMessageDialog(null, "Seleziona un elemento valido per la vendita",
						"Informazioni", JOptionPane.DEFAULT_OPTION);
			}
			else //chiedi se vuole vendere
			{
				int scelta =0;
				scelta = JOptionPane.showConfirmDialog(
						    null, "Sei sicuro di voler vendere questo oggetto? (Otterrai la metà di oro e "
						    		+ "materiali richiesti per l'acquisto)", "Conferma",
						    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(scelta == 0) //se si
				{
					char ultimoChar = nome.charAt(nome.length() - 1);
					
					if(ultimoChar == '1' || ultimoChar == '2' || ultimoChar == '3' || ultimoChar == '4') {
						nome = nome.substring(0, nome.length() - 1);
					}
					//Rimozione oggetto dai suoi possedimenti
					for(int k = 0; k < partita.getGiocatore().get(partita.getTurnoCorrente()).getStoricoPossedimenti().size(); k++)
					{
						if(partita.getGiocatore().get(partita.getTurnoCorrente()).getStoricoPossedimenti().get(k).equals(nome)) {
							partita.getGiocatore().get(partita.getTurnoCorrente()).getStoricoPossedimenti().remove(k);
						}
					}
					//Accredito della metà dell'oro e di materiali dell'oggetto venduto
					partita.getGiocatore().get(partita.getTurnoCorrente()).setOro(
							partita.getGiocatore().get(partita.getTurnoCorrente()).getOro() + (valoriDiGioco.getValoriOro().get(nome) / 2));
					partita.getGiocatore().get(partita.getTurnoCorrente()).setMateriali(
							partita.getGiocatore().get(partita.getTurnoCorrente()).getMateriali() + (valoriDiGioco.getValoriMat().get(nome) / 2));
					
					rimuoviElementoDaScenario(i, j, ultimoChar);
				}
			}
			
			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";
			
			aggiornaSchermata();
			aggiornaDatiGUI();
		}
		else
		if(azioneLblsGioco.equals("muovi"))
		{
			String nome = individuaOggetto(i, j);
			
			if(nome == null)
			{
				JOptionPane.showMessageDialog(null, "Non è possibile muovere questo elemento",
						"Informazioni", JOptionPane.DEFAULT_OPTION);
			}
			else
			{
				azioneLblsGioco = "posizionaElemento";
				elemLblsGioco = nome;
				ioldLblsGioco = i;
				joldLblsGioco = j;
			}
		}
		else
		if(azioneLblsGioco.equals("posizionaElemento"))
		{
			if(!isPiazzamentoPossibile(i, j))
			{
				JOptionPane.showMessageDialog(null, "Non è possibile muovere l'elemento in questa posizione",
						"Informazioni", JOptionPane.DEFAULT_OPTION);
			}
			else //piazzamento possibile
			{
				char ultimoChar = elemLblsGioco.charAt(elemLblsGioco.length() - 1);
				
				rimuoviElementoDaScenario(ioldLblsGioco, joldLblsGioco, ultimoChar);
				
				if(ultimoChar == '1' || ultimoChar == '2' || ultimoChar == '3' || ultimoChar == '4')
					elemLblsGioco = elemLblsGioco.substring(0, elemLblsGioco.length() - 1);
				posizionaElementoSuScenario(i, j);
				
				//Resetto i dati di azione e elemento in memoria
				azioneLblsGioco = "";
				elemLblsGioco = "";
				
				aggiornaSchermata();
				aggiornaDatiGUI();
			}
		}
	}
	
	public void rimuoviElementoDaScenario(int i, int j, char ultimoChar)
	{
		//Rimozione dell'oggetto dallo scenario
		if(ultimoChar == '1')
		{
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] = scenario.getScenario()[j+posSchermataY][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY][i+posSchermataX+1] = scenario.getScenario()[j+posSchermataY][i+posSchermataX+1]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX+1].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX] = scenario.getScenario()[j+posSchermataY+1][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY+1][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX+1] = scenario.getScenario()[j+posSchermataY+1][i+posSchermataX+1]
					.substring(0, scenario.getScenario()[j+posSchermataY+1][i+posSchermataX+1].lastIndexOf(" "));
		}
		else
		if(ultimoChar == '2')
		{
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] = scenario.getScenario()[j+posSchermataY][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY][i+posSchermataX-1] = scenario.getScenario()[j+posSchermataY][i+posSchermataX-1]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX-1].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX] = scenario.getScenario()[j+posSchermataY+1][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY+1][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX-1] = scenario.getScenario()[j+posSchermataY+1][i+posSchermataX-1]
					.substring(0, scenario.getScenario()[j+posSchermataY+1][i+posSchermataX-1].lastIndexOf(" "));
		}
		else
		if(ultimoChar == '3')
		{
			scenario.getScenario()[j+posSchermataY-1][i+posSchermataX] = scenario.getScenario()[j+posSchermataY-1][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY-1][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY-1][i+posSchermataX+1] = scenario.getScenario()[j+posSchermataY-1][i+posSchermataX+1]
					.substring(0, scenario.getScenario()[j+posSchermataY-1][i+posSchermataX+1].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] = scenario.getScenario()[j+posSchermataY][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY][i+posSchermataX+1] = scenario.getScenario()[j+posSchermataY][i+posSchermataX+1]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX+1].lastIndexOf(" "));
		}
		else
		if(ultimoChar == '4')
		{
			scenario.getScenario()[j+posSchermataY-1][i+posSchermataX-1] = scenario.getScenario()[j+posSchermataY-1][i+posSchermataX-1]
					.substring(0, scenario.getScenario()[j+posSchermataY-1][i+posSchermataX-1].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY-1][i+posSchermataX] = scenario.getScenario()[j+posSchermataY-1][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY-1][i+posSchermataX].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY][i+posSchermataX-1] = scenario.getScenario()[j+posSchermataY][i+posSchermataX-1]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX-1].lastIndexOf(" "));
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] = scenario.getScenario()[j+posSchermataY][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX].lastIndexOf(" "));
		}
		else
		{
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] = scenario.getScenario()[j+posSchermataY][i+posSchermataX]
					.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX].lastIndexOf(" "));
		}
	}
	
	public void posizionaElementoSuScenario(int i, int j)
	{
		/*Posizionamento oggetto sulla plancia di gioco*/
		if(elemLblsGioco.equals("Sentiero") || elemLblsGioco.equals("Lastricato") || elemLblsGioco.equals("Asfalto") ||
				elemLblsGioco.equals("Casa") || elemLblsGioco.equals("Villa") || 
				elemLblsGioco.equals("Casa a più piani") || elemLblsGioco.equals("Casa a schiera") ||
				elemLblsGioco.equals("Casa con mansarda") || elemLblsGioco.equals("Villetta"))
		{ //caso 1x1
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] += " ";
			elemLblsGioco = elemLblsGioco.replaceAll(" ", "_");
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] += elemLblsGioco;
		}
		else
		{ //caso 2x2
			elemLblsGioco = elemLblsGioco.replaceAll(" ", "_");
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] += " ";
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] += elemLblsGioco;
			scenario.getScenario()[j+posSchermataY][i+posSchermataX] += "1";
			
			scenario.getScenario()[j+posSchermataY][i+posSchermataX+1] += " ";
			scenario.getScenario()[j+posSchermataY][i+posSchermataX+1] += elemLblsGioco;
			scenario.getScenario()[j+posSchermataY][i+posSchermataX+1] += "2";
			
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX] += " ";
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX] += elemLblsGioco;
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX] += "3";
			
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX+1] += " ";
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX+1] += elemLblsGioco;
			scenario.getScenario()[j+posSchermataY+1][i+posSchermataX+1] += "4";
		}
	}
	
	public String individuaOggetto(int i, int j) //i è la x, j è la y
	{
		scenarioCorrente = scenario.getScenario();
		String strCercata = null;
		
		StringTokenizer st;
		int stItera = 0;
		
		st = new StringTokenizer(scenarioCorrente[j+posSchermataY][i+posSchermataX]);
		while(st.hasMoreTokens()) {
			if(stItera == 0) //controllo che il pavimento sia ghiaia
				if(!st.nextToken().equals("g"))
				{
					return null;
				}
			stItera++;
			if(stItera > 1)
				strCercata = st.nextToken();
		}
		if(strCercata == null)
			return null;
		
		strCercata = strCercata.replaceAll("_", " ");
		
		return strCercata;
	}
	
	public boolean isPiazzamentoPossibile(int i, int j) //i è la x, j è la y
	{
		int stItera;
		
		if(elemLblsGioco.equals("Sentiero") || elemLblsGioco.equals("Lastricato") || elemLblsGioco.equals("Asfalto") || 
				elemLblsGioco.equals("Casa") || elemLblsGioco.equals("Villa") || 
				elemLblsGioco.equals("Casa a più piani") || elemLblsGioco.equals("Casa a schiera") ||
				elemLblsGioco.equals("Casa con mansarda") || elemLblsGioco.equals("Villetta"))
		{
			//controlliamo che la lbl di posizione i, j sia disponibile (1x1)
			StringTokenizer st;
			stItera = 0;
			
			scenarioCorrente = scenario.getScenario();
			st = new StringTokenizer(scenarioCorrente[j+posSchermataY][i+posSchermataX]);
			while(st.hasMoreTokens()) {
				if(stItera == 0) //controllo che il pavimento sia ghiaia
					if(!st.nextToken().equals("g"))
					{
						return false;
					}
				stItera++;
				if(stItera > 1) //La lbl di posizione i,i è già impegnata
				{
					return false;
				}
			}
					
		}
		else
		{
			//controlliamo che le lbl di posizione i, j; i+1, j; i, j+1; i+1, j+1 siano disponibili (2x2)
			for(int k = 0; k < 2; k++)
			{
				for(int u = 0; u < 2; u++)
				{
					//controlliamo che la lbl di posizione i+k, j+k sia disponibile (2x2)
					StringTokenizer st;
					stItera = 0;
					
					scenarioCorrente = scenario.getScenario();
					st = new StringTokenizer(scenarioCorrente[j+posSchermataY+u][i+posSchermataX+k]);
					while(st.hasMoreTokens()) {
						if(stItera == 0) //controllo che il pavimento sia ghiaia
							if(!st.nextToken().equals("g"))
								return false;
						stItera++;
						if(stItera > 1) //La lbl di posizione i,i è già impegnata
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public String getAzioneLblsGioco() {
		return azioneLblsGioco;
	}

	public void setAzioneLblsGioco(String azioneLblsGioco) {
		this.azioneLblsGioco = azioneLblsGioco;
	}

	public String getElemLblsGioco() {
		return elemLblsGioco;
	}

	public void setElemLblsGioco(String elemLblsGioco) {
		this.elemLblsGioco = elemLblsGioco;
	}
}
