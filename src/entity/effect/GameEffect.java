package entity.effect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameEffect {
    protected String name;
    private Rectangle effectBound;
    private Image effectImg;
    private double speedX;
    private double speedY;
    private boolean isActive;


    // Constructor to specify the target position (x, y) and the speed of the fireball
    public GameEffect(double x, double y, double targetX, double targetY, double speed) {
        this.effectBound = new Rectangle(x, y, 20, 10);  
        this.effectBound.setFill(Color.RED);  // Placeholder for effect shape, will be replaced by image
        this.isActive = true;
      //  this.damage = damage;  // Set the damage of the effect (could be based on character's attack)

        // Calculate the direction towards the target
        double deltaX = targetX - x;
        double deltaY = targetY - y;

        // Calculate the length (distance) of the vector
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Normalize the direction (ensure the fireball moves at a constant speed)
        this.speedX = (deltaX / distance) * speed;
        this.speedY = (deltaY / distance) * speed;

        // Set the effect image (use any image for the fire effect here)
        setEffectImg(new Image("item/star.png"));  // Replace with fire image path
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getEffectBound() {
        return effectBound;
    }

    public void setEffectBound(Rectangle effectBound) {
        this.effectBound = effectBound;
    }

    public Image getEffectImg() {
        return effectImg;
    }

    public void setEffectImg(Image effectImg) {
        this.effectImg = effectImg;
    }


    // Update the position of the effect
    public void update() {
        if (isActive) {
            effectBound.setX(effectBound.getX() + speedX);  // Move the effect horizontally
            effectBound.setY(effectBound.getY() + speedY);  // Move the effect vertically
        }
    }

    // Collision check with target (Rectangle)
    public boolean collidesWith(Rectangle target) {
        return effectBound.getBoundsInParent().intersects(target.getBoundsInParent()); 
    }

    // Deactivate the effect
    public void deactivate() {
        isActive = false;
    }

    // Render the effect (draw the effect image on the GraphicsContext)
    public void render(GraphicsContext gc) {
        if (isActive) {
            gc.drawImage(effectImg, effectBound.getX(), effectBound.getY());  // Draw the image at the current position
        }
    }


}
