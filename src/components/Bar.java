package components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Bar extends Pane {
	private int maxStat;
	private int currentStat;
	private Rectangle bar;
	private double width;
	private double height;

	public Bar(int maxStat, double width, double height, Color color) {
		this.maxStat = maxStat;
		this.currentStat = maxStat;
		this.width = width;
		this.height = height;

		Rectangle background = new Rectangle(width, height);
		background.setFill(Color.GRAY);

		bar = new Rectangle(width, height);
		bar.setFill(color);

		this.getChildren().addAll(background, bar);
	}

	public void updateBar(int newStat) {
			setCurrentStat(newStat);
			System.out.println(newStat);


	}

	public int getMaxStat() {
		return maxStat;
	}

	public void setMaxStat(int maxStat) {
		this.maxStat = maxStat;
	}

	public int getCurrentStat() {
		return currentStat;
	}

	public void setCurrentStat(int currentStat) {
		this.currentStat = currentStat;
		bar.setWidth(currentStat);
	}
}
