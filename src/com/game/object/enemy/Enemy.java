package com.game.object.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.block.Block;
import com.game.object.item.Shell;
import com.game.object.util.AudioHandler;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public abstract class Enemy extends GameObject {
	protected AudioHandler audioHandler = Game.getAudioHandler();
	private Handler handler = Game.getHandler();
	protected boolean kill;
	protected boolean flipAnimation;

	public Enemy(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.Enemy, width, height, scale, 2);
	}
	
	public boolean isKilled() {
		return kill;
	}
	
	public boolean getFlip() {
		return flipAnimation;
	}
	
	public void flip() {
		audioHandler.playKick();
		flipAnimation = true;
	}
	
	public abstract void kill();
	
	protected void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			if (temp == this) continue;
			if (temp.getId() == ObjectId.Enemy && ((Enemy) temp).isKilled()) continue;
			if (temp.getId() == ObjectId.Block && ((Block) temp).isPassable()) continue;
			if (flipAnimation) continue;
			
			if (temp.getId() == ObjectId.Block && (((Block) temp).isHit() || ((Block) temp).isSmallHit())) {
				if (getBoundsBottom().intersects(((Block) temp).getBounds())) {
					flip();
				}
			}
			
			if (temp.getClass() == Shell.class && ((Shell) temp).getVelX() != 0) {
				if (getBounds().intersects(((Shell) temp).getBounds())) {
					flip();
				}
			}
			
			if (temp.getId() == ObjectId.Block || temp.getId() == ObjectId.Pipe || temp.getId() == ObjectId.Enemy) {
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

	protected Rectangle getBoundsBottom() {
		int x = (int) (this.x + (width/4));
		int y = (int) (this.y + (height/2));
		int w = (int) (width/2);
		int h = (int) (height/2);
		return new Rectangle(x, y, w, h);
	}
	
	protected Rectangle getBoundsTop() {
		int x = (int) (this.x + (width/2) - (width/4));
		int y = (int) this.y;
		int w = (int) (width / 2);
		int h = (int) (height / 2);
		return new Rectangle(x, y, w, h);
	}
	
	protected Rectangle getBoundsRight() {
		int x = (int) (this.x + width - 5);
		int y = (int) (this.y + 5);
		int w = 5;
		int h = (int) (height - 10);
		return new Rectangle(x, y, w, h);
	}
	
	protected Rectangle getBoundsLeft() {
		int x = (int) this.x;
		int y = (int) (this.y + 5);
		int w = 5;
		int h = (int) (height - 10);
		return new Rectangle(x, y, w, h);
	}
}