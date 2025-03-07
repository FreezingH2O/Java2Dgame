package entity.character;

import java.util.ArrayList;
import java.util.HashMap;

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
import javafx.scene.shape.Rectangle;
import logic.KeyboardController;
import world.Camera;
import world.Map;
import world.MapType;

public class Player extends BaseCharacter {
	private Image pic;
	private boolean xCheck, yCheck, toggle;
	private KeyboardController keyboard;
	private Camera camera;
	private int mana, maxMana;
	private ArrayList<KeyItem> keyItemList;
	private ArrayList<BaseWeapon> weaponList;
	private BaseWeapon heldWeapon;
	private static Player instant;
	private static HashMap<String, Image> images;

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
		toggle = false;
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

		// Load images for Sword
		images.put("sword00", new Image("player/sword00.png"));
		
//		images.put("sword21", new Image("player/sword21.png"));
//		images.put("sword22", new Image("player/sword22.png"));

		// Load images for Bow
		images.put("bow00", new Image("player/bow1.gif"));
		images.put("bow01", new Image("player/bow2.gif"));

		// Load images for Wand
		// images.put("wand00", new Image("player/wand00.png"));
		images.put("wand00", new Image("player/wand00.gif"));
		images.put("wand01", new Image("player/wand01.png"));
//		images.put("wand21", new Image("player/wand21.png"));
//		images.put("wand22", new Image("player/wand22.png"));
//	

		// Set default image
		pic = images.get("still");

		xCheck = true;
		yCheck = true;

		solidArea = new Rectangle(28, 28);
		solidArea.setX(8);
		solidArea.setY(16);

		wCollide = new WorldCollision(map);
		camera = new Camera(map);
		keyboard = new KeyboardController();
	}

	public void attackTarget(BaseCharacter target) {
		if (heldWeapon != null) {
			if (heldWeapon instanceof Wand) {
				GameEffect effect = new GameEffect(ElementType.MAGIC, getPosX(), getPosY(), target.getPosX(),
						target.getPosY(), 1, target);
				effectsList.add(effect);

				setMana(mana - ((Wand) heldWeapon).getManaCost());
				StatusDisplay.getInstant().useMana(((Wand) heldWeapon).getManaCost());
			} else if (heldWeapon instanceof Bow) {
				GameEffect effect = new arrow(ElementType.NONE, getPosX(), getPosY(), target.getPosX(),
						target.getPosY(), 1, target);
				effectsList.add(effect);
			}

			target.takeDamage(heldWeapon.getDamage());
		} else {
			target.takeDamage(getAttack());
		}
		System.out.println(getName() + " attack " + target.getName());

	}

	public void update(GraphicsContext gc) {
		int speed = this.getSpeed();
		xCheck = (getPosX() % 72 == 0) ? !xCheck : xCheck;
		yCheck = (getPosY() % 72 == 0) ? !yCheck : yCheck;
		// System.out.println(posY);

		double y = getPosY(), x = getPosX();

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

		if (heldWeapon != null) {
			//if (keyboard.isSpacePressed()) {
				setPic(images.get(heldWeapon.getName() + "00"));
//				}
//			else {
//				setPic(images.get(heldWeapon.getName() + "01"));
//			}
			solidArea = new Rectangle(45, 45);
			solidArea.setX(20);
			solidArea.setY(30);
		}

		else {
			solidArea = new Rectangle(28, 28);
			solidArea.setX(8);
			solidArea.setY(16);
		}

		// System.out.println(eCollide.isColliding(this, x, y));
		if (!wCollide.isCollide(x, y, solidArea) && !eCollide.isColliding(this, x, y)) {
			setPosX(x);
			setPosY(y);
		}

		// if (eCollide.isColliding(this, x, y)) {
		// System.out.println(eCollide.getTarget());
		if (eCollide.getTarget() instanceof BaseMonster) {
			if (keyboard.isSpacePressed()) {

				attackTarget(eCollide.getTarget());
//				if (eCollide.getTarget().isDeath()) {
//					StatusDisplay.getInstant().gainExperience(20);
//				}
			}
		}
		// }

		if (wCollide.isMovingArea()) {
			if (wCollide.getMovetile() == 88) {
				Instruction.getInstant().updateText("Press E to enter the Dungeon");

			} else if (wCollide.getMovetile() == 81) {
				Instruction.getInstant().updateText("Press E to enter to getout");

			} else if (wCollide.getMovetile() == 82) {
				if (HighMonster.getHighBossLi().size() != 0) {
					Instruction.getInstant().updateText("You need to collect all of the lialic to open the boss room");

				} else {
					Instruction.getInstant().updateText("Press E to enter the Bossroom");
				}

			} else if (wCollide.getMovetile() == 83) {
				// System.out.println("house");
				Instruction.getInstant().updateText("Press E to enter the House");
			}

			if (keyboard.isActionPressed()) {

				if (wCollide.getMovetile() == 88) {
					map.changeMap(MapType.DUNGEON);
				} else if (wCollide.getMovetile() == 81) {
					map.changeMap(MapType.ISLAND);
				} else if (wCollide.getMovetile() == 82) {
					if (HighMonster.getHighBossLi().size() == 0) {
						map.changeMap(MapType.BOSSROOM);
					}
				} else if (wCollide.getMovetile() == 83) {
					map.changeMap(MapType.HOUSE);
				}

			}
		} else {
			Instruction.getInstant().updateText("");
		}
		gc.drawImage(pic, getPosX(), getPosY());

		camera.cameraMove(getPosX(), getPosY());

		for (int i = 0; i < weaponList.size(); ++i) {
			Hotbar.updateSlot(i, weaponList.get(i));
		}
		super.update(gc);
	}

	public void takeDamage(int damage) {
		int tmp = getHealth();
		StatusDisplay.takeDamage(damage);
		super.takeDamage(damage);
		System.out.println(tmp + "->" + getHealth());

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
		if (heldWeapon == null) {
			StatusDisplay.updateEquipment(new Image("player/still.png"));
			return;
		} else
			StatusDisplay.updateEquipment(heldWeapon.getPic());

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

	public void setMana(int mana) {
		if (mana > maxMana)
			this.mana = maxMana;
		else if (mana < 0)
			this.mana = 0;
		else
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
