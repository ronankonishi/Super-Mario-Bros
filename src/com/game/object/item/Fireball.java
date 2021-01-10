package com.game.object.item;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.gfx.AnimationSimple;
import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.enemy.Enemy;
import com.game.object.block.Block;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class Fireball extends GameObject{
	private Handler handler = Game.getHandler();
	private AnimationSimple animation;

	public Fireball(float x, float y, float width, float height, int scale, boolean right) {
		super(x + width/2, y + height/2, ObjectId.Fireball, width/2, height/4, scale, 2);
		sprite = tex.getFireball();
		animation = new AnimationSimple(5, sprite[0], sprite[1], sprite[2], sprite[3]);
		if (right) {
			velX = 10;
		} else {
			velX = -10;
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		animation.runAnimation();
		
		x += velX;
		y += velY;

		applyGravity();
		collision();
	}
	

	private void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			if (temp == this) continue;
			if (temp.getId() == ObjectId.Block && ((Block) temp).isDisabled()) continue;
			if (temp.getId() == ObjectId.Enemy && ((Enemy) temp).getFlip()) continue;
			if (temp.getId() == ObjectId.Player) continue;
			
			if (temp.getClass() == Shell.class ) {
				if (getBounds().intersects(temp.getBounds())) {
					((Shell) temp).flip();
				}
				continue;
			}
			
			if (temp.getId() == ObjectId.Enemy) {
				if (getBounds().intersects(temp.getBounds())) {
					((Enemy) temp).flip();
					handler.addRemoveObjectQueue(this);
				}
				continue;
			}
			
			if (temp.getId() == ObjectId.Block || temp.getId() == ObjectId.Pipe) {
				if (getBoundsTop().intersects(temp.getBounds())) {
					y = temp.getY() + temp.getHeight();
					velY = 0;
				}
				
				if (getBoundsBottom().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = -5f;
				}
				
				if (getBoundsRight().intersects(temp.getBounds()) || getBoundsLeft().intersects(temp.getBounds())) {
					handler.addRemoveObjectQueue(this);
				}
			}
		}
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
		animation.drawAnimation(g, x, y, width, height);
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}

}
