package logic;

import javafx.geometry.Pos;  
import javafx.scene.control.Button;  
import javafx.scene.layout.VBox;  
import main.Main; // Assuming Main has width and height properties.  
import logic.GameManager; // Assuming GameManager and GameState exist  
import logic.GameState;  
import javafx.scene.text.Font; // Import Font  
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
        gameOverText.setFont(Font.font("Verdana", 50)); // Adjust font as needed  
        gameOverText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 2;"); // White fill with black outline  

        restartButton = new Button("Retry");  
        restartButton.setPrefWidth(BUTTON_WIDTH);  
        restartButton.setPrefHeight(BUTTON_HEIGHT);  

        exitButton = new Button("Quit");  
        exitButton.setPrefWidth(BUTTON_WIDTH);  
        exitButton.setPrefHeight(BUTTON_HEIGHT);  

        endScreenLayout.getChildren().addAll(gameOverText, restartButton, exitButton);  
    }  

    public VBox getEndScreenLayout() {  
        return endScreenLayout;  
    }  

    public void handleInput(double x, double y) {  
        double windowWidth = Main.getWidth();  
        double windowHeight = Main.getHeight();  

        // Button positions - adjust these based on your layout and desired spacing  
        double restartButtonX = (windowWidth - BUTTON_WIDTH) / 2;  
        double restartButtonY = windowHeight * 0.4;  

        double exitButtonX = (windowWidth - BUTTON_WIDTH) / 2;  
        double exitButtonY = windowHeight * 0.6;  

        if (x >= restartButtonX && x <= restartButtonX + BUTTON_WIDTH &&  
            y >= restartButtonY && y <= restartButtonY + BUTTON_HEIGHT) {  
            System.out.println("Restart button clicked (handleInput)");  
            gameManager.restartGame();  
            gameManager.setGameState(GameState.PLAYING);  // Make sure the game goes back to playing!  

        } else if (x >= exitButtonX && x <= exitButtonX + BUTTON_WIDTH &&  
                   y >= exitButtonY && y <= exitButtonY + BUTTON_HEIGHT) {  
            System.out.println("Exit button clicked (handleInput)");  
            System.exit(0);  
        } else {  
            System.out.println("Click outside buttons");  
            System.out.println("X: " + x + ", Y: " + y);  
        }  
    }  
}  