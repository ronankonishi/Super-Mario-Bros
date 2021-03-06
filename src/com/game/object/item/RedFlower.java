package com.game.object.item;

import java.awt.Graphics;

import com.game.gfx.AnimationSimple;
import com.game.object.GameObject;
import com.game.object.util.ObjectId;

public class RedFlower extends GameObject{
	AnimationSimple animation;
	private boolean entering = true;
	private int yCount = 1;
	
	public RedFlower(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.RedFlower, width, height, scale, 1);
		index = 0;
		sprite = tex.getFlower1();
		animation = new AnimationSimple(5, sprite[0], sprite[1], sprite[2], sprite[3]);
		this.y--;
	}

	@Override
	public void tick() {
		if (entering) {
			y--;
			yCount++;
			if (yCount == height) entering = false;
		}
		
		animation.runAnimation();
	}

	@Override
	public void render(Graphics g) {
//		showBounds(g);
		animation.drawAnimation(g, x, y, width, height);
	}

	@Override
	public boolean shouldRemove() {
		return true;
	}
	
}
