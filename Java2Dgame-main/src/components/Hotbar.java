package components;


import entity.character.Player;
import entity.item.BaseItem;
import entity.item.HealthPotion;
import entity.item.ManaPotion;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hotbar extends HBox {
	private static Button[] hotbarSlots;
	private static HealthPotion healthPotion;
	private static ManaPotion manaPotion;
	
	public Hotbar() {

		this.setFocusTraversable(false);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setPadding(new Insets(15));
		this.setSpacing(0);
		
		healthPotion = new  HealthPotion(50);
		manaPotion= new ManaPotion(50);
		
		hotbarSlots = new Button[10];
		for (int i = 0; i < hotbarSlots.length; i++) {

			Rectangle bg = new Rectangle(48, 48);
			bg.setFill(Color.WHEAT);
			bg.setArcWidth(10);
			bg.setArcHeight(10);

			ImageView itemImage = new ImageView();
			itemImage.setImage(null); 
			itemImage.setFitWidth(38);
			itemImage.setFitHeight(38);

			ImageView borderImage = new ImageView(new Image("itemBorder.png"));

			StackPane slotContent = new StackPane();
			slotContent.setPadding(Insets.EMPTY);
			slotContent.setAlignment(Pos.CENTER);
			slotContent.getChildren().addAll(borderImage, bg, itemImage);

			hotbarSlots[i] = new Button();
			hotbarSlots[i].setGraphic(slotContent);
			hotbarSlots[i].setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
			hotbarSlots[i].setFocusTraversable(false);
			hotbarSlots[i].setPadding(Insets.EMPTY);

			final int index = i;
			hotbarSlots[i].setOnMouseClicked(e -> {
				System.out.println("Slot " + (index + 1) + " clicked");
				if(index==8) {
					healthPotion.use(Player.getInstant());
					
				}else if(index==9) {
					manaPotion.use(Player.getInstant());
				}
				else if(Player.getInstant().getWeaponList().size()<=index) {
					Player.getInstant().setHeldWeapon(null);
				
				}
				else Player.getInstant().setHeldWeapon(Player.getInstant().getWeaponList().get(index));

			});

			this.getChildren().add(hotbarSlots[i]);
		}
		
		updateSlot(8, healthPotion);
		updateSlot(9, manaPotion);

	}

	public static void updateSlot(int slotIndex, BaseItem item) {
		Platform.runLater(()->{
		if (slotIndex >= 0 && slotIndex < hotbarSlots.length) {
			
			Rectangle bg = new Rectangle(48, 48);
			bg.setFill(Color.WHEAT);
			bg.setArcWidth(10);
			bg.setArcHeight(10);
			
			ImageView itemImage = new ImageView(item.getPic());
			itemImage.setFitWidth(38);
			itemImage.setFitHeight(38);

			ImageView borderImage = new ImageView(new Image("itemBorder.png"));

			StackPane slotContent = new StackPane();
			slotContent.setPadding(Insets.EMPTY);
			slotContent.setAlignment(Pos.CENTER);
			slotContent.getChildren().addAll(borderImage, bg, itemImage);
			
			hotbarSlots[slotIndex].setGraphic(slotContent);
		}
		});
	}

	public Button getSlotButton(int index) {
		if (index >= 0 && index < hotbarSlots.length) {
			return hotbarSlots[index];
		}
		return null;
	}
}
