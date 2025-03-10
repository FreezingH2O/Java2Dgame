
package entity.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import collision.EntityCollision;
import collision.WorldCollision;
import entity.effect.GameEffect;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import world.Map;

public abstract class BaseCharacter {
    // Constants to avoid magic numbers
    private static final int MIN_HEALTH = 0;
    private static final double MIN_POSITION = 0.0;

    // Private fields
    private int health;
    private int maxHealth;
    private double posX, posY;
    private int speed;
    private int size;
    // NOTE: According to naming conventions, boolean variables should use isXXX.
    // To comply, a new method isDead() has been added, while retaining the original 'death' variable.
    private boolean death;
    private int attack;
    protected List<GameEffect> effectsList = new ArrayList<>();
    protected Rectangle solidArea;
    protected WorldCollision wCollide;
    protected EntityCollision eCollide;
    protected Map map;
    private String name;

    // Constructor
    public BaseCharacter(String name, double posX, double posY, int health, int speed, int attack, int size, Map map) {
        // Initialize map and collision detection objects
        this.map = map;
        eCollide = new EntityCollision();
        // Set character properties using setter methods for proper encapsulation
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

    // Update method: processes game effects and collision detection for each frame
    public void update(GraphicsContext gc) {
        if (isDeath()) {
            return;
        }
        
        for (GameEffect effect : effectsList) {
            if (effect.isActive()) {
                effect.update();
                effect.render(gc);

                if (effect.checkCollision(eCollide.getTarget())) {
                    eCollide.getTarget().takeDamage(this.getAttack());
                    effect.deactivate();
                }
            }
        }
    }


    public abstract void attackTarget(BaseCharacter target);


    public int getHealth() {
        return health;
    }


    public void setHealth(int health) {
        if (health > maxHealth) {
            this.health = maxHealth;
        } else if (health <= MIN_HEALTH) { 
            this.health = MIN_HEALTH;
            setDeath(true);
            if (this instanceof HighMonster) {
                List<String> tmp = new ArrayList<>(HighMonster.getHighBossLi());
                tmp.remove(((HighMonster) this).getMonsterType() + "");
                System.out.println(tmp);
                HighMonster.setHighBossLi(tmp);
            }
            System.out.println(getName() + " is death");
            Platform.runLater(() -> {
                map.getEntities().remove(this);
            });
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
        if (d < MIN_POSITION) {
            posX = MIN_POSITION;
        } else if (d > map.getMapWidth()) {
            posX = map.getMapWidth();
        } else {
            this.posX = d;
        }
    }

    // Getter for posY
    public double getPosY() {
        return posY;
    }

    // Setter for posY: clamps value between MIN_POSITION and the map's maximum height.
    public void setPosY(double d) {
        if (d < MIN_POSITION) {
            posY = MIN_POSITION;
        } else if (d > map.getMapHeight()) {
            posY = map.getMapHeight();
        } else {
            posY = d;
        }
    }

    // Applies damage to the character by reducing health.
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    // Getter for speed
    public int getSpeed() {
        return speed;
    }

    // Checks if the character is dead.
    public boolean isDeath() {
        return death;
    }
    
    // New method following naming conventions for booleans.
    public boolean isDead() {
        return death;
    }

    // Setter for the death state.
    public void setDeath(boolean death) {
        this.death = death;
    }

    // Getter for maxHealth
    public int getMaxHealth() {
        return maxHealth;
    }

    // Setter for maxHealth: updates the max health and resets current health.
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        System.out.println("max health:" + maxHealth);
        setHealth(maxHealth);
    }

    // Getter for attack value
    public int getAttack() {
        return attack;
    }

    // Setter for attack value.
    public void setAttack(int attack) {
        this.attack = attack;
    }

    // Getter for name.
    public String getName() {
        return name;
    }

    // Setter for name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the solidArea used for collision detection.
    public Rectangle getSolidArea() {
        return solidArea;
    }

    // Setter for the solidArea.
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    
    // Overriding equals() for comparing BaseCharacter objects based on key attributes.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseCharacter other = (BaseCharacter) obj;
        return health == other.health &&
               maxHealth == other.maxHealth &&
               Double.compare(other.posX, posX) == 0 &&
               Double.compare(other.posY, posY) == 0 &&
               speed == other.speed &&
               size == other.size &&
               attack == other.attack &&
               death == other.death &&
               Objects.equals(name, other.name);
    }

    // Overriding toString() to provide a string representation of BaseCharacter objects.
    @Override
    public String toString() {
        return "BaseCharacter{" +
               "name='" + name + '\'' +
               ", health=" + health +
               ", maxHealth=" + maxHealth +
               ", posX=" + posX +
               ", posY=" + posY +
               ", speed=" + speed +
               ", size=" + size +
               ", attack=" + attack +
               ", death=" + death +
               '}';
    }
}

