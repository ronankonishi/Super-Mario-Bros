package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.item.RedShroom;

public class QuestionFlowerBlock extends Block{
	private RedShroom redShroom;
//	private Flower flower;
	
	public QuestionFlowerBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 24;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (hit) {
			index = 3;
			hit = false;
		}
		if (redShroom != null && redShroom.shouldRemove()) redShroom = null;
		if (redShroom != null) redShroom.tick();
	}
	
	@Override
	public void render(Graphics g) {
		if (redShroom != null) redShroom.render(g);
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}
	
	public void hit() {
		hit = true;
		redShroom = new RedShroom((int) x, (int) (y - height), (int) width, (int) height, scale);
	}
	
	public RedShroom getRedShroom() {
		return redShroom;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
}
