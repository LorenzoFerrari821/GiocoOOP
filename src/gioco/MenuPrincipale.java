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

public class MenuPrincipale extends JFrame {


	private JPanel contentPane;  
	private JPanel panelsx;
	private JPanel paneldx;
	private JButton btnNuovaPartita;
	private JButton btnCaricaPartita;
	private JButton btnMultiplayer;
	private JButton btnOpzioni;
	private JButton btnExtra;
	private JButton btnObiettivi;
	private JButton btnEsci;
	private JPanel panelTitolo;;
	private JLabel titolo;
	private JPanel nuovapartita;
	private JLabel giocatore;
	private JLabel tutorial;
	private JLabel mappa;


	public MenuPrincipale() {
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
		
		
		btnNuovaPartita = new JButton("Nuova Partita");
		panelsx.add(btnNuovaPartita);
		
		btnNuovaPartita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				paneldx.setVisible(false);
				nuovapartita=new JPanel();
				nuovapartita.setLayout(new GridLayout(5,2,5,5));
				nuovapartita.setBackground(Color.white);
				contentPane.add(nuovapartita,BorderLayout.CENTER);
				giocatore=new JLabel("                                        Nome Giocatore");
				tutorial=new JLabel("                                        Tutorial");
				mappa=new JLabel("                                         Mappa");
				nuovapartita.add(giocatore);
				nuovapartita.add(tutorial);
				nuovapartita.add(mappa);
				
			
				
				
			}
		});
		
		btnCaricaPartita = new JButton("Carica Partita");
		panelsx.add(btnCaricaPartita);
		
		btnMultiplayer = new JButton("Multiplayer");
		panelsx.add(btnMultiplayer);
		
		btnOpzioni = new JButton("Opzioni");
		panelsx.add(btnOpzioni);
		
		btnExtra = new JButton("Extra");
		panelsx.add(btnExtra);
		
		btnObiettivi = new JButton("Obiettivi");
		panelsx.add(btnObiettivi);
		
		btnEsci = new JButton("Esci");
		panelsx.add(btnEsci);
		
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		ImageIcon icon = new ImageIcon("media/titolo.png");
		Image scaleImage = icon.getImage().getScaledInstance(378, 133,Image.SCALE_DEFAULT);
		titolo = new JLabel(new ImageIcon(scaleImage));
		panelTitolo.add(titolo, BorderLayout.CENTER);
	}

}
