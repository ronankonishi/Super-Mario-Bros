package com.game.object.block;

import java.awt.Graphics;
import java.util.LinkedList;

import com.game.main.Game;
import com.game.object.item.Coin;
import com.game.object.util.AudioHandler;

public class BrickCoinsBlock extends Block {
	private AudioHandler audioHandler = Game.getAudioHandler();
	private int hitCount;
	private LinkedList<Coin> coins, removeCoins;
	private int yInc;
	private boolean flip;
	private boolean initHit;
	
	public BrickCoinsBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 1;
		sprite = tex.getTile1();
		coins = new LinkedList<Coin>();
		removeCoins = new LinkedList<Coin>();
	}
	
	@Override
	public void tick() {
		if (hit && !disabled) {
			if (initHit) {
				hitCount++;
				if (hitCount == 10) {
					index = 3;
					disabled = true;
				}
				audioHandler.playCoin();
				coins.add(new Coin(x, y, width, height, 1));
				initHit = false;
			}
			
			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				flip = false;
				hit = false;
			}
		} else if (hit) {
			audioHandler.playBump();
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

		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);		
	}
	
	@Override
	public void largeHit() {
		hit = true;
		initHit = true;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
