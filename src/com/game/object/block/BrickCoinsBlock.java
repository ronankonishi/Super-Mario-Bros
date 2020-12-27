package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.item.Coin;

public class BrickCoinsBlock extends Block {
	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	private boolean hit;
	private int hitCount;
	private LinkedList<Coin> coins, removeCoins;
	
	public BrickCoinsBlock(int x, int y, int width, int height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
		coins = new LinkedList<Coin>();
		removeCoins = new LinkedList<Coin>();
	}
	
	@Override
	public void tick() {
		if (hit) {
			hitCount++;
			if (hitCount == 10) {
				index = 3;
			}
			coins.add(new Coin(getX(), getY(), getWidth(), getHeight(), getScale()));
			hit = false;
		}
		
		for (Coin coin : coins) {
			coin.tick();
			if (coin.shouldRemove()) removeCoins.add(coin);
		}
		for (Coin coin : removeCoins) {
			coins.remove(coin);
		}
	}
	
	@Override
	public void render(Graphics g) {
		for (Coin coin : coins) {
			coin.render(g);
		}

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
