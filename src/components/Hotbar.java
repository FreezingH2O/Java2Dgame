package components;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Hotbar extends HBox {
	private Button[] hotbarSlots;

	public Hotbar() {

		setSpacing(10);
		setPrefHeight(100);

		this.setFocusTraversable(false);

		hotbarSlots = new Button[10];
		for (int i = 0; i < hotbarSlots.length; i++) {
			hotbarSlots[i] = new Button("Slot " + (i + 1));
			hotbarSlots[i].setStyle("-fx-background-color: gray; -fx-text-fill: white;");
			hotbarSlots[i].setMinWidth(0);
			hotbarSlots[i].setFocusTraversable(false);
			final int index = i;
			hotbarSlots[i].setOnMouseClicked(e -> {
				System.out.println("Slot " + (index + 1) + " clicked");
			});

			this.getChildren().add(hotbarSlots[i]);
		}
		this.setTranslateX(160);
		this.setTranslateY(600);

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
