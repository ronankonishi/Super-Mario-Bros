package com.game.object.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Animation;
import com.game.gfx.Texture;
import com.game.main.Game;

public class Coin {
	private Texture tex = Game.getTexture();
	private BufferedImage[] coin;
	private float width, height, velY, x, y, turnY, endY;
	private Animation animation;
	
	public Coin(float x, float y, float width, float height, int scale) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.velY = -5f;
		this.turnY = y - 140;
		this.endY = y;
		
		coin = tex.getCoinA1();
		this.animation = new Animation(10, coin[0], coin[1], coin[2], coin[3], coin[2], coin[1]);
	}
	
	public void tick() {
		y += velY;
		animation.runAnimation();
		if (y < turnY) velY *= -1;
	}
	
	public void render(Graphics g) {
		animation.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
	}
	
	public boolean shouldRemove() {
		if (y > endY) {
			return true;
		}
		return false;
	}
}
