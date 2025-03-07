
package entity.effect;

import entity.ElementType;
import entity.character.BaseCharacter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameEffect {
    protected double x;
    protected double y; 
    protected double speedX;
    protected double speedY; 
    protected boolean isActive; 
    private double speed; 
    protected Image pic;
    private ElementType type;
    private BaseCharacter target;
    
    public GameEffect(ElementType type, double startX, double startY, double targetX, double targetY, double speed, BaseCharacter target) {
        this.x = startX;
        this.y = startY;
        this.speed = 2; speed = 2;
        this.isActive = true;
        this.target = target;
       // System.out.println("skill/" + type + ".gif");
        this.type = type;
        if (!(this instanceof arrow)) {
            this.pic = new Image("skill/" + type + ".gif");
        }
        
        double dx = targetX - startX;
        double dy = targetY - startY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance != 0) {
            this.speedX = (dx / distance) * speed;
            this.speedY = (dy / distance) * speed;
        } else {
            this.speedX = 0;
            this.speedY = 0;
        }
    }
    
    public void update() {
        if (!isActive) return;
        
        x += speedX;
        y += speedY;
        
        if (isTargetFarAway()) {
            isActive = false; 
        }
    }
    
    private boolean isTargetFarAway() {
        // Use helper function to compute distance between current position and target.
        double distance = computeDistance(x, y, target.getPosX(), target.getPosY());
        final double MAX_DISTANCE = 500.0;
        return distance > MAX_DISTANCE; 
    }
    
    // Helper function to compute the Euclidean distance between two points.
    private double computeDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    
    public void render(GraphicsContext gc) {
        if (!isActive) return;
        
        double scaleFactor = 0.8; 
        double resizedWidth = pic.getWidth() * scaleFactor;
        double resizedHeight = pic.getHeight() * scaleFactor;
        
        gc.save();
        gc.translate(x, y);
        applyRotationIfNeeded(gc);
        gc.drawImage(pic, -resizedWidth / 1.5, -resizedHeight / 1.5, resizedWidth, resizedHeight);
        gc.restore();
    }
    
    // Helper function to apply rotation to the graphics context if the effect's type requires it.
    private void applyRotationIfNeeded(GraphicsContext gc) {
        if (type != ElementType.WIND && type != ElementType.DARK) {
            double angle = Math.atan2(speedY, speedX);
            gc.rotate(Math.toDegrees(angle));
        }
    }
    
    public boolean checkCollision(BaseCharacter player) {
        if (player == null) return false;
        double distance = Math.sqrt(Math.pow(player.getPosX() - x, 2) + Math.pow(player.getPosY() - y, 2));
        return distance < 10; 
    }
    
    public void deactivate() {
        isActive = false;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    // Getter and Setter methods

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Image getPic() {
        return pic;
    }

    public void setPic(Image pic) {
        this.pic = pic;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public BaseCharacter getTarget() {
        return target;
    }

    public void setTarget(BaseCharacter target) {
        this.target = target;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
