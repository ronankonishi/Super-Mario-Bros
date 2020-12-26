package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;

public class InvisibleBlock extends Block{
	private Texture tex = Game.getTexture();
	private int index = -1;
	private BufferedImage[] sprite;
	private boolean hit;
	
	public InvisibleBlock(int x, int y, int width, int height, int scale) {
		super(x, y, width, height, scale);
		sprite = tex.getTile1();
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
