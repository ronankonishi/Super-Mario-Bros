package com.game.object.block;

import com.game.object.GameObject;
import com.game.object.util.ObjectId;

public abstract class Block extends GameObject {
	protected boolean hit;
	
	public Block(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.Block, width, height, scale);
	}
	
	public abstract void hit();
	public abstract boolean shouldRemove();
}