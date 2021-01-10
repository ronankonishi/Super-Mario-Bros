package com.game.object.enemy;

import java.awt.Graphics;

import com.game.gfx.AnimationSimple;

public class Goomba extends Enemy {
	private AnimationSimple animation;
	private boolean remove;
	private int counter;
	private boolean setInitialHitVel;
	
	public Goomba(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		sprite = tex.getGoomba1();
		animation = new AnimationSimple(5, sprite[0], sprite[1]);
		velX = -2f;
	}

	@Override
	public void kill() {
		audioHandler.playKick();
		kill = true;
	}
	
	@Override
	public boolean shouldRemove() {
		return remove;
	}
	
	@Override
	public void tick() {
		if (!setInitialHitVel) {
			if (flipAnimation) {
				velY = -5;
				setInitialHitVel = true;
			}
		}
		
		if (kill) {
			counter++;
			if (counter > 50) {
				remove = true;
			}
			return;
		}
		
		x += velX;
		y += velY;
		
		animation.runAnimation();
		applyGravity();
		collision();
	}


	@Override
	public void render(Graphics g) {
//		showBounds(g);
		if (flipAnimation) {
			animation.drawAnimation(g, x, y + height, width, -height);
			return;
		}
		
		if (!kill) {
			animation.drawAnimation(g, x, y, width, height);
		} else {
			g.drawImage(sprite[2], (int) x, (int) y, (int) width, (int) height, null);
		}
	}
}
