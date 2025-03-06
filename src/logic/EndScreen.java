// EndScreen.java  
package logic;  

import javafx.geometry.Pos;  
import javafx.scene.control.Button;  
import javafx.scene.control.Label;  
import javafx.scene.layout.VBox;  
import javafx.scene.text.Font;  
import main.Main; // Import Main  

public class EndScreen {  

    private GameManager gameManager;  
    private VBox endScreenLayout;  // Changed from Stage to VBox  

    public EndScreen(GameManager gameManager) {  
        this.gameManager = gameManager;  

        Label gameOverLabel = new Label("Game Over!");  
        gameOverLabel.setFont(Font.font("Arial", 48));  

        Button restartButton = new Button("Restart");  
        restartButton.setOnAction(e -> {  
            gameManager.restartGame();  
        });  

        Button exitButton = new Button("Exit");  
        exitButton.setOnAction(e -> {  
            // Handle exit logic here (e.g., close the game)  
            System.exit(0); // Or platform.exit()  
        });  

        endScreenLayout = new VBox(20, gameOverLabel, restartButton, exitButton);  
        endScreenLayout.setAlignment(Pos.CENTER);  
        endScreenLayout.setPrefWidth(Main.getWidth()); // Set width based on Main's width  
        endScreenLayout.setPrefHeight(Main.getHeight()); // Set height based on Main's height  
        endScreenLayout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);"); // Darkened background  
    }  

    public VBox getEndScreenLayout() {  // Return the layout  
        return endScreenLayout;  
    }  

    public void handleInput(double x, double y) {  
        // No need to handle input directly here since the buttons have their own actions  
    }  

    public void show() {  
        // Set the game state to END_SCREEN, which will trigger the visibility update  
        gameManager.setGameState(GameState.END_SCREEN);  
        Main.updateVisibility(); //Manually call updateVisibility  

    }  
}  