package gioco;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Gestisce le musiche dei menù e gli effetti sonori di menù e della partita
 * @author Werther e Lorenzo
 *
 */
public class Music{
	private static Clip audio;
	private static FloatControl gainControl;
	private static File canzone1= new File("media/menu1.wav");
	private static File canzone2= new File("media/menu2.wav");
	private static File canzone3= new File("media/menu3.wav");
	private static Random rand;
	private static int numero;
	
	/**
	 * Riproduce le musiche
	 */
	static void playSound(){
		try{
			audio=AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(scegliCanzone()));
			gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(Global.getLivVolume()); 
			audio.loop(1);
			audio.start();
		}
		catch(Exception e){}
	}
	
	/**
	 * Interrompe la riproduzione di musiche
	 */
	static void stopSound(){
		audio.stop();
	}
	
	/**
	 * Setta il volume in base al volume di sistema
	 */
	static void controlVolume(){
		gainControl.setValue(Global.getLivVolume()); 
	}
	
	/**
	 * Sceglie canzone da riprodurre in modo randomico fra le musiche disponibili
	 * @return Ritorna il file da riprodurre
	 */
	static File scegliCanzone(){
		rand=new Random();
		numero=rand.nextInt((3 - 1) + 1) + 1;
		if(numero==1)
			return canzone1;
		else if (numero==2)
			return canzone2;
		else
			return canzone3;
	}
}
