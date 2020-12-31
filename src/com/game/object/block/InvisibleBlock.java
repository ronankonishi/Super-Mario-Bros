package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.GreenShroom;

public class InvisibleBlock extends Block{
	private GreenShroom greenShroom;
	private boolean disabled;
	private int yInc;
	private boolean smallHit;
	private boolean flip;
	
	public InvisibleBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		sprite = tex.getTile1();
		index = -1;
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
		if (index == -1) return;
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
	
	public boolean isHit() {
		return hit;
	}
	
	public void spawnGreenShroom() {
		greenShroom = new GreenShroom(x, (y - height), width, height, 1);
	}
	
	public GreenShroom getGreenShroom() {
		return greenShroom;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
}
