package com.game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.game.gfx.Animation;
import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.block.Block;
import com.game.object.block.BrickBlock;
import com.game.object.block.InvisibleBlock;
import com.game.object.block.QuestionFlowerBlock;
import com.game.object.item.RedShroom;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class Player extends GameObject {

	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	
	private Handler handler;
	private Texture tex;
	
	private PlayerState state;
	private BufferedImage[] spriteL, spriteS;
	private Animation playerWalkL, playerWalkS;
	private BufferedImage[] currSprite;
	private Animation currAnimation;
	private LinkedList<GameObject> removeObjs, addObjs;
	
	private boolean jumped = false;
	private boolean forward = false;
	
	
	public Player(float x, float y, int scale, Handler handler) {
		super(x, y, ObjectId.Player, WIDTH, HEIGHT, scale);
		this.handler = handler;
		removeObjs = new LinkedList<GameObject>();
		addObjs = new LinkedList<GameObject>();
		
		tex = Game.getTexture();
				
		spriteL = tex.getMarioL();
		spriteS = tex.getMarioS();
		
		playerWalkL = new Animation(5, spriteL[1], spriteL[2], spriteL[3]);
		playerWalkS = new Animation(5, spriteS[1], spriteS[2], spriteS[3]);
		
		state = PlayerState.Small;
		currSprite = spriteS;
		currAnimation = playerWalkS;
//		setStateLarge();
	}

	@Override
	public void render(Graphics g) {
		if (jumped) {
			if (forward) {
				g.drawImage(currSprite[5], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
			} else {
				g.drawImage(currSprite[5], (int) getX() + (int)getWidth(), (int) getY(), (int) -getWidth(), (int) getHeight(), null);
			}
		} else if (getVelX() > 0) {
			currAnimation.drawAnimation(g, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
			forward = true;
		} else if (getVelX() < 0) {
			currAnimation.drawAnimation(g, (int)getX() + (int)getWidth(), (int)getY(), (int)-getWidth(), (int)getHeight());
			forward = false;
		} else {
			g.drawImage(currSprite[0], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(), null);
		}
		
		showBounds(g);		
	}

	private void setStateSmall() {
		state = PlayerState.Small;
		currSprite = spriteS;
		currAnimation = playerWalkS;
		setY(getY() + (getHeight()/2));
		setHeight(getHeight()/2);
	}
	
	private void setStateLarge() {
		state = PlayerState.Large;
		currSprite = spriteL;
		currAnimation = playerWalkL;
		setY(getY() - getHeight());
		setHeight((float) 2 * (getHeight() / getScale()));
	}

	@Override
	public void tick() {
		setX(getVelX() + getX());
		setY(getVelY() + getY());
		applyGravity();
		
		currAnimation.runAnimation();
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			if (temp == this) continue;
			if (removeObjs.contains(temp) || addObjs.contains(temp)) continue;
			
			if (temp.getClass() == RedShroom.class) {
				if (getBounds().intersects(temp.getBounds())) {
					setStateLarge();
					((RedShroom) temp).shouldRemove();
					removeObjs.add(temp);
				}
			} else if (temp.getId() == ObjectId.Block && getBoundsTop().intersects(temp.getBounds())) {
				setY(temp.getY() + temp.getHeight());
				setVelY(0);
				
				((Block) temp).hit();
				if (temp.getClass() == BrickBlock.class) removeObjs.add(temp);
				if (temp.getClass() == QuestionFlowerBlock.class) {
					addObjs.add(((QuestionFlowerBlock) temp).getRedShroom());
				}
			} else {
				if (temp.getClass() == InvisibleBlock.class) continue; 
				
				if (getBoundsBottom().intersects(temp.getBounds())) {
					setY(temp.getY() - getHeight());
					setVelY(0);
					jumped = false;
				} 
				
				if (getBoundsTop().intersects(temp.getBounds())) {
					setY(temp.getY() + temp.getHeight());
					setVelY(0);
				}
				
				if (getBoundsRight().intersects(temp.getBounds())) {
					setX(temp.getX() - getWidth());
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) {
					setX(temp.getX() + temp.getWidth());
				}
			}
		}
	}
	
	public LinkedList<GameObject> removeObjs() {
		LinkedList<GameObject> output = new LinkedList<GameObject>();
		for (GameObject removeObj : removeObjs) {
			if (!removeObj.shouldRemove()) continue;
			output.add(removeObj);
		}
		for (GameObject removeObj : output) {
			removeObjs.remove(removeObj);
		}
		return output;
	}
	
	public LinkedList<GameObject> addObjs() {
		LinkedList<GameObject> output = new LinkedList<GameObject>();
		for (GameObject addObj : addObjs) {
			output.add(addObj);
		}
		for (GameObject addObj : output) {
			addObjs.remove(addObj);
		}
		return output;
	}
	
	private void showBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g.setColor(Color.blue);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}

	public Rectangle getBoundsBottom() {
		int x = (int) (getX() + (getWidth()/4));
		int y = (int) (getY() + (getHeight()/2));
		int w = (int) (getWidth()/2);
		int h = (int) (getHeight()/2);
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getBoundsTop() {
		int x = (int) ((int)getX() + (getWidth()/2) - (getWidth()/4));
		int y = (int) getY();
		int w = (int) (getWidth() / 2);
		int h = (int) (getHeight() / 2);
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getBoundsRight() {
		int x = (int) (getX() + getWidth() - 5);
		int y = (int) (getY() + 5);
		int w = (int) 5;
		int h = (int) (getHeight() - 10);
		return new Rectangle(x, y, w, h);
	}
	
	public Rectangle getBoundsLeft() {
		int x = (int) getX();
		int y = (int) (getY() + 5);
		int w = (int) 5;
		int h = (int) (getHeight() - 10);
		return new Rectangle(x, y, w, h);
	}

	public boolean hasJumped() {
		return jumped;
	}
	
	public void setJumped(boolean hasJumped) {
		jumped = hasJumped;
	}

	@Override
	public Rectangle getBounds() {
		int x = (int) getX();
		int y = (int) getY();
		int w = (int) getWidth();
		int h = (int) getHeight();
		return new Rectangle(x, y, w, h);
	}

	@Override
	protected boolean shouldRemove() {
		return false;
	}
}
