package com.game.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.object.util.ObjectId;

public class BackgroundObject extends GameObject {
	private BufferedImage img;
	
	public BackgroundObject(float x, float y, float width, float height, int scale, BufferedImage img) {
		super(x, y, ObjectId.BackgroundObj, width, height, scale, 0);
		this.img = img;
	}

	@Override
	public void tick() {		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int) x, (int) y, (int) width, (int) height, null);
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}