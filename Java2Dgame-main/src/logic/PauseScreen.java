package logic;  

import javafx.scene.canvas.GraphicsContext;  
import javafx.scene.paint.Color;  
import javafx.scene.text.Font;  
import javafx.scene.text.TextAlignment;  

public class PauseScreen {  
    private GameManager gameManager;  

    public PauseScreen(GameManager gameManager) {  
        this.gameManager = gameManager;  
    }  

    public void draw(GraphicsContext gc) {  
        gc.setFill(Color.GREY); // A slightly less jarring background color than bright red/black  
        gc.fillRect(0, 0, 800, 600);  

        gc.setFill(Color.WHITE);  
        gc.setFont(new Font("Arial", 30));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Game Paused", 400, 200);  

        gc.setFill(Color.YELLOW); // Button Color  
        gc.fillRect(300, 350, 200, 50); // Button rectangle  

        gc.setFill(Color.BLACK);  
        gc.setFont(new Font("Arial", 20));  
        gc.fillText("Resume", 400, 380); // Button text.  

		gc.setFill(Color.YELLOW); // Button Color  
		gc.fillRect(300, 450, 200, 50); // Button rectangle  

		gc.setFill(Color.BLACK);  
		gc.setFont(new Font("Arial", 20));  
		gc.fillText("Main Menu", 400, 480); // Button text.  
    }  

    public void handleInput(double x, double y) {  
        // Basic Resume Functionality  

		if (x >= 300 && x <= 500 && y >= 350 && y <= 400) {  
			gameManager.setGameState(GameState.PLAYING); // Go back to playing  
		} else if (x >= 300 && x <= 500 && y >= 450 && y <= 500) {  
			gameManager.setGameState(GameState.START_SCREEN);  
		}  
        
    }  
}  