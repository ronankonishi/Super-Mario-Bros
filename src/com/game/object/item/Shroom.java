package com.game.object.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.block.Block;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public abstract class Shroom extends GameObject {
	private Handler handler = Game.getHandler();
	private boolean entering = true;
	private int yCount = 1;

	public Shroom(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.MovingItem, width, height, scale, 1);
		sprite = tex.getShroom1();
		velX = 3f;
		this.y--;
	}
	
	@Override
	public void tick() {
		if (entering) {
			y--;
			yCount++;
			if (yCount == height) entering = false;
			return;
		}
		
		x += velX;
		y += velY;
		
		applyGravity();
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			if (temp == this) continue;
			if (temp.getId() == ObjectId.Block && ((Block) temp).isPassable()) continue;
			
			if (temp.getId() == ObjectId.Block || temp.getId() == ObjectId.Pipe) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = 0;
				}
				
				if (getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - width;
					velX *= -1;
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + temp.getWidth();
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
		int y = (int) (this.y + 5);
		int w = 5;
		int h = (int) (height - 10);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsLeft() {
		int x = (int) this.x;
		int y = (int) (this.y + 5);
		int w = 5;
		int h = (int) (height - 10);
		return new Rectangle(x, y, w, h);
	}
	
	@Override
	public void render(Graphics g) {
//		showBounds(g);
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}

	@Override
	public boolean shouldRemove() {
		return true;
	}
}
