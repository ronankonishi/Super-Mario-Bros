package com.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectId;

public class Block extends GameObject {

	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	private boolean hit;
	private Debris debris;
	private BlockId id;
	
	public Block(int x, int y, int width, int height, int scale, BlockId id) {
		super(x, y, ObjectId.Block, width, height, scale);
		this.id = id;
		sprite = tex.getTile1();
		setBlockSprite();
	}
	
	private void setBlockSprite() {
		switch (id) {
			case Brick:
				index = 0;
				break;
			case Question:
				index = 120/5;
				break;
			case Solid:
				index = 140/5;
				break;
		}
	}
	
	@Override 
	public void tick() {
		if (hit) {
			debris.tick();
		}
	}
	
	public boolean shouldRemove() {
		if (debris.shouldRemove()) {
			return true;
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		if (!hit) {
			g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
		} else {
			debris.draw(g);
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}
	
	public void hit() {
		hit = true;
		debris = new Debris(getX(), getY(), getWidth(), getHeight(), getScale());
	}
}