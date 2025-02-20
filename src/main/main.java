package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class main extends Application {
	public static Scene scene;
	private static final int tileSize = 48;
	private static final int col = 21, row = 15;
	
	public  static final int width = col*tileSize, height = row *tileSize;
	public void start(Stage stage) {

		gameCanvas canvas = new gameCanvas();
		StackPane root = new StackPane(canvas);
		scene = new Scene(root);
		
		KeyboardController keyboard = new KeyboardController();
		scene.setOnKeyPressed(e -> keyboard.handleKeyPress(e.getCode(), true));
		scene.setOnKeyReleased(e -> keyboard.handleKeyPress(e.getCode(), false));
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("THE DESTINED ONE");
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	
	

}
