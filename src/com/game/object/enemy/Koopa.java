package com.game.object.enemy;

import java.awt.Graphics;

import com.game.gfx.AnimationSimple;
import com.game.object.item.Shell;

public class Koopa extends Enemy{
	private AnimationSimple animation;
	private Shell shell;
	
	public Koopa(float x, float y, float width, float height, int scale) {
		super(x, y, width, height, scale);
		sprite = tex.getKoopa1();
		animation = new AnimationSimple(5, sprite[0], sprite[1]);
		velX = -3f;
	}
	
	@Override
	public void kill() {
		kill = true;
		shell = new Shell(x, y + (height/2), width, height/2, 1);
	}

	@Override
	public boolean shouldRemove() {
		return true;
	}

	public Shell getShell() {
		return shell;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		animation.runAnimation();
		applyGravity();
		collision();
	}

	@Override
	public void render(Graphics g) {
//		showBounds(g);
		
		if (flipAnimation) {
			g.drawImage(tex.getShell1()[0], (int) x, (int) (y + height/2), (int) width, (int) (-height/2), null);
			return;
		}
		animation.drawAnimation(g, x, y, width, height);
	}
}
