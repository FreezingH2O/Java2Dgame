package main;

import entitiy.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class gameCanvas extends Canvas{
	
	private static final int tileSize = 48;
	private static final int col = 20, row = 15;
	private static final int width = col*tileSize, height = row *tileSize;
	public static GraphicsContext gc ;
	private static Player player;
	
	public gameCanvas() {
		super(width , height);
		gc = getGraphicsContext2D();
		player = new Player();
		gameloop();
		
	}
	
	private void gameloop() {
		new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, width, height);
				update();
				gc.drawImage(new Image("tree1.png"),300,300);
				gc.drawImage(new Image("tree1.png"),348,300);
				gc.drawImage(new Image("tree1.png"),300-48,300);
				gc.drawImage(new Image("tree1.png"),300-48-48,300);
				gc.drawImage(new Image("tree1.png"),300,300-50);
				gc.drawImage(new Image("tree1.png"),348,300-50);
				gc.drawImage(new Image("tree1.png"),300-48,300-50);
				gc.drawImage(new Image("tree1.png"),300-48-48,300-50);
				
			}
		}.start();
	}

	private void update() {
		player.update(gc);
	}
	
	
}

