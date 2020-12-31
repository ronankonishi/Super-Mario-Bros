package com.game.object.block;

import java.awt.Graphics;

import com.game.object.item.Star;

public class BrickStarBlock extends Block {
	private boolean disabled;
	private Star star;
	
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
	}
	
	@Override
	public void render(Graphics g) {				
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
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
		star = new Star(x, (y - (height + 1)), width, height, 1);
	}
	
	public Star getStar() {
		return star;
	}

	public boolean isDisabled() {
		return disabled;
	}
}
