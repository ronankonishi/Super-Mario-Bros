package com.game.main.util;

import java.awt.image.BufferedImage;

import com.game.gfx.BufferedImageLoader;
import com.game.gfx.Texture;
import com.game.object.BackgroundObject;
import com.game.object.GameObject;
import com.game.object.Pipe;
import com.game.object.Player;
import com.game.object.block.Block;
import com.game.object.block.BrickBlock;
import com.game.object.block.BrickCoinsBlock;
import com.game.object.block.BrickStarBlock;
import com.game.object.block.GroundBlock;
import com.game.object.block.InvisibleBlock;
import com.game.object.block.QuestionBlock;
import com.game.object.block.QuestionFlowerBlock;
import com.game.object.block.SolidBlock;
import com.game.object.enemy.Goomba;
import com.game.object.enemy.Koopa;
import com.game.object.util.Handler;

public class LevelHandler {
	
	private final String PARENT_FOLDER = "/level";
	
	private BufferedImageLoader loader;
	private Handler handler;
	private Texture tex;
	
	public LevelHandler(Handler handler, Texture texture) {
		this.handler = handler;
		this.tex = texture;
		loader = new BufferedImageLoader();
	}
	
	public void start() {
		loadTiles(PARENT_FOLDER + "/1_1.png");
		loadBackground(PARENT_FOLDER + "/1_1b.png");
		loadCharacters(PARENT_FOLDER + "/1_1c.png");
	}
	
	private void loadTiles(String levelTilesPath) {
		BufferedImage levelTiles = loader.loadImage(levelTilesPath);
		
		int width = levelTiles.getWidth();
		int height = levelTiles.getHeight();
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int pixel = levelTiles.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) continue;
				
				if (red == green && red == blue) {
					Block block = null;
					int x = i * 16;
					int y = j * 16;
					int w = 16;
					int h = 16;
					int s = 3;

					if (red == 0) block = new BrickBlock(x, y, w, h, s);
					if (red == 2) block = new BrickCoinsBlock(x, y, w, h, s);
					if (red == 4) block = new BrickStarBlock(x, y, w, h, s);
					if (red == 30) block = new QuestionBlock(x, y, w, h, s);
					if (red == 32) block = new QuestionFlowerBlock(x, y, w, h, s);
					if (red == 60) block = new SolidBlock(x, y, w, h, s);
					if (red == 90) block = new InvisibleBlock(x, y, w, h, s);
					if (red == 100) block = new GroundBlock(x, y, w, h, s);
										
					handler.addObj(block);
				} else if (blue == 0 && green == 0 && red == 5) {
					handler.addObj(new Pipe(i*16, j*16, 32, 16, 0, 3, false));
				} else if (blue == 0 && green == 0 && red == 10) {
					handler.addObj(new Pipe(i*16, j*16, 32, 16, 1, 3, false));
				} else if (blue == 0 && green == 0 && red == 15) {
					handler.addObj(new Pipe(i*16, j*16, 32, 16, 2, 3, false));
				} else if (blue == 0 && green == 0 && red == 20) {
					handler.addObj(new Pipe(i*16, j*16, 32, 16, 3, 3, false));
				} else if (blue == 0 && green == 0 && red == 25) {
					handler.addObj(new Pipe(i*16, j*16, 32, 16, 0, 3, true));
				} else if (blue == 0 && green == 0 && red == 30) {
					handler.addObj(new Pipe(i*16, j*16, 32, 16, 2, 3, true));
				}
				
			}
		}	
	}
	
	private void loadBackground(String levelBackgroundPath) {
		BufferedImage levelTiles = loader.loadImage(levelBackgroundPath);
		
		int width = levelTiles.getWidth();
		int height = levelTiles.getHeight();
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int pixel = levelTiles.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) continue;
				
				if (red == green && red == blue) {
					BufferedImage img = null;
					int h = 16;
					if (red == 0) {
						img = tex.getMountain1()[0];
					} else if (red == 2) {
						img = tex.getMountain1()[1];
					} else if (red == 4) {
						img = tex.getMountain1()[2];
					} else if (red == 6) {
						img = tex.getMountain1()[3];
					} else if (red == 8) {
						img = tex.getMountain1()[4];
					} else if (red == 10) {
						img = tex.getMountain1()[5];
					} else if (red == 30) {
						img = tex.getBush1()[0];
					} else if (red == 32) {
						img = tex.getBush1()[1];
					} else if (red == 34) {
						img = tex.getBush1()[2];
					} else if (red == 60) {
						img = tex.getCloud1()[0];
						h = 32;
					} else if (red == 62) {
						img = tex.getCloud1()[1];
						h = 32;
					} else if (red == 64) {
						img = tex.getCloud1()[2];
						h = 32;
					} else if (red == 90) {
						img = tex.getCastle1()[0];
					} else if (red == 92) {
						img = tex.getCastle1()[1];
					} else if (red == 94) {
						img = tex.getCastle1()[2];
					} else if (red == 96) {
						img = tex.getCastle1()[3];
					} else if (red == 98) {
						img = tex.getCastle1()[4];
					} else if (red == 100) {
						img = tex.getCastle1()[5];
					} else if (red == 102) {
						img = tex.getCastle1()[6];
					} else if (red == 104) {
						img = tex.getCastle1()[7];
					}
					
					handler.addObj(new BackgroundObject(i * 16, j*16, 16, h, 3, img));
				}
			}
		}
	}
	
	private void loadCharacters(String levelCharactersPath) {
		BufferedImage levelTiles = loader.loadImage(levelCharactersPath);
		
		int width = levelTiles.getWidth();
		int height = levelTiles.getHeight();
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int pixel = levelTiles.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) continue;
				
				if (red == green && red == blue) {
					if (red == 0) {
						handler.setPlayer(new Player(i*16, j*16, 3, handler));
					} else if (red == 30) {
						handler.addObj(new Goomba(i*16, j*16, 16, 16, 3));
					} else if (red == 60) {
						handler.addObj(new Koopa(i*16, j*16, 16, 32, 3));
					}					
				}
			}
		}
	}
		
}