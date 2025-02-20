package main;

import entitiy.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import map.map;

public class gameCanvas extends Canvas{
	
	private static final int tileSize = main.getTilesize();
	
	public  static final int width = main.getWidth(), height = main.getHeight();
	public static GraphicsContext gc ;
	private static Player player;
	private map map1 ;
	
	public gameCanvas() {
		super(width , height);
		gc = getGraphicsContext2D();
		player = new Player();
		map1 = new map(player);
		gameloop();
		
	}
	
	private void gameloop() {
		new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, width, height);
				update();
				
			}
		}.start();
	}

	private void update() {
		
		map1.update(gc);
		player.update(gc);
	}


	
	
}

