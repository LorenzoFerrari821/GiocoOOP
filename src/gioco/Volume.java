package gioco;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Questa classe permette di settare il volume Audio del gioco
public class Volume {  

	private float livVolume=0;  //Volume standard
	private Clip audio;
	
	
	public float getLevel(){
		return livVolume;	

	}
	public void setLevel(float l){
		FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		if(l==0)
			livVolume=gainControl.getMinimum();
		if(l==1)
			livVolume=0;
	}
}

