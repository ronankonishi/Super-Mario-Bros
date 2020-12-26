package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;

public class BrickCoinsBlock extends Block {
	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	private boolean hit;
	private int hitCount;
	
	public BrickCoinsBlock(int x, int y, int width, int height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (hit) {
			hitCount++;
			if (hitCount == 10) {
				index = 3;
			}
			hit = false;
		}
	}
	
	@Override
	public void render(Graphics g) {				
		g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
	}
	
	public void hit() {
		hit = true;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
