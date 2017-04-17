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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GUIPartita extends JFrame{

	private JPanel contentPane;
	private JPanel panelTop;
	private JPanel panelBottom;
	private JPanel panelCenter;
	private JPanel panelBtnGoRight;
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
	private RoundedCornerButton btnGoUp;
	private RoundedCornerButton btnGoRight;
	private RoundedCornerButton btnGoDown;
	private RoundedCornerButton btnGoLeft;
	private JPanel panelBtnGoUp;
	private JPanel panelBtnGoDown;
	private JPanel panelBtnGoLeft;
	
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
		
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout2(3, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(0 , 0 , 0 , 0 ));
		panelTop = new JPanel(new GridLayout(1, 3, 0, 0));
		panelBottom = new JPanel(new GridLayout(1, 3, 0, 0));
		panelCenter = new JPanel(new GridLayout(1, 3, 0, 0));
		panelBtnGoRight = new JPanel();
		panelBtnGoLeft = new JPanel();
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
		JLabel mondo = new JLabel("Ciao Mondo");
		mondo.setHorizontalAlignment(SwingConstants.RIGHT);
		panelTop.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelTop.add(mondo);
		
		btnGoRight = new RoundedCornerButton();
		ImageIcon iconbtnGoRight = new ImageIcon("media/btnGoRight.png");
		Image scalebtnGoRight = iconbtnGoUp.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		btnGoRight.setIcon(iconbtnGoRight);
		btnGoRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		panelBtnGoRight.setBackground(Color.BLUE);
		panelBtnGoRight.add(btnGoRight);
		
		btnGoLeft = new RoundedCornerButton();
		ImageIcon iconbtnGoLeft = new ImageIcon("media/btnGoLeft.png");
		Image scalebtnGoLeft = iconbtnGoLeft.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		btnGoLeft.setIcon(iconbtnGoLeft);
		btnGoLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		panelBtnGoLeft.setBackground(Color.BLUE);
		panelBtnGoLeft.add(btnGoLeft);
		
		panelCenter.add(panelBtnGoLeft);
		panelCenter.add(new JLabel("ciao"));
		panelCenter.add(panelBtnGoRight);
		
		panelBottom.add(new JLabel("Ciao mondo"));
		
		btnGoDown = new RoundedCornerButton();
		ImageIcon iconbtnGoDown = new ImageIcon("media/btnGoDown.png");
		Image scalebtnGoDown = iconbtnGoDown.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		btnGoDown.setIcon(iconbtnGoDown);
		btnGoDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		panelBtnGoDown = new JPanel();
		panelBtnGoDown.setBackground(Color.BLUE);
		panelBtnGoDown.add(btnGoDown);
		
		panelBottom.add(panelBtnGoDown);
		
		panelBottom.add(new JLabel("Ciao mondo"));
		
		contentPane.add(panelTop);
		contentPane.add(panelCenter);
		contentPane.add(panelBottom);
		this.add(contentPane);
	}
}
