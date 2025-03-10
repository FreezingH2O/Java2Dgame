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

public class WinScreen {  

    private VBox endScreenLayout;  
    private Button exitButton;  
    private GameManager gameManager;  

    private final double BUTTON_WIDTH = 150; // or gp.tileSize * 3  
    private final double BUTTON_HEIGHT = 40; // or gp.tileSize  

    public WinScreen(GameManager gameManager) {  
        this.gameManager = gameManager;  

        endScreenLayout = new VBox(10);  
        endScreenLayout.setAlignment(Pos.CENTER);  
        endScreenLayout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Semi-transparent black  

        Text gameOverText = new Text("Congratulations");  
        gameOverText.setFont(Font.font("Cloister Black", FontWeight.BOLD, 60)); 
        gameOverText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;"); // White fill with black outline  

        exitButton = new Button("Quit");  
        exitButton.setFont(Font.font("Cloister Black")); 
        exitButton.setPrefWidth(BUTTON_WIDTH);  
        exitButton.setPrefHeight(BUTTON_HEIGHT);  
        exitButton.setOnAction(e -> { // Set action for exit button  
            System.out.println("Exit button clicked (setOnAction)");  
            System.exit(0);  
        });  

        endScreenLayout.getChildren().addAll(gameOverText, exitButton);  
    }  

    public VBox getEndScreenLayout() {  
        return endScreenLayout;  
    }  
}  