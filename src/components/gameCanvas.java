package components;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.GameManager;

public class gameCanvas extends Canvas{
	

	
	public static final int width = 60 * 48, height = 60 * 48;
    private GraphicsContext gc;
    private GameManager gameManager;

    public gameCanvas() {
        super(width, height);
      //  System.out.println(width + " " + height);
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

