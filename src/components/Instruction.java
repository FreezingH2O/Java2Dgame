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
        

        instructionLabel.setFont(Font.font("Cloister Black", FontWeight.BOLD, 20)); 
 
        
        instructionLabel.setTextFill(Color.WHITE);
        instructionLabel.setAlignment(Pos.CENTER); 


        background = new Rectangle();
        background.setArcWidth(20);
        background.setArcHeight(20);
        background.setFill(Color.rgb(0, 0, 0, 0.5)); 

        DropShadow shadow = new DropShadow(5, 5, 5, Color.rgb(0, 0, 0, 0.7));
        background.setEffect(shadow);


        background.widthProperty().bind(instructionLabel.widthProperty().add(20)); 

        background.heightProperty().bind(instructionLabel.heightProperty().add(10)); 

     
        instructionLabel.setPadding(new javafx.geometry.Insets(5));
background.setTranslateX(-8);
background.setTranslateY(-5);
 
        this.getChildren().addAll(background, instructionLabel);
        
   
        this.setTranslateX(main.Main.width / 2 - 80); 
        this.setTranslateY(main.Main.height / 2 - 80); 
        
        
    }

    public void updateText(String newText) {
        instructionLabel.setText(newText);
        if (newText.isBlank()) {
            background.setOpacity(0); 
        } else {
            background.setOpacity(1); 
        }
    }

    public static Instruction getInstant() {
        return instant;
    }
}
