package com.game.object;

import java.awt.Graphics;

import com.game.main.Game;
import com.game.object.util.ObjectId;

public class FlagPole extends GameObject {
	private boolean fall;
	private int fallHeight;

	public FlagPole(float x, float y, float width, float height, int scale) {
		super(x, y, ObjectId.Flag, width, height, scale, 0);
		sprite = tex.getFlag();
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite[1], (int) x, (int) y, (int) width, (int) (height/10), null);
		for (int i = 0; i < 9; i++) {
			g.drawImage(sprite[2], (int) x, (int) (y + (height/10) + i*(height/10)), (int) width, (int) (height/10), null);
		}
		g.drawImage(sprite[0], (int) (x - width/2), (int) (y + (height/10) + fallHeight), (int) width, (int) (height/10), null);
		
		if (fall) {
			fallHeight += 1;
			if (y + fallHeight > Game.getScreenHeight() - 264) fall = false;
		}
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
	
	public void fall() {
		fall = true;
	}
	
	public boolean isDown() {
		return !fall;
	}

}
