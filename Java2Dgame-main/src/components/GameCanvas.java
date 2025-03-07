package components;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import logic.GameManager;
import logic.GameState;
import main.Main;

public class GameCanvas extends Canvas {

	public static final int width = Main.getMapSize() * 48, height = Main.getMapSize() * 48;
	private GraphicsContext gc;
	private GameManager gameManager;
	private GameState gameState;

	public GameCanvas() {
		super(width, height);
		gc = getGraphicsContext2D();
		gameManager = new GameManager();
		gameManager.setCanvas(this);
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

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	

}
