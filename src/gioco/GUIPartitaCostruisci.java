package gioco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUIPartitaCostruisci extends JFrame 
{
	private Font fontFuturist;
	private JPanel contentPane;
	private JPanel pnlTop;
	private JPanel pnlMid;
	private JPanel pnlBot;
	
	private JRadioButton rdoClassica;
	private JRadioButton rdoMedioevo;
	private JRadioButton rdoVittoriana;
	private ButtonGroup groupRdoEta;
	private JLabel lblEta;
	
	private RoundedCornerButton btnMuovi;
	private RoundedCornerButton btnVendi;
	private RoundedCornerButton btnInfo;
	private RoundedCornerButton btnCompra;
	private RoundedCornerButton btnIndietro;
	
	private Giocatore giocatore;
	private IconeGrafiche iconeGrafiche;
	private ValoriDiGioco valoriDiGioco;
	
	GUIPartitaCostruisci(Giocatore giocatore, ValoriDiGioco valoriDiGioco)
	{
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
		
		setTitle("Costruisci");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 650, 450);
		setMinimumSize(new Dimension(650, 450));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new BorderLayout(0,0));
		
		this.giocatore = giocatore;
		this.valoriDiGioco = valoriDiGioco;
		
		iconeGrafiche = new IconeGrafiche();
		
		//costruzione pannello top
		rdoClassica = new JRadioButton("Classica");
		rdoMedioevo = new JRadioButton("Medioevo");
		rdoVittoriana = new JRadioButton("Vittoriana");
		
		groupRdoEta = new ButtonGroup();
		groupRdoEta.add(rdoClassica);
		groupRdoEta.add(rdoMedioevo);
		groupRdoEta.add(rdoVittoriana);
		
		pnlTop = new JPanel(new GridLayout2(1, 4, 0, 0));
		lblEta = new JLabel("ETÀ:");
		lblEta.setFont(fontFuturist.deriveFont(13f));
		pnlTop.add(lblEta);
		
		
		
		ItemListener itemListener = new ItemListener() 
		{
			String lastSelected;
			public void itemStateChanged(ItemEvent itemEvent) {
		    AbstractButton aButton = (AbstractButton)itemEvent.getSource();
		    int state = itemEvent.getStateChange();
		    String label = aButton.getText(); //label rappresenta la label selezionata al momento
		    
		    if (state == ItemEvent.SELECTED) 
		    {
		    	popolaNegozio(label);
		    } 
		  }
		};
		
		rdoClassica.addItemListener(itemListener);
		rdoMedioevo.addItemListener(itemListener);
		rdoVittoriana.addItemListener(itemListener);
		  
	    pnlTop.add(rdoClassica);
		pnlTop.add(rdoMedioevo);
		pnlTop.add(rdoVittoriana);
		contentPane.add(pnlTop, BorderLayout.NORTH);
		
		pnlMid = new JPanel(new GridLayout(20, 3));
		
		contentPane.add(new JScrollPane(pnlMid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		
		pnlBot = new JPanel(new GridLayout2(1, 5, 0, 0));
		
		btnMuovi = new RoundedCornerButton();
		btnMuovi.setFont(fontFuturist.deriveFont(13f));
		btnMuovi.setText("MUOVI");
		btnMuovi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		pnlBot.add(btnMuovi);
		
		btnVendi = new RoundedCornerButton();
		btnVendi.setFont(fontFuturist.deriveFont(13f));
		btnVendi.setText("VENDI");
		btnVendi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		pnlBot.add(btnVendi);
		
		btnInfo = new RoundedCornerButton();
		btnInfo.setFont(fontFuturist.deriveFont(13f));
		btnInfo.setText("INFORMAZIONI");
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		pnlBot.add(btnInfo);
		
		btnCompra = new RoundedCornerButton();
		btnCompra.setFont(fontFuturist.deriveFont(13f));
		btnCompra.setText("COMPRA");
		btnCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
		pnlBot.add(btnCompra);
		
		btnIndietro = new RoundedCornerButton();
		btnIndietro.setFont(fontFuturist.deriveFont(13f));
		btnIndietro.setText("INDIETRO");
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		pnlBot.add(btnIndietro);
		
		contentPane.add(pnlBot, BorderLayout.SOUTH);
		
		add(contentPane);
	}
	
	/*
	 * Riempie la pagina negozio in base all'età selezionata
	 */
	public void popolaNegozio(String eta)
	{
		if(eta == "Classica")
		{
			pnlMid.removeAll();
			if(giocatore.getRicercheEffettuate().contains("Sentieri"))
			{
				for(int i = 0; i < 20; i++)
				{
					JLabel lblSentiero = new JLabel("Sentiero");
					lblSentiero.setIcon(iconeGrafiche.newiconSentieri);
					pnlMid.add(lblSentiero);
					
					JLabel lblOro = new JLabel();
					lblOro.setText(valoriDiGioco.getValoriOro().get("Sentiero").toString());
					pnlMid.add(lblOro);
					
					JLabel lblMat = new JLabel();
					lblMat.setText(valoriDiGioco.getValoriMat().get("Sentiero").toString());
					pnlMid.add(lblMat);
					
					pnlMid.setVisible(false);
					pnlMid.setVisible(true);
				}
				
			}
		}
		
		if(eta == "Medioevo")
		{
			
		}
		
		if(eta == "Vittoriana")
		{
			
		}
	}
}
