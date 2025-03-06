package components;

import entity.character.Player;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class StatusDisplay extends Pane {
	private static Bar healthBar;
	private static Bar manaBar;
	private static Bar expBar;
	private static int level;
	private static int experience;
	private static int[] experienceToNextLevel = { 30, 60, 100, 150, 210, 280, 360, 450, 550 };
	private static Label levelUI;
	private static ImageView cir; 
	private static ImageView overlayImageView; 
	public static StatusDisplay instant = new StatusDisplay();

	public StatusDisplay() {
		int maxHealth = 200;
		int maxMana = 100;
		healthBar = new Bar(maxHealth, maxHealth, 20, Color.RED);
		manaBar = new Bar(maxMana, maxMana, 20, Color.CYAN);
		level = 0;
		experience = 0;
		expBar = new Bar(experienceToNextLevel[level], 200, 10, Color.GREEN);
		expBar.setCurrentStat(0);

		Image circleImage = new Image("circle.png");
		cir = new ImageView(circleImage);
		cir.setFitWidth(120);
		cir.setFitHeight(120);

		// Drop shadow effect
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.rgb(100, 50, 10, 0.7));
		shadow.setRadius(10);
		shadow.setOffsetX(5);
		shadow.setOffsetY(5);
		cir.setEffect(shadow);

	
		overlayImageView = new ImageView();
		overlayImageView.setFitWidth(70);
		overlayImageView.setFitHeight(70);

		StackPane imageContainer = new StackPane(cir, overlayImageView);
		imageContainer.setTranslateX(-90);
		imageContainer.setTranslateY(-25);

		levelUI = new Label("Level " + level);
		levelUI.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
		levelUI.setPrefWidth(80);
		levelUI.setPrefHeight(32);
		levelUI.setAlignment(Pos.CENTER);
		levelUI.setTextAlignment(TextAlignment.CENTER);
		levelUI.setStyle("-fx-background-image: url('levelUIbg.png'); -fx-background-size: cover;");

		levelUI.setTranslateX(30);
		levelUI.setTranslateY(-25);

		expBar.setTranslateX(15);
		expBar.setTranslateY(10);

		healthBar.setTranslateX(15);
		healthBar.setTranslateY(23);

		manaBar.setTranslateX(15);
		manaBar.setTranslateY(45);

		this.getChildren().addAll(healthBar, manaBar, expBar, imageContainer, levelUI);

		this.setTranslateX(120);
		this.setTranslateY(50);

	}

	public static void updateEquipment(Image newImage) {
		overlayImageView.setImage(newImage);
	}

	public static void takeDamage(int damage) {
		healthBar.updateBar(healthBar.getCurrentStat() - damage);
	}

	public static void heal(int amount) {
		healthBar.updateBar(Math.min(healthBar.getCurrentStat() + amount,healthBar.getMaxStat()));
	}

	public static void useMana(int amount) {
		manaBar.updateBar(manaBar.getCurrentStat() - amount);
	}

	public static void regainMana(int amount) {
		manaBar.updateBar(Math.min(manaBar.getCurrentStat() + amount,manaBar.getMaxStat()));
	}

	public static void gainExperience(int amount) {
		experience += amount;
		System.out.println("exp: " + experience);
		expBar.setCurrentStat(experience);
		if (level >= 8)
			return;
		while (experience >= experienceToNextLevel[level]) {
			levelUp();
		}
	}

	private static void levelUp() {
		experience -= experienceToNextLevel[level];
		level++;

		healthBar.setMaxStat(healthBar.getMaxStat() + 50);
		healthBar.setbgWidth(healthBar.getMaxStat());
		healthBar.setCurrentStat(healthBar.getMaxStat());
		Player.getInstant().setMaxHealth(healthBar.getMaxStat());

		manaBar.setMaxStat(manaBar.getMaxStat() + 25);
		manaBar.setbgWidth(manaBar.getMaxStat());
		manaBar.setCurrentStat(manaBar.getMaxStat());

		expBar.setMaxStat(experienceToNextLevel[level]);
		expBar.setCurrentStat(experience);

		levelUI.setText("Level " + level);

		System.out.println("Level Up! New Level: " + level);

	}

	public static void displayStatus(GraphicsContext gc, double x, double y) {
		System.out.println("Level: " + level);
		System.out.println("XP: " + experience + "/" + experienceToNextLevel);
	}

	public static StatusDisplay getInstant() {
		return instant;
	}

}
