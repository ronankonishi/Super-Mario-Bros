package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;

public class SolidBlock extends Block{
	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	
	public SolidBlock(int x, int y, int width, int height, int scale) {
		super(x, y, width, height, scale);
		index = 28;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
	}
	
	@Override
	public void render(Graphics g) {				
		g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
	}

	@Override
	public void hit() {}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
