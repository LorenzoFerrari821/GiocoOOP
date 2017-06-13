package gioco;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUIPartitaOpzioni extends JFrame{
	private RoundedCornerButton btnTornaMenu;
	private RoundedCornerButton btnEsci;
	private Window[] finestreAttive;
	private JPanel contentPane;
	
	GUIPartitaOpzioni () {
		setLayout(new FlowLayout()); //impostato un flowlayout
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout2(3, 1, 0, 0));
		contentPane.setBorder(new EmptyBorder(10 , 10 , 10 , 10 ));
		btnTornaMenu = new RoundedCornerButton();
		setTitle("Opzioni");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(300, 500));   
		setLocationRelativeTo(null);
		
		btnTornaMenu = new RoundedCornerButton("Torna al menù");
		btnTornaMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				finestreAttive = Frame.getWindows();
				 int scelta =0;
				 scelta = JOptionPane.showConfirmDialog(
						    null, "Stai per tornare al menù principale, perderai i progressi non salvati.\nUscire dalla partita?", "Conferma",
						    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				   if(scelta == 0)
				   {
					   finestreAttive[0].setVisible(true);
					   finestreAttive[1].dispose();
					   dispose();
				   }
			}
		});
		contentPane.add(btnTornaMenu);
		
		btnEsci = new RoundedCornerButton("Esci");
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int scelta =0;
				scelta = JOptionPane.showConfirmDialog(
						    null, "Stai per uscire dal gioco, perderai i progressi non salvati.\nUscire?", "Conferma",
						    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(scelta == 0)
					System.exit(0);
			}
		});
		contentPane.add(btnEsci);
		
		
		add(contentPane);
	}
}
