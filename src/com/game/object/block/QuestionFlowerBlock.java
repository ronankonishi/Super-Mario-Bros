package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.RedFlower;
import com.game.object.item.RedShroom;

public class QuestionFlowerBlock extends Block{
	private RedShroom redShroom;
	private RedFlower redFlower;
	private boolean disabled;
	private int yInc;
	private boolean smallHit;
	private boolean flip;
	
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
		
		if (smallHit) {
			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				smallHit = false;
				flip = false;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);
	}
	
	public void spawnRedShroom() {
		redShroom = new RedShroom(x, y, width, height, 1);
	}
	
	public void spawnRedFlower() {
		redFlower = new RedFlower(x, y, width, height, 1);
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
	public void largeHit() {
		smallHit = true;

		hit = true;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
}
