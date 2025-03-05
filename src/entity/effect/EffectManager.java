package entity.effect;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class EffectManager extends Thread {
    private static EffectManager instance;
    private CopyOnWriteArrayList<GameEffect> effects = new CopyOnWriteArrayList<>();
    private boolean running = true;

    private EffectManager() {
        start();
    }

    public static EffectManager getInstance() {
        if (instance == null) {
            instance = new EffectManager();
        }
        return instance;
    }

    public void addEffect(GameEffect effect) {
        effects.add(effect);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(16); // ~60 FPS update rate
                updateEffects(0.016); // Simulating deltaTime of 16ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateEffects(double deltaTime) {
        for (GameEffect effect : effects) {
            effect.update(deltaTime);
            if (!effect.isActive()) {
                effects.remove(effect);
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (GameEffect effect : effects) {
            effect.render(gc);
        }
    }

    public void stopManager() {
        running = false;
    }
}


