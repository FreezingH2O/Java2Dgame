package entitiy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import logic.KeyboardController;
import main.main;
import world.camera;
import world.collision;
import world.map;

public class Player {
	private double posX = map.getMapWidth() / 2;
	private double posY = map.getMapHeight() / 2;
	private int speed = 4;
	private Image pic;
	private static Image up, down1, down2, left1, left2, right1, right2, still;
	private boolean xCheck, yCheck;
	private KeyboardController keyboard;
	private collision collide;
	private camera camera;

	public Player(map map) {
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
		collide = new collision(map);
		camera = new camera();
		keyboard = new KeyboardController();
	}

	public void update(GraphicsContext gc) {

		xCheck = (posX % 72 == 0) ? !xCheck : xCheck;
		yCheck = (posY % 72 == 0) ? !yCheck : yCheck;
		// System.out.println(posY);
		double y = posY, x = posX;

		if (keyboard.isUpPressed()) {
			y = getPosY() - speed;
			// setPosY(getPosY()-speed);
			this.pic = up;
		} else if (keyboard.isDownPressed()) {
			y = getPosY() + speed;
			// setPosY(getPosY()+speed);
			this.pic = (yCheck) ? down1 : down2;
		} else if (keyboard.isLeftPressed()) {
			x = getPosX() - speed;
			// setPosX(getPosX()-speed);
			this.pic = (xCheck) ? left1 : left2;

		} else if (keyboard.isRightPressed()) {
			x = getPosX() + speed;
			// setPosX(getPosX()+speed);
			this.pic = (xCheck) ? right1 : right2;

		} else {
			this.pic = still;
		}
		// System.out.println(x);

		if (!collide.isCollide(x, y)) {
			setPosX(x);
			setPosY(y);
		}

		gc.drawImage(pic, posX, posY);

		camera.cameraMove(posX, posY);

	}

	public void setPic(Image pic) {
		this.pic = pic;

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

}
