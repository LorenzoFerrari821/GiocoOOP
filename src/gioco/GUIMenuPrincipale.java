package gioco;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private RoundedCornerButton btnCaricaPartita;
	private RoundedCornerButton btnMultiplayer;
	private RoundedCornerButton btnOpzioni;
	private RoundedCornerButton btnExtra;
	private RoundedCornerButton btnObiettivi;
	private RoundedCornerButton btnEsci;
	private JPanel panelTitolo;;
	private JLabel titolo;
	private GUINuovaPartita panelNuovaPartita;
	private GUICaricaPartita panelCaricaPartita;
	private GUIMultiplayer panelMultiplayer;
	private GUIOpzioni panelOpzioni;
	private GUIExtra panelExtra;
	private GUIObiettivi panelObiettivi;
	

	/**
	 * Costruttore della classe; molto corposo poichè si occupa di posizionare ogni elemento
	 * all'interno dell'interfaccia, costruisce quindi un contentPane di base e altri pannelli
	 * per la GUI, insieme a tutti gli altri elementi.
	 */
	public GUIMenuPrincipale() {
		setTitle("GiocoOOP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(800,600));   
		
		setBounds(0, 0, 1280, 720);
		
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
		panelsx = new JPanel();
		paneldx = new JPanel();
		panelTitolo = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);

		
		panelsx.setLayout(new GridLayout(12, 1, 5, 5));
		panelsx.setBackground(Color.DARK_GRAY);
		contentPane.add(panelsx, BorderLayout.LINE_START);
		
		
		paneldx.setLayout(new GridLayout(1, 1, 5, 5));
		paneldx.setBackground(Color.decode("0xFABCD"));
		contentPane.add(paneldx, BorderLayout.CENTER);
		
		panelTitolo.setLayout(new BorderLayout(4, 4)); //questi numeri non hanno effetto
		panelTitolo.setBackground(Color.white);
		contentPane.add(panelTitolo, BorderLayout.NORTH);
		
		
		btnNuovaPartita = new RoundedCornerButton();
		ImageIcon iconbtnNuovaPartita = new ImageIcon("media/btnNuovaPartita.png");
		Image scalebtnNuovaPartita = iconbtnNuovaPartita.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnNuovaPartita.setIcon(iconbtnNuovaPartita);

		btnNuovaPartita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelNuovaPartita.setVisible(true);
				}catch(NullPointerException e){
					panelNuovaPartita=new GUINuovaPartita();
					contentPane.add(panelNuovaPartita,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnNuovaPartita);
		
		btnCaricaPartita = new RoundedCornerButton();
		ImageIcon iconbtnCaricaPartita = new ImageIcon("media/btnCaricaPartita.png");
		Image scalebtnCaricaPartita = iconbtnCaricaPartita.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnCaricaPartita.setIcon(iconbtnCaricaPartita);
		btnCaricaPartita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelCaricaPartita.setVisible(true);
				}catch(NullPointerException e){
					panelCaricaPartita=new GUICaricaPartita();
					contentPane.add(panelCaricaPartita,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnCaricaPartita);
		
		btnMultiplayer = new RoundedCornerButton();
		ImageIcon iconbtnMultiplayer = new ImageIcon("media/btnMultiplayer.png");
		Image scalebtnMultiplayer = iconbtnMultiplayer.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnMultiplayer.setIcon(iconbtnMultiplayer);
		
		btnMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelMultiplayer.setVisible(true);
				}catch(NullPointerException e){
					panelMultiplayer=new GUIMultiplayer();
					contentPane.add(panelMultiplayer,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnMultiplayer);
		
		btnOpzioni = new RoundedCornerButton();
		ImageIcon iconbtnOpzioni = new ImageIcon("media/btnOpzioni.png");
		Image scalebtnOpzioni = iconbtnOpzioni.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnOpzioni.setIcon(iconbtnOpzioni);
		btnOpzioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelOpzioni.setVisible(true);
				}catch(NullPointerException e){
					panelOpzioni=new GUIOpzioni();
					contentPane.add(panelOpzioni,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnOpzioni);
		
		btnExtra = new RoundedCornerButton();
		ImageIcon iconbtnExtra = new ImageIcon("media/btnExtra.png");
		Image scalebtnExtra = iconbtnExtra.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnExtra.setIcon(iconbtnExtra);
		btnExtra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelExtra.setVisible(true);
				}catch(NullPointerException e){
					panelExtra = new GUIExtra();
					contentPane.add(panelExtra, BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnExtra);
		
		btnObiettivi = new RoundedCornerButton();
		ImageIcon iconbtnObiettivi = new ImageIcon("media/btnObiettivi.png");
		Image scalebtnObiettivi = iconbtnObiettivi.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnObiettivi.setIcon(iconbtnObiettivi);
		btnObiettivi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				allPanelsdxNotVisibles();
				try {
					panelObiettivi.setVisible(true);
				}catch(NullPointerException e){
					panelObiettivi = new GUIObiettivi();
					contentPane.add(panelObiettivi,BorderLayout.CENTER);
				};
			}
		});
		panelsx.add(btnObiettivi);
		
		btnEsci = new RoundedCornerButton();
		ImageIcon iconbtnEsci = new ImageIcon("media/btnEsci.png");
		Image scalebtnEsci = iconbtnEsci.getImage().getScaledInstance(200, 30,Image.SCALE_DEFAULT);
		btnEsci.setIcon(iconbtnEsci);
		panelsx.add(btnEsci);
		
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		ImageIcon iconTitolo = new ImageIcon("media/titolo.png");
		Image scaleTitolo = iconTitolo.getImage().getScaledInstance(378, 133,Image.SCALE_DEFAULT);
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
