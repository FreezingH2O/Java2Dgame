package entity.effect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fireball extends GameEffect {
	
	private Image fireballImage;
    private double speedX, speedY;

	public Fireball(double x, double y, double speedX, double speedY) {
		super(x, y, 2);
		this.speedX = speedX;
		this.speedY = speedY;
        this.fireballImage = new Image(getClass().getResourceAsStream("/fireball.png")); 
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		x += speedX * deltaTime;
        y += speedY * deltaTime;
        timeElapsed += deltaTime;

        if (timeElapsed >= duration) {
            isActive = false;
        }
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (isActive) {
            gc.drawImage(fireballImage, x, y, 32, 32);
        }
	}

}
