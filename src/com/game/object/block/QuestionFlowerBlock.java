package com.game.object.block;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.item.RedShroom;

public class QuestionFlowerBlock extends Block{
	private Texture tex = Game.getTexture();
	private int index;
	private BufferedImage[] sprite;
	private boolean hit;
	private RedShroom redShroom;
//	private Flower flower;
	
	public QuestionFlowerBlock(int x, int y, int width, int height, int scale) {
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
		g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
	}
	
	public void hit() {
		hit = true;
		redShroom = new RedShroom(getX(), getY() - getHeight(), getWidth(), getHeight(), scale);
	}
	
	public RedShroom getRedShroom() {
		return redShroom;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
}
