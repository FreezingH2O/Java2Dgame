package entity.character;

import collision.WorldCollision;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.KeyboardController;
import world.camera;
import world.map;

public class Player extends BaseCharacter {
	private Image pic;
	private static Image up, down1, down2, left1, left2, right1, right2, still;
	private boolean xCheck, yCheck;
	private KeyboardController keyboard;
	private WorldCollision collide;
	private camera camera;

	public Player(map map) {
		super(world.map.getMapWidth() / 2 -2*48, world.map.getMapWidth() / 2 -5*48, 100, 4, 10);
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
		collide = new WorldCollision(map);
		camera = new camera();
		keyboard = new KeyboardController();
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
		// System.out.println(x);

		if (!collide.isCollide(x, y)) {
			setPosX(x);
			setPosY(y);
		}

		gc.drawImage(pic, getPosX(), getPosY());

		camera.cameraMove(getPosX(), getPosY());

	}

	public void setPic(Image pic) {
		this.pic = pic;

	}

}
