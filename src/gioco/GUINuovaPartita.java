package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Questa classe si occupa di organizzare e definire l'interfaccia grafica
 * del sottomen� 'Nuova Partita', contiene anche i mouseListener dei relativi
 * pulsanti in essa contenuti e le azioni da compiere.
 * @author Werther&Lorenzo
 *
 */
public class GUINuovaPartita extends JPanel {

	private JPanel pnlMenu;
	private GridBagConstraints c;
	private JTextField txtNomeGiocatore;
	private JCheckBox chkTut;
	private JComboBox<String> listMappa;
	private JComboBox<String> listCivilta;
	private JComboBox<String> listDifficolta;
	private RoundedCornerButton btnIndietro;
	private RoundedCornerButton btnAvvia;
	private JLabel lblNomeGiocatore;
	private JLabel lblTutorial;
	private JLabel lblMappa;
	private JLabel lblCivilta;
	private JLabel lblDifficolta;
	private Font fontFuturist;
	private GUIPartita framePartita;
	
	/**
	 * Costruttore della classe; molto corposo poich� si occupa di posizionare ogni elemento
	 * all'interno dell'interfaccia.
	 */
	GUINuovaPartita() {	
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
		
		pnlMenu = new JPanel(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		pnlMenu.setBackground(Color.WHITE);
		
		c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		
		lblNomeGiocatore = new JLabel("Nome Giocatore: ");
		lblNomeGiocatore.setFont(lblNomeGiocatore.getFont().deriveFont(22f));
		pnlMenu.add(lblNomeGiocatore, c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		lblTutorial = new JLabel("Tutorial: ");
		lblTutorial.setFont(lblTutorial.getFont().deriveFont(22f));
		pnlMenu.add(lblTutorial, c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		lblDifficolta = new JLabel("Difficolt�: ");
		lblDifficolta.setFont(lblDifficolta.getFont().deriveFont(22f));
		pnlMenu.add(lblDifficolta, c);
		
		
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		lblMappa = new JLabel("Mappa: ");
		lblMappa.setFont(lblMappa.getFont().deriveFont(22f));
		pnlMenu.add(lblMappa, c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		lblCivilta = new JLabel("Civilt�: ");
		lblCivilta.setFont(lblCivilta.getFont().deriveFont(22f));
		pnlMenu.add(lblCivilta, c);
		
		c.gridy = 0;
		c.gridx = 2;
		c.anchor = GridBagConstraints.LINE_START;
		
		pnlMenu.add(txtNomeGiocatore = new JTextField(16), c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(chkTut = new JCheckBox(), c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		listDifficolta = new  JComboBox<String>();
		listDifficolta.addItem("Facile");
		listDifficolta.addItem("Medio");
		listDifficolta.addItem("Difficile");
		listDifficolta.addItem("Maestro");
		pnlMenu.add(listDifficolta, c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		listMappa = new  JComboBox<String>();
		listMappa.addItem("Predefinita");
		listMappa.addItem("Generata casualmente");
		listMappa.addItem("Estiva");
		listMappa.addItem("Invernale");
		pnlMenu.add(listMappa, c);
		
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		listCivilta = new  JComboBox<String>();
		listCivilta.addItem("Romani");
		listCivilta.addItem("Galli");
		listCivilta.addItem("Britanni");
		listCivilta.addItem("Sassoni");
		pnlMenu.add(listCivilta, c);
		
		c.gridx = 0;
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		
		btnIndietro = new RoundedCornerButton("INDIETRO");
		btnIndietro.setFont(fontFuturist.deriveFont(16f));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		pnlMenu.add(btnIndietro, c);
		
		c.gridy -= 2;
		c.gridx += 3;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		btnAvvia = new RoundedCornerButton("AVVIA PARTITA");
		btnAvvia.setFont(fontFuturist.deriveFont(16f));
		btnAvvia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(creaNuovaPartita() == 1)
				{
					framePartita = new GUIPartita();
					framePartita.setVisible(true);
				}
			}
		});
		pnlMenu.add(btnAvvia, c);
		
		add(pnlMenu, BorderLayout.CENTER);
	}
	
	/**
	 * Questo metodo viene invocato nel momento in cui il JButton Avvia Partita �
	 * premuto. Raccoglie la configurazione della partita inserita dall'utente e con
	 * essi crea un primo file di salvataggio nella cartella 'data/salvataggi'.
	 */
	public int creaNuovaPartita()
	{
		if(txtNomeGiocatore.getText().equals(""))
		{
			JOptionPane.showMessageDialog(pnlMenu, "Il campo Nome Giocatore non pu� essere vuoto.");
			return -1;
		}
		else
		{
			long numFile = -1;
			String nomeFile;
			
			try {
				numFile = Files.list(Paths.get("data/salvataggi")).count();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(pnlMenu, "Si � verificato un errore inaspettato "
						+ "nella creazione della partita, verificare che la cartella dei salvataggi esista.",
						"Errore", JOptionPane.ERROR_MESSAGE);
			}
			
			if(numFile != -1)
			{
				Boolean nomeUnico = true;
				File folder = new File("data/salvataggi");
				File[] listOfFiles = folder.listFiles();
				do {
					nomeUnico = true;
					nomeFile = "salvataggio_" + Long.toString(numFile + 1) + ".txt";
					for(File f : listOfFiles)
					{
						if(nomeFile.equals(f.getName()))
							nomeUnico = false;
					}
					if(nomeUnico == false)
						numFile++;
				}while(nomeUnico == false);
				
				
				File f = new File("data/salvataggi/"+ nomeFile);
				f.getParentFile().mkdirs();
				BufferedWriter out;
				
				try {
					f.createNewFile();
					FileWriter fstream = new FileWriter("data/salvataggi/" + nomeFile, true);
					out = new BufferedWriter(fstream);
					out.write("NomeGiocatore_:" + txtNomeGiocatore.getText());
					out.newLine();
					if(chkTut.isSelected())
						out.write("Tutorial_:Si");
					else
						out.write("Tutorial_:No");
					out.newLine();
					out.write("Difficolta_:" + listDifficolta.getItemAt(listDifficolta.getSelectedIndex()));
					out.newLine();
					out.write("Mappa_:" + listMappa.getItemAt(listMappa.getSelectedIndex()));
					out.newLine();
					out.write("Civilta_:" + listCivilta.getItemAt(listCivilta.getSelectedIndex()));
					out.newLine();
					out.write("Turno_:0");
					out.newLine();
					out.write("Epoca_:Classica");
					out.newLine();
					out.write("Oro_:0");
					out.newLine();
					out.write("Materiali_:0");
					out.newLine();
					out.write("PuntiRicerca_:0");
					out.newLine();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			}
		}
		return 1;
	}
}
