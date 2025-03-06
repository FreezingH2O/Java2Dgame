// GameManager.java  
package logic;

import world.Map;
import world.MapType;


import components.GameCanvas;
import entity.character.BaseCharacter;
import entity.character.Player;
import entity.effect.EffectManager;
import javafx.scene.canvas.GraphicsContext;

public class GameManager {

	private Map map;
	private GameState gameState;
	private StartScreen startScreen;
	private PauseScreen pauseScreen;
	private EndScreen endScreen;
	private GameCanvas canvas;

	public GameManager() {
		gameState = GameState.START_SCREEN;
		startScreen = new StartScreen(this);
		pauseScreen = new PauseScreen(this);
		endScreen = new EndScreen(this);
	}

	public void startGame() {
		map = new Map(MapType.ISLAND);

		if (Player.getInstant() == null) {
			throw new IllegalStateException("Map failed to initialize with a Player.");
		}

		gameState = GameState.PLAYING;
	}

	public void update(GraphicsContext gc) {
		switch (gameState) {
		case START_SCREEN:
			startScreen.draw(gc);
			break;
		case PLAYING:
			Player player = Player.getInstant();

			if (player.getHealth() <= 0) {
				gameState = GameState.END_SCREEN;
			} else {
				gc.clearRect(0, 0, map.getMapWidth(), map.getMapHeight());
				map.update(gc);
				player.update(gc);
				for (BaseCharacter entity : map.getEntities()) {
					if (!(entity instanceof Player)) {
						entity.update(gc);
					}
				}
				EffectManager.getInstance().render(gc);
			}
			break;
		case PAUSED:
			pauseScreen.draw(gc);
			break;
		case END_SCREEN:
			endScreen.draw(gc);
			break;
		}
	}

	public void reset() {
		Player player = Player.getInstant();
		player.setHealth(player.getMaxHealth());

		map = new Map(MapType.ISLAND);
		if (Player.getInstant() == null) {
			throw new IllegalStateException("Map failed to initialize with a Player.");
		}
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public StartScreen getStartScreen() {
		return startScreen;
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

	public PauseScreen getPauseScreen() {
		return pauseScreen;
	}

	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}

	public EndScreen getEndScreen() {
		return endScreen;
	}

	public void setEndScreen(EndScreen endScreen) {
		this.endScreen = endScreen;
	}

	public GameCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(GameCanvas canvas) {
		this.canvas = canvas;
	}

}
