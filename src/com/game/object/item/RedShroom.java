package com.game.object.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class RedShroom extends GameObject {
	private Handler handler = Game.getHandler();
	
	public RedShroom(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.RedShroom, width, height, 1);
		index = 0;
		sprite = tex.getShroom1();
		setVelX(3f);
	}
	
	@Override
	public void tick() {
		setX(getVelX() + getX());
		setY(getVelY() + getY());
		
		applyGravity();
		collision();
	}
	
	public void applyGravity() {
		velY += 0.5f;
	}
	
	private void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			if (temp == this) continue;
			
			if (temp.getId() == ObjectId.Block || temp.getId() == ObjectId.Pipe) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - getHeight());
					setVelY(0);
				}
				
				if (getBoundsRight().intersects(temp.getBounds())) {
					setX(temp.getX() - getWidth());
					velX *= -1;
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) {
					setX(temp.getX() + temp.getWidth());
					velX *= -1;
				}
			}
		}
	}
	
	@Override
	protected void showBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.red);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}

	public Rectangle getBoundsBottom() {
		int x = (int) (getX() + (getWidth()/4));
		int y = (int) (getY() + (getHeight()/2));
		int w = (int) (getWidth()/2);
		int h = (int) (getHeight()/2);
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getBoundsTop() {
		int x = (int) ((int)getX() + (getWidth()/2) - (getWidth()/4));
		int y = (int) getY();
		int w = (int) (getWidth() / 2);
		int h = (int) (getHeight() / 2);
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getBoundsRight() {
		int x = (int) (getX() + getWidth() - 5);
		int y = (int) (getY() + 5);
		int w = (int) 5;
		int h = (int) (getHeight() - 10);
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getBoundsLeft() {
		int x = (int) getX();
		int y = (int) (getY() + 5);
		int w = (int) 5;
		int h = (int) (getHeight() - 10);
		return new Rectangle(x, y, w, h);
	}
	
	@Override
	public void render(Graphics g) {
		showBounds(g);
		g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

	@Override
	public boolean shouldRemove() {
		return true;
	}
}
