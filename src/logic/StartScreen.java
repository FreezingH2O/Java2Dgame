package logic;  

import components.GameCanvas;  
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
        double windowWidth = Main.width;  
        double windowHeight = Main.height;  

        GameCanvas gameCanvas = gameManager.getCanvas();  
        double canvasWidth = gameCanvas.getWidth();  
        double canvasHeight = gameCanvas.getHeight();  

        double offsetX = (canvasWidth - windowWidth) / 2;  
        double offsetY = (canvasHeight - windowHeight) / 2;  

        gc.clearRect(0, 0, canvasWidth, canvasHeight);  
        
        Image backgroundImage = new Image("startScreen.png");
        gc.drawImage(backgroundImage, offsetX, offsetY, windowWidth, windowHeight);

        double buttonX = offsetX + (windowWidth - BUTTON_WIDTH) / 2;  
        double buttonY = offsetY + windowHeight * 0.6;  
    }  

    public void handleInput(double x, double y) {  
        // Window dimensions  
        double windowWidth = Main.width;  
        double windowHeight = Main.height;  

        GameCanvas gameCanvas = gameManager.getCanvas();  
        double canvasWidth = gameCanvas.getWidth();  
        double canvasHeight = gameCanvas.getHeight();  

        double offsetX = (canvasWidth - windowWidth) / 2;  
        double offsetY = (canvasHeight - windowHeight) / 2;  

        double adjustedX = x;  
        double adjustedY = y;  

        double buttonX = (windowWidth - BUTTON_WIDTH) / 2;  
        double buttonY = windowHeight * 0.6;  

        if (adjustedX >= buttonX && adjustedX <= buttonX + BUTTON_WIDTH &&  
            adjustedY >= buttonY && adjustedY <= buttonY + BUTTON_HEIGHT) {  
        	gameManager.startGame();
            gameManager.setGameState(GameState.PLAYING);  
            System.out.println("The start button is clicked");
        } else {  
            System.out.println("Click outside the button");   
            System.out.println("AdjustedX: " + adjustedX + ", adjustedY: " + adjustedY);
            System.out.println("ButtonX: " + buttonX + ", ButtonY: " + buttonY);
        }  
    }  
}  