package com.game.object.block;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.util.ObjectId;

public abstract class Block extends GameObject {	
	public Block(int x, int y, int width, int height, int scale) {
		super(x, y, ObjectId.Block, width, height, scale);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}
	
	public abstract void hit();
	public abstract boolean shouldRemove();
}