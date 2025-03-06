package entity.effect;

import entity.character.BaseCharacter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fireball extends GameEffect {

    private double speedX, speedY;
    private BaseCharacter target;

    public Fireball(double startX, double startY, double targetX, double targetY, double speed, BaseCharacter target) {
        super(startX, startY, 5.0); // 5 seconds as duration, for example
        this.target = target;

        double deltaX = targetX - startX;
        double deltaY = targetY - startY;
        double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Normalize and scale by speed
        this.speedX = (deltaX / length) * speed;
        this.speedY = (deltaY / length) * speed;

        this.effectImage = new Image("item/star.png");  // Assuming fireball image path
    }

    @Override
    public void update(double deltaTime) {
        x += speedX * deltaTime;
        y += speedY * deltaTime;
        timeElapsed += deltaTime;

        if (timeElapsed >= duration) {
            isActive = false; // Deactivate after a certain duration
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isActive) {
            gc.drawImage(effectImage, x, y, 32, 32); // Draw fireball
        }
    }

    // Collision detection is inherited from GameEffect
    @Override
    public boolean checkCollision(BaseCharacter target) {
        return super.checkCollision(target);
    }
}
