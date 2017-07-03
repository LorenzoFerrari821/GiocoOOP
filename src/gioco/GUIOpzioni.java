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
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Questa classe si occupa di definire l'interfaccia delle Opzioni.
 * @author Werther&Lorenzo
 *
 */

public class GUIOpzioni extends JPanel {
	private JPanel pnlMenu;
	private RoundedCornerButton btnIndietro;
	private JLabel lblAudio;
	private JRadioButton audioSi;
	private JRadioButton audioNo;
	private ButtonGroup groupAudio;
	private GridBagConstraints c;
	private Font fontFuturist;
	private Clip audio;

	/**
	 * Costruttore della classe con gli elementi che compongono l'interfaccia grafica.
	 */	
	GUIOpzioni() {
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

		c = new GridBagConstraints();



		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;

		lblAudio=new JLabel("Audio  ");
		lblAudio.setFont(lblAudio.getFont().deriveFont(22f));
		pnlMenu.add(lblAudio, c);

		audioSi=new JRadioButton("Si");
		audioSi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			
				Global.setLivVolume(0f);   //Volume standard
			}
		});
		audioNo=new JRadioButton("No");
		audioNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					audio = AudioSystem.getClip();
					audio.open(AudioSystem.getAudioInputStream(new File("media/suonoindietro.wav")));  //Suono a caso ed inutile,solo per evitare errori
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				}
				FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
				Global.setLivVolume(gainControl.getMinimum());
			}
		});

		groupAudio=new ButtonGroup();
		groupAudio.add(audioSi);
		groupAudio.add(audioNo);
		c.gridx++;
		pnlMenu.add(audioSi,c);
		c.gridx++;
		pnlMenu.add(audioNo,c);


		c.gridx=-1;
		c.gridy=1;
		c.anchor = GridBagConstraints.LINE_END;

		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;
		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;
		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;
		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;
		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;
		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;
		pnlMenu.add(new JLabel(" "),c);
		c.gridy++;

		btnIndietro = new RoundedCornerButton("INDIETRO");
		btnIndietro.setFont(fontFuturist.deriveFont(16f));
		btnIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
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
			}
		});
		pnlMenu.add(btnIndietro, c);
		add(pnlMenu, BorderLayout.CENTER);
	}
}

