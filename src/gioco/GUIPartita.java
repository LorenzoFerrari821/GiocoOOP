package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIPartita extends JFrame{

	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelBottom;
	private JPanel panelSx;
	private JPanel panelDx;
	private JPanel panelPartita;
	private JPanel panelStats;
	private JPanel panelMappa;
	private JPanel panelPulsanti;
	private JLabel lblOro;
	private JLabel lblOrov;
	private JLabel lblMateriali;
	private JLabel lblMaterialiv;
	private JLabel lblPuntiRicerca;
	private JLabel lblPuntiRicercav;
	private JButton btnGoUp;
	private JButton btnGoRight;
	private JButton btnGoDown;
	private JButton btnGoLeft;
	private JPanel panelBtnGoUp;
	
	GUIPartita()
	{
		setTitle("Play");
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
		
		contentPane = new JPanel(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		panelTop = new JPanel(new GridLayout(1, 3, 0, 0));
		panelBottom = new JPanel(new BorderLayout(0, 0));
		panelSx = new JPanel(new BorderLayout (0, 0));
		panelDx = new JPanel(new BorderLayout(0, 0));
		panelTop.setBackground(Color.DARK_GRAY);
		
		lblOro = new JLabel("Oro: ");
		lblOrov = new JLabel("0");
		lblMateriali = new JLabel("Materiali: ");
		lblMaterialiv = new JLabel("0");
		lblPuntiRicerca = new JLabel("Punti ricerca: ");
		lblPuntiRicercav = new JLabel("0");
		
		panelStats = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panelStats.add(lblOro, c);
		c.gridx ++;
		panelStats.add(lblOrov, c);
		c.gridx ++;
		panelStats.add(lblMateriali, c);
		c.gridx ++;
		panelStats.add(lblMaterialiv, c);
		c.gridx ++;
		panelStats.add(lblPuntiRicerca, c);
		c.gridx ++;
		panelStats.add(lblPuntiRicercav, c);

		panelTop.add(panelStats);
		
		btnGoUp = new JButton();
		btnGoRight = new JButton("DESTRA");
		btnGoDown = new JButton("GIU");
		btnGoLeft = new JButton("SINISTRA");
		
		ImageIcon iconbtnGoUp = new ImageIcon("media/btnGoUp.png");
		Image scalebtnGoUp = iconbtnGoUp.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		btnGoUp.setIcon(iconbtnGoUp);
		btnGoUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		panelBtnGoUp = new JPanel();
		panelBtnGoUp.add(btnGoUp);
		
		panelTop.add(panelBtnGoUp);
		panelTop.add(new JLabel("Ciao mondo"));
		
		contentPane.add(panelTop, BorderLayout.NORTH);
		this.add(contentPane);
	}
}
