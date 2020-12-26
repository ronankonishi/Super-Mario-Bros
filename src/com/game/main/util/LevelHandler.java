package com.game.main.util;

import java.awt.image.BufferedImage;

import com.game.gfx.BufferedImageLoader;
import com.game.object.Block;
import com.game.object.BlockId;
import com.game.object.Pipe;
import com.game.object.Player;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

public class LevelHandler {
	
	private final String PARENT_FOLDER = "/level";
	
	private BufferedImageLoader loader;
	private BufferedImage levelTiles;
	private Handler handler;
	
	public LevelHandler(Handler handler) {
		this.handler = handler;
		loader = new BufferedImageLoader();
	}
	
	public void start() {
		setLevel(PARENT_FOLDER + "/1_1.png");
	}
	
	public void setLevel(String levelTilesPath) {
		this.levelTiles = loader.loadImage(levelTilesPath);
		
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
					int blockIdInd = (int) (red/10);
					BlockId id = null;
					if (blockIdInd == 0) id = BlockId.Brick;
					if (blockIdInd == 3) id = BlockId.Question;
					if (blockIdInd == 6) id = BlockId.Solid;
					if (blockIdInd == 9) id = BlockId.Invisible;
					
					handler.addObj(new Block(i*16, j*16, 16, 16, 3, id));
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
				} else if (red == 0 && green == 0 && blue == 255) {
					handler.setPlayer(new Player(i*16, j*16, 3, handler));
				}
				
			}
		}	
	}
}