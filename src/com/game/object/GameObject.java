package com.game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectId;

/**
 * An object in the game.
 */
public abstract class GameObject {
	
	protected float x;
	protected float y;
	protected ObjectId id;
	protected float velX, velY;
	protected float width, height;
	protected int scale;

	protected Texture tex = Game.getTexture();
	protected int index;
	protected BufferedImage[] sprite;
	
	/**
	 * Creates game object with x and y coordinates and object type id
	 */
	public GameObject(float x, float y, ObjectId id, float width, float height, int scale) {
		this.x = x * scale;
		this.y = y * scale;
		this.id = id;
		this.width = width * scale;
		this.height = height * scale;
		this.scale = scale;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract boolean shouldRemove();
	
	public Rectangle getBounds() {
		int x = (int) this.x;
		int y = (int) this.y;
		int w = (int) width;
		int h = (int) height;
		return new Rectangle(x, y, w, h);
	}
	
	protected void showBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.red);
		g2d.draw(getBounds());
	}
	
	public void applyGravity() {
		velY += 0.5f;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public int getScale() {
		return scale;
	}
}
