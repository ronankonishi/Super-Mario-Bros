package com.game.object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private boolean[] keyDown = new boolean[4];
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		if (handler.getPlayer().disabled()) return;
		
		if (key == KeyEvent.VK_W) {
			if (!handler.getPlayer().hasJumped()) {
				handler.getPlayer().setVelY(-15);
				keyDown[0] = true;
				handler.getPlayer().setJumped();
			}
		}
		if (key == KeyEvent.VK_A) {
			handler.getPlayer().setVelX(-8);
			keyDown[1] = true;
		}
		if (key == KeyEvent.VK_D) {
			handler.getPlayer().setVelX(8);
			keyDown[2] = true;
		}
		if (key == KeyEvent.VK_J && ! keyDown[3]) {
			handler.getPlayer().fire();
			keyDown[3] = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			keyDown[0] = false;
		}
		if (key == KeyEvent.VK_A) {
			keyDown[1] = false;
		}
		if (key == KeyEvent.VK_D) {
			keyDown[2] = false;
		}
		if (key == KeyEvent.VK_J) {
			keyDown[3] = false;
		}
		
		if (!keyDown[1] && !keyDown[2]) {
			handler.getPlayer().setVelX(0);
		}
	}
}
