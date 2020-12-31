package com.game.object.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.gfx.AnimationSimple;
import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class Star extends GameObject {
	private Handler handler = Game.getHandler();
	private AnimationSimple animation;

	public Star(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.Star, width, height, scale);
		sprite = tex.getStar1();
		velX = 3f;
		velY = -5f;
		animation = new AnimationSimple(4, sprite[0], sprite[1], sprite[2], sprite[3]);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		animation.runAnimation();
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
				if (getBoundsTop().intersects(temp.getBounds())) {
					y = temp.getY() + temp.getHeight();
					velY = 0;
				}
				
				if (getBoundsBottom().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = -10f;
				}
				
				if (getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - width;
					velX = -3f;
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + temp.getWidth();
					velX = 3f;
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

	private Rectangle getBoundsBottom() {
		int x = (int) (this.x + (width/4));
		int y = (int) (this.y + (height/2));
		int w = (int) (width/2);
		int h = (int) (height/2);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsTop() {
		int x = (int) (this.x + (width/2) - (width/4));
		int y = (int) this.y;
		int w = (int) (width / 2);
		int h = (int) (height / 2);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsRight() {
		int x = (int) (this.x + width - 5);
		int y = (int) (this.y + 10);
		int w = 5;
		int h = (int) (height - 20);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsLeft() {
		int x = (int) this.x;
		int y = (int) (this.y + 10);
		int w = 5;
		int h = (int) (height - 20);
		return new Rectangle(x, y, w, h);
	}
	
	@Override
	public void render(Graphics g) {
//		showBounds(g);
		animation.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public boolean shouldRemove() {
		return true;
	}
}
