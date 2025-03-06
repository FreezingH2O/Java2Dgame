package entity.character;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import collision.WorldCollision;
import components.Bar;
import entity.ElementType;
import entity.effect.EffectManager;
import entity.effect.Fireball;
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
			// System.out.println(getName() + " is death");
			return;
		}

		gc.drawImage(pic, getPosX(), getPosY());
		healthBar.render(gc, getPosX(), getPosY() - 10, getHealth());

		// Ensure collision detection updates the target
		boolean colliding = eCollide.isColliding(this, getPosX(), getPosY());
		boolean playerNearby = eCollide.isPlayerCollide();

		if (playerNearby) {
			 System.out.println(getName() + " detects the player!");

			BaseCharacter target = eCollide.getTarget();
			Fireball fireball = new Fireball(getPosX(), getPosY(), target.getPosX(), target.getPosY(), 200.0, target);
			EffectManager.getInstance().addEffect(fireball);
			System.out.println(fireball.isCollison());
			
			if (canAttack && map.getEntities().contains(this)) {
			canAttack = false;
			attackTarget(eCollide.getTarget());

			Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
				canAttack = true;
				// System.out.println("Attack cooldown finished! You can attack again.");
			}));
			delay.setCycleCount(1);
			delay.play();
		}

		}
	}

	private void moveRandomly(WorldCollision worldCollision) {
		this.newX = getPosX() + dx;
		this.newY = getPosY() + dy;

		if (!worldCollision.isCollide(newX, newY, solidArea) && !eCollide.isColliding(this, newX, newY)) {
			setPosX(newX);
			setPosY(newY);
		}

//		if (eCollide.isPlayerCollide() && canAttack && map.getEntities().contains(this)) {
//			canAttack = false;
//			attackTarget(eCollide.getTarget());
//
//			Timeline delay = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
//				canAttack = true;
//				// System.out.println("Attack cooldown finished! You can attack again.");
//			}));
//			delay.setCycleCount(1);
//			delay.play();
//		}

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

}
