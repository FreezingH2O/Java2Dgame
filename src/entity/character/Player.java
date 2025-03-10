

package entity.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import collision.WorldCollision;
import components.Hotbar;
import components.Instruction;
import components.StatusDisplay;
import entity.ElementType;
import entity.effect.GameEffect;
import entity.effect.arrow;
import entity.item.KeyItem;
import entity.weapon.Bow;
import entity.weapon.BaseWeapon;
import entity.weapon.Sword;
import entity.weapon.Wand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import logic.GameManager;
import logic.KeyboardController;
import logic.Sound;
import world.Camera;
import world.Map;
import world.MapType;

public class Player extends BaseCharacter {

    private static final int TILE_SIZE = 72;
    private static final int DEFAULT_SOLID_WIDTH = 28;
    private static final int DEFAULT_SOLID_HEIGHT = 28;
    private static final int DEFAULT_SOLID_OFFSET_X = 8;
    private static final int DEFAULT_SOLID_OFFSET_Y = 16;
    private static final int WEAPON_SOLID_WIDTH = 45;
    private static final int WEAPON_SOLID_HEIGHT = 45;
    private static final int WEAPON_SOLID_OFFSET_X = 20;
    private static final int WEAPON_SOLID_OFFSET_Y = 30;
    

    private Image pic;
    private boolean xCheck, yCheck ;
    private KeyboardController keyboard;
    private Camera camera;
    private int mana, maxMana;
    private ArrayList<KeyItem> keyItemList;
    private ArrayList<BaseWeapon> weaponList;
    private BaseWeapon heldWeapon;
    private static Player instant;
    private static HashMap<String, Image> images;

    // Constructor
    public Player(double posX, double posY, int speed, int health, int mana, int attack, int size, Map map) {
        super("Player", posX, posY, health, speed, attack, size, map);
        // instant = new Player(posX, posY, speed, health, mana, attack, size, map);
        setMana(mana);
        setMaxMana(mana);
        setMaxHealth(health);
        keyItemList = new ArrayList<KeyItem>();
        weaponList = new ArrayList<BaseWeapon>();
        setHeldWeapon(null);
        weaponList.add(new Sword("sword", 10, 10, 10, ElementType.FIRE));
        weaponList.add(new Wand(5));
        weaponList.add(new Bow());
        Hotbar.updateSlot(0, weaponList.get(0));
        images = new HashMap<>();

        // Load images into the map
        images.put("up", new Image(getClass().getResource("/player/up.png").toExternalForm()));
        images.put("down1", new Image(getClass().getResource("/player/down1.png").toExternalForm()));
        images.put("down2", new Image(getClass().getResource("/player/down2.png").toExternalForm()));
        images.put("left1", new Image(getClass().getResource("/player/left1.png").toExternalForm()));
        images.put("left2", new Image(getClass().getResource("/player/left2.png").toExternalForm()));
        images.put("right1", new Image(getClass().getResource("/player/right1.png").toExternalForm()));
        images.put("right2", new Image(getClass().getResource("/player/right2.png").toExternalForm()));
        images.put("still", new Image(getClass().getResource("/player/still.png").toExternalForm()));

        images.put("axe00", new Image("player/axe00.png"));
        images.put("sword00", new Image("player/sword00.png"));
        images.put("bow00", new Image("player/bow1.gif"));
        images.put("bow01", new Image("player/bow2.gif"));
        images.put("wand00", new Image("player/wand00.gif"));
        images.put("wand01", new Image("player/wand01.png"));

        pic = images.get("still");

        xCheck = true;
        yCheck = true;

        // Initialize default solid area for collision
        solidArea = new Rectangle(DEFAULT_SOLID_WIDTH, DEFAULT_SOLID_HEIGHT);
        solidArea.setX(DEFAULT_SOLID_OFFSET_X);
        solidArea.setY(DEFAULT_SOLID_OFFSET_Y);

        wCollide = new WorldCollision(map);
        camera = new Camera(map);
        keyboard = new KeyboardController();
    }

