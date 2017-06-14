package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.awt.Window;
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
	private JPanel panelMappa;
	private JPanel panelPulsanti;
	private JLabel lblOro;
	private JLabel lblOrov;
	private JLabel lblMateriali;
	private JLabel lblMaterialiv;
	private JLabel lblPuntiRicerca;
	private JLabel lblPuntiRicercav;
	private JLabel lblMappa;
	private RoundedCornerButton btnGoUp;
	private RoundedCornerButton btnGoRight;
	private RoundedCornerButton btnGoDown;
	private RoundedCornerButton btnGoLeft;
	private JPanel panelBtnGoUp;
	private JPanel panelBtnGoDown;
	private JPanel panelBtnGoLeft;
	private JLabel[][] lblsGiocoFondo;
	private JLabel[][] lblsGioco;
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
	private Map<String, ImageIcon> icone;
	private GUIPartita guiPartita; //utilizzato per avere un riferimento a questa classe nelle chiamate a thread esterni
	GUIPartita()
	{
		setTitle("Empire Conquerors");
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
					   finestreAttive[0].setVisible(true);
					   dispose();
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
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout2(3, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		panelTop = new JPanel(new GridLayout(1, 3, 0, 0));
		panelBottom = new JPanel(new GridLayout(1, 3, 0, 0));
		panelCenter = new JPanel(new GridLayout2(1, 3, 0, 0));
		panelBtnGoRight = new JPanel();
		panelBtnGoLeft = new JPanel();
		
		lblOro = new JLabel("ORO");
		lblOro.setFont(fontFuturist.deriveFont(13f));
		lblOro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblOrov = new JLabel("0");
		lblOrov.setFont(fontFuturist.deriveFont(13f));
		lblOrov.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblMateriali = new JLabel("MATERIALI");
		lblMateriali.setFont(fontFuturist.deriveFont(13f));
		lblMateriali.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblMaterialiv = new JLabel("0");
		lblMaterialiv.setFont(fontFuturist.deriveFont(13f));
		lblMaterialiv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPuntiRicerca = new JLabel("PUNTI RICERCA");
		lblPuntiRicerca.setFont(fontFuturist.deriveFont(13f));
		lblPuntiRicerca.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblPuntiRicercav = new JLabel("0");
		lblPuntiRicercav.setFont(fontFuturist.deriveFont(13f));
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
		panelStats.add(new JLabel("    "), c);
		c.gridx ++;
		panelStats.add(lblMateriali, c);
		c.gridx ++;
		panelStats.add(lblMaterialiv, c);
		c.gridx ++;
		panelStats.add(new JLabel("    "), c);
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
		
		panelMappa = new JPanel(new GridLayout2(1, 1, 0, 0));
		lblMappa = new JLabel();
		ImageIcon iconlblMappa = new ImageIcon("media/mappa.png");
		Image scalelblMappa = iconlblMappa.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon newiconlblMappa = new ImageIcon(scalelblMappa);
		lblMappa.setIcon(newiconlblMappa);
		lblMappa.setHorizontalAlignment(SwingConstants.RIGHT);
		panelTop.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelTop.add(lblMappa);
		
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
				panelGioco.add(lblsGioco[i][j]);
				
			}	
		}
		caricaIcone(36, 37); //Questo metodo carica tutte le icone grafiche all'interno del gioco con dimensione di default (36 e 37)
		aggiornaSchermata(); //prende la situazione attuale da Scenario e parsando la matrice di stringhe crea il corrispondente grafico
		
		
		
		panelGioco.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				caricaIcone(lblsGioco[0][0].getSize().width, lblsGioco[0][0].getSize().height+1);
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
	}
	
	public void caricaIcone(int width, int height) {
		icone = new HashMap<>();
		
		//Caricamento pavimentazioni
		ImageIcon iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("t", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/v.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("v", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/d.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("d", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/n.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("n", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/g.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("g", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/gh.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("gh", new ImageIcon(scalelblPartita));
		
		//Caricamento icone scenario
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/x.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("x", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/y.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("y", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/z.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("z", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("at", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/am.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("am", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pm.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("pm", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/pg.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("pg", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/ap.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("ap", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/tr.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("tr", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/c.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("c", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/f.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("f", new ImageIcon(scalelblPartita));
		
		//caricamento icone alberi tondi
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("at1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("at2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("at3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/at4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("at4", new ImageIcon(scalelblPartita));
		
		//caricamento icone alberi
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("a1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("a2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("a3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/a4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("a4", new ImageIcon(scalelblPartita));
		
		//caricamento icone tronchetti
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("t1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("t2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("t3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/scenario/t4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("t4", new ImageIcon(scalelblPartita));
		
		//caricamento municipio età classica
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio1.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("ecmunicipio1", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio2.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("ecmunicipio2", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio3.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("ecmunicipio3", new ImageIcon(scalelblPartita));
		iconlblPartita = new ImageIcon("media/asset_grafici/1eta_classica/municipio4.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		icone.put("ecmunicipio4", new ImageIcon(scalelblPartita));
		
	}
	
	//Aggiorna la schermata in base alla posizione di visualizzazione corrente
	public void aggiornaSchermata() {
		scenario = new Scenario();
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
					daAggiungere.add(icone.get(st.nextToken()));
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

	public void setPosSchermataY(int posSchermataY) {
		this.posSchermataY = posSchermataY;
	}
	
}
