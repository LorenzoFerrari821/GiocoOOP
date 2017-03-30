import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class Bim extends JFrame {
	private JPanel pannellodx;
	private JPanel contentPane;
	private JTextField txtNomeGiocatore;
	private JTextField textField;
	private JTextField txtCivilta;
	private JTextField txtTutorial;
	private JTextField txtMappa;
	private JTextField txtDifficolt;

	public Bim() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(800,600));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JPanel pannellotitolo = new JPanel();
		pannellotitolo.setBackground(Color.ORANGE);
		contentPane.add(pannellotitolo, BorderLayout.NORTH);
		
		ImageIcon icon = new ImageIcon("titolo.png");         // Bisogna trovare l'immagine
		Image scaleImage = icon.getImage().getScaledInstance(378, 133,Image.SCALE_DEFAULT);
		JLabel titolo = new JLabel(new ImageIcon(scaleImage));
		pannellotitolo.add(titolo, BorderLayout.CENTER);
	

		
		JPanel pannellosx = new JPanel();
		pannellosx.setBackground(Color.YELLOW);
		contentPane.add(pannellosx, BorderLayout.WEST);
		pannellosx.setLayout(new MigLayout("", "[]", "[][][][][][][][][]"));
		
		JButton btnNewButton = new JButton("Nuova partita");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pannellodx.setVisible(false);
				JPanel pannellonuovapartita=new JPanel();
				contentPane.add(pannellonuovapartita,BorderLayout.CENTER);

				pannellonuovapartita.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][]"));
				pannellonuovapartita.setBackground(Color.blue);
				
				txtNomeGiocatore = new JTextField();
				txtNomeGiocatore.setEditable(false);
				txtNomeGiocatore.setText("Nome Giocatore");
				pannellonuovapartita.add(txtNomeGiocatore, "flowx,cell 3 3");
				txtNomeGiocatore.setColumns(10);
				
				textField = new JTextField();
				pannellonuovapartita.add(textField, "cell 3 3,growx");
				textField.setColumns(10);
				
				txtCivilta = new JTextField();
				txtCivilta.setEditable(false);
				txtCivilta.setText("Civilta");
				pannellonuovapartita.add(txtCivilta, "flowx,cell 3 4,alignx left");
				txtCivilta.setColumns(10);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setToolTipText("");
				comboBox.setMaximumRowCount(4);
				pannellonuovapartita.add(comboBox, "cell 3 4,growx");
				
				txtTutorial = new JTextField();
				txtTutorial.setText("Tutorial");
				txtTutorial.setEditable(false);
				pannellonuovapartita.add(txtTutorial, "flowx,cell 3 5,alignx left");
				txtTutorial.setColumns(10);
				
				JRadioButton rdbtnSi = new JRadioButton("Si");
				pannellonuovapartita.add(rdbtnSi, "cell 3 5");
				
				JRadioButton rdbtnNo = new JRadioButton("No");
				pannellonuovapartita.add(rdbtnNo, "cell 3 5");
				
				txtMappa = new JTextField();
				txtMappa.setText("Mappa");
				txtMappa.setEditable(false);
				pannellonuovapartita.add(txtMappa, "flowx,cell 3 6,alignx left");
				txtMappa.setColumns(10);
				
				JComboBox comboBox_1 = new JComboBox();
				pannellonuovapartita.add(comboBox_1, "cell 3 6,growx");
				
				txtDifficolt = new JTextField();
				txtDifficolt.setText("Difficolt\u00E0");
				txtDifficolt.setEditable(false);
				pannellonuovapartita.add(txtDifficolt, "flowx,cell 3 7,alignx left");
				txtDifficolt.setColumns(10);
				
				JComboBox comboBox_2 = new JComboBox();
				pannellonuovapartita.add(comboBox_2, "cell 3 7,growx");
			
					
			}
		});
		
		pannellosx.add(btnNewButton, "cell 0 0,growx");
		
		JButton btnNewButton_1 = new JButton("Carica partita");
		pannellosx.add(btnNewButton_1, "cell 0 1,growx");
		
		JButton btnNewButton_2 = new JButton("MultiPlayer");
		pannellosx.add(btnNewButton_2, "cell 0 2,growx");
		
		JButton btnNewButton_3 = new JButton("Opzioni");
		pannellosx.add(btnNewButton_3, "cell 0 3,growx");
		
		JButton btnNewButton_4 = new JButton("Obbiettivi");
		pannellosx.add(btnNewButton_4, "cell 0 4,growx");
		
		JButton btnNewButton_5 = new JButton("Extra");
		pannellosx.add(btnNewButton_5, "cell 0 5,growx");
		
		JButton btnNewButton_6 = new JButton("Esci");
		pannellosx.add(btnNewButton_6, "cell 0 6,growx");
		
		 pannellodx = new JPanel();
		pannellodx.setBackground(Color.RED);
		contentPane.add(pannellodx, BorderLayout.CENTER);
		
	}

}
