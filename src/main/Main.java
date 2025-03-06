package main;

import components.GameCanvas;
import components.Hotbar;
import components.Instruction;
import components.StatusDisplay;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import logic.GameManager;
import logic.GameState;
import logic.KeyboardController;

public class Main extends Application {
	private static GameCanvas canvas;  
    private static GameManager gameManager;
	public static Scene scene;
	private static final int tileSize = 48;
	private static final int col = 21, row = 13;
	private static StackPane root;
	public static final int MAP_SIZE = 80;
	public static final int width = col * tileSize, height = row * tileSize;

	public void start(Stage stage) {

		canvas = new GameCanvas();
		gameManager = canvas.getGameManager();  

		root = new StackPane(canvas);

		scene = new Scene(root);
		
		scene.setOnMouseClicked(e -> {  
            GameState currentState = canvas.getGameManager().getGameState();  
            if (currentState == GameState.START_SCREEN) {  
            	canvas.getGameManager().getStartScreen().handleInput(e.getX(), e.getY());  
            } else if (currentState == GameState.END_SCREEN) {  
            	canvas.getGameManager().getEndScreen().handleInput();  
            } else if (currentState == GameState.PAUSED) {  
            	canvas.getGameManager().getPauseScreen().handleInput(e.getX(), e.getY());  
            }  
            updateVisibility();
        }); 

		KeyboardController keyboard = new KeyboardController();
		scene.setOnKeyPressed(e -> keyboard.handleKeyPress(e.getCode(), true));
		scene.setOnKeyReleased(e -> keyboard.handleKeyPress(e.getCode(), false));

		stage.setScene(scene);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setResizable(false);
		stage.setTitle("THE DESTINED ONE");
		stage.show();
		
		updateVisibility();
	}
	
    public static void updateVisibility() {  
    	GameState currentState = gameManager.getGameState();
    	System.out.println("update visibility at state "+currentState);
    	
    	StatusDisplay statusDisplay = StatusDisplay.getInstant();  
    	Instruction instructions = Instruction.getInstant();  
    	Hotbar hotbar = new Hotbar(); 

        root.getChildren().clear();  
        root.getChildren().add(canvas);  

        if (currentState == GameState.PLAYING) {  
        	System.out.println("display game components");
            root.getChildren().addAll(statusDisplay, instructions, hotbar);  
        }  
    }  
	public static void Main(String[] args) {

		launch(args);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int getTilesize() {
		return tileSize;
	}

	public static StackPane getRoot() {
		return root;

	}

	public static int getMapSize() {
		return MAP_SIZE;
	}

}
