package components;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class StatusDisplay extends Pane {
	private static Bar healthBar;
	private static Bar manaBar;
	private static Bar expBar;
	private static int level;
	private static int experience;
	private static int[] experienceToNextLevel = { 30, 60, 100, 150, 210, 280, 360,450 ,550 };
	private static Label levelUI;
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

        ImageView cir = new ImageView(circleImage);


		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.rgb(100, 50, 10, 0.7)); // Dark brown shadow with some transparency
		shadow.setRadius(10); // Spread of the shadow
		shadow.setOffsetX(5); // Horizontal shadow offset
		shadow.setOffsetY(5); // Vertical shadow offset

		// Apply the shadow to the circle
		cir.setEffect(shadow);
		cir.setTranslateX(-80);
		cir.setTranslateY(-20);



		levelUI = new Label("Level " + level);
		levelUI.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
		levelUI.setPrefWidth(80); // Adjust width
		levelUI.setPrefHeight(32);
		levelUI.setAlignment(Pos.CENTER);
		levelUI.setTextAlignment(TextAlignment.CENTER);
		levelUI.setStyle("-fx-background-image: url('levelUIbg.png'); -fx-background-size: cover;");

		levelUI.setTranslateX(45);
		levelUI.setTranslateY(-20);

		expBar.setTranslateX(30);
		expBar.setTranslateY(15);

		healthBar.setTranslateX(30);
		healthBar.setTranslateY(30);

		manaBar.setTranslateX(30);
		manaBar.setTranslateY(50);

		this.getChildren().addAll(healthBar, manaBar, expBar, cir, levelUI);

		this.setTranslateX(120);
		this.setTranslateY(50);

	}

	public static void takeDamage(int damage) {
		healthBar.updateBar(healthBar.getCurrentStat() - damage);
	}

	public static void heal(int amount) {
		healthBar.updateBar(healthBar.getCurrentStat() + amount);
	}

	public static void useMana(int amount) {
		healthBar.updateBar(healthBar.getCurrentStat() - amount);
	}

	public static void regainMana(int amount) {
		healthBar.updateBar(healthBar.getCurrentStat() + amount);
	}

	public static void gainExperience(int amount) {
		experience += amount;
		System.out.println("exp: " + experience);
		System.out.println("exp width: " + expBar.getWidth());
		expBar.setCurrentStat(experience);
		if(level>=8)return;
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
