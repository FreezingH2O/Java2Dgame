package entity.character;

import collision.EntityCollision;
import collision.WorldCollision;
import javafx.scene.canvas.GraphicsContext;
import world.map;

public abstract class BaseCharacter {
	private double posX, posY;
	private int size;
	private int health;
	private int maxHealth;
	private int speed;
	private boolean death;
	private int attack;
	protected WorldCollision wCollide;
	protected static EntityCollision eCollide;
	protected map map;
	private String name;

	public BaseCharacter(String name, double posX, double posY, int health, int speed, int attack, int size, map map) {
		this.map = map;
		eCollide = new EntityCollision(map.getEntities());
		
		setName(name);
		maxHealth = health;
		setPosX(posX);
		setPosY(posY);
		setHealth(maxHealth);
		setAttack(attack);
		this.speed = speed;
		this.size = size;
		setDeath(false);
		
	}

	public abstract void update(GraphicsContext gc);
	
	public void attackTarget(BaseCharacter target) {
	       target.takeDamage(getAttack());
	       System.out.println(getName()+" attack "+ target.getName());
	         
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

	public int getSize() {
		return size;
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

	public void takeDamage(int damage) {
		setHealth(getHealth() - damage);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
