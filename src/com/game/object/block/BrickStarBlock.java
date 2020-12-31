package com.game.object.block;

import java.awt.Graphics;

import com.game.object.item.Star;

public class BrickStarBlock extends Block {
	private boolean disabled;
	private Star star;
	private int yInc;
	private boolean smallHit;
	private boolean flip;
	
	public BrickStarBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (hit) {
			index = 3;
			hit = false;
			disabled = true;
		}
		
		if (smallHit) {
			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				smallHit = false;
				flip = false;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {				
		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);
	}
	
	@Override
	public void largeHit() {
		smallHit = true;
		hit = true;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
	
	public void spawnStar() {
		star = new Star(x, y, width, height, 1);
	}
	
	public Star getStar() {
		return star;
	}

	public boolean isDisabled() {
		return disabled;
	}
}
