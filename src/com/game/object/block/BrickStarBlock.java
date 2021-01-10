package com.game.object.block;

import java.awt.Graphics;

import com.game.main.Game;
import com.game.object.item.Star;
import com.game.object.util.AudioHandler;

public class BrickStarBlock extends Block {
	private AudioHandler audioHandler = Game.getAudioHandler();
	private boolean disabled;
	private Star star;
	private int yInc;
	private boolean flip;
	
	public BrickStarBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (!disabled && hit) {
			index = 3;

			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				flip = false;
				disabled = true;
				hit = false;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {				
		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);
	}
	
	@Override
	public void largeHit() {
		hit = true;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
	
	public void spawnStar() {
		audioHandler.playPowerupAppears();
		star = new Star(x, y, width, height, 1);
	}
	
	public Star getStar() {
		return star;
	}

	public boolean isDisabled() {
		return disabled;
	}
}
