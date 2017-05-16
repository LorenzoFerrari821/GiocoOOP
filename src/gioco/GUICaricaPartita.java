package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Questa classe si occupa di organizzare e definire l'interfaccia grafica
 * del sottomenù 'Carica Partita', contiene anche i mouseListener dei relativi
 * pulsanti in essa contenuti e le azioni da compiere.
 * @author Werther&Lorenzo
 *
 */
public class GUICaricaPartita extends JPanel {

	private JPanel pnlMenu;
	private JPanel pnlerror;
	private GridBagConstraints c, d;
	private Font fontFuturist;
	private JLabel lblData,lblDatav;
	private JLabel lblNumSalvataggio;
	private JLabel lblNomeGiocatore, lblNomeGiocatorev;
	private JLabel lblTutorial, lblTutorialv;
	private JLabel lblDifficolta, lblDifficoltav;
	private JLabel lblMappa, lblMappav;
	private JLabel lblCivilta, lblCiviltav;
	private JLabel lblTurno, lblTurnov;
	private JLabel lblEpoca, lblEpocav;
	private JLabel lblOro, lblOrov;
	private JLabel lblMateriali, lblMaterialiv;
	private JLabel lblPuntiRicerca, lblPuntiRicercav;
	private JPanel pnlInfoPartita;
	private JComboBox<Integer> cmbSalvataggi;
	private RoundedCornerButton btnIndietro;
	private RoundedCornerButton btnCarica;
	private RoundedCornerButton btnElimina;
	private ConnectionDB connessione1 = null;
	private ConnectionDB connessione2 = null;
	private ConnectionDB connessione3 = null;
	private ResultSet rs;
	private int dialogResult;
	private int index;
	private String linea;
	private int val;
	private int i=0;
	private int k=0;

