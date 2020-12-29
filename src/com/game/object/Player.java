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
import com.game.object.item.GreenShroom;
import com.game.object.item.RedFlower;
import com.game.object.item.RedShroom;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class Player extends GameObject {

	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	
	private Handler handler;
	private State state;
	
	enum State {
		SMALL,
		LARGE,
		FIRE;
	}
	
	private BufferedImage[] spriteL, spriteS, spriteF;
	private Animation playerWalkL, playerWalkS, playerWalkF;
	private Animation currAnimation;
	private LinkedList<GameObject> removeObjs, addObjs;
	
	private boolean jumped = false;
	private boolean forward = false;
	
	
	public Player(float x, float y, int scale, Handler handler) {
		super(x, y, ObjectId.Player, WIDTH, HEIGHT, scale);
		this.handler = handler;
		removeObjs = new LinkedList<GameObject>();
		addObjs = new LinkedList<GameObject>();
						
		spriteL = tex.getMarioL();
		spriteS = tex.getMarioS();
		spriteF = tex.getMarioF();
		
		playerWalkL = new Animation(5, spriteL[1], spriteL[2], spriteL[3]);
		playerWalkS = new Animation(5, spriteS[1], spriteS[2], spriteS[3]);
		playerWalkF = new Animation(5, spriteF[1], spriteF[2], spriteF[3]);
		
		sprite = spriteS;
		currAnimation = playerWalkS;
		
		state = State.SMALL;
//		setStateLarge();
//		setStateSmall();
	}

	@Override
	public void render(Graphics g) {
		if (jumped) {
			if (forward) {
				g.drawImage(sprite[5], (int) x, (int) y, (int) width, (int) height, null);
			} else {
				g.drawImage(sprite[5], (int) (x + width), (int) y, (int) -width, (int) height, null);
			}
		} else if (velX > 0) {
			currAnimation.drawAnimation(g, (int) x, (int) y, (int) width, (int) height);
			forward = true;
		} else if (velX < 0) {
			currAnimation.drawAnimation(g, (int) (x + width), (int) y, (int) -width, (int) height);
			forward = false;
		} else {
			g.drawImage(sprite[0], (int) x, (int) y, (int) width, (int) height, null);
		}
		
		showBounds(g);		
	}

	private void setStateSmall() {
		if (state == State.SMALL) return;
		state = State.SMALL;
		sprite = spriteS;
		currAnimation = playerWalkS;
		y += height/2;
		height /= 2;
	}
	
	private void setStateLarge() {
		if (state == State.LARGE) return;
		state = state.LARGE;
		sprite = spriteL;
		currAnimation = playerWalkL;
		y -= height;
		height *= 2;
	}
	
	private void setStateFire() {
		if (state == State.FIRE) return;
		state = state.FIRE;
		sprite = spriteF;
		currAnimation = playerWalkF;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		applyGravity();
		
		currAnimation.runAnimation();
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			if (temp == this) continue;
			if (removeObjs.contains(temp) || addObjs.contains(temp)) continue;
			
			if (temp.getClass() == RedShroom.class && getBounds().intersects(temp.getBounds())) {
				setStateLarge();
				removeObjs.add(temp);
				continue;
			}
			
			if (temp.getClass() == RedFlower.class && getBounds().intersects(temp.getBounds())) {
				setStateFire();
				removeObjs.add(temp);
				continue;
			} 
			
			if (temp.getClass() == GreenShroom.class && getBounds().intersects(temp.getBounds())) {
				removeObjs.add(temp);
				continue;
			}
			
			if (temp.getId() == ObjectId.Block && getBoundsTop().intersects(temp.getBounds())) {
				if(temp.getClass() == InvisibleBlock.class && velY > 0) continue;
				
				y = temp.getY() + temp.getHeight();
				velY = 0;
				
				((Block) temp).hit();
				if (temp.getClass() == BrickBlock.class) removeObjs.add(temp);
				if (temp.getClass() == QuestionFlowerBlock.class && !((QuestionFlowerBlock) temp).isDisabled()) {
					if (state == State.SMALL) {
						((QuestionFlowerBlock) temp).spawnRedShroom();
						addObjs.add(((QuestionFlowerBlock) temp).getRedShroom());
					} else if (state == State.LARGE || state == State.FIRE) {
						((QuestionFlowerBlock) temp).spawnRedFlower();
						addObjs.add(((QuestionFlowerBlock) temp).getRedFlower());
					}
					continue;
				}
				
				if (temp.getClass() == InvisibleBlock.class) {
					((InvisibleBlock) temp).spawnGreenShroom();
					addObjs.add(((InvisibleBlock)temp).getGreenShroom());
					System.out.println("spawning");
				}
			} else {
				if (temp.getClass() == InvisibleBlock.class && !((InvisibleBlock) temp).isHit()) continue; 
				
				if (getBoundsBottom().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = 0;
					jumped = false;
				} 
				
				if (getBoundsTop().intersects(temp.getBounds())) {
					y = temp.getY() + temp.getHeight();
					velY = 0;
				}
				
				if (getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - width;
				}
				
				if (getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + temp.getWidth();
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
	
	@Override
	protected void showBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g.setColor(Color.blue);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}

	private Rectangle getBoundsBottom() {
		int x = (int) (this.x + (width/4));
		int y = (int) (this.y + (height/2));
		int w = (int) (width/2);
		int h = (int) (height/2);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsTop() {
		int x = (int) (this.x + (width/2) - (width/4));
		int y = (int) this.y;
		int w = (int) (width / 2);
		int h = (int) (height / 2);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsRight() {
		int x = (int) (this.x + width - 5);
		int y = (int) (this.y + 5);
		int w = 5;
		int h = (int) (height - 10);
		return new Rectangle(x, y, w, h);
	}
	
	private Rectangle getBoundsLeft() {
		int x = (int) this.x;
		int y = (int) (this.y + 5);
		int w = 5;
		int h = (int) (height - 10);
		return new Rectangle(x, y, w, h);
	}

	public boolean hasJumped() {
		return jumped;
	}
	
	public void setJumped(boolean hasJumped) {
		jumped = hasJumped;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
}
