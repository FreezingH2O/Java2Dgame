package entity.character;

import java.util.Random;
import collision.WorldCollision;
import components.Bar;
import entity.ElementType;
import entity.effect.GameEffect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import world.Map;

public abstract class BaseMonster extends BaseCharacter {
    private Random random = new Random();
    protected Image pic;
    private int dx, dy;
    private int moveDelay = 2000;
    private Timeline movementTimer;
    protected MonsterType monsterType;
    private ElementType elementType;
    private boolean frozen;
    private Bar healthBar;
    private boolean canAttack = true;
    private double newX;
    private double newY;
    // New field to force movement regardless of collision
    private boolean alwaysMove = true;

    public BaseMonster(MonsterType type, double posX, double posY, int health, int speed, int attack, int size,
            Map map) {
        super(type + " monster", posX, posY, health, speed, attack, size, map);
        this.monsterType = type;
        wCollide = new WorldCollision(map);
        startMoving();
        setFrozen(false);

        healthBar = new Bar(health, size, 8, Color.RED);

        elementType = (type == MonsterType.FINAL_BOSS || type == MonsterType.LOW_CLASS) ? entity.ElementType.NONE
                : entity.ElementType.valueOf(type.name());

        solidArea = new Rectangle(45, 75);
        solidArea.setX(8);
        solidArea.setY(16);
    }

    public void update(GraphicsContext gc) {

        if (isDeath()) {
            return;
        }
        super.update(gc);
        // Refactored rendering logic into a helper function.
        renderMonster(gc);
    }

    // Helper function that renders the monster's image and health bar.
    private void renderMonster(GraphicsContext gc) {
        gc.drawImage(pic, getPosX(), getPosY());
        healthBar.render(gc, getPosX(), getPosY() - 10, getHealth());
    }

    public void attackTarget(BaseCharacter target) {
        if (target == null)
            return;
        if (this instanceof HighMonster) {
            GameEffect fireball = new GameEffect(getElementType(), getPosX(), getPosY(), target.getPosX(),
                    target.getPosY(), 3, target);
            effectsList.add(fireball);
            
            //System.out.println(getElementType());
        } else {
            target.takeDamage(getAttack());
        }
    }

    private void moveRandomly(WorldCollision worldCollision) {
        this.newX = getPosX() + dx;
        this.newY = getPosY() + dy;

        if (!worldCollision.isCollide(newX, newY, solidArea) && !eCollide.isColliding(this, newX, newY)) {
            setPosX(newX);
            setPosY(newY);
        }
        
        // New block: force movement regardless of collisions if alwaysMove is true.
        if (alwaysMove) {
            setPosX(newX);
            setPosY(newY);
        }

        if (eCollide.isPlayerCollide() && canAttack && map.getEntities().contains(this)) {
            canAttack = false;
            attackTarget(eCollide.getTarget());
            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
                canAttack = true;
            }));
            delay.setCycleCount(1);
            delay.play();
        }
    }

    private void startMoving() {
        changeDirection();

        movementTimer = new Timeline(new KeyFrame(Duration.millis(500), e -> moveRandomly(wCollide)));
        movementTimer.setCycleCount(Timeline.INDEFINITE);
        movementTimer.play();

        Timeline directionChangeTimer = new Timeline(new KeyFrame(Duration.millis(moveDelay), e -> changeDirection()));
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

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
    
    // Getter and setter for alwaysMove
    public boolean isAlwaysMove() {
        return alwaysMove;
    }
    
    public void setAlwaysMove(boolean alwaysMove) {
        this.alwaysMove = alwaysMove;
    }
}
