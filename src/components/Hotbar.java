package components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hotbar extends HBox {
	private Button[] hotbarSlots;

	public Hotbar() {

		this.setFocusTraversable(false);

		hotbarSlots = new Button[10];
		for (int i = 0; i < hotbarSlots.length; i++) {
			
			Rectangle bg = new Rectangle(48, 48);
			bg.setFill(Color.WHITE);
			bg.setArcWidth(10);
			bg.setArcHeight(10);
			
			ImageView itemImage = new ImageView(new Image("Swordcute.png")); // Change this to your item image
			itemImage.setFitWidth(38);
			itemImage.setFitHeight(38);
			
			ImageView borderImage = new ImageView(new Image("itemBorder.png"));

			StackPane slotContent = new StackPane();
			slotContent.getChildren().addAll(borderImage, bg, itemImage);

			hotbarSlots[i] = new Button();
			hotbarSlots[i].setGraphic(slotContent);
			hotbarSlots[i].setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
			hotbarSlots[i].setFocusTraversable(false);

			final int index = i;
			hotbarSlots[i].setOnMouseClicked(e -> {
				System.out.println("Slot " + (index + 1) + " clicked");
			});

			this.getChildren().add(hotbarSlots[i]);
		}
		this.setTranslateX(100);
		this.setTranslateY(main.Main.getHeight()-120);

	}

	public void updateSlot(int slotIndex, String itemName) {
		if (slotIndex >= 0 && slotIndex < hotbarSlots.length) {
			hotbarSlots[slotIndex].setText(itemName);
		}
	}

	public Button getSlotButton(int index) {
		if (index >= 0 && index < hotbarSlots.length) {
			return hotbarSlots[index];
		}
		return null;
	}
}
