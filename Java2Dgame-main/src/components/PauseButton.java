package components;  

import javafx.geometry.Pos;
import javafx.scene.Cursor;  
import javafx.scene.image.Image;  
import javafx.scene.image.ImageView;  
import javafx.scene.layout.StackPane;
import main.Main;  

public class PauseButton extends StackPane {  

    private boolean isPaused = false;  
    private ImageView iconView;  
    private Image pauseIcon;  
    private Image resumeIcon;  
    private double size; //size of the images.  

    public PauseButton(double size) {  
        this.size = size;  

        try {  
            pauseIcon = new Image("pause.png"); 
            resumeIcon = new Image("pause.png");
        } catch (Exception e) {  
            System.err.println("Error loading images: " + e.getMessage());  
            return;  
        }  

        iconView = new ImageView(pauseIcon);  
        iconView.setFitWidth(size);  
        iconView.setFitHeight(size);  
        
//        iconView.setTranslateX(Main.getWidth() - 10); 
//        iconView.setTranslateY(10);

        setAlignment(Pos.CENTER);  
        getChildren().add(iconView);  
        
        System.out.println("Pause button added to scene");

        //Hover Effect (optional)  
//        setOnMouseEntered(e -> setCursor(Cursor.HAND));  
//        setOnMouseExited(e -> setCursor(Cursor.DEFAULT));  


        setOnMouseClicked(e -> {  
            isPaused = !isPaused;  
            updateIcon();  
            // Call your game pause/resume logic here (e.g., gameMasnager.pauseGame())  
            System.out.println("Pause button clicked. Paused: " + isPaused);  
        });  
    }  

    private void updateIcon() {  
        if (isPaused) {  
            iconView.setImage(resumeIcon);  
        } else {  
            iconView.setImage(pauseIcon);  
        }  
    }  

    public boolean isPaused() {  
        return isPaused;  
    }  

    public void setPaused(boolean paused) {  
        isPaused = paused;  
        updateIcon();  
    }  
}  