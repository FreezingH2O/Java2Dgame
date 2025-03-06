package entity.effect;

import entity.character.BaseCharacter;
import entity.character.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fireball extends GameEffect {

	private Image fireballImage;
	private double speedX, speedY;
	private boolean collison;
	private BaseCharacter target;

	public Fireball(double startX, double startY, double targetX, double targetY, double speed, BaseCharacter target) {
		super(startX, startY, 1);
		this.target = target;
		// Calculate direction vector (normalized)
		double deltaX = targetX - startX;
		double deltaY = targetY - startY;
		double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Distance

		// Normalize and scale by speed
		this.speedX = (deltaX / length) * speed;
		this.speedY = (deltaY / length) * speed;

		this.fireballImage = new Image("item/star.png");
	}

	@Override
	public void update(double deltaTime) {
		x += speedX * deltaTime;
		y += speedY * deltaTime;
		timeElapsed += deltaTime;

		setCollision(this, target);

		if (timeElapsed >= duration) {
			isActive = false;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if (isActive) {
			gc.drawImage(fireballImage, x, y, 32, 32);
		}
	}

	public void setCollision(Fireball fireball, BaseCharacter target) {

		Rectangle2D fireballBound = new Rectangle2D(getX()-16, getY()-16, 32, 32);

		Rectangle2D targetBound = new Rectangle2D(target.getPosX()-24, target.getPosY()-24, target.getSolidArea().getWidth(),
				target.getSolidArea().getWidth());


		//System.out.println(fireballBound.intersects(targetBound));
		this.collison = fireballBound.intersects(targetBound);
	}

	public Image getFireballImage() {
		return fireballImage;
	}

	public void setFireballImage(Image fireballImage) {
		this.fireballImage = fireballImage;
	}

	public boolean isCollison() {
		System.out.println(getX() + " " + getY());
		System.out.println(Player.getInstant().getPosX() + " vv" + Player.getInstant().getPosY());
		return collison;
	}

	public void setCollison(boolean collison) {
		this.collison = collison;
	}

}
