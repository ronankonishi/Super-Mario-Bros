package com.game.object.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandler {

	private final String PARENT_FOLDER = "/sfx";
	private Clip clip;
	private AudioInputStream inputStream;
	
	public AudioHandler () {
		try {
			clip = AudioSystem.getClip();
			inputStream = AudioSystem.getAudioInputStream(getClass().getResource(PARENT_FOLDER + "/theme.wav"));
			clip.open(inputStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
}
