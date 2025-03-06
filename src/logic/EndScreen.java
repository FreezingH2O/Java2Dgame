package logic;  

import components.GameCanvas;  
import javafx.scene.canvas.GraphicsContext;  
import javafx.scene.paint.Color;  
import javafx.scene.text.Font;  
import javafx.scene.text.TextAlignment;  
import main.Main;  

public class EndScreen {  
    private GameManager gameManager;  
    private final int BUTTON_WIDTH = 200;  
    private final int BUTTON_HEIGHT = 50;  

    public EndScreen(GameManager gameManager) {  
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
        gc.setFill(Color.DARKRED);  
        gc.fillRect(offsetX, offsetY, windowWidth, windowHeight);  

        // Game Over Title (centered within the window)  
        gc.setFill(Color.GOLD);  
        gc.setFont(new Font("Arial", 40));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Game Over", offsetX + windowWidth / 2, offsetY + windowHeight * 0.25);  

        // Game Score (centered within the window)  
        gc.setFill(Color.WHITE);  
        gc.setFont(new Font("Arial", 20));  
        gc.setTextAlign(TextAlignment.CENTER);  
//        gc.fillText("Your Score: " + gameManager.getScore(), offsetX + windowWidth / 2, offsetY + windowHeight * 0.4); // Assuming you have a getScore() method  

        // Restart Button (centered within the window)  
        double buttonX = offsetX + (windowWidth - BUTTON_WIDTH) / 2;  
        double buttonY = offsetY + windowHeight * 0.6;  

        gc.setFill(Color.GREEN);  
        gc.fillRect(buttonX, buttonY, BUTTON_WIDTH, BUTTON_HEIGHT);  

        gc.setFill(Color.BLACK);  
        gc.setFont(new Font("Arial", 20));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Restart Game", offsetX + windowWidth / 2, buttonY + BUTTON_HEIGHT * 0.75);  

        // Back to Menu Button (centered within the window)  
        double menuButtonX = offsetX + (windowWidth - BUTTON_WIDTH) / 2;  
        double menuButtonY = offsetY + windowHeight * 0.75;  

        gc.setFill(Color.BLUE);  
        gc.fillRect(menuButtonX, menuButtonY, BUTTON_WIDTH, BUTTON_HEIGHT);  

        gc.setFill(Color.WHITE);  
        gc.setFont(new Font("Arial", 20));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Back to Menu", offsetX + windowWidth / 2, menuButtonY + BUTTON_HEIGHT * 0.75);  

        // Additional information (optional, centered within the window)  
        gc.setFill(Color.LIGHTGREY);  
        gc.setFont(new Font("Arial", 12));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Thanks for Playing!", offsetX + windowWidth / 2, offsetY + windowHeight * 0.95);  
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

        double adjustedX = x;  
        double adjustedY = y;  

        // Recalculate button position (within the window)  
        double buttonX = (windowWidth - BUTTON_WIDTH) / 2;  
        double buttonY = windowHeight * 0.6;  

        // Check if the click was within the Restart button's bounds (relative to the window)  
        if (adjustedX >= buttonX && adjustedX <= buttonX + BUTTON_WIDTH &&  
                adjustedY >= buttonY && adjustedY <= buttonY + BUTTON_HEIGHT) {  
            gameManager.reset(); // Implement restartGame() in GameManager  
            gameManager.setGameState(GameState.PLAYING);  
            System.out.println("The restart button is clicked");  
        } else {  
            //Back to menu button  
            double menuButtonX = (windowWidth - BUTTON_WIDTH) / 2;  
            double menuButtonY = windowHeight * 0.75;  

            if (adjustedX >= menuButtonX && adjustedX <= menuButtonX + BUTTON_WIDTH &&  
                    adjustedY >= menuButtonY && adjustedY <= menuButtonY + BUTTON_HEIGHT) {  
                gameManager.setGameState(GameState.START_SCREEN);  
                System.out.println("The back to menu button is clicked");  
            } else {  
                System.out.println("Click outside the button");  
                System.out.println("AdjustedX: " + adjustedX + ", adjustedY: " + adjustedY);  
                System.out.println("ButtonX: " + buttonX + ", ButtonY: " + buttonY);  
            }  
        }  
    }  
}  