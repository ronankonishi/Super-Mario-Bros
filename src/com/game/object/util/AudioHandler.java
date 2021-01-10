package com.game.object.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandler {

	private final String PARENT_FOLDER = "/sfx";
	private String theme, invincible, oneup, bowserfalls, bowserfire, breakblock, bump, coin, fireball, fireworks, 
				   flagpole, gameover, jumpsmall, jumpsuper, kick, mariodie, pause, pipe, powerupAppears,
				   powerup, stageClear, stomp, vine, warning, worldClear;
	private Clip song;
	
	public AudioHandler () {
		theme = PARENT_FOLDER + "/theme.wav";
		invincible = PARENT_FOLDER + "/invincible.wav";
		
		oneup = PARENT_FOLDER + "/smb_1-up.wav";
		bowserfalls = PARENT_FOLDER + "/smb_bowserfalls.wav";
		bowserfire = PARENT_FOLDER + "/smb_bowserfire.wav";
		breakblock = PARENT_FOLDER + "/smb_breakblock.wav";
		bump = PARENT_FOLDER + "/smb_bump.wav";
		coin = PARENT_FOLDER + "/smb_coin.wav";
		fireball = PARENT_FOLDER + "/smb_fireball.wav";
		fireworks = PARENT_FOLDER + "/smb_fireworks.wav";
		flagpole = PARENT_FOLDER + "/smb_flagpole.wav";
		gameover = PARENT_FOLDER + "/smb_gameover.wav";
		jumpsmall = PARENT_FOLDER + "/smb_jump-small.wav";
		jumpsuper = PARENT_FOLDER + "/smb_jump-super.wav";
		kick = PARENT_FOLDER + "/smb_kick.wav";
		mariodie = PARENT_FOLDER + "/smb_mariodie.wav";
		pause = PARENT_FOLDER + "/smb_pause.wav";
		pipe = PARENT_FOLDER + "/smb_pipe.wav";
		powerupAppears = PARENT_FOLDER + "/smb_powerup_appears.wav";
		powerup = PARENT_FOLDER + "/smb_powerup.wav";
		stageClear = PARENT_FOLDER + "/smb_stage_clear.wav";
		stomp = PARENT_FOLDER + "/smb_stomp.wav";
		vine = PARENT_FOLDER + "/smb_vine.wav";
		warning = PARENT_FOLDER + "/smb_warning.wav";
		worldClear = PARENT_FOLDER + "/smb_world_clear.wav";
	}
	
	private void play(String path) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip.open(inputStream);
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f);
			clip.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void playSong(String path) {
        try {
        	if (song != null) song.stop();
			song = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
			song.open(inputStream);
			FloatControl gainControl = 
				    (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f);
	        song.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playTheme() {
		playSong(theme);
	}
	
	public void playInvincible() {
		playSong(invincible);
	}
	
	public void play1Up() {
		play(oneup);
	}
	
	public void playBowserfalls() {
		play(bowserfalls);
	}
	
	public void playBowerserfire() {
		play(bowserfire);
	}
	
	public void playBreakblock() {
		play(breakblock);
	}
	
	public void playBump() {
		play(bump);
	}
	
	public void playCoin() {
		play(coin);
	}
	
	public void playFireball() {
		play(fireball);
	}
	
	public void playFireworks() {
		play(fireworks);
	}
	
	public void playFlagpole() {
		play(flagpole);
	}
	
	public void playGameover() {
		play(gameover);
	}
	
	public void playJumpSmall() {
		play(jumpsmall);
	}
	
	public void playJumpSuper() {
		play(jumpsuper);
	}
	
	public void playKick() {
		play(kick);
	}
	
	public void playMariodie() {
		play(mariodie);
	}
	
	public void playPause() {
		play(pause);
	}
	
	public void playPipe() {
		play(pipe);
	}
	
	public void playPowerupAppears() {
		play(powerupAppears);
	}
	
	public void playPowerup() {
		play(powerup);
	}
	
	public void playStageClear() {
		play(stageClear);
	}
	
	public void playStomp() {
		play(stomp);
	}
	
	public void playVine() {
		play(vine);
	}
	
	public void playWarning() {
		play(warning);
	}
	
	public void playWorldClear() {
		play(worldClear);
	}
}
