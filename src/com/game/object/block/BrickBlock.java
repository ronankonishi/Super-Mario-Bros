package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.Debris;

public class BrickBlock extends Block{
	private Debris debris;
	private int yInc;
	private boolean smallHit;
	private boolean flip;
	
	public BrickBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
	}
	
	@Override
	public boolean shouldRemove() {
		if (debris != null && debris.shouldRemove()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void tick() {
		if (hit) {
			debris.tick();
			passable = true;
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
		if (hit) {
			debris.draw(g);
			return;
		}
				
		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);
	}
	
	@Override
	public void smallHit() {
		smallHit = true;
	}
	
	@Override
	public void largeHit() {
		hit = true;
		debris = new Debris(x, y, width, height, scale);
	}
}
