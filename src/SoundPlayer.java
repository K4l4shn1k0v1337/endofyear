
import javax.sound.sampled.*;
import java.io.*;

public class SoundPlayer
{
	
	public void playtirosound(String musicfile) {
		File soundFile = new File(musicfile);
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream= AudioSystem.getAudioInputStream(soundFile);
			clip.open(inputStream);
			clip.start();
		}
	catch(Exception e)
		{
		System.out.println(e);
		}
	}
}


