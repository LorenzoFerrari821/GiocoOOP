package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	//private JPanel panelPartita;
	private JPanel panelStats;
	private JPanel panelMappa;
	//private JPanel panelPulsanti;
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
	private JLabel[][] lblsGioco;
	private JPanel panelGioco;
	Image scalelblPartita;
	ImageIcon newiconlblPartita;
	int partitaHeight = 16, partitaWidth = 31;
	
	GUIPartita()
	{
		setTitle("Play");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(1280,720));   
		
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
		contentPane.setLayout(new GridLayout2(3, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		panelTop = new JPanel(new GridLayout(1, 3, 0, 0));
		panelBottom = new JPanel(new GridLayout2(1, 3, 0, 0));
		panelCenter = new JPanel(new GridLayout2(1, 3, 0, 0));
		panelBtnGoRight = new JPanel();
		panelBtnGoLeft = new JPanel();
		
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
		c.anchor = GridBagConstraints.WEST;
		panelStats.add(lblOro, c);
		c.gridx ++;
		panelStats.add(new JLabel("  "), c);
		c.gridx ++;
		panelStats.add(lblOrov, c);
		c.gridx ++;
		panelStats.add(new JLabel("      "), c);
		c.gridx ++;
		panelStats.add(lblMateriali, c);
		c.gridx ++;
		panelStats.add(new JLabel("  "), c);
		c.gridx ++;
		panelStats.add(lblMaterialiv, c);
		c.gridx ++;
		panelStats.add(new JLabel("      "), c);
		c.gridx ++;
		panelStats.add(lblPuntiRicerca, c);
		c.gridx ++;
		panelStats.add(new JLabel("  "), c);
		c.gridx ++;
		panelStats.add(lblPuntiRicercav, c);
		
		panelTop.add(panelStats);
		
		btnGoUp = new RoundedCornerButton();
		btnGoRight = new RoundedCornerButton();
		btnGoDown = new RoundedCornerButton();
		btnGoLeft = new RoundedCornerButton();
		
		ImageIcon iconbtnGoUp = new ImageIcon("media/btnGoUp.png");
		Image scalebtnGoUp = iconbtnGoUp.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoUp = new ImageIcon(scalebtnGoUp);
		
		btnGoUp.setIcon(newiconbtnGoUp);
		btnGoUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		panelBtnGoLeft.setLayout(new BoxLayout(panelBtnGoLeft, BoxLayout.PAGE_AXIS));
		panelBtnGoLeft.add(Box.createVerticalGlue());
		panelBtnGoLeft.add(btnGoLeft);
		panelBtnGoLeft.add(Box.createVerticalGlue());
		
		panelCenter.add(panelBtnGoLeft);
		lblsGioco = new JLabel[partitaHeight][partitaWidth];
		panelGioco = new JPanel(new GridLayout(16, 31, 0, 0));
		
		ImageIcon iconlblPartita = new ImageIcon("media/paesaggio.png");
		scalelblPartita = iconlblPartita.getImage().getScaledInstance(36, 37, Image.SCALE_DEFAULT);
		newiconlblPartita = new ImageIcon(scalelblPartita);
		
		for(int i = 0; i < partitaHeight; i++)
		{
			for(int j = 0; j < partitaWidth; j++)
			{
				lblsGioco[i][j] = new JLabel();
				lblsGioco[i][j].setIcon(newiconlblPartita);
				panelGioco.add(lblsGioco[i][j]);
				
			}	
		}
		
		panelGioco.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				scalelblPartita = iconlblPartita.getImage().getScaledInstance(lblsGioco[0][0].getSize().width, 
						lblsGioco[0][0].getSize().height+1, Image.SCALE_DEFAULT);
				newiconlblPartita = new ImageIcon(scalelblPartita);
				panelGioco.removeAll();
				
				for(int i = 0; i < partitaHeight; i++)
				{
					for(int j = 0; j < partitaWidth; j++)
					{
						lblsGioco[i][j].setIcon(newiconlblPartita);
						panelGioco.add(lblsGioco[i][j]);
					}
				}
			}
		});
		
		panelCenter.add(panelGioco);
		
		panelCenter.add(panelBtnGoRight);
		
		panelBottom.add(new JLabel("Ciao mondo"));
		
		btnGoDown = new RoundedCornerButton();
		ImageIcon iconbtnGoDown = new ImageIcon("media/btnGoDown.png");
		Image scalebtnGoDown = iconbtnGoDown.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		ImageIcon newiconbtnGoDown = new ImageIcon(scalebtnGoDown);
		
		btnGoDown.setIcon(newiconbtnGoDown);
		btnGoDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		panelBtnGoDown = new JPanel();
		panelBtnGoDown.add(btnGoDown);
		
		panelBottom.add(panelBtnGoDown);
		
		panelBottom.add(new JLabel("Ciao mondo"));
		
		contentPane.add(panelTop);
		contentPane.add(panelCenter);
		contentPane.add(panelBottom);
		this.add(contentPane);
		
		pack();
	}
}
