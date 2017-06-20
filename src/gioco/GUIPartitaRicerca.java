package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIPartitaRicerca extends JFrame {
	
	private Font fontFuturist;
	private GridBagConstraints c;
	private JPanel contentPane;
	private ImageIcon iconTick;
	private ImageIcon iconCross;
	private JLabel lblTick;
	private JLabel lblCross;
	private ImageIcon newiconCosto;
	
	private ValoriDiGioco valoriDiGioco; //Salvato qui per facilitarne l'accesso dalle funzioni di questa classe diverse dal costruttore
	private Giocatore giocatore;  //Salvato qui per facilitarne l'accesso dalle funzioni di questa classe diverse dal costruttore
	
	GUIPartitaRicerca(Giocatore giocatore, ValoriDiGioco valoriDiGioco) {
		
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
		
		setTitle("Ricerca");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 650, 450);
		setMinimumSize(new Dimension(650, 450));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new GridBagLayout());
		
		//Setting icone tick e cross
		iconTick = new ImageIcon("media/asset_grafici/icone/tick.png");
		Image scaleTick = iconTick.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconTick = new ImageIcon(scaleTick);
		lblTick = new JLabel();
		lblTick.setIcon(newiconTick);
		lblTick.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		iconCross = new ImageIcon("media/asset_grafici/icone/cross.png");
		Image scaleCross = iconCross.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconCross = new ImageIcon(scaleCross);
		lblCross = new JLabel();
		lblCross.setIcon(newiconCross);
		lblCross.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Setting icona costo
		ImageIcon iconCosto = new ImageIcon("media/asset_grafici/icone/ricerca.png");
		Image scaleCosto = iconCosto.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		newiconCosto = new ImageIcon(scaleCosto);
		
		c = new GridBagConstraints();
		c.anchor = new GridBagConstraints().CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		
		c.gridx++;
		
		this.valoriDiGioco = valoriDiGioco;
		this.giocatore = giocatore;
		
		//Ricerca sentieri
		ImageIcon iconSentieri = new ImageIcon("media/asset_grafici/icone/1etaclassica/sentieri.png");
		Image scaleSentieri = iconSentieri.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon newiconSentieri = new ImageIcon(scaleSentieri);
		inserisci1(1, 7, "Sentieri", newiconSentieri);
		
		
		add(new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
	
	public void inserisci2(int posx, int posy, String nome1, String nome2,
			ImageIcon icona1, ImageIcon icona2) {
		
		inserisci1(posx, posy, nome1, icona1);
		inserisci1(posx, posy+3, nome2, icona2);
	}
	
	public void inserisci1(int posx, int posy, String nome, ImageIcon icona) {

		//Aggiungo il nome della ricerca
		c.gridx = posx;
		c.gridy = posy;
		
		JLabel lblNome = new JLabel("  " + nome + "  ");
		lblNome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblNome, c);
		
		//Aggiungo l'icona della ricerca
		c.gridx++;
		JLabel lblIcon = new JLabel();
		lblIcon.setIcon(icona);
		lblIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblIcon, c);
		
		//Aggiungo il costo in punti ricerca
		c.gridx = posx;
		c.gridy++;
		JLabel lblCosto = new JLabel("  " + Integer.toString(valoriDiGioco.getValoriRicerche().get(nome)) + "  ");
		lblCosto.setIcon(newiconCosto);
		lblCosto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(lblCosto, c);
		
		
		//Aggiungo lo stato corrente della ricerca (tick o cross)
		c.gridx++;
		if(giocatore.getRicercheEffettuate().contains(nome))
			contentPane.add(lblTick, c);
		else
			contentPane.add(lblCross, c);
	}
}