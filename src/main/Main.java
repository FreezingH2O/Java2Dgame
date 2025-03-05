package main;

import components.GameCanvas;
import components.Hotbar;
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
	private static final int col = 21, row = 13;
	private static StackPane root;
	public static final int MAP_SIZE = 80;
	public static final int width = col * tileSize, height = row * tileSize;

	public void start(Stage stage) {

		GameCanvas canvas = new GameCanvas();
		StatusDisplay statusDisplay = StatusDisplay.getInstant();
		Instruction instructions = Instruction.getInstant();
		Hotbar hotbar = new Hotbar();

		root = new StackPane(canvas, statusDisplay, instructions, hotbar);

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
