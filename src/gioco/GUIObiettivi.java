package gioco;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIObiettivi extends JPanel {

	GUIObiettivi() {
		setBackground(Color.red);
		
		JLabel lbl = new JLabel();
		ImageIcon iconlblPartita = new ImageIcon("media/cartman.png");
		
		lbl.setIcon(iconlblPartita);
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(lbl);
	}
}
