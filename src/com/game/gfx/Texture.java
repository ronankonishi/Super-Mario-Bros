package com.game.gfx;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Texture {
	
	private final String PARENT_FOLDER = "/tile";
	
	private final int MARIO_L_COUNT = 21;
	private final int MARIO_S_COUNT = 14;
	
	private final int TILE_1_COUNT = 28;
	private final int TILE_2_COUNT = 33;
	
	
	private BufferedImageLoader loader;
	private BufferedImage player_sheet, enemy_sheet, npc_sheet, block_sheet, 
						  tile_sheet, game_over_sheet, intro_sheet;
	private List<BufferedImage> sheetList;
		
	public BufferedImage[] mario_l, mario_s, mario_f, 
						   tile_1, tile_2, tile_3, tile_4,
						   pipe_1, debris_1, coin_a1, shroom_1, flower_1, star_1,
						   mountain_1, cloud_1, bush_1, castle_1,
						   goomba_1, koopa_1, shell_1, fireball,
						   flag_1;
	public BufferedImage[][] mario_il, mario_is;
	
	public Texture() {
		mario_l = new BufferedImage[MARIO_L_COUNT];
		mario_s = new BufferedImage[MARIO_S_COUNT];
		mario_f = new BufferedImage[MARIO_L_COUNT];
		mario_il = new BufferedImage[3][MARIO_L_COUNT];
		mario_is = new BufferedImage[3][MARIO_S_COUNT];
		
		tile_1 = new BufferedImage[TILE_1_COUNT + TILE_2_COUNT];
		tile_2 = new BufferedImage[TILE_1_COUNT + TILE_2_COUNT];
		tile_3 = new BufferedImage[TILE_1_COUNT + TILE_2_COUNT];
		tile_4 = new BufferedImage[TILE_1_COUNT + TILE_2_COUNT];
		pipe_1 = new BufferedImage[4];
		debris_1 = new BufferedImage[4];
		coin_a1 = new BufferedImage[4];
		shroom_1 = new BufferedImage[3];
		flower_1 = new BufferedImage[4];
		star_1 = new BufferedImage[4];
		
		mountain_1 = new BufferedImage[6];
		bush_1 = new BufferedImage[3];
		cloud_1 = new BufferedImage[3];
		castle_1 = new BufferedImage[8];
		
		goomba_1 = new BufferedImage[3];
		koopa_1 = new BufferedImage[2];
		shell_1 = new BufferedImage[1];
		
		fireball = new BufferedImage[4];
		
		flag_1 = new BufferedImage[3];
		
		loader = new BufferedImageLoader();
		sheetList = new LinkedList<BufferedImage>();
		
		try {
			player_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Mario & Luigi.png");
			enemy_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Enemies & Bosses.png");
			npc_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Items Objects and NPCs.png");
			block_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Item and Brick Blocks.png");
			tile_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Tileset.png");
			game_over_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Time Up Game Over Screens and Text.png");
			intro_sheet = loader.loadImage(PARENT_FOLDER + "/NES - Super Mario Bros - Title Screen.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getPlayerTextures();
		getTileTextures();
		getDebrisTextures();
		getPipeTextures();
		getCoinA1Textures();
		getShroomTextures();
		getFlowerTextures();
		getStarTextures();
		
		getMountainTextures();
		getBushTextures();
		getCloudTextures();
		getCastleTextures();
		
		getGoombaTextures();
		getKoopaTextures();
		getShellTextures();
		
		getFireballTextures();
		getFlagTextures();
	}
	
	public BufferedImage[] getMarioL() {
		return mario_l;
	}
	
	public BufferedImage[] getMarioS() {
		return mario_s;
	}
	
	public BufferedImage[] getMarioF() {
		return mario_f;
	}
	
	public BufferedImage[][] getMarioIL() {
		return mario_il;
	}
	
	public BufferedImage[][] getMarioIS() {
		return mario_is;
	}
	
	public BufferedImage[] getTile1() {
		return tile_1;
	}
	
	public BufferedImage[] getTile2() {
		return tile_2;
	}
	
	public BufferedImage[] getTile3() {
		return tile_3;
	}	
	
	public BufferedImage[] getTile4() {
		return tile_4;
	}	
	
	public BufferedImage[] getPipe1() {
		return pipe_1;
	}	
	
	public BufferedImage[] getDebris1() {
		return debris_1;
	}	
	
	public BufferedImage[] getCoinA1() {
		return coin_a1;
	}
	
	public BufferedImage[] getShroom1() {
		return shroom_1;
	}
	
	public BufferedImage[] getFlower1() {
		return flower_1;
	}
	
	public BufferedImage[] getStar1() {
		return star_1;
	}
	
	public BufferedImage[] getMountain1() {
		return mountain_1;
	}
	
	public BufferedImage[] getBush1() {
		return bush_1;
	}
	
	public BufferedImage[] getCloud1() {
		return cloud_1;
	}
	
	public BufferedImage[] getCastle1() {
		return castle_1;
	}
	
	public BufferedImage[] getGoomba1() {
		return goomba_1;
	}
	
	public BufferedImage[] getKoopa1() {
		return koopa_1;
	}
	
	public BufferedImage[] getShell1() {
		return shell_1;
	}
	
	public BufferedImage[] getFireball() {
		return fireball;
	}
	
	public BufferedImage[] getFlag() {
		return flag_1;
	}
	
	private void getPlayerTextures() {
		int x_off = 80;
		int y_off = 1;
		int width = 16;
		int height = 32;
		int sprite_off = 63;
		
		for (int i = 0; i < MARIO_L_COUNT; i++) {
			mario_l[i] = player_sheet.getSubimage(x_off + i*(width+1), y_off, width, height);
		}
		
		y_off += height+1;
		height = 16;
		
		for (int i = 0; i < MARIO_S_COUNT; i++) {
			mario_s[i] = player_sheet.getSubimage(x_off + i*(width+1), y_off, width, height);
		}
		
		
		x_off = 80;
		y_off = 129;
		width = 16;
		height = 32;
		
		for (int i = 0; i < MARIO_L_COUNT; i++) {
			mario_f[i] = player_sheet.getSubimage(x_off + i*(width+1), y_off, width, height);
		}
		
		
		x_off = 80;
		y_off = 192;
		width = 16;
		height = 32;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < MARIO_L_COUNT; i++) {
				mario_il[j][i] = player_sheet.getSubimage(x_off + i*(width+1), y_off + (sprite_off * j), width, height);
			}
		}
		
		y_off += height+1;
		height = 16;
		
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < MARIO_S_COUNT; i++) {
				mario_is[j][i] = player_sheet.getSubimage(x_off + i*(width+1), y_off + (sprite_off * j), width, height);
			}
		}
	}
	
	private void getTileTextures() {
		int x_off = 0;
		int y_off = 0;
		int width = 16;
		int height = 16;
		
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < TILE_1_COUNT; i++) {
				if (j == 0) {
					tile_1[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				} else if (j == 1) {
					tile_2[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				} else if (j == 2) {
					tile_3[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				} else if (j == 3) {
					tile_4[i] = tile_sheet.getSubimage(x_off + i*width, y_off + j*height*2, width, height);
				}
			}
			
			y_off += height;
			
			for (int i = 0; i < TILE_2_COUNT; i++) {
				if (j == 0) {
					tile_1[i + TILE_1_COUNT] = tile_sheet.getSubimage(x_off + i*width, y_off  + j*height*2, width, height);
				} else if (j == 1) {
					tile_2[i + TILE_1_COUNT] = tile_sheet.getSubimage(x_off + i*width, y_off  + j*height*2, width, height);
				} else if (j == 2) {
					tile_3[i + TILE_1_COUNT] = tile_sheet.getSubimage(x_off + i*width, y_off  + j*height*2, width, height);
				} else if (j == 3) {
					tile_4[i + TILE_1_COUNT] = tile_sheet.getSubimage(x_off + i*width, y_off  + j*height*2, width, height);
				}
			}
		}
	}
	
	private void getDebrisTextures() {
		int x_off = 304;
		int y_off = 112;
		int width = 8;
		int height = 8;
		
		debris_1[0] = block_sheet.getSubimage(x_off, y_off, width, height);
		debris_1[1] = block_sheet.getSubimage(x_off, y_off + height, width, height);
		debris_1[2] = block_sheet.getSubimage(x_off + width, y_off, width, height);
		debris_1[3] = block_sheet.getSubimage(x_off + width, y_off + height, width, height);
	}
	
	private void getPipeTextures() {
		int x_off = 0;
		int y_off = 16*8;
		int width = 32;
		int height = 16;
		
		pipe_1[0] = tile_sheet.getSubimage(x_off, y_off, width, height);
		pipe_1[1] = tile_sheet.getSubimage(x_off, y_off + height, width, height);
		pipe_1[2] = tile_sheet.getSubimage(x_off + width, y_off, width, height);
		pipe_1[3] = tile_sheet.getSubimage(x_off + width, y_off + height, width, height);
	}
	
	private void getCoinA1Textures() {
		int x_off = 0;
		int y_off = 7 * 16;
		int width = 16;
		int height = 16;
		
		coin_a1[2] = npc_sheet.getSubimage(x_off, y_off, width, height);
		coin_a1[1] = npc_sheet.getSubimage(x_off + width, y_off, width, height);
		coin_a1[0] = npc_sheet.getSubimage(x_off + 2*width, y_off, width, height);
		coin_a1[3] = npc_sheet.getSubimage(x_off + 3*width, y_off, width, height);		
	}
	
	private void getShroomTextures() {
		int x_off = 0;
		int y_off = 0;
		int width = 16;
		int height = 16;
		shroom_1[0] = npc_sheet.getSubimage(x_off, y_off, width, height);
		shroom_1[1] = npc_sheet.getSubimage(x_off + width, y_off, width, height);
		shroom_1[2] = npc_sheet.getSubimage(x_off + 2*width, y_off, width, height);
	}
	
	private void getFlowerTextures() {
		int x_off = 0;
		int y_off = 16*2;
		int width = 16;
		int height = 16;
		flower_1[0] = npc_sheet.getSubimage(x_off, y_off, width, height);
		flower_1[1] = npc_sheet.getSubimage(x_off + width, y_off, width, height);
		flower_1[2] = npc_sheet.getSubimage(x_off + 2*width, y_off, width, height);
		flower_1[3] = npc_sheet.getSubimage(x_off + 3*width, y_off, width, height);
	}
	
	private void getStarTextures() {
		int x_off = 0;
		int y_off = 16*3;
		int width = 16;
		int height = 16;
		star_1[0] = npc_sheet.getSubimage(x_off, y_off, width, height);
		star_1[1] = npc_sheet.getSubimage(x_off + width, y_off, width, height);
		star_1[2] = npc_sheet.getSubimage(x_off + 2*width, y_off, width, height);
		star_1[3] = npc_sheet.getSubimage(x_off + 3*width, y_off, width, height);
	}
	
	private void getMountainTextures() {
		int x_off = 8*16;
		int y_off = 8*16;
		int width = 16;
		int height = 16;
		mountain_1[0] = tile_sheet.getSubimage(x_off, y_off, width, height);
		mountain_1[1] = tile_sheet.getSubimage(x_off + width, y_off, width, height);
		mountain_1[2] = tile_sheet.getSubimage(x_off + 2*width, y_off, width, height);
		
		mountain_1[3] = tile_sheet.getSubimage(x_off, y_off + height, width, height);
		mountain_1[4] = tile_sheet.getSubimage(x_off + width, y_off + height, width, height);
		mountain_1[5] = tile_sheet.getSubimage(x_off + 2*width, y_off + height, width, height);
	}
	
	private void getBushTextures() {
		int x_off = 11*16;
		int y_off = 9*16;
		int width = 16;
		int height = 16;
		bush_1[0] = tile_sheet.getSubimage(x_off, y_off, width, height);
		bush_1[1] = tile_sheet.getSubimage(x_off + width, y_off, width, height);
		bush_1[2] = tile_sheet.getSubimage(x_off + 2*width, y_off, width, height);
	}
	
	private void getCloudTextures() {
		int x_off = 0;
		int y_off = 20*16;
		int width = 16;
		int height = 32;
		cloud_1[0] = tile_sheet.getSubimage(x_off, y_off, width, height);
		cloud_1[1] = tile_sheet.getSubimage(x_off + width, y_off, width, height);
		cloud_1[2] = tile_sheet.getSubimage(x_off + 2*width, y_off, width, height);
	}
	
	private void getCastleTextures() {
		int x_off1 = 17*16;
		int y_off1 = 0;
		int width = 16;
		int height = 16;
		castle_1[7] = npc_sheet.getSubimage(x_off1, y_off1, width, height);
		
		int x_off = 11*16;
		int y_off = 0;
		castle_1[0] = tile_sheet.getSubimage(x_off, y_off, width, height);
		castle_1[1] = tile_sheet.getSubimage(x_off + width, y_off, width, height);
		castle_1[2] = tile_sheet.getSubimage(x_off + 2*width, y_off, width, height);
		castle_1[3] = tile_sheet.getSubimage(x_off + 3*width, y_off, width, height);
	
		castle_1[4] = tile_sheet.getSubimage(x_off, y_off + height, width, height);
		castle_1[5] = tile_sheet.getSubimage(x_off + width, y_off + height, width, height);
		castle_1[6] = tile_sheet.getSubimage(x_off + 2*width, y_off + height, width, height);
	}
	
	private void getGoombaTextures() {
		int x_off = 0;
		int y_off = 16;
		int width = 16;
		int height = 16;
		
		goomba_1[0] = enemy_sheet.getSubimage(x_off, y_off, width, height);
		goomba_1[1] = enemy_sheet.getSubimage(x_off + width, y_off, width, height);
		goomba_1[2] = enemy_sheet.getSubimage(x_off + 2*width, y_off, width, height);
	}
	
	private void getKoopaTextures() {
		int x_off = 6*16;
		int y_off = 0;
		int width = 16;
		int height = 32;
		
		koopa_1[0] = enemy_sheet.getSubimage(x_off, y_off, width, height);
		koopa_1[1] = enemy_sheet.getSubimage(x_off + width, y_off, width, height);
	}
	
	private void getShellTextures() {
		int x_off = 6*16;
		int y_off = 16;
		int width = 16;
		int height = 16;
		shell_1[0] = enemy_sheet.getSubimage(x_off + 4*width, y_off, width, height); 
	}
	
	private void getFireballTextures() {
		int x_off = 6 * 16;
		int y_off = 9 * 16;
		int width = 8;
		int height = 8;
		fireball[0] = npc_sheet.getSubimage(x_off, y_off, width, height);
		fireball[1] = npc_sheet.getSubimage(x_off + width, y_off, width, height);
		fireball[2] = npc_sheet.getSubimage(x_off, y_off + height, width, height);
		fireball[3] = npc_sheet.getSubimage(x_off + width, y_off + height, width, height);
	}
	
	private void getFlagTextures() {
		int x_off1 = 8*16;
		int y_off1 = 2*16;
		int width = 16;
		int height = 16;
		flag_1[0] = npc_sheet.getSubimage(x_off1, y_off1, width, height);

		int x_off = 16*16;
		int y_off = 8*16;
		flag_1[1] = tile_sheet.getSubimage(x_off, y_off, width, height);
		flag_1[2] = tile_sheet.getSubimage(x_off, y_off + height, width, height);
	}
}