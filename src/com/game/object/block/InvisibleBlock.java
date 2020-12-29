package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;

public class InvisibleBlock extends Block{
	public InvisibleBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		sprite = tex.getTile1();
		index = -1;
	}
	
	@Override
	public void tick() {
		if (hit) {
			index = 3;
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
}
