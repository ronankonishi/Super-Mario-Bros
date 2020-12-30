package com.game.object.item;

import java.awt.Graphics;

import com.game.object.GameObject;
import com.game.object.util.ObjectId;

public class RedFlower extends GameObject{

	public RedFlower(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.RedFlower, width, height, scale);
		index = 0;
		sprite = tex.getFlower1();
	}

	@Override
	public void tick() {}

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
