
import javax.sound.sampled.*;
import java.io.*;

public class Shoot
{
	
	public void playmusic(String musicfile) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("disparo.wav")));
            clip.start();
            clip.drain();
            clip.stop();
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