	/**
	 * Costruttore, si occupa di definire tutti gli elementi dell'interfaccia grafica e azioni dei pulsanti
	 */
	GUICaricaPartita() {	
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

		lblNumSalvataggio=new JLabel("Salvataggio numero:");
		lblNumSalvataggio.setFont(lblNumSalvataggio.getFont().deriveFont(20f));
		lblData=new JLabel("Data: ");
		lblData.setFont(lblData.getFont().deriveFont(20f));
		lblNomeGiocatore = new JLabel("Giocatore: ");
		lblNomeGiocatore.setFont(lblNomeGiocatore.getFont().deriveFont(20f));
		lblTutorial = new JLabel("Tutorial: ");
		lblTutorial.setFont(lblTutorial.getFont().deriveFont(20f));
		lblDifficolta = new JLabel("Difficoltà: ");
		lblDifficolta.setFont(lblDifficolta.getFont().deriveFont(20f));
		lblMappa = new JLabel("Mappa: ");
		lblMappa.setFont(lblMappa.getFont().deriveFont(20f));
		lblCivilta = new JLabel("Civiltà: ");
		lblCivilta.setFont(lblCivilta.getFont().deriveFont(20f));
		lblTurno = new JLabel("Turno: ");
		lblTurno.setFont(lblTurno.getFont().deriveFont(20f));
		lblEpoca = new JLabel("Epoca: ");
		lblEpoca.setFont(lblEpoca.getFont().deriveFont(20f));
		lblOro = new JLabel("Oro: ");
		lblOro.setFont(lblOro.getFont().deriveFont(20f));
		lblMateriali = new JLabel("Materiali: ");
		lblMateriali.setFont(lblMateriali.getFont().deriveFont(20f));
		lblPuntiRicerca = new JLabel("Punti ricerca: ");
		lblPuntiRicerca.setFont(lblPuntiRicerca.getFont().deriveFont(20f));

		pnlInfoPartita = new JPanel(new GridBagLayout());

		c = new GridBagConstraints();
		c.anchor = new GridBagConstraints().LINE_END;
		c.gridx = 0;
		c.gridy = 0;

		pnlInfoPartita.add(lblData,c);
		c.gridy ++;
		pnlInfoPartita.add(lblNomeGiocatore, c);
		c.gridy ++;
		pnlInfoPartita.add(lblTutorial, c);
		c.gridy ++;
		pnlInfoPartita.add(lblDifficolta, c);
		c.gridy ++;
		pnlInfoPartita.add(lblMappa, c);
		c.gridy ++;
		pnlInfoPartita.add(lblCivilta, c);
		c.gridy ++;
		pnlInfoPartita.add(lblTurno, c);
		c.gridy ++;
		pnlInfoPartita.add(lblEpoca, c);
		c.gridy ++;
		pnlInfoPartita.add(lblOro, c);
		c.gridy ++;
		pnlInfoPartita.add(lblMateriali, c);
		c.gridy ++;
		pnlInfoPartita.add(lblPuntiRicerca, c);

		lblDatav=new JLabel("");
		lblDatav.setFont(lblDatav.getFont().deriveFont(16f));
		lblNomeGiocatorev = new JLabel("");
		lblNomeGiocatorev.setFont(lblNomeGiocatorev.getFont().deriveFont(16f));
		lblTutorialv = new JLabel("");
		lblTutorialv.setFont(lblTutorialv.getFont().deriveFont(16f));
		lblDifficoltav = new JLabel("");
		lblDifficoltav.setFont(lblDifficoltav.getFont().deriveFont(16f));
		lblMappav = new JLabel("");
		lblMappav.setFont(lblMappav.getFont().deriveFont(16f));
		lblCiviltav = new JLabel("");
		lblCiviltav.setFont(lblCiviltav.getFont().deriveFont(16f));
		lblTurnov = new JLabel("");
		lblTurnov.setFont(lblTurnov.getFont().deriveFont(16f));
		lblEpocav = new JLabel("");
		lblEpocav.setFont(lblEpocav.getFont().deriveFont(16f));
		lblOrov = new JLabel("");
		lblOrov.setFont(lblOrov.getFont().deriveFont(16f));
		lblMaterialiv = new JLabel("");
		lblMaterialiv.setFont(lblMaterialiv.getFont().deriveFont(16f));
		lblPuntiRicercav = new JLabel("");
		lblPuntiRicercav.setFont(lblPuntiRicercav.getFont().deriveFont(16f));



		c.anchor = new GridBagConstraints().LINE_START;
		c.gridx = 1;
		c.gridy = 0;

		pnlInfoPartita.add(lblDatav,c);
		c.gridy ++;
		pnlInfoPartita.add(lblNomeGiocatorev, c);
		c.gridy ++;
		pnlInfoPartita.add(lblTutorialv, c);
		c.gridy ++;
		pnlInfoPartita.add(lblDifficoltav, c);
		c.gridy ++;
		pnlInfoPartita.add(lblMappav, c);
		c.gridy ++;
		pnlInfoPartita.add(lblCiviltav, c);
		c.gridy ++;
		pnlInfoPartita.add(lblTurnov, c);
		c.gridy ++;
		pnlInfoPartita.add(lblEpocav, c);
		c.gridy ++;
		pnlInfoPartita.add(lblOrov, c);
		c.gridy ++;
		pnlInfoPartita.add(lblMaterialiv, c);
		c.gridy ++;
		pnlInfoPartita.add(lblPuntiRicercav, c);

		d = new GridBagConstraints();
		d.gridx = 2;
		d.gridy = 0;
		pnlMenu.add(pnlInfoPartita,  d);

		d.gridy ++;
		pnlMenu.add(new JLabel(" "), d);
		d.gridy ++;
		pnlMenu.add(new JLabel(" "), d);
		d.gridy ++;

		cmbSalvataggi = new JComboBox<Integer>();
		cmbSalvataggi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
					caricaInfoFile();
			}
		});
		d.gridx--;
		pnlMenu.add(lblNumSalvataggio,d);
		d.gridx++;
		pnlMenu.add(cmbSalvataggi, d);
		d.gridy ++;
		pnlMenu.add(new JLabel(" "), d);
		d.gridy ++;
		pnlMenu.add(new JLabel(" "), d);
		d.gridy ++;

		d.gridx = 0;
		btnIndietro = new RoundedCornerButton("INDIETRO");
		btnIndietro.setFont(fontFuturist.deriveFont(16f));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setVisible(false);
			}
		});
		pnlMenu.add(btnIndietro, d);

		d.gridx ++;
		pnlMenu.add(new JLabel("     "), d);
		d.gridx ++;

		btnCarica = new RoundedCornerButton("CARICA PARTITA");
		btnCarica.setFont(fontFuturist.deriveFont(16f));
		btnCarica.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		pnlMenu.add(btnCarica, d);

		d.gridx ++;
		pnlMenu.add(new JLabel("     "), d);
		d.gridx ++;

		btnElimina = new RoundedCornerButton("ELIMINA PARTITA");
		btnElimina.setFont(fontFuturist.deriveFont(16f));
		btnElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dialogResult = JOptionPane.showConfirmDialog (pnlerror, "Sei sicuro di voler eliminare questo salvataggio?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult== JOptionPane.YES_OPTION){
					try {    //Otteniamo l'indice del salvataggio da cancellare e chiamiamo la funzione apposita
						connessione3=new ConnectionDB();
						index=(cmbSalvataggi.getItemAt(cmbSalvataggi.getSelectedIndex()));  
						connessione3.elimina(index);
						creaComboBox();
					} catch (ClassNotFoundException | SQLException  | NullPointerException e) {
						e.printStackTrace();
						pnlerror = new JPanel();
						pnlerror.setBackground(Color.WHITE);
						JOptionPane.showMessageDialog(pnlerror, "Nessun salvataggio da eliminare",
								"Errore", JOptionPane.ERROR_MESSAGE);
						if(connessione3!=null)
							connessione3.closeConnection();
					} finally{
						if(connessione3!=null)
							connessione3.closeConnection();
					}
				}
			}
		});
		pnlMenu.add(btnElimina, d);
		add(pnlMenu, BorderLayout.CENTER);
	}

	/**
	 * Cerca tutti i salvataggi nel database e li carica nella ComboBox
	 */
	public void creaComboBox(){
		cmbSalvataggi.removeAllItems();	
		try {	
			connessione2 = new ConnectionDB();           //Connessione al database
			rs=connessione2.executeQuery();              //Lettura di tutti i salvataggi
			for(i=0;rs.next();++i){                      //Conteggio delle righe 
			}
			for(k=0;k<i;++k)                             //Riempiamo la ComboBox con i vari indici
				cmbSalvataggi.addItem(k+1);
			if(cmbSalvataggi.getItemCount() == 0){       //Errore se non vengono trovati salvataggi. Non mostriamo nessuna informazione
				lblDatav.setText("");
				lblNomeGiocatorev.setText("");
				lblTutorialv.setText("");
				lblDifficoltav.setText("");
				lblMappav.setText("");
				lblCiviltav.setText("");					
				lblTurnov.setText("");
				lblEpocav.setText("");					
				lblOrov.setText("");					
				lblMaterialiv.setText("");				
				lblPuntiRicercav.setText("");
				pnlerror = new JPanel();
				pnlerror.setBackground(Color.WHITE);
				JOptionPane.showMessageDialog(pnlerror, "Non sono stati trovati salvataggi.",
						"Attenzione", JOptionPane.WARNING_MESSAGE);
			}
		} catch(SQLException |ClassNotFoundException e) {
			e.printStackTrace();
			if(connessione2!=null)
				connessione2.closeConnection();
		}  finally{
			if(connessione2!=null)
				connessione2.closeConnection();
		}
	}

	/**
	 * Controlla il salvataggio selezionato e visualizza le relative informazioni
	 */
	private void caricaInfoFile(){
		try {			
			connessione1 = new ConnectionDB();             //Crea la connessione al DB
			rs=connessione1.executeQuery();                
			index=(cmbSalvataggi.getItemAt(cmbSalvataggi.getSelectedIndex()));     //Otteniamo l'indice selezionato dall'utente
			while(rs.getRow()!=index)     //Cerchiamo la riga corrispondente all'indice e leggiamo le varie informazioni
				rs.next();                         
			linea=rs.getString("Data");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblDatav.setText(linea);
			linea=rs.getString("NomeGiocatore");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblNomeGiocatorev.setText(linea);
			linea=rs.getString("Tutorial");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblTutorialv.setText(linea);
			linea=rs.getString("Difficoltà");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblDifficoltav.setText(linea);
			linea=rs.getString("Mappa");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblMappav.setText(linea);
			linea=rs.getString("Civiltà");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblCiviltav.setText(linea);
			val=rs.getInt("Turno");						
			lblTurnov.setText(Integer.toString(val));
			linea=rs.getString("Epoca");
			linea=linea.substring(linea.indexOf("_:")+1);
			lblEpocav.setText(linea);
			val=rs.getInt("Oro");						
			lblOrov.setText(Integer.toString(val));
			val=rs.getInt("Materiali");						
			lblMaterialiv.setText(Integer.toString(val));
			val=rs.getInt("Punti_ricerca");						
			lblPuntiRicercav.setText(Integer.toString(val));
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			if(connessione1!=null)
				connessione1.closeConnection();
		}  finally{
			if(connessione1!=null)
				connessione1.closeConnection();
		}
	}			
}


