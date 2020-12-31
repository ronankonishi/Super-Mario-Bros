package com.game.gfx;

import com.game.main.Game;
import com.game.object.GameObject;

public class Camera {

	private GameObject player;
	private float cX, cY;
	private float pX;
	
	public Camera(float cX, float cY, GameObject player) {
		this.cX = cX;
		this.cY = cY;
		this.player = player;
	}
	
	public void tick() {
		float offset = player.getX() - (cX + Game.getScreenWidth()/2);
		if (offset > 0) {
			cX += offset;
		}
	}
	
	public void setX(float cX) {
		this.cX = cX;
	}
	
	public void setY(float cY) {
		this.cY = cY;
	}
	
	public float getCX() {
		return cX;
	}
	
	public float getCY() {
		return cY;
	}
}
