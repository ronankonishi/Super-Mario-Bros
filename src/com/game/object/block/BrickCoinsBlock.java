package com.game.object.block;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.object.item.Coin;

public class BrickCoinsBlock extends Block {
	private int hitCount;
	private LinkedList<Coin> coins, removeCoins;
	
	public BrickCoinsBlock(float x, float y, float width, float height, int scale) {
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
			coins.add(new Coin((int) x, (int) y, (int) width, (int) height, 1));
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

		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);		
	}
	
	public void hit() {
		hit = true;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
