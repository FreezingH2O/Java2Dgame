package entity.character;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;

import collision.WorldCollision;
import components.Hotbar;
import components.Instruction;
import components.StatusDisplay;
import entity.ElementType;
import entity.item.KeyItem;
import entity.weapon.BaseWeapon;
import entity.weapon.Sword;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import logic.KeyboardController;
import world.Camera;
import world.Map;
import world.MapType;

public class Player extends BaseCharacter {
	private Image pic;
	private static Image up, down1, down2, left1, left2, right1, right2, still;
	private boolean xCheck, yCheck;
	private KeyboardController keyboard;
	private Camera camera;
	private int mana, maxMana;
	private ArrayList<KeyItem> keyItemList;
	private ArrayList<BaseWeapon> weaponList;
	private BaseWeapon heldWeapon;
	 private static Player instant ;

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
		Hotbar.updateSlot(0,weaponList.get(0));

		up = new Image("player/up.png");
		down1 = new Image("player/down1.png");
		down2 = new Image("player/down2.png");
		left1 = new Image("player/left1.png");
		left2 = new Image("player/left2.png");
		right1 = new Image("player/right1.png");
		right2 = new Image("player/right2.png");
		still = new Image("player/still.png");

		xCheck = true;
		yCheck = true;
		pic = still;

	 solidArea = new Rectangle(28, 28);
		solidArea.setX(8);
		solidArea.setY(16);

		wCollide = new WorldCollision(map);
		camera = new Camera(map);
		keyboard = new KeyboardController();
	}
	
	public void attackTarget(BaseCharacter target) {
	       target.takeDamage(getAttack());
	       System.out.println(getName()+" attack "+ target.getName());
	         
	    }

	public void update(GraphicsContext gc) {
		int speed = this.getSpeed();
		xCheck = (getPosX() % 72 == 0) ? !xCheck : xCheck;
		yCheck = (getPosY() % 72 == 0) ? !yCheck : yCheck;
		// System.out.println(posY);

		double y = getPosY(), x = getPosX();

		if (keyboard.isUpPressed()) {
			y = getPosY() - speed;
			this.pic = up;

		} else if (keyboard.isDownPressed()) {
			y = getPosY() + speed;
			this.pic = (yCheck) ? down1 : down2;

		} else if (keyboard.isLeftPressed()) {
			x = getPosX() - speed;
			this.pic = (xCheck) ? left1 : left2;

		} else if (keyboard.isRightPressed()) {
			x = getPosX() + speed;
			this.pic = (xCheck) ? right1 : right2;

		} else {
			this.pic = still;
		}

		if (!wCollide.isCollide(x, y, solidArea) && !eCollide.isColliding(this, x, y)) {
			setPosX(x);
			setPosY(y);
		}

		//if (eCollide.isColliding(this, x, y)) {

			if (eCollide.getTarget() instanceof BaseMonster) {
				if (keyboard.isSpacePressed()) {
					attackTarget(eCollide.getTarget());
					if(eCollide.getTarget().isDeath()) {
						StatusDisplay.getInstant().gainExperience(20);
					}
				}
			}
		//}

		if (wCollide.isMovingArea()) {
			Instruction.getInstant().updateText("Press E to enter");

			if (keyboard.isActionPressed()) {

				if (map.getMapType() == MapType.ISLAND) {
					map.changeMap(MapType.DUNGEON);
				} else {
					map.changeMap(MapType.ISLAND);
				}
			}
		} else {
			Instruction.getInstant().updateText("");
		}
		gc.drawImage(pic, getPosX(), getPosY());

		camera.cameraMove(getPosX(), getPosY());

	}

	
	public void attackTarget(BaseMonster target) {
		if(heldWeapon!=null) {
			target.takeDamage(heldWeapon.getDamage());
		}
		else {
	       target.takeDamage(getAttack());
	       }
	       System.out.println(getName()+" attack "+ target.getName());
	         
	    }
	
	public void takeDamage(int damage) {
		int tmp = getHealth();
		StatusDisplay.takeDamage(damage);
		super.takeDamage(damage);
		System.out.println(tmp +"->"+getHealth());

	}

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
		if(heldWeapon ==null)StatusDisplay.updateEquipment(new Image("player/still.png"));
		else StatusDisplay.updateEquipment(heldWeapon.getPic());
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

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
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
	
	

}
