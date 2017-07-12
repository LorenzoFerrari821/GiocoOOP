


package gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Questa classe si occupa di mostrare all'utente gli extra riguardo al gioco
 * gestendo anche la parte grafica dell'interfaccia Extra.
 * @author Werther e Lorenzo
 *
 */
public class GUIExtra extends JPanel {
	

	private JPanel pnlMenu;
	private GridBagConstraints c;
	private RoundedCornerButton btnIndietro;
	private Font fontFuturist;
	private Clip audio;
	private GUIMenuPrincipale guiMenuPrincipale; //Inserito per avere un riferimento a menu principale
	private GUIExtra guiExtra;
	
	/**
	 * Costruttore della classe extra, contiene gli elementi che compongono l'interfaccia grafica.
	 */
	GUIExtra(GUIMenuPrincipale guiMenuPrincipale) {
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

		pnlMenu = new JPanel(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 0));
		pnlMenu.setBackground(Color.WHITE);
		this.guiMenuPrincipale = guiMenuPrincipale;
		guiExtra = this;
		
		c = new GridBagConstraints(); 
	
		//Prima Colonna
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;


		pnlMenu.add(new JLabel("Creato da Berselli Werther e Ferrari Lorenzo"), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("         "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("          "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("         "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("           "), c);
		
		//Seconda colonna
		c.gridy = 0;
		c.gridx = 2;
		c.anchor = GridBagConstraints.LINE_START;


	
		pnlMenu.add(new JLabel("     "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("      "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		//Fondo
		c.gridx = 0;
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;

		btnIndietro = new RoundedCornerButton(Global.getLabels("a0"));
		btnIndietro.setFont(fontFuturist.deriveFont(16f));
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
				setVisible(false);
				guiMenuPrincipale.ripristinaPanelDx(guiExtra.getWidth(), guiExtra.getHeight());
			}
		});
		pnlMenu.add(btnIndietro, c);

		c.gridy -= 2;
		c.gridx += 3;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel(" "), c);
		c.gridy ++;
		pnlMenu.add(new JLabel("                                                                                "),c); //Stringa vuota per modificare la struttura generale

		add(pnlMenu, BorderLayout.CENTER);
	}
}   
