package entity.character;

import java.util.Random;
import collision.WorldCollision;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import world.map;

public abstract class BaseMonster extends BaseCharacter {
    private Random random = new Random();
    protected Image pic;
    private int dx, dy; 
    private int moveDelay = 2000; 
    private Timeline movementTimer;

    public BaseMonster(double posX, double posY, int health, int speed, int attack, int size, map map) {
        super(posX, posY, health, speed, attack, size,map);
        wCollide = new WorldCollision(map);
        startMoving();
    }

    public abstract void attackTarget(BaseCharacter target);

    public void update(GraphicsContext gc) {
        gc.drawImage(pic, getPosX(), getPosY());
    }


    private void moveRandomly(WorldCollision worldCollision) {
        double newX = getPosX() + dx;
        double newY = getPosY() + dy;

        if (!worldCollision.isCollide(newX, newY, getSize())&& !eCollide.isColliding(this, newX, newY)) {
            setPosX(newX);
            setPosY(newY);
        }
        
        if(eCollide.isColliding(this, newX, newY)&&eCollide.isPlayerCollide()){
        	attackTarget(eCollide.getTarget());
        }
    }


    private void startMoving() {

        changeDirection();  

       
        movementTimer = new Timeline(
            new KeyFrame(Duration.millis(500), e -> moveRandomly(wCollide)) 
        );

   
        movementTimer.setCycleCount(Timeline.INDEFINITE);
        movementTimer.play();

 
        Timeline directionChangeTimer = new Timeline(
            new KeyFrame(Duration.millis(moveDelay), e -> changeDirection())
        );
        directionChangeTimer.setCycleCount(Timeline.INDEFINITE);
        directionChangeTimer.play();
    }

   
    private void changeDirection() {
  
        dx = (random.nextInt(3) - 1) * getSpeed();
        dy = (random.nextInt(3) - 1) * getSpeed();
    }

    public void stopMoving() {
        if (movementTimer != null) {
            movementTimer.stop();
        }
    }
}



