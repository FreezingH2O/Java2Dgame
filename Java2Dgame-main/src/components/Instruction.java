package components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Instruction extends Pane {

    public static Instruction instant = new Instruction();
    private Label instructionLabel;
    private Rectangle background;

    public Instruction() {
        instructionLabel = new Label("");
        
        // Set a custom "soul-like" font, default to a fallback font if not found
        instructionLabel.setFont(Font.font("Cloister Black", FontWeight.BOLD, 20)); 
        // Fallback font
        // instructionLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        instructionLabel.setTextFill(Color.WHITE);
        instructionLabel.setAlignment(Pos.CENTER); // Ensures the text is centered

        // Create the background rectangle with rounded corners
        background = new Rectangle();
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(Color.rgb(0, 0, 0, 0.5)); // Semi-transparent black color

        DropShadow shadow = new DropShadow(5, 5, 5, Color.rgb(0, 0, 0, 0.7));
        background.setEffect(shadow);

        // Bind background width to label width with padding (10px on each side)
        background.widthProperty().bind(instructionLabel.widthProperty().add(20)); 
        // Bind background height to label height with padding (5px vertically)
        background.heightProperty().bind(instructionLabel.heightProperty().add(10)); 

        // Apply padding to the label to ensure space inside the background
        instructionLabel.setPadding(new javafx.geometry.Insets(5));
background.setTranslateX(-8);
background.setTranslateY(-5);
        // Center the background and label within the pane
        this.getChildren().addAll(background, instructionLabel);
        
        // Centering this Pane on the screen (positioned in the middle of the screen)
        this.setTranslateX(main.Main.width / 2 - 80); 
        this.setTranslateY(main.Main.height / 2 - 80); 
        
        
    }

    public void updateText(String newText) {
        instructionLabel.setText(newText);
        if (newText.isBlank()) {
            background.setOpacity(0); // Hide the background if text is empty
        } else {
            background.setOpacity(1); // Show the background when there's text
        }
    }

    public static Instruction getInstant() {
        return instant;
    }
}
