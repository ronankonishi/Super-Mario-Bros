package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;

public class GroundBlock extends Block {
	public GroundBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 0;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
	}
	
	@Override
	public void render(Graphics g) {				
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}

	@Override
	public void hit() {}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
