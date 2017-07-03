package gioco;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music{
	private static Clip audio;
	private static FloatControl gainControl;
	static void playSound(File canzone){
		try{
			audio=AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(canzone));
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
}
