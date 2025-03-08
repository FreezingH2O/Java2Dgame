package logic;

import world.Map;  
import world.MapType;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;

import components.GameCanvas;
import components.StatusDisplay;
import entity.character.BaseCharacter;
import entity.character.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Main;
import javafx.geometry.Point2D;

public class GameManager {
	private Main main;
	private Map map;
	private GameState gameState;
	private StartScreen startScreen;
	private PauseScreen pauseScreen;
	private EndScreen endScreen;
	private GameCanvas canvas;
	private static final double PAUSE_BUTTON_X = 750; // Adjust as needed
	private static final double PAUSE_BUTTON_Y = 20; // Adjust as needed
	private static final double PAUSE_BUTTON_WIDTH = 40;
	private static final double PAUSE_BUTTON_HEIGHT = 30;
	
	AudioClip island_sound = Sound.getIslandSound();
    AudioClip dungeon_sound = Sound.getDungeon_sound();
    AudioClip bossroom_sound = Sound.getBossroom_sound();

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public static double getPauseButtonX() {
		return PAUSE_BUTTON_X;
	}

	public static double getPauseButtonY() {
		return PAUSE_BUTTON_Y;
	}

	public static double getPauseButtonWidth() {
		return PAUSE_BUTTON_WIDTH;
	}

	public static double getPauseButtonHeight() {
		return PAUSE_BUTTON_HEIGHT;
	}

	public GameManager() {
		gameState = GameState.START_SCREEN;
		startScreen = new StartScreen(this);
		pauseScreen = new PauseScreen(this);
		endScreen = new EndScreen(this);
	}

	public void startGame() {
		map = new Map(MapType.ISLAND);
		
		if (map.getPlayer() == null) {
			throw new IllegalStateException("Map failed to initialize with a Player.");
		}
		this.setGameState(gameState.PLAYING);
		island_sound.setCycleCount(AudioClip.INDEFINITE);
		island_sound.setVolume(0.3);
        island_sound.play();
		
//        gameState = GameState.PLAYING;  
	}

	public void update(GraphicsContext gc) {
//		System.out.println("GameState: " + gameState);
		switch (gameState) {
		case START_SCREEN:
			startScreen.draw(gc);
			break;
		case PLAYING:
			Player player = Player.getInstant();

			if (player.getHealth() <= 0) {
//                    gameState = GameState.END_SCREEN;
				this.setGameState(gameState.END_SCREEN);
			} else {
				gc.clearRect(0, 0, map.getMapWidth(), map.getMapHeight());
				map.update(gc);
				player.update(gc);
				for (BaseCharacter entity : Map.getEntities()) {
					if (!(entity instanceof Player)) {
						entity.update(gc);
					}
				}
			}

			break;
		case PAUSED:
//                pauseScreen.draw(gc);  
			break;
		case END_SCREEN:
//                endScreen.draw(gc);  
//            	endScreen.show();   
			break;
		}
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void restartGame() {
		Player player = Player.getInstant();
		player.setHealth(player.getMaxHealth());
		StatusDisplay.getInstant().heal(player.getMaxHealth());
		StatusDisplay.getInstant().heal(player.getMaxMana());
		map = new Map(MapType.ISLAND);
		this.setGameState(gameState.PLAYING);
		island_sound.setCycleCount(AudioClip.INDEFINITE);
		island_sound.setVolume(0.3);
        island_sound.play();

	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		if (main != null) {
			Main.updateVisibility(); 
		}
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