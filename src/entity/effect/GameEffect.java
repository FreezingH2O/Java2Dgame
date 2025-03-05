package entity.effect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameEffect {
    protected double x, y;
    protected double duration, timeElapsed;
    protected boolean isActive;

    public GameEffect(double x, double y, double duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        this.timeElapsed = 0;
        this.isActive = true;
    }

    public abstract void update(double deltaTime);
    public abstract void render(GraphicsContext gc);

    public boolean isActive() {
        return isActive;
    }
}
