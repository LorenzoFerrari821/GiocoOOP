package gioco;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUIPartitaInformazioni extends JDialog {

	private JPanel contentPane;
	private JLabel lblBonusEco;
	private JLabel lblBonusMil;
	private JLabel lblBonusRic;
	private JLabel lblTruppeDef;
	private JLabel lblTruppeAtk;
	private JLabel lblGruppiAtk;
	private JLabel lblOroXTurno;
	private JLabel lblMatXTurno;
	private JLabel lblPRXTurno;
	private JLabel lblBEVal;
	private JLabel lblBMVal;
	private JLabel lblBRVal;
	private JLabel lblTruppeDefVal;
	private JLabel lblTruppeAtkVal;
	private JLabel lblGruppiAtkVal;
	private JLabel lblOroXTurnoVal;
	private JLabel lblMatXTurnoVal;
	private JLabel lblPRXTurnoVal;
	private RoundedCornerButton btnIndietro;
	private GridBagConstraints c;
	private Font fontFuturist;
	private Partita partita;
	private Clip audio;
	
	GUIPartitaInformazioni(Partita partita) {
		ImageIcon icona = new ImageIcon("media/Icona.png");                  //Carichiamo l'icona personalizzata
		Image scaledicona = icona.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		setIconImage(scaledicona);  

		Toolkit t1 = Toolkit.getDefaultToolkit();                                 //Cursore personalizzato
		Image img = t1.getImage("media/cursore.png");
		Point point = new Point(0,0);
		Cursor cursor = t1.createCustomCursor(img, point, "Cursore Personalizzato");
		setCursor(cursor); 

		this.partita = partita;
		
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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle(Global.getLabels("a7"));
		setBounds(0, 0, 650, 450);
		setMinimumSize(new Dimension(650, 450));   
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel(new GridBagLayout());
		setModal(true);
		
		c = new GridBagConstraints();
		c.anchor = new GridBagConstraints().LINE_END;
		
		c.gridx = 0;
		c.gridy = 0;
		lblBonusEco = new JLabel(Global.getLabels("s96"));
		lblBonusEco.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblBonusEco, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblBonusMil = new JLabel(Global.getLabels("s97"));
		lblBonusMil.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblBonusMil, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblBonusRic = new JLabel(Global.getLabels("s98"));
		lblBonusRic.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblBonusRic, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblTruppeDef = new JLabel(Global.getLabels("s99"));
		lblTruppeDef.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblTruppeDef, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblTruppeAtk = new JLabel(Global.getLabels("s100"));
		lblTruppeAtk.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblTruppeAtk, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblGruppiAtk = new JLabel(Global.getLabels("s101"));
		lblGruppiAtk.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblGruppiAtk, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblOroXTurno = new JLabel(Global.getLabels("s102"));
		lblOroXTurno.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblOroXTurno, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblMatXTurno = new JLabel(Global.getLabels("s103"));
		lblMatXTurno.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblMatXTurno, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblPRXTurno = new JLabel(Global.getLabels("s104"));
		lblPRXTurno.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblPRXTurno, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		if(partita.getTurnoCorrente() == partita.getGuiPartita().getIndiceProprietario())
			partita.calcolaRisorse(false);
		
		c.gridx++;
		c.gridy = 0;
		lblBEVal = new JLabel();
		lblBEVal.setText(Double.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getBonusEconomia()));
		lblBEVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblBEVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblBMVal = new JLabel();
		lblBMVal.setText(Double.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getBonusMilitare()));
		lblBMVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblBMVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblBRVal = new JLabel();
		lblBRVal.setText(Double.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getBonusRicerca()));
		lblBRVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblBRVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblTruppeDefVal = new JLabel();
		lblTruppeDefVal.setText(Integer.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getUnitaMunicipio().size()));
		lblTruppeDefVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblTruppeDefVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblTruppeAtkVal = new JLabel();
		int truppe = 0;
		for(GruppoMilitare g: partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getGruppiInAttacco())
		{
			for(String u: g.getGruppoMilitare())
			{
				truppe++;
			}
		}
		lblTruppeAtkVal.setText(Integer.toString(truppe));
		lblTruppeAtkVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblTruppeAtkVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblGruppiAtkVal = new JLabel();
		lblGruppiAtkVal.setText(Integer.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getGruppiInAttacco().size()));
		lblGruppiAtkVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblGruppiAtkVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblOroXTurnoVal = new JLabel();
		lblOroXTurnoVal.setText(Integer.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getOroXTurno()));
		lblOroXTurnoVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblOroXTurnoVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblMatXTurnoVal = new JLabel();
		lblMatXTurnoVal.setText(Integer.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getMatXTurno()));
		lblMatXTurnoVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblMatXTurnoVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		lblPRXTurnoVal = new JLabel();
		lblPRXTurnoVal.setText(Integer.toString(partita.getGiocatore().get(partita.getGuiPartita().getIndiceProprietario()).getPrXTurno()));
		lblPRXTurnoVal.setFont(fontFuturist.deriveFont(13f));
		contentPane.add(lblPRXTurnoVal, c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		c.gridy++;
		contentPane.add(new JLabel(" "), c);
		
		c.gridy++;
		btnIndietro = new RoundedCornerButton();
		btnIndietro.setFont(fontFuturist.deriveFont(13f));
		btnIndietro.setText(Global.getLabels("a0"));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/suonoindietro.wav")));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(Global.getLivVolume()); 
				audio.start();
				dispose();
			}
		});
		contentPane.add(btnIndietro, c);
		
		add(contentPane, BorderLayout.CENTER);
	}
}
