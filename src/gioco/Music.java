package gioco;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music{
	private static Clip audio;
	private static FloatControl gainControl;
	private static List<File> canzoni= new ArrayList<File>();
	private static Random rand;
	private static int numero;
	static void playSound(){
		canzoni.add(new File("media/menu1.wav"));
		canzoni.add(new File("media/menu2.wav"));
		canzoni.add(new File("media/menu3.wav"));
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
	static void stopSound(){
		audio.stop();
	}
	static void controlVolume(){
		gainControl.setValue(Global.getLivVolume()); 
	}
	static File scegliCanzone(){
		rand=new Random();
		numero=rand.nextInt((3 - 1) + 1) + 1;
		if(numero==1)
			return canzoni.get(0);
		else if (numero==2)
			return canzoni.get(1);
		else
			return canzoni.get(2);
	}
}
