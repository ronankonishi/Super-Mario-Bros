package com.game.object.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.AnimationSimple;
import com.game.gfx.Texture;
import com.game.main.Game;

public class Coin {
	private Texture tex = Game.getTexture();
	private BufferedImage[] coin;
	private float width, height, velY, x, y, turnY, endY;
	private AnimationSimple animation;
	
	public Coin(float x, float y, float width, float height, int scale) {
		this.x = x * scale;
		this.y = y * scale;
		this.width = width * scale;
		this.height = height * scale;
		
		this.velY = -5f;
		this.turnY = y - 150;
		this.endY = y;
		
		coin = tex.getCoinA1();
		this.animation = new AnimationSimple(10, coin[0], coin[1], coin[2], coin[3], coin[2], coin[1]);
	}
	
	public void tick() {
		y += velY;
		animation.runAnimation();
		if (y < turnY) velY *= -1;
	}
	
	public void render(Graphics g) {
		animation.drawAnimation(g, x, y, width, height);
	}
	
	public boolean shouldRemove() {
		if (y > endY) {
			return true;
		}
		return false;
	}
}
