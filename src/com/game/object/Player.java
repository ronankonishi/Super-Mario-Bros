package com.game.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.game.gfx.AnimationComplex;
import com.game.gfx.AnimationSimple;
import com.game.main.Game;
import com.game.object.block.Block;
import com.game.object.block.BrickBlock;
import com.game.object.block.BrickStarBlock;
import com.game.object.block.InvisibleBlock;
import com.game.object.block.QuestionFlowerBlock;
import com.game.object.enemy.Enemy;
import com.game.object.enemy.Koopa;
import com.game.object.item.Fireball;
import com.game.object.item.GreenShroom;
import com.game.object.item.RedFlower;
import com.game.object.item.RedShroom;
import com.game.object.item.Shell;
import com.game.object.item.Star;
import com.game.object.util.AudioHandler;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class Player extends GameObject {

	private static final float WIDTH = 16;
	private static final float HEIGHT = 16;
	
	private Handler handler;
	private AudioHandler audioHandler;
	private State state;
	
	enum State {
		SMALL,
		LARGE,
		FIRE;
	}
	
	private BufferedImage[] spriteL, spriteS, spriteF;
	private BufferedImage[][] spriteIL, spriteIS;
	private AnimationSimple playerWalkL, playerWalkS, playerWalkF;
	private AnimationComplex playerWalkIL, playerWalkIS;
	private AnimationSimple currAnimationS;
	private AnimationComplex currAnimationC;
	private LinkedList<GameObject> removeObjs, addObjs;
	private boolean immune;
	private int immuneTimer;
	
	private boolean jumped;
	private boolean forward = true;
	private boolean invincible;
	private int invincibleTimer;
	private int fireAnimationCounter;
	
//	private int shellTimer;
//	private boolean shellDelay;
	
	private float leftBound;
	
	public Player(float x, float y, int scale, Handler handler) {
		super(x, y, ObjectId.Player, WIDTH, HEIGHT, scale, 1);
		this.handler = handler;
		removeObjs = new LinkedList<GameObject>();
		addObjs = new LinkedList<GameObject>();
		
		audioHandler = Game.getAudioHandler();
						
		spriteL = tex.getMarioL();
		spriteS = tex.getMarioS();
		spriteF = tex.getMarioF();
		spriteIL = tex.getMarioIL();
		spriteIS = tex.getMarioIS();
		
		playerWalkL = new AnimationSimple(5, spriteL[1], spriteL[2], spriteL[3]);
		playerWalkS = new AnimationSimple(5, spriteS[1], spriteS[2], spriteS[3]);
		playerWalkF = new AnimationSimple(5, spriteF[1], spriteF[2], spriteF[3]);
		playerWalkIL = new AnimationComplex(5, spriteIL[0], spriteIL[1], spriteIL[2]);
		playerWalkIS = new AnimationComplex(5, spriteIS[0], spriteIS[1], spriteIS[2]);
		
		sprite = spriteS;
		currAnimationS = playerWalkS;
		
		state = State.SMALL;
		setStateLarge();
		setStateFire();
	}

	@Override
	public void render(Graphics g) {
		if (fireAnimationCounter > 0) {
			if (forward) {
				g.drawImage(sprite[1], (int) x, (int) y, (int) width, (int) height, null);
			} else {
				g.drawImage(sprite[1], (int) (x - width), (int) y, (int) -width, (int) height, null);
			}
			fireAnimationCounter++;
			if (fireAnimationCounter == 20) {
				fireAnimationCounter = 0;
			}
		} else if (jumped) {
			if (forward) {
				if (currAnimationC != null) {
					currAnimationC.drawJumpR(g, x, y, width, height);
				} else {
					g.drawImage(sprite[5], (int) x, (int) y, (int) width, (int) height, null);
				}
			} else {
				if (currAnimationC != null) {
					currAnimationC.drawJumpL(g, x, y, width, height);
				} else {
					g.drawImage(sprite[5], (int) (x + width), (int) y, (int) -width, (int) height, null);
				}
			}
		} else if (velX > 0) {
			if (currAnimationC != null) {
				currAnimationC.drawWalkR(g, x,  y, width, height);
			} else {
				currAnimationS.drawAnimation(g, x, y, width, height);
			}
			forward = true;
		} else if (velX < 0) {
			if (currAnimationC != null) {
				currAnimationC.drawWalkL(g, x, y, width, height);
			} else {
				currAnimationS.drawAnimation(g, x + width, y, -width, height);
			}
			forward = false;
		} else {
			if (forward) {
				if (currAnimationC != null) {
					currAnimationC.drawStillR(g, x, y, width, height);
				} else {
					g.drawImage(sprite[0], (int) x, (int) y, (int) width, (int) height, null);
				}
			} else {
				if (currAnimationC != null) {
					currAnimationC.drawStillL(g, x, y, width, height);
				} else {
					g.drawImage(sprite[0], (int) (x + width), (int) y, (int) -width, (int) height, null);
				}
			}
		}
		
//		showBounds(g);		
	}

	private void setStateSmall() {
		sprite = spriteS;
		currAnimationS = playerWalkS;
		if (state == State.SMALL) return;
		state = State.SMALL;
		y += height/2;
		height /= 2;
	}
	
	private void setStateLarge() {
		sprite = spriteL;
		currAnimationS = playerWalkL;
		if (currAnimationC != null) currAnimationC = playerWalkIL;
		if (state == State.LARGE) return;
		if (state == State.FIRE) {
			state = state.LARGE;
			return;
		}
		state = state.LARGE;
		y -= height;
		height *= 2;
	}
	
	private void setStateFire() {
		sprite = spriteF;
		currAnimationS = playerWalkF;
		if (state == State.FIRE) return;
		state = state.FIRE;
	}
	
	private void setStateIL() {
		invincibleTimer = 0;
		invincible = true;
		currAnimationS = null;
		currAnimationC = playerWalkIL;
	}
	
	private void setStateIS() {
		invincibleTimer = 0;
		invincible = true;
		currAnimationS = null;
		currAnimationC = playerWalkIS;
	}

	@Override
	public void tick() {
		if (currAnimationC != null) {
			invincibleTimer = invincibleTimer + 1;
			if (invincibleTimer == 599) {
				invincibleTimer = 0;
				currAnimationC = null;
				invincible = false;
				audioHandler.playTheme();
				switch(state) {
					case SMALL:
						setStateSmall();
						break;
					case LARGE:
						setStateLarge();
						break;
					case FIRE:
						setStateFire();
						break;
				}
			}
		}
		
		if (immune) {
			immuneTimer = immuneTimer + 1;
			if (immuneTimer == 19) {
				immuneTimer = 0;
				immune = false;
			}
		}
		
		if (x + velX >= leftBound) {
			x += velX;
		}
		
		y += velY;
		applyGravity();
		
		if (currAnimationC != null) {
			currAnimationC.runAnimation();
		} else {
			currAnimationS.runAnimation();
		}
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.getGameObjs().size(); i++) {
			GameObject temp = handler.getGameObjs().get(i);
			
			if (temp.getClass() == BackgroundObject.class) continue; 
			if (temp == this) continue;
			if (removeObjs.contains(temp) || addObjs.contains(temp)) continue;
			if (temp.getId() == ObjectId.Enemy && ((Enemy) temp).getFlip()) continue;
			if (temp.getClass() == Fireball.class) continue; 
			
			if (temp.getId() == ObjectId.Enemy && invincible) {
				if (getBounds().intersects(temp.getBounds())) {
					((Enemy) temp).flip();
				}
				continue;
			}
			
			if (temp.getId() == ObjectId.Enemy && !immune) {
				if (getBoundsBottom().intersects(temp.getBounds())) {
					((Enemy) temp).kill();
					removeObjs.add(temp);
					velY = -5f;
					if (temp.getClass() == Koopa.class) {
						addObjs.add(((Koopa) temp).getShell());
					}
				} else if (getBoundsTop().intersects(temp.getBounds()) || 
						   getBoundsRight().intersects(temp.getBounds()) || 
						   getBoundsLeft().intersects(temp.getBounds())) {
					playerHit();
				}
				
				continue;
			}
			
			if (temp.getClass() == Shell.class && getBounds().intersects(((Shell) temp).getBounds())) {
				if (((Shell) temp).getVelX() != 0) {
					if (immune || invincible) continue;
					playerHit();
					continue;
				}
				
				immune = true;
				audioHandler.playKick();
				if (x < temp.getX()) {
					temp.setVelX(5f);
				} else {
					temp.setVelX(-5f);
				}
				continue;
			}
			
			if (temp.getClass() == RedShroom.class && getBounds().intersects(temp.getBounds())) {
				audioHandler.playPowerup();
				playerPowerup();
				removeObjs.add(temp);
				continue;
			}
			
			if (temp.getClass() == RedFlower.class && getBounds().intersects(temp.getBounds())) {
				audioHandler.playPowerup();
				playerPowerup();
				removeObjs.add(temp);
				continue;
			} 
			
			if (temp.getClass() == GreenShroom.class && getBounds().intersects(temp.getBounds())) {
				removeObjs.add(temp);
				audioHandler.play1Up();
				continue;
			}
			
			if (temp.getClass() == Star.class && getBounds().intersects(temp.getBounds())) {
				audioHandler.playInvincible();
				if (state == State.SMALL) {
					setStateIS();
				} else {
					setStateIL();
				}
				removeObjs.add(temp);
				continue;
			}
			
			if (temp.getId() == ObjectId.Block && getBoundsTop().intersects(temp.getBounds())) {
				if(temp.getClass() == InvisibleBlock.class && velY > 0) continue;
				
				y = temp.getY() + temp.getHeight();
				velY = 0;
				
				if (state == State.SMALL) {
					((Block) temp).smallHit();
				} else {
					((Block) temp).largeHit();
					if (temp.getClass() == BrickBlock.class) removeObjs.add(temp);
				}
				
				
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
				
				if (temp.getClass() == InvisibleBlock.class && !((InvisibleBlock) temp).isDisabled()) {
					((InvisibleBlock) temp).spawnGreenShroom();
					addObjs.add(((InvisibleBlock)temp).getGreenShroom());
					continue;
				}
				
				if (temp.getClass() == BrickStarBlock.class && !((BrickStarBlock) temp).isDisabled()) {
					((BrickStarBlock) temp).spawnStar();
					addObjs.add(((BrickStarBlock) temp).getStar());
					continue;
				}
				
			} else {
				if (temp.getClass() == InvisibleBlock.class && !((InvisibleBlock) temp).isDisabled()) continue; 
				
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
	
	private void playerHit() {
		audioHandler.playPipe();
		immune = true;
		switch(state) {
			case SMALL:
				
				break;
			case LARGE:
				setStateSmall();
				break;
			case FIRE:
				setStateLarge();
				break;
		}
	}
	
	private void playerPowerup() {
		switch(state) {
			case SMALL:
				setStateLarge();
				break;
			case LARGE:
				setStateFire();
				break;
			case FIRE:
				break;
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
	
	public void setJumped() {
		jumped = true;
		if (state == State.SMALL) {
			audioHandler.playJumpSmall();
		} else {
			audioHandler.playJumpSuper();
		}
	}
	
	public void setLeftBound(float x) {
		leftBound = x;
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}
	
	public void fire() {
		if (state != State.FIRE) return;
		audioHandler.playFireball();
		Fireball fireball = new Fireball(x, y, width, height, 1, forward);
		addObjs.add(fireball);
		fireAnimationCounter = 1;
	}
}
