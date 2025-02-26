package entity.character;

import world.map;

public abstract class BaseCharacter {
	private double posX ,posY;
	private int health;
	private int maxHealth;
	private int speed;
	private boolean death;
	private int attack;

	public BaseCharacter(double posX, double posY, int health, int speed, int attack) {
		setPosX(posX);
		setPosY(posY);
		maxHealth = health;
		setHealth(maxHealth);
		setAttack(attack);
		this.speed = speed;

		setDeath(false);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health > maxHealth) {
			this.health = maxHealth;
		} else if (health <= 0) {
			this.health = 0;
			setDeath(true);
		} else {
			this.health = health;
		}

	}
	
	public double getPosX() {
		return posX;
	}

	public void setPosX(double d) {
		if (d < 0)
			posX = 0;
		else if (d > map.getMapWidth()) {
			posX = map.getMapWidth();
		} else
			this.posX = d;

	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double d) {
		if (d < 0) {
			posY = 0;
		} else if (d > map.getMapHeight()) {
			posY = map.getMapHeight();
		} else {
			posY = d;
		}

	}

	public int getSpeed() {
		return speed;
	}

	public boolean isDeath() {
		return death;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

}
