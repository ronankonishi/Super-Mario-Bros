package com.game.object.block;

import java.awt.Graphics;

import com.game.main.Game;
import com.game.object.item.RedFlower;
import com.game.object.item.RedShroom;
import com.game.object.util.AudioHandler;

public class QuestionFlowerBlock extends Block{
	private AudioHandler audioHandler = Game.getAudioHandler();
	private RedShroom redShroom;
	private RedFlower redFlower;
	private int yInc;
	private boolean flip;
	
	public QuestionFlowerBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 24;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (hit && !disabled) {
			index = 3;
			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				flip = false;
				hit = false;
				disabled = true;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);
	}
	
	public void spawnRedShroom() {
		audioHandler.playPowerupAppears();
		redShroom = new RedShroom(x, y, width, height, 1);
	}
	
	public void spawnRedFlower() {
		audioHandler.playPowerupAppears();
		redFlower = new RedFlower(x, y, width, height, 1);
	}
	
	public RedShroom getRedShroom() {
		return redShroom;
	}
	
	public RedFlower getRedFlower() {
		return redFlower;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
	
	@Override
	public void largeHit() {
		hit = true;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
}
