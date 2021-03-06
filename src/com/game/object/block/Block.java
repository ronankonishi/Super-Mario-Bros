package com.game.object.block;

import com.game.object.GameObject;
import com.game.object.util.ObjectId;

public abstract class Block extends GameObject {
	protected boolean hit;
	protected boolean smallHit;
	protected boolean disabled;
	protected boolean passable;
	
	public Block(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.Block, width, height, scale, 2);
	}
	
	public void smallHit() {
		largeHit();
	}
	
	public boolean isSmallHit() {
		return smallHit;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public abstract void largeHit();
	public abstract boolean shouldRemove();
}