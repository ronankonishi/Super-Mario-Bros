package com.game.object.util;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

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
			if (obj.getZ() == 1) {
				obj.tick();
			}
		}
		for (GameObject obj : gameObjs) {
			if (obj.getZ() == 2) {
				obj.tick();
			}
		}
		LinkedList<GameObject> removeObjs = player.removeObjs();
		for (GameObject removeObj : removeObjs) {
			removeObj(removeObj);
		}
		LinkedList<GameObject> addObjs = player.addObjs();
		for (GameObject addObj : addObjs) {
			addObj(addObj);
		}
	}
	
	public void render(Graphics g) {
		for (GameObject obj : gameObjs) {
			if (obj.getZ() == 1) {
				obj.render(g);
			}
		}
		for (GameObject obj : gameObjs) {
			if (obj.getZ() == 2) {
				obj.render(g);
			}
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
