package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import com.game.gfx.Camera;
import com.game.gfx.Texture;
import com.game.gfx.Window;
import com.game.object.util.Handler;
import com.game.object.util.KeyInput;
import com.game.main.util.LevelHandler;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// GAME CONSTANTS
	private static final int MILLIS_PER_SEC = 1000;
	private static final int NANOS_PER_SEC = 1000000000;
	private static final double NUM_TICKS = 60.0;
	private static final String NAME = "Super Mario Bros";

	private final static int WINDOW_WIDTH = 960;
	private final static int WINDOW_HEIGHT = 720;
	private final static int SCREEN_WIDTH = WINDOW_WIDTH - 67;
	private final static int SCREEN_HEIGHT = WINDOW_HEIGHT;
	private final static int SCREEN_OFFSET = -16;

	// GAME VARIABLES
	private boolean running;

	// GAME COMPONENTS
	private Thread thread;
	private static Handler handler;
	private Camera cam;
	private LevelHandler levelHandler;
	
	private static Texture tex;
	
	public static void main(String args[]) {
		new Game();
	}
	
	public Game() {
		running = false;
		initialize();
	}
	
	private void initialize() {
		tex = new Texture();
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		levelHandler = new LevelHandler(handler, tex);
		levelHandler.start();

		cam = new Camera(0, SCREEN_OFFSET, handler.getPlayer());
		
//		hud = new HUD();
		new Window(WINDOW_WIDTH, WINDOW_HEIGHT, NAME, this);

		this.setFocusable(true);
		this.start();
	}

	private synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = NUM_TICKS;
		double ns = NANOS_PER_SEC / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		// while running, update graphics and process and get FPS
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			// call tick() and reset time passed
			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running) {
				render();
			}

			frames++;

			// get number of frames every second
			if (System.currentTimeMillis() - timer > MILLIS_PER_SEC) {
				timer += MILLIS_PER_SEC;
//				System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.getPlayer().setLeftBound(cam.getCX() - WIDTH/2);
		handler.tick();
		cam.tick();
//		hud.tick();
	}

	private void render() {
		BufferStrategy buf = this.getBufferStrategy();
		if (buf == null) {
			this.createBufferStrategy(3);
			return;
		}

		// draw graphics
		Graphics g = buf.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(new Color(92, 148, 252));
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		g2d.translate(-cam.getCX(), cam.getCY());
		handler.render(g);
		g2d.translate(cam.getCX(), -cam.getCY());

//		hud.render(g);
		
		// clean for next frame
		g.dispose();
		buf.show();
	}
	
	private synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Texture getTexture() {
		return tex;
	}

	public static Handler getHandler() {
		return handler;
	}
	
	public static int getWindowHeight() {
		return WINDOW_HEIGHT;
	}

	public static int getWindowWidth() {
		return WINDOW_WIDTH;
	}

	public static int getScreenHeight() {
		return SCREEN_HEIGHT;
	}

	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}
}