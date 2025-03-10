package logic;  

import entity.character.Player;
import javafx.geometry.Pos;  
import javafx.scene.control.Button;  
import javafx.scene.layout.VBox;  
import main.Main; // Assuming Main has width and height properties.  
import logic.GameManager; // Assuming GameManager and GameState exist  
import logic.GameState;  
import javafx.scene.text.Font; // Import Font  
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text; // Import Text  

public class EndScreen {  

    private VBox endScreenLayout;  
    private Button restartButton;  
    private Button exitButton;  
    private GameManager gameManager;  

    private final double BUTTON_WIDTH = 150; // or gp.tileSize * 3  
    private final double BUTTON_HEIGHT = 40; // or gp.tileSize  

    public EndScreen(GameManager gameManager) {  
        this.gameManager = gameManager;  

        endScreenLayout = new VBox(10);  
        endScreenLayout.setAlignment(Pos.CENTER);  
        endScreenLayout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Semi-transparent black  

        // Game Over Text  
        Text gameOverText = new Text("Game Over");  
        gameOverText.setFont(Font.font("Cloister Black", FontWeight.BOLD, 60)); 
        gameOverText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;"); // White fill with black outline  

        restartButton = new Button("Respawn");  
        restartButton.setFont(Font.font("Cloister Black")); 
        restartButton.setPrefWidth(BUTTON_WIDTH);  
        restartButton.setPrefHeight(BUTTON_HEIGHT);  
        restartButton.setOnAction(e -> { // Set action for restart button  
            System.out.println("Restart button clicked (setOnAction)");  
            gameManager.restartGame();  
            gameManager.setGameState(GameState.PLAYING);  
            Main.updateVisibility(); // Update visibility after restart  
        });  

        exitButton = new Button("Quit");  
        exitButton.setFont(Font.font("Cloister Black")); 
        exitButton.setPrefWidth(BUTTON_WIDTH);  
        exitButton.setPrefHeight(BUTTON_HEIGHT);  
        exitButton.setOnAction(e -> { // Set action for exit button  
            System.out.println("Exit button clicked (setOnAction)");  
            System.exit(0);  
        });  

        endScreenLayout.getChildren().addAll(gameOverText, restartButton, exitButton);  
    }  

    public VBox getEndScreenLayout() {  
        return endScreenLayout;  
    }  
}  