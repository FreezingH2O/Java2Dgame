package entity.effect;

import entity.character.BaseCharacter;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameEffect {

    protected double x, y;
    protected boolean isActive;
    protected double timeElapsed;
    protected double duration;
    protected Image effectImage;

    public GameEffect(double startX, double startY, double duration) {
        this.x = startX;
        this.y = startY;
        this.duration = duration;
        this.isActive = true;
        this.timeElapsed = 0;
    }

    // Abstract method for subclasses to implement their specific update logic
    public abstract void update(double deltaTime);

    public abstract void render(GraphicsContext gc);

    // Collision detection method
    public boolean checkCollision(BaseCharacter target) {
        Rectangle2D effectBound = new Rectangle2D(x - 16, y - 16, 32, 32);
        Rectangle2D targetBound = new Rectangle2D(target.getPosX() - 24, target.getPosY() - 24,
                target.getSolidArea().getWidth(), target.getSolidArea().getHeight());

        return effectBound.intersects(targetBound);
    }

    public boolean isActive() {
        return isActive;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Debugging method
    public void debugTargetPosition(BaseCharacter target) {
        System.out.println("Effect Position: (" + x + ", " + y + ")");
        System.out.println("Target Position: (" + target.getPosX() + ", " + target.getPosY() + ")");
    }
}
