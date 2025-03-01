package components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Instruction extends Pane {

	public static Instruction instant = new Instruction();
	private Label instructionLabel;
	private Rectangle background;

	public Instruction() {
	
		instructionLabel = new Label(""); 
		instructionLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
		instructionLabel.setTextFill(Color.WHITE);

	
		instructionLabel.setAlignment(Pos.CENTER); 

	
		background = new Rectangle(180, 40); 
		background.setArcWidth(20); 
		background.setArcHeight(20); 
		background.setFill(Color.SADDLEBROWN); 

		DropShadow shadow = new DropShadow(5, 5, 5, Color.rgb(0, 0, 0, 0.7));

		background.setEffect(shadow);
		
		instructionLabel.prefWidthProperty().bind(background.widthProperty());
		instructionLabel.prefHeightProperty().bind(background.heightProperty());


		this.getChildren().addAll(background, instructionLabel);

		this.setTranslateX(main.Main.width / 2 - 80);
		this.setTranslateY(main.Main.height / 2 - 80);

	}



	public void updateText(String newText) {
		instructionLabel.setText(newText);
		if (newText.isBlank()) {
			background.setOpacity(0);
		} else {
			background.setOpacity(100);
		}
	}

	public static Instruction getInstant() {
		return instant;
	}
}