    // Override attackTarget: handles different weapon types and applies corresponding effects.
    public void attackTarget(BaseCharacter target) {
        if (heldWeapon != null) {
            if (heldWeapon instanceof Wand) {
                GameEffect effect = new GameEffect(ElementType.MAGIC, getPosX(), getPosY(),
                        target.getPosX(), target.getPosY(), 1, target);
                effectsList.add(effect);

                setMana(mana - ((Wand) heldWeapon).getManaCost());
                StatusDisplay.getInstant().useMana(((Wand) heldWeapon).getManaCost());
            } else if (heldWeapon instanceof Bow) {
                GameEffect effect = new arrow(ElementType.NONE, getPosX(), getPosY(),
                        target.getPosX(), target.getPosY(), 1, target);
                effectsList.add(effect);
            }
            target.takeDamage(heldWeapon.getDamage());
        } else {
            target.takeDamage(getAttack());
        }
        AudioClip hitMonster_sound = Sound.getHitmonster_sound();
        hitMonster_sound.play();
        System.out.println(getName() + " attack " + target.getName());
    }

    // The update() method has been refactored to be shorter by calling helper functions.
    public void update(GraphicsContext gc) {
        int speed = getSpeed();
        xCheck = (getPosX() % TILE_SIZE == 0) ? !xCheck : xCheck;
        yCheck = (getPosY() % TILE_SIZE == 0) ? !yCheck : yCheck;
        
        // Update movement and player image based on keyboard input.
        double[] newPos = updateMovementAndPic(speed);
        double x = newPos[0];
        double y = newPos[1];
        
        // Update the solidArea depending on whether a weapon is held.
        updateSolidArea();
        
        // Update position if no collision is detected.
        updatePosition(x, y);
        
        // Attack target if a BaseMonster is in range and space is pressed.
        if (eCollide.getTarget() instanceof BaseMonster && keyboard.isSpacePressed()) {
            attackTarget(eCollide.getTarget());
        }
        
        // Update instruction text and handle map changes.
        updateInstructions();
        
        gc.drawImage(pic, getPosX(), getPosY());
        camera.cameraMove(getPosX(), getPosY());
        
        // Update hotbar with current weapons.
        updateHotbar();
        
        // Call the superclass update to process game effects.
        super.update(gc);
    }
    
    // Helper function to update movement and set the proper image based on keyboard input.
    private double[] updateMovementAndPic(int speed) {
        double x = getPosX();
        double y = getPosY();
        if (keyboard.isUpPressed()) {
            y = getPosY() - speed;
            setPic(images.get("up"));
        } else if (keyboard.isDownPressed()) {
            y = getPosY() + speed;
            setPic(images.get(yCheck ? "down1" : "down2"));
        } else if (keyboard.isLeftPressed()) {
            x = getPosX() - speed;
            setPic(images.get(xCheck ? "left1" : "left2"));
        } else if (keyboard.isRightPressed()) {
            x = getPosX() + speed;
            setPic(images.get(xCheck ? "right1" : "right2"));
        } else {
            setPic(images.get("still")); // Default idle state
        }
        return new double[]{x, y};
    }
    
    // Helper function to update the collision solid area based on whether a weapon is held.
    private void updateSolidArea() {
        if (heldWeapon != null) {
            setPic(images.get(heldWeapon.getName() + "00"));
            solidArea = new Rectangle(WEAPON_SOLID_WIDTH, WEAPON_SOLID_HEIGHT);
            solidArea.setX(WEAPON_SOLID_OFFSET_X);
            solidArea.setY(WEAPON_SOLID_OFFSET_Y);
        } else {
            solidArea = new Rectangle(DEFAULT_SOLID_WIDTH, DEFAULT_SOLID_HEIGHT);
            solidArea.setX(DEFAULT_SOLID_OFFSET_X);
            solidArea.setY(DEFAULT_SOLID_OFFSET_Y);
        }
    }
    
    // Helper function to update the player's position if no collision is detected.
    private void updatePosition(double x, double y) {
        if (!wCollide.isCollide(x, y, solidArea) && !eCollide.isColliding(this, x, y)) {
            setPosX(x);
            setPosY(y);
        }
    }
    
