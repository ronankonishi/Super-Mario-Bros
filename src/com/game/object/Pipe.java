package com.game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectId;

public class Pipe extends GameObject {
	private boolean enterable;
	
	public Pipe(int x, int y, int width, int height, int index, int scale, boolean enterable) {
		super(x, y, ObjectId.Pipe, width, height, scale, 2);
		this.index = index;
		this.enterable = enterable;
		sprite = tex.getPipe1();
	}
	
	@Override
	public void tick() {		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
