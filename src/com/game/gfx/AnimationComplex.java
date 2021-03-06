package com.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AnimationComplex {
	private int speed;
	private int frames;
	
	private int index = 0;
	private int iCount = 0;
	private int wCount = 0;
	private boolean rWalk, lWalk;
	
	private BufferedImage[][] images;
	
	public AnimationComplex(int speed, BufferedImage[]... args) {
		this.speed = speed;
		images = args;
		frames = args.length;
	}
	
	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame() {
		iCount = (iCount + 1) % 3;
		if (rWalk || lWalk) wCount = (wCount + 1) % 3;
	}
	
	public void drawStillR(Graphics g, float x, float y, float width, float height) {
		g.drawImage(images[iCount][0], (int) x, (int) y, (int) width, (int) height, null);
		rWalk = false;
		lWalk = false;
	}
	
	public void drawStillL(Graphics g, float x, float y, float width, float height) {
		g.drawImage(images[iCount][0], (int) (x + width), (int) y, (int) -width, (int) height, null);
		rWalk = false;
		lWalk = false;
	}
	
	public void drawWalkR(Graphics g, float x, float y, float width, float height) {
		if (!rWalk) wCount = 0;
		g.drawImage(images[iCount][wCount + 1], (int) x, (int) y, (int) width, (int) height, null);
		rWalk = true;
		lWalk = false;
	}
	
	public void drawWalkL(Graphics g, float x, float y, float width, float height) {
		if (!lWalk) wCount = 0;
		g.drawImage(images[iCount][wCount + 1], (int) (x + width), (int) y, (int) -width, (int) height, null);
		rWalk = false;
		lWalk = true;
	}	
	
	public void drawJumpR(Graphics g, float x, float y, float width, float height) {
		g.drawImage(images[iCount][5], (int) x, (int) y, (int) width, (int) height, null);
		rWalk = false;
		lWalk = false;
	}
	
	public void drawJumpL(Graphics g, float x, float y, float width, float height) {
		g.drawImage(images[iCount][5], (int) (x + width), (int) y, (int) -width, (int) height, null);
		rWalk = false;
		lWalk = false;
	}
}
