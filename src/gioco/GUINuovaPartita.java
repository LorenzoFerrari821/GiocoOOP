package gioco;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUINuovaPartita extends JPanel {

	private JPanel pnlMenu;
	private GridBagConstraints c;
	private JTextField txtNomeGiocatore;
	private JCheckBox chkTut;
	private JComboBox<String> listMappa;
	private JComboBox<String> listCivilta;
	private JComboBox<String> listDifficolta;
	
	GUINuovaPartita() {
		pnlMenu = new JPanel(new GridBagLayout());
		setBackground(Color.decode("0x9379db"));
		add(pnlMenu);
		
		pnlMenu.setBackground(Color.BLUE);
		
		c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		pnlMenu.add(new JLabel("Nome Giocatore: "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("Tutorial: "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("Mappa: "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("Civiltà: "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("Difficoltà: "), c);
		
		c.gridy = 0;
		c.gridx = 1;
		pnlMenu.add(txtNomeGiocatore = new JTextField(10), c);
		
		c.gridy ++;
		pnlMenu.add(chkTut = new JCheckBox(), c);
		
		c.gridy ++;
		listMappa = new  JComboBox<String>();
		listMappa.addItem("Predefinita");
		listMappa.addItem("Generata casualmente");
		listMappa.addItem("Estiva");
		listMappa.addItem("Invernale");
		pnlMenu.add(listMappa, c);
		
		c.gridy ++;
		listCivilta = new  JComboBox<String>();
		listCivilta.addItem("Romani");
		listCivilta.addItem("Galli");
		listCivilta.addItem("Britanni");
		listCivilta.addItem("Crauti");
		pnlMenu.add(listCivilta, c);
		
		c.gridy ++;
		listDifficolta = new  JComboBox<String>();
		listDifficolta.addItem("Facile");
		listDifficolta.addItem("Medio");
		listDifficolta.addItem("Difficile");
		listDifficolta.addItem("Incubo");
		pnlMenu.add(listDifficolta, c);
	}
}
