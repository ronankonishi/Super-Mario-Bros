package com.game.object.block;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.object.item.Coin;

public class BrickCoinsBlock extends Block {
	private int hitCount;
	private LinkedList<Coin> coins, removeCoins;
	private int yInc;
	private boolean smallHit;
	private boolean flip;
	
	public BrickCoinsBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		System.out.println(y);
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
			coins.add(new Coin(x, y, width, height, 1));
			hit = false;
		}
		
		for (Coin coin : coins) {
			coin.tick();
			if (coin.shouldRemove()) removeCoins.add(coin);
		}
		for (Coin coin : removeCoins) {
			coins.remove(coin);
		}
		
		if (smallHit) {
			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				smallHit = false;
				flip = false;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		for (Coin coin : coins) {
			coin.render(g);
		}

		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);		
	}
	
	@Override
	public void largeHit() {
		smallHit = true;
		hit = true;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
