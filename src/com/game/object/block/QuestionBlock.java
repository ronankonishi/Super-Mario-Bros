package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.item.Coin;

public class QuestionBlock extends Block{
	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	private boolean hit;
	private Coin coin;
	private boolean disabled;
	
	public QuestionBlock(int x, int y, int width, int height, int scale) {
		super(x, y, width, height, scale);
		index = 24;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (!disabled && hit) {
			index = 3;
			coin = new Coin((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), (int) getScale());
			hit = false;
			disabled = true;
		}
		if (coin != null) coin.tick();
		if (coin != null && coin.shouldRemove()) coin = null;
	}
	
	@Override
	public void render(Graphics g) {	
		if (coin != null) coin.render(g);
		
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
