// StartScreen.java (package: logic)  
package logic;  

import components.GameCanvas;  
import javafx.scene.canvas.GraphicsContext;  
import javafx.scene.paint.Color;  
import javafx.scene.text.Font;  
import javafx.scene.text.TextAlignment;  
import main.Main;  

public class StartScreen {  
    private GameManager gameManager;  
    private final int BUTTON_WIDTH = 200;  
    private final int BUTTON_HEIGHT = 50;  

    public StartScreen(GameManager gameManager) {  
        this.gameManager = gameManager;  
    }  

    public void draw(GraphicsContext gc) {  
        // Window dimensions  
        double windowWidth = Main.width;  
        double windowHeight = Main.height;  

        // Canvas dimensions  
        GameCanvas gameCanvas = gameManager.getCanvas();  
        double canvasWidth = gameCanvas.getWidth();  
        double canvasHeight = gameCanvas.getHeight();  

        // Calculate the offset to center the window within the canvas  
        double offsetX = (canvasWidth - windowWidth) / 2;  
        double offsetY = (canvasHeight - windowHeight) / 2;  

        // Clear the entire canvas (important for the "viewport" effect)  
        gc.clearRect(0, 0, canvasWidth, canvasHeight);  

        // Background for the visible window area only  
        gc.setFill(Color.DARKBLUE);  
        gc.fillRect(offsetX, offsetY, windowWidth, windowHeight);  

        // Game Title (centered within the window)  
        gc.setFill(Color.GOLD);  
        gc.setFont(new Font("Arial", 40));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("GameName", offsetX + windowWidth / 2, offsetY + windowHeight * 0.25);  

        // Game Information (centered within the window)  
        gc.setFill(Color.WHITE);  
        gc.setFont(new Font("Arial", 16));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Use WASD keys to move", offsetX + windowWidth / 2, offsetY + windowHeight * 0.4);  
        gc.fillText("Collect items to score points", offsetX + windowWidth / 2, offsetY + windowHeight * 0.45);  
        gc.fillText("Avoid enemies!", offsetX + windowWidth / 2, offsetY + windowHeight * 0.5);  

        // Start Button (centered within the window)  
        double buttonX = offsetX + (windowWidth - BUTTON_WIDTH) / 2;  
        double buttonY = offsetY + windowHeight * 0.6;  

        gc.setFill(Color.GREEN);  
        gc.fillRect(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);  

        gc.setFill(Color.BLACK);  
        gc.setFont(new Font("Arial", 20));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Start Game", offsetX + windowWidth / 2, buttonY + BUTTON_HEIGHT * 0.75);  

        // Additional information (optional, centered within the window)  
        gc.setFill(Color.LIGHTGREY);  
        gc.setFont(new Font("Arial", 12));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Created by [Your Name]", offsetX + windowWidth / 2, offsetY + windowHeight * 0.95);  
    }  

    public void handleInput(double x, double y) {  
        // Window dimensions  
        double windowWidth = Main.width;  
        double windowHeight = Main.height;  

        // Canvas dimensions  
        GameCanvas gameCanvas = gameManager.getCanvas();  
        double canvasWidth = gameCanvas.getWidth();  
        double canvasHeight = gameCanvas.getHeight();  

        // Calculate the offset to center the window within the canvas  
        double offsetX = (canvasWidth - windowWidth) / 2;  
        double offsetY = (canvasHeight - windowHeight) / 2;  

        //  REMOVED the adjustment of input coordinates.  The x,y are already relative to the window.  
        double adjustedX = x;  
        double adjustedY = y;  

        // Recalculate button position (within the window)  
        double buttonX = (windowWidth - BUTTON_WIDTH) / 2;  
        double buttonY = windowHeight * 0.6;  

        // Check if the click was within the Start button's bounds (relative to the window)  
        if (adjustedX >= buttonX && adjustedX <= buttonX + BUTTON_WIDTH &&  
            adjustedY >= buttonY && adjustedY <= buttonY + BUTTON_HEIGHT) {  
        	gameManager.startGame();
            gameManager.setGameState(GameState.PLAYING);  
        } else {  
            System.out.println("Click outside the button"); //Added this line  
            System.out.println("AdjustedX: " + adjustedX + ", adjustedY: " + adjustedY);  //Added this line  
            System.out.println("ButtonX: " + buttonX + ", ButtonY: " + buttonY); //Added this line  
        }  
    }  
}  