    // Helper function to update instructions and handle map changes based on the moving area tile.
    private void updateInstructions() {
        if (wCollide.isMovingArea()) {
            int tile = wCollide.getMovetile();
            if (tile == 88) {
                Instruction.getInstant().updateText("Press E to enter the Dungeon");
            } else if (tile == 81) {
                Instruction.getInstant().updateText("Press E to enter to getout");
            } else if (tile == 82) {
                if (HighMonster.getHighBossLi().size() != 0) {
                    Instruction.getInstant().updateText("You need to collect all of the lialic to open the boss room");
                } else {
                    Instruction.getInstant().updateText("Press E to enter the Bossroom");
                }
            } else if (tile == 83) {
                Instruction.getInstant().updateText("Press E to enter the House");
            }
            if (keyboard.isActionPressed()) {
            	AudioClip dooropen_sound = Sound.getdooropen_sound();
            	AudioClip island_sound = Sound.getIslandSound();
                AudioClip dungeon_sound = Sound.getDungeon_sound();
                AudioClip bossroom_sound = Sound.getBossroom_sound();
                AudioClip door_sound = Sound.getdooropen_sound();
                door_sound.setVolume(1.0);
                door_sound.play();
                
                if (tile == 88) {
                    map.changeMap(MapType.DUNGEON);
                    island_sound.stop();
                    dungeon_sound.setCycleCount(AudioClip.INDEFINITE);
                    dungeon_sound.setVolume(1.0);
                    dungeon_sound.play();
                    
                } else if (tile == 81) {
                    map.changeMap(MapType.ISLAND);
                    dungeon_sound.stop();
                    island_sound.setCycleCount(AudioClip.INDEFINITE);
                    island_sound.setVolume(0.3);
                    island_sound.play();
                   
                } else if (tile == 82) {
                    if (HighMonster.getHighBossLi().size() == 0) {
                        map.changeMap(MapType.BOSSROOM);
                        dungeon_sound.stop();
                        bossroom_sound.setCycleCount(AudioClip.INDEFINITE);
                        bossroom_sound.setVolume(0.3);
                        bossroom_sound.play();
                    }
                } else if (tile == 83) {
                    map.changeMap(MapType.HOUSE);
                }
            }
        } else {
            Instruction.getInstant().updateText("");
        }
    }
    
    // Helper function to update the hotbar with the current weapon list.
    private void updateHotbar() {
        for (int i = 0; i < weaponList.size(); ++i) {
            Hotbar.updateSlot(i, weaponList.get(i));
        }
    }

    // Override takeDamage to also update the StatusDisplay.
    public void takeDamage(int damage) {
        int tmp = getHealth();
        StatusDisplay.takeDamage(damage);
        super.takeDamage(damage);
        System.out.println(tmp + "->" + getHealth());
    }

    // Adds a key item to the player's key item list.
    public void addKeyItem(KeyItem item) {
        keyItemList.add(item);
    }

    public ArrayList<BaseWeapon> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(ArrayList<BaseWeapon> weaponList) {
        this.weaponList = weaponList;
    }

    public BaseWeapon getHeldWeapon() {
        return heldWeapon;
    }

    public void setHeldWeapon(BaseWeapon heldWeapon) {
        this.heldWeapon = heldWeapon;
        if (heldWeapon == null) {
            StatusDisplay.updateEquipment(new Image("player/still.png"));
            return;
        } else {
            StatusDisplay.updateEquipment(heldWeapon.getPic());
        }
    }

    public ArrayList<KeyItem> getKeyItemList() {
        return keyItemList;
    }

    public void setKeyItemList(ArrayList<KeyItem> keyItemList) {
        this.keyItemList = keyItemList;
    }

    public void setPic(Image pic) {
        this.pic = pic;
    }

    public Image getPic() {
        return pic;
    }

    public int getMana() {
        return mana;
    }

    // Setter for mana, ensuring it stays between 0 and maxMana.
    public void setMana(int mana) {
        if (mana > maxMana) {
            this.mana = maxMana;
        } else if (mana < 0) {
            this.mana = 0;
        } else {
            this.mana = mana;
        }
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public static Player getInstant() {
        return instant;
    }

    public static void setInstant(Player instant) {
        Player.instant = instant;
    }
    
    // Overriding equals() for comparing Player objects based on key attributes.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        return mana == other.mana &&
               maxMana == other.maxMana &&
               Objects.equals(getName(), other.getName()) &&
               Double.compare(getPosX(), other.getPosX()) == 0 &&
               Double.compare(getPosY(), other.getPosY()) == 0;
    }

    // Overriding toString() to provide a string representation of the Player object.
    @Override
    public String toString() {
        return "Player{" +
               "name='" + getName() + '\'' +
               ", posX=" + getPosX() +
               ", posY=" + getPosY() +
               ", mana=" + mana +
               ", maxMana=" + maxMana +
               '}';
    }
}