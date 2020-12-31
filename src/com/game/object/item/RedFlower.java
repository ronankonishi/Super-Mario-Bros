package com.game.object.item;

import java.awt.Graphics;

import com.game.gfx.AnimationSimple;
import com.game.object.GameObject;
import com.game.object.util.ObjectId;

public class RedFlower extends GameObject{
	AnimationSimple animation;
	
	public RedFlower(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.RedFlower, width, height, scale);
		index = 0;
		sprite = tex.getFlower1();
		animation = new AnimationSimple(5, sprite[0], sprite[1], sprite[2], sprite[3]);
	}

	@Override
	public void tick() {
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
