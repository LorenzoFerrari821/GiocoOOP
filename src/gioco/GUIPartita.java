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
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

/**
 * GUI principale che rappresenta la partita al giocatore
 * @author Werther e Lorenzo
 *
 */
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

	static int su = KeyEvent.VK_W;                                        //Codice dei tasti
	static int giu = KeyEvent.VK_S;
	static int dest = KeyEvent.VK_D;
	static int sinist = KeyEvent.VK_A;
	static int frecciasu = KeyEvent.VK_UP;                                       
	static int frecciagiu = KeyEvent.VK_DOWN;
	static int frecciadest = KeyEvent.VK_RIGHT;
	static int frecciasinist = KeyEvent.VK_LEFT;

	private int oldLblsGiocoWidth;  //Variabile per ricordare la vecchia dimensione delle lblsGioco
	private int oldLblsGiocoHeight; //Variabile per ricordare la vecchia dimensione delle lblsGioco

	private ValoriDiGioco valoriDiGioco;
	private GUIPartitaCostruisci frmCostruisci;
	private IconeGrafiche iconeGrafiche;

	private String proprietario; //INDICA IL PROPRIETARIO DELLA GUIPartita, in multiplayer utilizzato per distinguere i due giocatori
	private int indiceProprietario; //INDICA L'INDICE DEL PROPRIETARIO

	private GUIArruolaUnita guiArruolaUnita; //Schermata per arruolare nuove unità, invocata se clicchiamo su un edificio militare
	private GUIMunicipio guiMunicipio; //Schermata del muncipio
	private GUIGruppoMilitare guiGruppoMilitare; //Schermata del gruppo militare
	
	private GruppoMilitare gruppoMilitare; //Individua il gruppo militare corrente
	
	/**
	 * Crea l'interfaccia grafica della partita e inizializza tutte le variabili per poterle presentare al giocatore
	 * @param nomeGiocatore Nome del giocatore
	 * @param tutorial Tutorial si/no
	 * @param difficolta Difficoltà scelta dal giocatore
	 * @param mappa Mappa scelta dal giocatore
	 * @param civilta Civiltà scelta dal giocatore
	 */
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
						null, Global.getLabels("s54"),Global.getLabels("a6") ,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				finestreAttive = Frame.getWindows();
				if(scelta == 0)
				{
					disattivaThread(); //disattiva tutti i thread prima di chiudere la partita
					finestreAttive[0].setVisible(true);
					dispose();
					File musica= new File("media/MusicaMenu.wav");
					Music.playSound();
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
		scenario = new Scenario(mappa);
		proprietario = "utente1";  
		indiceProprietario = civilta;
		azioneLblsGioco = "";

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



		//MOVIMENTO SCHERMATA DA TASTIERA CON WASD

		KeyboardFocusManager.getCurrentKeyboardFocusManager() 
		.addKeyEventDispatcher(new KeyEventDispatcher() {

			ThreadScorrimentoSchermata threadScorrimentoSu = null;
			ThreadScorrimentoSchermata threadScorrimentoGiu = null;
			ThreadScorrimentoSchermata threadScorrimentoSinist = null;
			ThreadScorrimentoSchermata threadScorrimentoDest = null;

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if(KeyEvent.KEY_PRESSED == e.getID()&&(Global.getTastiSchermata()==2))
				{
					if(e.getKeyCode()==su){
						if(threadScorrimentoSu == null)
						{
							threadScorrimentoSu = new ThreadScorrimentoSchermata(guiPartita, 1);
							threadScorrimentoSu.start();
						}
					}
					if(e.getKeyCode()==giu){
						if(threadScorrimentoGiu == null)
						{
							threadScorrimentoGiu = new ThreadScorrimentoSchermata(guiPartita, 3);
							threadScorrimentoGiu.start();
						}
					}
					if(e.getKeyCode()==sinist){
						if(threadScorrimentoSinist == null)
						{
							threadScorrimentoSinist = new ThreadScorrimentoSchermata(guiPartita, 4);
							threadScorrimentoSinist.start();
						}
					}
					if(e.getKeyCode()==dest){
						if(threadScorrimentoDest == null)
						{
							threadScorrimentoDest = new ThreadScorrimentoSchermata(guiPartita, 2);
							threadScorrimentoDest.start();
						}
					}
				}
				if(KeyEvent.KEY_RELEASED == e.getID()&&(Global.getTastiSchermata()==2))
				{
					if(e.getKeyCode()==su) {
						if(threadScorrimentoSu != null)
						{
							threadScorrimentoSu.setScorri(false);
							threadScorrimentoSu = null;
						}
					}
					if(e.getKeyCode()==giu) {
						if(threadScorrimentoGiu != null)
						{
							threadScorrimentoGiu.setScorri(false);
							threadScorrimentoGiu = null;
						}
					}
					if(e.getKeyCode()==sinist) {
						if(threadScorrimentoSinist != null)
						{
							threadScorrimentoSinist.setScorri(false);
							threadScorrimentoSinist = null;
						}
					}
					if(e.getKeyCode()==dest) {
						if(threadScorrimentoDest != null)
						{
							threadScorrimentoDest.setScorri(false);
							threadScorrimentoDest = null;
						}
					}
				}
				return false; //Rilascia il key dispatcher
			}
		});
		//FINE MOVIMENTO SCHERMATA CON WASD

		//MOVIMENTO SCHERMATA DA TASTIERA CON FRECCE DIREZIONALI
		KeyboardFocusManager.getCurrentKeyboardFocusManager() 
		.addKeyEventDispatcher(new KeyEventDispatcher() {

			ThreadScorrimentoSchermata threadScorrimentoSu = null;
			ThreadScorrimentoSchermata threadScorrimentoGiu = null;
			ThreadScorrimentoSchermata threadScorrimentoSinist = null;
			ThreadScorrimentoSchermata threadScorrimentoDest = null;

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if(KeyEvent.KEY_PRESSED == e.getID()&&(Global.getTastiSchermata()==1))
				{
					if(e.getKeyCode()==frecciasu){
						if(threadScorrimentoSu == null)
						{
							threadScorrimentoSu = new ThreadScorrimentoSchermata(guiPartita, 1);
							threadScorrimentoSu.start();
						}
					}
					if(e.getKeyCode()==frecciagiu){
						if(threadScorrimentoGiu == null)
						{
							threadScorrimentoGiu = new ThreadScorrimentoSchermata(guiPartita, 3);
							threadScorrimentoGiu.start();
						}
					}
					if(e.getKeyCode()==frecciasinist){
						if(threadScorrimentoSinist == null)
						{
							threadScorrimentoSinist = new ThreadScorrimentoSchermata(guiPartita, 4);
							threadScorrimentoSinist.start();
						}
					}
					if(e.getKeyCode()==frecciadest){
						if(threadScorrimentoDest == null)
						{
							threadScorrimentoDest = new ThreadScorrimentoSchermata(guiPartita, 2);
							threadScorrimentoDest.start();
						}
					}
				}
				if(KeyEvent.KEY_RELEASED == e.getID()&&(Global.getTastiSchermata()==1))
				{
					if(e.getKeyCode()==frecciasu) {
						if(threadScorrimentoSu != null)
						{
							threadScorrimentoSu.setScorri(false);
							threadScorrimentoSu = null;
						}
					}
					if(e.getKeyCode()==frecciagiu) {
						if(threadScorrimentoGiu != null)
						{
							threadScorrimentoGiu.setScorri(false);
							threadScorrimentoGiu = null;
						}
					}
					if(e.getKeyCode()==frecciasinist) {
						if(threadScorrimentoSinist != null)
						{
							threadScorrimentoSinist.setScorri(false);
							threadScorrimentoSinist = null;
						}
					}
					if(e.getKeyCode()==frecciadest) {
						if(threadScorrimentoDest != null)
						{
							threadScorrimentoDest.setScorri(false);
							threadScorrimentoDest = null;
						}
					}
				}
				return false; //Rilascia il key dispatcher
			}
		});
		//FINE MOVIMENTO SCHERMATA CON FRECCE DIREZIONALI

		ImageIcon iconbtnGoUp = new ImageIcon("media/btnGoUp.png");
		Image scalebtnGoUp = iconbtnGoUp.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoUp = new ImageIcon(scalebtnGoUp);

		btnGoUp.setIcon(newiconbtnGoUp);

		btnGoUp.addMouseListener(new MouseAdapter() {
			ThreadScorrimentoSchermata threadScorrimento;
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(Global.getTastiSchermata()==0){
					threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 1);
					threadScorrimento.start();
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Global.getTastiSchermata()==0)
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
				if(Global.getTastiSchermata()==0){
					threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 2);
					threadScorrimento.start();
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Global.getTastiSchermata()==0)
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
				if(Global.getTastiSchermata()==0){
					threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 4);
					threadScorrimento.start();
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Global.getTastiSchermata()==0)
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


		oldLblsGiocoWidth = 0;
		oldLblsGiocoHeight = 0;

		panelGioco.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				if(lblsGioco[0][0].getSize().width != oldLblsGiocoWidth || lblsGioco[0][0].getSize().height != oldLblsGiocoHeight)
				{
					oldLblsGiocoWidth = lblsGioco[0][0].getSize().width;
					oldLblsGiocoHeight = lblsGioco[0][0].getSize().height;
					iconeGrafiche.caricaIconeScenario(lblsGioco[0][0].getSize().width, lblsGioco[0][0].getSize().height+1);
					aggiornaSchermata();
				}
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
		btnPassaTurno = new JButton(Global.getLabels("s55"));
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
				if(Global.getTastiSchermata()==0){
					threadScorrimento = new ThreadScorrimentoSchermata(guiPartita, 3);
					threadScorrimento.start();
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(Global.getTastiSchermata()==0)
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
				frmOpzioni = new GUIPartitaOpzioni(guiPartita);
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

	/**
	 * In base alla stringa situazione di gioco presa da una vecchia partita salvata
	 * ricrea la partita
	 * @param stringa Stringa situazione di gioco
	 */
	public GUIPartita(String stringa) {
		
	}

	/**
	 * Aggiorna la schermata in base alla posizione di visualizzazione corrente
	 */
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

	/**
	 * Prepara tutte le variabili e le utility alla partita
	 * @param nomeGiocatore Nome del giocatore
	 * @param tutorial Tutorial si/no
	 * @param difficolta Difficoltà scelta dal giocatore
	 * @param mappa Mappa scelta dal giocatore
	 * @param civilta Civiltà scelta dal giocatore
	 */
	public void setupPartita(String nomeGiocatore, int tutorial, int difficolta, int mappa, int civilta) {
		valoriDiGioco = new ValoriDiGioco();

		partita = new Partita(null, nomeGiocatore, tutorial, difficolta, mappa, civilta, this, valoriDiGioco);
		aggiornaDatiGUI();
	}

	/**
	 * Aggiorna i dati che compongono la GUI
	 */
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

	/**
	 * Disattiva tutti i thread in esecuzione (prima di chiudere solitamente). Metodo stop antico e deprecated ma efficace
	 */
	public void disattivaThread()
	{
		partita.getThreadCicloPartita().stop();
	}

	/**
	 * Metodo che permette di comprare un elemento dal negozio
	 * @param elemento Elemento da posizionare e comprare
	 */
	public void posizionaECompra(String elemento)
	{
		azioneLblsGioco = Global.getLabels("s56"); //Indica che la prossima azione di click su una lblGioco è per comprare
		elemLblsGioco = elemento; //Indica l'elemento da comprare
	}

	/**
	 * Metodo che permette di vendere una costruzione
	 */
	public void vendiCostruzione()
	{
		azioneLblsGioco = Global.getLabels("s57"); //Indica che la prossima azione di click è per vendere
	}


	/**
	 * Metodo che permette di muovere una costruzione
	 */
	public void muoviCostruzione()
	{
		azioneLblsGioco = Global.getLabels("s58");
	}
	
	/**
	 * Metodo che permette di piazzare un esercito sul campo di battaglia
	 * @param gruppoMilitare gruppo militare da piazzarre
	 */
	public void piazzaEsercito(GruppoMilitare gruppoMilitare)
	{
		this.gruppoMilitare = gruppoMilitare;
		azioneLblsGioco = Global.getLabels("s59");
		elemLblsGioco = Global.getLabels("s60");
	}
	
	/**
	 * Metodo che permette di richiamare un esercito all'interno del municipio
	 */
	public void richiamaEsercito()
	{
		azioneLblsGioco = Global.getLabels("s61");
	}
	
	/**
	 * Metodo che permette di attaccare con un gruppo militare
	 * @param gruppoMilitare gruppo militare in attacco
	 */
	public void gruppoMilitareAttacca(GruppoMilitare gruppoMilitare)
	{
		azioneLblsGioco = Global.getLabels("s62");
		elemLblsGioco = Global.getLabels("s60");
		this.gruppoMilitare = gruppoMilitare;
	}
	
	/**
	 * Metodo che permette di muovere un gruppo militare
	 * @param gruppoMilitare gruppo militare da muovere
	 */
	public void gruppoMilitareMuovi(GruppoMilitare gruppoMilitare)
	{
		azioneLblsGioco = Global.getLabels("s63");
		elemLblsGioco = Global.getLabels("s60");
		this.gruppoMilitare = gruppoMilitare;
	}

	/**
	 * Gestisce tutti i click che avvengono sulle labels lblsGioco
	 * @param i X corrente (senza tenere in conto della pos schermata)
	 * @param j Y corrente (senza tenere in conto della pos schermata)
	 */
	public void gestoreClickLblGioco(int i, int j) //i è la x, j è la y
	{
		if(azioneLblsGioco.equals(Global.getLabels("s56")))
		{
			if(isPiazzamentoPossibile(i, j)) //compra e piazza
			{
				
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/costruisci.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				
				
				
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
				JOptionPane.showMessageDialog(null, Global.getLabels("e9")+ elemLblsGioco +" "+Global.getLabels("e10"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}

			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";

			aggiornaSchermata();
			aggiornaDatiGUI();
		}
		else
		if(azioneLblsGioco.equals(Global.getLabels("s57")))
		{
			//Controllo che sulla lbl ci sia un elemento vendibile
			String nome = individuaOggetto(i, j, true); //ritorna l'oggetto contenuto nella lbl che può essere venduto

			if(nome == null || nome.contains(Global.getLabels("i49")))
			{
				JOptionPane.showMessageDialog(null, Global.getLabels("s64"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			else //chiedi se vuole vendere
			{
				int scelta =0;
				scelta = JOptionPane.showConfirmDialog(
						null,Global.getLabels("s65") , Global.getLabels("a6"),JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(scelta == 0) //se si
				{
					try {
						audio = AudioSystem.getClip();
						audio.open(AudioSystem.getAudioInputStream(new File("media/vendi.wav")));
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(Global.getLivVolume()); 
					audio.start();
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
		if(azioneLblsGioco.equals(Global.getLabels("s58")))
		{
			String nome = individuaOggetto(i, j, true);

			if(nome == null)
			{
				JOptionPane.showMessageDialog(null,Global.getLabels("e11"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			else
			{
				azioneLblsGioco = Global.getLabels("s66");
				elemLblsGioco = nome;
				ioldLblsGioco = i;
				joldLblsGioco = j;
			}
		}
		else
		if(azioneLblsGioco.equals(Global.getLabels("s66")))
		{
			if(!isPiazzamentoPossibile(i, j))
			{
				JOptionPane.showMessageDialog(null,Global.getLabels("e12"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
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
		else
		if(azioneLblsGioco.equals(Global.getLabels("s62")))
		{
			int civiltaDaAttaccare;
			
			//2 casi possibili: o attacca un municipio oppure un gruppo mil. nemico
			civiltaDaAttaccare = esercitoPresente(i+posSchermataX, j+posSchermataY);
			
			if(civiltaDaAttaccare != -1) //presente un esercito
			{
				if(civiltaDaAttaccare != partita.getGiocatore().get(indiceProprietario).getCiviltà()) //se esercito è nemico
				{
					//controllo che i due gruppi militari siano adiacenti
					int distanza = 0, tmp;
					tmp = gruppoMilitare.getPosX() - (i+posSchermataX);
					if(tmp < 0)
						tmp *= -1;
					distanza += tmp;
					tmp = gruppoMilitare.getPosY() - (j+posSchermataY);
					if(tmp < 0)
						tmp *= -1;
					distanza += tmp;
					
					if(distanza == 1) //attacca
					{
						gruppoMilitare.setAttaccoPossibile(false);
						int esito = partita.esercitoAttaccaEsercito(gruppoMilitare, i+posSchermataX, j+posSchermataY);
						if(esito == 1){
							try {
								audio = AudioSystem.getClip();
								audio.open(AudioSystem.getAudioInputStream(new File("media/suonovittoria.wav")));
							} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
								e1.printStackTrace();
							}
							FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
							gainControl.setValue(Global.getLivVolume()); 
							audio.start();
							JOptionPane.showMessageDialog(null,Global.getLabels("s122"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
						}
						else
						if(esito == 0){
							JOptionPane.showMessageDialog(null,Global.getLabels("s123"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);}
						try {
							audio = AudioSystem.getClip();
							audio.open(AudioSystem.getAudioInputStream(new File("media/morte.wav")));
						} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
							e1.printStackTrace();
						}
						FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(Global.getLivVolume()); 
						audio.start();
					}
					else
						JOptionPane.showMessageDialog(null, Global.getLabels("s124"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
				}
				else
					JOptionPane.showMessageDialog(null, Global.getLabels("s125"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			else
			{
				//controllo se municipio nemico
				int municipioPresente = municipioPresente(i+posSchermataX, j+posSchermataY);
				if(municipioPresente != -1)
				{
					if(municipioPresente != partita.getGiocatore().get(indiceProprietario).getCiviltà())
					{
						if(isVicinoACitta(gruppoMilitare.getPosX(), gruppoMilitare.getPosY()) == municipioPresente) //attacco municipio permesso
						{
							gruppoMilitare.setAttaccoPossibile(false);
							int esito = partita.esercitoAttaccaMunicipio(gruppoMilitare, municipioPresente);
							if(esito == -1)
								JOptionPane.showMessageDialog(null, Global.getLabels("s126"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
							else
							if(esito == 1){
								try {
									audio = AudioSystem.getClip();
									audio.open(AudioSystem.getAudioInputStream(new File("media/suonovittoria.wav")));
								} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
									e1.printStackTrace();
								}
								FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
								gainControl.setValue(Global.getLivVolume()); 
								audio.start();
								JOptionPane.showMessageDialog(null,Global.getLabels("s127"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
							}
							else
							if(esito == 0){
								JOptionPane.showMessageDialog(null, Global.getLabels("s123"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
								try {
									audio = AudioSystem.getClip();
									audio.open(AudioSystem.getAudioInputStream(new File("media/morte.wav")));
								} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
									e1.printStackTrace();
								}
								FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
								gainControl.setValue(Global.getLivVolume()); 
								audio.start();
							}
						}
					}
					else
						JOptionPane.showMessageDialog(null, Global.getLabels("s128"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
				}
				else
					JOptionPane.showMessageDialog(null,Global.getLabels("s129"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			
			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";

			aggiornaSchermata();
			aggiornaDatiGUI();
		}
		else
		if(azioneLblsGioco.equals(Global.getLabels("s63"))) //gruppo militare muovi
		{
			if(isPiazzamentoPossibile(i, j)) //controlla che la casella sia vuota (solo la base)
			{
				if(!scenario.getScenario()[j+posSchermataY][i+posSchermataX].substring(0, 1).equals("g")) //se la base di questa casella NON è ghiaia
				{
					int unitaPiuLenta = 100;
					int tmp, distanza = 0;
					
					//Calcolo la velocità a cui può muoversi il gruppo militare
					for(String unita: gruppoMilitare.getGruppoMilitare())
					{
						if(valoriDiGioco.getVelUnita().get(unita) < unitaPiuLenta)
							unitaPiuLenta = valoriDiGioco.getVelUnita().get(unita);
					}
					
					tmp = gruppoMilitare.getPosX() - (i+posSchermataX);
					if(tmp < 0)
						tmp *= -1;
					distanza += tmp;
					
					tmp = gruppoMilitare.getPosY() - (j+posSchermataY);
					if(tmp < 0)
						tmp *= -1;
					distanza += tmp;
					
					if(distanza <= unitaPiuLenta) 
					{
						if(gruppoMilitare.isMovimentoPossibile())//si puo muovere
						{
							try {
								audio = AudioSystem.getClip();
								audio.open(AudioSystem.getAudioInputStream(new File("media/marcia.wav")));
							} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
								e1.printStackTrace();
							}
							FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
							gainControl.setValue(Global.getLivVolume()); 
							audio.start();
							
							//se presente una cava adiacente al gruppo militare e di sua proprietà la rendo libera
							guiPartita.controllaCava(gruppoMilitare.getPosX(), gruppoMilitare.getPosY(), 1);
							
							//tolgo il gruppo militare dallo scenario di posizione vecchia
							scenario.getScenario()[gruppoMilitare.getPosY()][gruppoMilitare.getPosX()] = 
									scenario.getScenario()[gruppoMilitare.getPosY()][gruppoMilitare.getPosX()]
									.substring(0, scenario.getScenario()[gruppoMilitare.getPosY()][gruppoMilitare.getPosX()].length() -Integer.parseInt(Global.getLabels("s135")));    //La linghezza è diversa a seconda della lingua
							
							//aggiorno la posizione in gruppo militare
							gruppoMilitare.setPosX(i+posSchermataX);
							gruppoMilitare.setPosY(j+posSchermataY);
							gruppoMilitare.setMovimentoPossibile(false);
							
							//aggiorno la posizione nello scenario
							scenario.aggiungiEsercito(i+posSchermataX, j+posSchermataY, partita.getGiocatore().get(indiceProprietario).getCiviltà());
							
							//controllo se presente un falò lo raccolgo
							controllaFalo(i+posSchermataX, j+posSchermataY);
							
							//se è presente una cava libera nelle celle adiacenti la gestisco
							controllaCava(i+posSchermataX, j+posSchermataY, 0);
						}
						else
							JOptionPane.showMessageDialog(null, Global.getLabels("s68"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
					}
					else
						JOptionPane.showMessageDialog(null, Global.getLabels("s69") + Integer.toString(unitaPiuLenta),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
				}
				else
					JOptionPane.showMessageDialog(null, Global.getLabels("s70"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			else
				JOptionPane.showMessageDialog(null, Global.getLabels("s71"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			
			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";

			aggiornaSchermata();
			aggiornaDatiGUI();
		}
		else
		if(azioneLblsGioco.equals("")) //Se è un edificio militare apri schermata arruola truppe
		{
			String edificio = individuaOggetto(i, j, false);

			if(edificio != null)
			{
				char ultimoChar = edificio.charAt(edificio.length() - 1);
				if(ultimoChar == '1' || ultimoChar == '2' || ultimoChar == '3' || ultimoChar == '4') {
					edificio = edificio.substring(0, edificio.length() - 1);
				}
				
				if(edificio.equals(Global.getLabels("i0")) || edificio.equals(Global.getLabels("i24")) || edificio.equals(Global.getLabels("i27")) || 
						edificio.equals(Global.getLabels("i32")) || edificio.equals(Global.getLabels("i35")) || edificio.equals(Global.getLabels("i41")) || 
						edificio.equals(Global.getLabels("i39")) || edificio.equals(Global.getLabels("i42")))
				{
					guiArruolaUnita = new GUIArruolaUnita(partita, this, valoriDiGioco, iconeGrafiche, edificio);
					guiArruolaUnita.setVisible(true);
				}
				else
				if(edificio.equals(Global.getLabels("i50")) || edificio.equals(Global.getLabels("i51")) || edificio.equals(Global.getLabels("i52")))
				{
					guiMunicipio = new GUIMunicipio(partita, guiPartita, valoriDiGioco, iconeGrafiche);
					guiMunicipio.setVisible(true);
				}
				else
				if(edificio.contains(Global.getLabels("s72")))
				{
					GruppoMilitare gruppoMilitareCercato = null;
					
					//individuo il gruppo militare associato alla posizione i, j
					for(GruppoMilitare g: partita.getGruppiMilitariSchierati())
					{
						if(g.getPosX() == i + posSchermataX && g.getPosY() == j + posSchermataY)
						{
							gruppoMilitareCercato = g;
						}
					}
					
					guiGruppoMilitare = new GUIGruppoMilitare(partita, guiPartita, valoriDiGioco, iconeGrafiche, gruppoMilitareCercato);
					guiGruppoMilitare.setVisible(true);
				}
			}
		}
		else
		if(azioneLblsGioco.equals(Global.getLabels("s59")))
		{
			if(isPiazzamentoPossibile(i, j))
			{
				int vicinanza = isVicinoACitta(i + posSchermataX, j + posSchermataY);
				
				int civilta = partita.getGiocatore().get(indiceProprietario).getCiviltà();
				
				if(vicinanza == civilta) //ok, può piazzare qui
				{
					try {
						audio = AudioSystem.getClip();
						audio.open(AudioSystem.getAudioInputStream(new File("media/marcia.wav")));
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(Global.getLivVolume()); 
					audio.start();
					
					gruppoMilitare.setCivilta(civilta);
					gruppoMilitare.setPosX(i+posSchermataX);
					gruppoMilitare.setPosY(j+posSchermataY);
					for(String s: gruppoMilitare.getGruppoMilitare())
					{
						partita.getGiocatore().get(indiceProprietario).getUnitaMunicipio().remove(s);
					}
					partita.getGruppiMilitariSchierati().add(gruppoMilitare);
					partita.getGiocatore().get(indiceProprietario).getGruppiInAttacco().add(gruppoMilitare);
					scenario.aggiungiEsercito(i+posSchermataX, j+posSchermataY, civilta);
					
					//controllo la presenza di falo nelle caselle adiacenti
					controllaFalo(i+posSchermataX, j+posSchermataY);
					
					//se è presente una cava libera nelle celle adiacenti la gestisco
					controllaCava(i+posSchermataX, j+posSchermataY, 0);
				}
				else
					JOptionPane.showMessageDialog(null, Global.getLabels("s73"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			
			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";

			aggiornaSchermata();
		}
		else
			if(azioneLblsGioco.equals(Global.getLabels("s61")))
		{
			int vicinanza = isVicinoACitta(i + posSchermataX, j + posSchermataY);
			
			int civilta = partita.getGiocatore().get(indiceProprietario).getCiviltà();
			
			if(vicinanza == civilta) //l'esercito è adiacente alla città
			{
				int nazionEsercito = esercitoPresente(i + posSchermataX, j + posSchermataY);
				
				if(nazionEsercito == civilta) //possiamo prelevare l'esercito e inserirlo nel municipio
				{
					int conta = 0, indice = -1;
					try {
						audio = AudioSystem.getClip();
						audio.open(AudioSystem.getAudioInputStream(new File("media/marcia.wav")));
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
						e1.printStackTrace();
					}
					FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(Global.getLivVolume()); 
					audio.start();
					
					//prelevo il gruppo giusto
					for(GruppoMilitare g: partita.getGruppiMilitariSchierati())
					{
						if(g.getPosX() == i+posSchermataX && g.getPosY() == j+posSchermataY)
						{
							indice = conta;
							break;
						}
						conta++;
					}
					//Le truppe tornano nel municipio
					for(String s: partita.getGruppiMilitariSchierati().get(indice).getGruppoMilitare())
					{
						partita.getGiocatore().get(indiceProprietario).getUnitaMunicipio().add(s);
					}
					//Il gruppo militare viene sciolto
					partita.getGruppiMilitariSchierati().remove(indice);
					
					//tolgo il gruppo militare dallo scenario
					scenario.getScenario()[j+posSchermataY][i+posSchermataX] = scenario.getScenario()[j+posSchermataY][i+posSchermataX]
							.substring(0, scenario.getScenario()[j+posSchermataY][i+posSchermataX].length() - 10);
				}
				else
					JOptionPane.showMessageDialog(null, Global.getLabels("s74"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			}
			else
				JOptionPane.showMessageDialog(null, Global.getLabels("s136"),Global.getLabels("e7"), JOptionPane.DEFAULT_OPTION);
			
			//Resetto i dati di azione e elemento in memoria
			azioneLblsGioco = "";
			elemLblsGioco = "";

			aggiornaSchermata();
		}
	}
	
	/**
	 * Metodo che libera la cava in posizione i,j dai possedimenti di cave del giocatore
	 * @param i X
	 * @param j Y
	 */
	public void liberaCava(int i, int j)
	{
		for(CavaDiRisorse c: partita.getCaveScenario())
		{
			if(c.getX() == i && c.getY() == j)
			{
				c.setCiviltaProprietaria(-1);
			}
		}
	}
	
	/**
	 * Metodo che permette al giocatore del turno corrente di conquistare la cava di posizione i, j
	 * @param i X
	 * @param j Y
	 */
	public void conquistaCava(int i, int j)
	{
		for(CavaDiRisorse c: partita.getCaveScenario())
		{
			if(c.getX() == i && c.getY() == j)
			{
				if(c.getCiviltaProprietaria() == -1)
					c.setCiviltaProprietaria(partita.getGiocatore().get(partita.getTurnoCorrente()).getCiviltà());
			}
		}
	}
	
	/**
	 * Metodo che controlla se è presente una cava nelle caselle vicine a i e j, ed eventualmente la controlla o la libera (azione)
	 * @param i X
	 * @param j Y
	 * @param azione 0 controlla, 1 libera
	 */
	public void controllaCava(int i, int j, int azione)
	{
		String cella;
		
		if(j > 0) //controlla parte superiore
		{
			if(scenario.getScenario()[j-1][i].length() >= 3)
			{
				cella = scenario.getScenario()[j-1][i].substring(scenario.getScenario()[j-1][i].length() - 2, 
						scenario.getScenario()[j-1][i].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i, j-1);
					else
						liberaCava(i, j-1);
				}
			}
		}
		if(i > 0) //controlla parte sx
		{
			if(scenario.getScenario()[j][i-1].length() >= 3)
			{
				cella = scenario.getScenario()[j][i-1].substring(scenario.getScenario()[j][i-1].length() - 2, 
						scenario.getScenario()[j][i-1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i-1, j);
					else
						liberaCava(i-1, j);
				}
			}
		}
		if(j < 47) //controlla parte bassa
		{
			if(scenario.getScenario()[j+1][i].length() >= 3)
			{
				cella = scenario.getScenario()[j+1][i].substring(scenario.getScenario()[j+1][i].length() - 2, 
						scenario.getScenario()[j+1][i].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i, j+1);
					else
						liberaCava(i, j+1);
				}
			}
		}
		if(j < 92) //controlla parte dx
		{
			if(scenario.getScenario()[j][i+1].length() >= 3)
			{
				cella = scenario.getScenario()[j][i+1].substring(scenario.getScenario()[j][i+1].length() - 2, 
						scenario.getScenario()[j][i+1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i+1, j);
					else
						liberaCava(i+1, j);
				}
			}
		}
		if(j > 0 && j > 0) //controlla parte sx in alto
		{
			if(scenario.getScenario()[j-1][i-1].length() >= 3)
			{
				cella = scenario.getScenario()[j-1][i-1].substring(scenario.getScenario()[j-1][i-1].length() - 2, 
						scenario.getScenario()[j-1][i-1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i-1, j-1);
					else
						liberaCava(i-1, j-1);
				}
			}
		}
		if(i < 92 && j > 0) //controlla parte dx in alto
		{
			if(scenario.getScenario()[j-1][i+1].length() >= 3)
			{
				cella = scenario.getScenario()[j-1][i+1].substring(scenario.getScenario()[j-1][i+1].length() - 2, 
						scenario.getScenario()[j-1][i+1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i+1, j-1);
					else
						liberaCava(i+1, j-1);
				}
			}
		}
		if(i > 0 && j < 47) //sx in basso
		{
			if(scenario.getScenario()[j+1][i-1].length() >= 3)
			{
				cella = scenario.getScenario()[j+1][i-1].substring(scenario.getScenario()[j+1][i-1].length() - 2, 
						scenario.getScenario()[j+1][i-1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i-1, j+1);
					else
						liberaCava(i-1, j+1);
				}
			}
		}
		if(i < 92 && j < 47) //dx in basso
		{
			if(scenario.getScenario()[j+1][i+1].length() >= 3)
			{
				cella = scenario.getScenario()[j+1][i+1].substring(scenario.getScenario()[j+1][i+1].length() - 2, 
						scenario.getScenario()[j+1][i+1].length());
				if(cella.equals(" x") || cella.equals(" y") || cella.equals(" z"))
				{
					if(azione == 0)
						conquistaCava(i+1, j+1);
					else
						liberaCava(i+1, j+1);
				}
			}
		}
	}
	
	/**
	 * Controlla se è presente un falo in una delle caselle adiacenti. Se si lo raccoglie
	 * @param i X
	 * @param j Y
	 */
	public void controllaFalo(int i, int j)
	{
		if(j > 0) //controlla parte superiore
		{
			if(scenario.getScenario()[j-1][i].length() >= 3 && 
					scenario.getScenario()[j-1][i].substring(scenario.getScenario()[j-1][i].length() - 2, 
					scenario.getScenario()[j-1][i].length()).equals(" f")) //Se nella casella superiore c'è un falò
			{
				ottieniFalo(i, j-1);
			}
		}
		if(i > 0) //controlla parte sx
		{
			if(scenario.getScenario()[j][i-1].length() >= 3 && 
					scenario.getScenario()[j][i-1].substring(scenario.getScenario()[j][i-1].length() - 2, 
					scenario.getScenario()[j][i-1].length()).equals(" f")) //Se nella casella sx c'è un falò
			{
				ottieniFalo(i-1, j);
			}
		}
		if(j < 47) //controlla parte bassa
		{
			if(scenario.getScenario()[j+1][i].length() >= 3 && 
					scenario.getScenario()[j+1][i].substring(scenario.getScenario()[j+1][i].length() - 2, 
					scenario.getScenario()[j+1][i].length()).equals(" f")) //Se nella casella giu c'è un falò
			{
				ottieniFalo(i, j+1);
			}
		}
		if(i < 92) //controlla parte dx
		{
			if(scenario.getScenario()[j][i+1].length() >= 3 && 
					scenario.getScenario()[j][i+1].substring(scenario.getScenario()[j][i+1].length() - 2, 
					scenario.getScenario()[j][i+1].length()).equals(" f")) //Se nella casella dx c'è un falò
			{
				ottieniFalo(i+1, j);
			}
		}
		if(i > 0 && j > 0) //sx in alto
		{
			if(scenario.getScenario()[j-1][i-1].length() >= 3 && 
					scenario.getScenario()[j-1][i-1].substring(scenario.getScenario()[j-1][i-1].length() - 2, 
					scenario.getScenario()[j-1][i-1].length()).equals(" f")) //Se nella casella sx c'è un falò
			{
				ottieniFalo(i-1, j-1);
			}
		}
		if(i < 92 && j > 0) //dx in alto
		{
			if(scenario.getScenario()[j-1][i+1].length() >= 3 && 
					scenario.getScenario()[j-1][i+1].substring(scenario.getScenario()[j-1][i+1].length() - 2, 
					scenario.getScenario()[j-1][i+1].length()).equals(" f")) //Se nella casella dx in alto c'è un falò
			{
				ottieniFalo(i+1, j-1);
			}
		}
		if(i > 0 && j < 47) //sx in basso
		{
			if(scenario.getScenario()[j+1][i-1].length() >= 3 && 
					scenario.getScenario()[j+1][i-1].substring(scenario.getScenario()[j+1][i-1].length() - 2, 
					scenario.getScenario()[j+1][i-1].length()).equals(" f")) //Se nella casella sx in basso c'è un falò
			{
				ottieniFalo(i-1, j+1);
			}
		}
		if(i < 92 && j < 47) //dx in basso
		{
			if(scenario.getScenario()[j+1][i+1].length() >= 3 && 
					scenario.getScenario()[j+1][i+1].substring(scenario.getScenario()[j+1][i+1].length() - 2, 
					scenario.getScenario()[j+1][i+1].length()).equals(" f")) //Se nella casella dx in basso c'è un falò
			{
				ottieniFalo(i+1, j+1);
			}
		}
	}
	
	/**
	 * Metodo che permette di ottenere un falò sulla mappa
	 * @param i X
	 * @param j Y
	 */
	public void ottieniFalo(int i, int j)
	{
		int bottino;
		
		//tolgo il falo dallo scenario
		scenario.getScenario()[j][i] = scenario.getScenario()[j][i].substring(0, scenario.getScenario()[j][i].length() - 2);
		
		//calcolo valore falò: 10% di oro o materiali
		if(Math.random() < 0.5) {
			bottino = (int)(partita.getGiocatore().get(indiceProprietario).getOro() * 0.1);
			partita.getGiocatore().get(indiceProprietario).setOro(partita.getGiocatore().get(indiceProprietario).getOro() + bottino);
		}
		else
		{
			bottino = (int)(partita.getGiocatore().get(indiceProprietario).getMateriali() * 0.1);
			partita.getGiocatore().get(indiceProprietario).setMateriali(
					partita.getGiocatore().get(indiceProprietario).getMateriali() + bottino);
		}
	}
	
	/**
	 * Metodo che controlla se nella casella di posizione i, j è presente un esercito. Se è presente ritorna 0 se l'esercito è romano,
	 * 1 se inglese, 2 se francese, 3 se tedesco, -1 se non è presente alcun esercito
	 * @param i X
	 * @param j Y
	 * @return nazionalità esercito, -1 se non presente
	 */
	public int esercitoPresente(int i, int j)
	{
		String str = scenario.getScenario()[j][i];
		
		if(str.contains(Global.getLabels("s72")))
			return Integer.parseInt(str.substring(str.length()-1, str.length()));
		
		return -1;
	}
	
	/**
	 * Metodo che controlla se nella casella di posizione i, j è presente un municipio. Se è presente ritorna 0 se il municipio è romano,
	 * 1 se inglese, 2 se francese, 3 se tedesco, -1 se non è presente alcun municipio
	 * @param i X
	 * @param j Y
	 * @return nazionalità municipio, -1 se non presente
	 */
	public int municipioPresente(int i, int j)
	{
		String str = scenario.getScenario()[j][i];
		
		if(str.contains(Global.getLabels("i49")))
		{
			if(i > 37 && i < 55 && j > 34 && j < 45) //romani
				return 0;
			if(i > 37 && i < 55 && j > 2 && j < 13) //britanni
				return 1;
			if(i > 6 && i < 24 && j > 18 && j < 29) //galli
				return 2;
			if(i > 68 && i < 86 && j > 18 && j < 29) //sassoni
				return 3;
		}
		
		return -1;
	}
	
	/**
	 * Metodo che controlla se la casella di indice i e j è adiacente a una città e ritorna 0 se vicino a romani, 1 se vicino a britanni,
	 * 2 se vicino a galli, 3 se vicino a sassoni, -1 se non adiacente a nessuna città
	 * @param i X
	 * @param j Y
	 * @return civiltà
	 */
	public int isVicinoACitta(int i, int j)
	{
		int SxX = -1;
		int SuY = -1;
		int DxX = -1;
		int GiuY = -1;
		
		for(int k = 0; k < 4; k++)
		{
			switch(k)
			{
			case 0:
				SxX = 38;
				SuY = 35;
				DxX = 54;
				GiuY = 44;
				break;
			case 1:
				SxX = 38;
				SuY = 3;
				DxX = 54;
				GiuY = 12;
				break;
			case 2:
				SxX = 7;
				SuY = 19;
				DxX = 23;
				GiuY = 28;
				break;
			case 3:
				SxX = 69;
				SuY = 19;
				DxX = 85;
				GiuY = 28;
				break;
			}
			if(i == SxX - 1 && j >= SuY - 1 && j <= GiuY + 1) //lato sx citta
				return k;
			if(j == SuY - 1 && i >= SxX && i <= DxX) //lato superiore citta
				return k;
			if(i == DxX + 1 && j >= SuY - 1 && j <= GiuY + 1) //lato dx citta
				return k;
			if(j == GiuY + 1 && i >= SxX && i <= DxX) //lato inferiore citta
				return k;
		}
		
		return -1;
	}

	/**
	 * Rimuove elemento dallo scenario
	 * @param i X
	 * @param j Y
	 * @param ultimoChar Ultimo carattere della stringa dell'elemento da rimuovere
	 */
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

	/**
	 * Posiziona l'elemento contenuto nella variabile elemLblsGioco sullo scenario
	 * @param i X
	 * @param j Y
	 */
	public void posizionaElementoSuScenario(int i, int j)
	{
		/*Posizionamento oggetto sulla plancia di gioco*/
		if(elemLblsGioco.equals(Global.getLabels("i53")) || elemLblsGioco.equals(Global.getLabels("i54")) || elemLblsGioco.equals(Global.getLabels("i55")) ||
				elemLblsGioco.equals(Global.getLabels("i56")) || elemLblsGioco.equals(Global.getLabels("i57")) || 
				elemLblsGioco.equals(Global.getLabels("i58")) || elemLblsGioco.equals(Global.getLabels("i59")) ||
				elemLblsGioco.equals(Global.getLabels("i60")) || elemLblsGioco.equals(Global.getLabels("i61")))
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

	/**
	 * Individua l'oggetto su cui è stato cliccato tenendo conto se interno al villaggio o esterno al villaggio
	 * @param i X
	 * @param j Y
	 * @param interno true se interno ad una città, false altrimenti
	 * @return Ritorna il nome dell'oggetto individuato
	 */
	public String individuaOggetto(int i, int j, boolean interno) //i è la x, j è la y
	{
		scenarioCorrente = scenario.getScenario();
		String strCercata = null;

		StringTokenizer st;
		int stItera = 0;

		st = new StringTokenizer(scenarioCorrente[j+posSchermataY][i+posSchermataX]);
		while(st.hasMoreTokens()) {
			if(stItera == 0) //controllo che il pavimento sia ghiaia
				if(interno) //se e solo se l'edificio in questione è interno
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

	/**
	 * Controlla se è possibile piazzare l'elemento in elemLblsGioco nella cella corrente
	 * @param i X
	 * @param j Y
	 * @return true se possibile, false altrimenti
	 */
	public boolean isPiazzamentoPossibile(int i, int j) //i è la x, j è la y
	{
		int stItera;

		if(elemLblsGioco.equals(Global.getLabels("i53")) || elemLblsGioco.equals(Global.getLabels("i54")) || elemLblsGioco.equals(Global.getLabels("i55")) ||
				elemLblsGioco.equals(Global.getLabels("i56")) || elemLblsGioco.equals(Global.getLabels("i57")) || 
				elemLblsGioco.equals(Global.getLabels("i58")) || elemLblsGioco.equals(Global.getLabels("i59")) ||
				elemLblsGioco.equals(Global.getLabels("i60")) || elemLblsGioco.equals(Global.getLabels("i61")) || elemLblsGioco.equals(Global.getLabels("s60")))
		{
			//controlliamo che la lbl di posizione i, j sia disponibile (1x1)
			StringTokenizer st;
			stItera = 0;

			scenarioCorrente = scenario.getScenario();
			st = new StringTokenizer(scenarioCorrente[j+posSchermataY][i+posSchermataX]);
			while(st.hasMoreTokens()) {
				if(stItera == 0) //controllo che il pavimento sia ghiaia
					if(!st.nextToken().equals("g") && !elemLblsGioco.equals(Global.getLabels("s60")))
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
	public String getSitua(){
			return partita.getSituazioneDiGioco();
	}

	public void setSitua(String stringa) {
		partita.setSituazioneDiGioco(stringa);
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
}
