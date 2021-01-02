package com.game.object.util;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import com.game.gfx.Camera;
import com.game.main.Game;
import com.game.object.GameObject;
import com.game.object.Player;

/**
 * Manager for updating all game objects
 */
public class Handler {
	
	private List<GameObject> gameObjs;
	private Player player;
	private Camera cam;
	
	public Handler() {
		gameObjs = new LinkedList<GameObject>();
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}
	
	public void tick() {
		player.setLeftBound(cam.getCX());		

		LinkedList<GameObject> removeObjsOffscreen = new LinkedList<GameObject>();
		for (GameObject obj : gameObjs) {
			if (obj.getId() == ObjectId.MovingItem && obj.getY() > Game.getScreenHeight()) {
				removeObjsOffscreen.add(obj);
			}
		}
		for (GameObject removeObj : removeObjsOffscreen) {
			removeObj(removeObj);
		}
		
		for (GameObject obj : gameObjs) {
			if ((obj.getX() < cam.getCX() + 60 + Game.getScreenWidth()) && (obj.getX() > cam.getCX() - 60)) {
				obj.setRenderStatus(true);
			} else {
				if (obj.getRenderStatus() && obj.getId() == ObjectId.MovingItem) continue;
				obj.setRenderStatus(false);
			}
		}
		
		for (GameObject obj : gameObjs) {
			if (obj.getRenderStatus()) {
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
		for(GameObject obj: gameObjs) {
			if (obj.getZ() == 0 && obj.getRenderStatus()) {
				obj.render(g);
			}
		}
		for (GameObject obj : gameObjs) {
			if (obj.getZ() == 1 && obj.getRenderStatus()) {
				obj.render(g);
			}
		}
		for (GameObject obj : gameObjs) {
			if (obj.getZ() == 2 && obj.getRenderStatus()) {
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
