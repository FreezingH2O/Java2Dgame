package components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bar extends Pane {
	private int maxStat;
	private int currentStat;
	private Rectangle bar;
	private double width;
	private double height;
	private Color color;

	public Bar(int maxStat, double width, double height, Color color) {
		this.maxStat = maxStat;
		this.currentStat = maxStat;
		this.width = width;
		this.height = height;
		this.color = color;

		Rectangle background = new Rectangle(width, height);
		background.setFill(Color.GRAY);  
		background.setStroke(Color.rgb(220,117,22)); 
		background.setStrokeWidth(5); 
		background.setArcWidth(20);  
		background.setArcHeight(20);


		bar = new Rectangle(width, height);
		bar.setFill(color); 
		bar.setStroke(Color.rgb(220,117,22)); 
		bar.setStrokeWidth(1.2); 
		bar.setArcWidth(20);  
		bar.setArcHeight(20);


		this.getChildren().addAll(background, bar);
	}

	public void updateBar(int newStat) {
		setCurrentStat(newStat);
		System.out.println(newStat);

	}

	public void render(GraphicsContext gc, double x, double y,int  health) {
		gc.setFill(Color.GRAY);
		gc.fillRect(x, y, width, height);

		gc.setFill(color);
		gc.fillRect(x, y, health * width / maxStat, height);
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
		bar.setWidth(currentStat * width / maxStat);
	}
}
