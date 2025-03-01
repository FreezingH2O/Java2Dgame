package components;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.GameManager;
import main.Main;

public class GameCanvas extends Canvas{
	

	
	public static final int width = Main.getMapSize() * 48, height = Main.getMapSize() * 48;
    private GraphicsContext gc;
    private GameManager gameManager;

    public GameCanvas() {
        super(width, height);
        gc = getGraphicsContext2D();
        gameManager = new GameManager();
        startGameLoop();
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameManager.update(gc);
            }
        }.start();
    }

	
	
}

