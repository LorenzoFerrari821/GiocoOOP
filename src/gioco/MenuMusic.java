package gioco;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;





public class MenuMusic{

	static void PlaySound(File canzone){
		try{
			Clip audio=AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(canzone));
			audio.loop(1);
			audio.start();
		}
		catch(Exception e){}
	}
}
