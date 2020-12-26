package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;

public class BrickBlock extends Block{
	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	private boolean hit;
	private Debris debris;
	
	public BrickBlock(int x, int y, int width, int height, int scale) {
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
		
		g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
	}
	
	
	public void hit() {
		hit = true;
		debris = new Debris(getX(), getY(), getWidth(), getHeight(), getScale());
	}
}
