package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.Debris;

public class BrickBlock extends Block{
	private Debris debris;
	
	public BrickBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
	}
	
	@Override
	public boolean shouldRemove() {
		if (debris.shouldRemove()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void tick() {
		if (hit) {
			debris.tick();
		}
	}
	
	@Override
	public void render(Graphics g) {
		if (hit) {
			debris.draw(g);
			return;
		}
		
		if (index == -1) return;
		
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}
	
	
	public void hit() {
		hit = true;
		debris = new Debris(x, y, width, height, scale);
	}
}
