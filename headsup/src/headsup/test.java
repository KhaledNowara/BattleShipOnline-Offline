package headsup;

import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;

import sun.audio.*;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sun.media.jfxmedia.AudioClip;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class test {
	//static MediaPlayer mediaPlayer;
	//static Media hit = new Media (new File(getClass().getClassLoader().getResource("SomeTextFile.txt").toURI()).toString());

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.print(JOptionPane.showConfirmDialog(null, "please select a player","Error",JOptionPane.YES_NO_OPTION ));}}
	
		/*@SuppressWarnings("deprecation")
		java.net.URL url;
		try {
			url = new File("/codes/headsup/src/headsup/boom.wav").toURL();
			java.applet.AudioClip clip = Applet.newAudioClip(url);
			java.applet.AudioClip clip2 = Applet.newAudioClip(url);
			clip.play();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		System.out.println ("test");

}
public static void play (Media File) {
	try {
		mediaPlayer.stop();
		mediaPlayer = new MediaPlayer(File);
		mediaPlayer.play();
		
		//while (true) {
		//System.out.println (mediaPlayer.getOnPlaying());}
		
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
}
}*/