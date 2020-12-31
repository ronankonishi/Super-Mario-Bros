package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.GreenShroom;

public class InvisibleBlock extends Block{
	private GreenShroom greenShroom;
	private boolean disabled;
	
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
	}
	
	@Override
	public void render(Graphics g) {	
		if (index == -1) return;
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}
	
	public void hit() {
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
