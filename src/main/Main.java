package main;

import components.GameCanvas;
import components.Instruction;
import components.StatusDisplay;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;


import javafx.stage.Stage;
import logic.KeyboardController;

public class Main extends Application {
	public static Scene scene;
	private static final int tileSize = 48;
	private static final int col = 21, row = 15;
	private static StackPane root;
	public static final int MAP_SIZE = 50;
	public  static final int width = col*tileSize, height = row *tileSize;
	public void start(Stage stage) {
		
		GameCanvas canvas = new GameCanvas(); 
		StatusDisplay statusDisplay = new StatusDisplay(200, 100); 
        Instruction instructions = Instruction.getInstant(); 

	    root = new StackPane(canvas, statusDisplay,instructions); 

	    scene = new Scene(root);
	    
	    
		KeyboardController keyboard = new KeyboardController();
		scene.setOnKeyPressed(e -> keyboard.handleKeyPress(e.getCode(), true));
		scene.setOnKeyReleased(e -> keyboard.handleKeyPress(e.getCode(), false));
		
		stage.setScene(scene);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setResizable(false);
		stage.setTitle("THE DESTINED ONE");
		stage.show();
	}

	public static void main(String[] args) {

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
