package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.RedFlower;
import com.game.object.item.RedShroom;

public class QuestionFlowerBlock extends Block{
	private RedShroom redShroom;
	private RedFlower redFlower;
	private boolean disabled;
	
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
			disabled = true;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite[index], (int) x, (int) y, (int) width, (int) height, null);
	}
	
	public void spawnRedShroom() {
		redShroom = new RedShroom(x, y - height, width, height, 1);
	}
	
	public void spawnRedFlower() {
		redFlower = new RedFlower(x, y - height, width, height, 1);
	}
	
	public RedShroom getRedShroom() {
		return redShroom;
	}
	
	public RedFlower getRedFlower() {
		return redFlower;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}

	@Override
	public void hit() {
		hit = true;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
}
