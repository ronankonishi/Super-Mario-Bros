package com.game.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AnimationSimple {

	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	public AnimationSimple(int speed, BufferedImage... args) {
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
		currentImg = images[count];
		count++;
		
		if (count >= frames) {
			count = 0;
		}
	}
	
	public BufferedImage getSprite() {
		return currentImg;
	}
	
	public void drawAnimation(Graphics g, float x, float y, float width, float height) {
		g.drawImage(currentImg, (int) x, (int) y, (int) width, (int) height, null);
	}
}
