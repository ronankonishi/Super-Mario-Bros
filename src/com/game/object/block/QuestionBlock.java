package com.game.object.block;

import java.awt.Graphics;
import com.game.object.item.Coin;

public class QuestionBlock extends Block{
	private Coin coin;
	private int yInc;
	private boolean flip;
	
	public QuestionBlock(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		index = 24;
		sprite = tex.getTile1();
	}
	
	@Override
	public void tick() {
		if (coin != null) coin.tick();
		if (coin != null && coin.shouldRemove()) coin = null;
		
		if (!disabled && hit) {
			index = 3;
			if (coin == null) coin = new Coin(x, y, width, height, 1);
		
			if (!flip) {
				yInc--;
			} else {
				yInc++;
			}
			if (yInc == -10) flip = true;
			if (yInc == 0) {
				flip = false;
				disabled = true;
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
		hit = true;
	}
	
	@Override
	public boolean shouldRemove() {
		return false;
	}
}
