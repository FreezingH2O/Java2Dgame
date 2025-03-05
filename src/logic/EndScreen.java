// EndScreen.java (logic package)  
package logic;  

import javafx.scene.canvas.GraphicsContext;  
import javafx.scene.paint.Color;  
import javafx.scene.text.Font;  
import javafx.scene.text.TextAlignment;  

public class EndScreen {  
    private GameManager gameManager;  

    public EndScreen(GameManager gameManager) {  
        this.gameManager = gameManager;  
    }  

    public void draw(GraphicsContext gc) {  
        gc.setFill(Color.RED);  
        gc.fillRect(0, 0, 800, 600);  

        gc.setFill(Color.WHITE);  
        gc.setFont(new Font("Arial", 30));  
        gc.setTextAlign(TextAlignment.CENTER);  
        gc.fillText("Game Over!", 400, 200);  

        gc.setFill(Color.GREEN);  // Button color  
        gc.fillRect(300, 400, 200, 50); // Approximate button rectangle  

        gc.setFill(Color.BLACK);  
        gc.setFont(new Font("Arial", 20));  
        gc.fillText("New Game", 400, 430); // Centered text in button  
    }  

    public void handleInput() {  
        // Simple logic:  If ANY click happens during END_SCREEN, restart the game.  
        gameManager.setGameState(GameState.PLAYING);  
        gameManager.reset();  
    }  
}  