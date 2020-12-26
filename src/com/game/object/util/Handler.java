package com.game.object.util;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import com.game.object.Block;
import com.game.object.GameObject;
import com.game.object.Player;

/**
 * Manager for updating all game objects
 */
public class Handler {
	
	private List<GameObject> gameObjs;
	private Player player;
	
	public Handler() {
		gameObjs = new LinkedList<GameObject>();
	}
	
	public void tick() {
		for (GameObject obj : gameObjs) {
			obj.tick();
		}
		Block removeBlock = player.getAndResetRemoveBlock();
		if (removeBlock != null) {
			removeObj(removeBlock);
		}
	}
	
	public void render(Graphics g) {
		for (GameObject obj : gameObjs) {
			obj.render(g);
		}
	}

	public void addObj(GameObject obj) {
		gameObjs.add(obj);
	}
	
	public void removeObj(GameObject obj) {
		gameObjs.remove(obj);
	}
	
	public int setPlayer(Player player) {
		if (this.player != null) {
			return -1;
		}
		addObj(player);
		this.player = player;
		return 0;
	}
	
	public int removePlayer() {
		if (player == null) {
			return -1;
		}
		removeObj(player);
		this.player = null;
		return 0;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<GameObject> getGameObjs(){
		return gameObjs;
	}
}
