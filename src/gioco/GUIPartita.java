package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
	private JLabel lblOro;
	private JLabel lblOrov;
	private JLabel lblMateriali;
	private JLabel lblMaterialiv;
	private JLabel lblPuntiRicerca;
	private JLabel lblPuntiRicercav;
	
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
		panelTop = new JPanel(new BorderLayout(0, 0));
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
		
		panelTop.add(lblOro);
		panelTop.add(lblOrov);
		
		contentPane.add(panelTop, BorderLayout.NORTH);
		this.add(contentPane);
	}
}
