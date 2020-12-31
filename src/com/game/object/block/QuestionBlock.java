package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.Coin;

public class QuestionBlock extends Block{
	private Coin coin;
	private boolean disabled;
	private int yInc;
	private boolean smallHit;
	private boolean flip;
	
	public QuestionBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 24;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (!disabled && hit) {
			index = 3;
			coin = new Coin(x, y, width, height, 1);
			hit = false;
			disabled = true;
		}
		if (coin != null) coin.tick();
		if (coin != null && coin.shouldRemove()) coin = null;
		
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
		if (coin != null) coin.render(g);
		
		g.drawImage(sprite[index], (int) x, (int) (y + yInc), (int) width, (int) height, null);
	}
	
	@Override
	public void largeHit() {
		smallHit = true;
		hit = true;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
}
