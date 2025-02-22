package entitiy;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import logic.KeyboardController;
import main.main;
import world.map;

public class Player {
	private double posX = map.getMapWidth() / 2;
	private double posY = map.getMapHeight() / 2;
	private int speed = 3;
	private Image pic;
	private static Image up, down1, down2, left1, left2, right1, right2, still;
	private boolean xCheck, yCheck;
	private KeyboardController keyboard;
	private boolean collide;

	public Player() {
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
		this.pic = still;

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

		setCollide(x, y);
		if (!isCollide()) { 
			setPosX(x);
			setPosY(y);
		}

		gc.drawImage(pic, posX, posY);

		StackPane root = main.getRoot();

		root.setTranslateX(map.getMapWidth() / 2 - posX);
		root.setTranslateY(map.getMapHeight() / 2 - posY);

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

	public boolean isCollide() {
		return collide;
	}

	public void setCollide(double x, double y) {
	    int indX1 = (int) Math.floor(x / 48);
	    int indY1 = (int) Math.floor(y / 48);
	    
	    int indX2 = (int) Math.ceil(x / 48);
	    int indY2 = (int) Math.ceil(y / 48);

	   // System.out.println("Checking: (" + indX1 + ", " + indY1 + ") and (" + indX2 + ", " + indY2 + ")");

	
	
	    if (map.arr[indY1][indX1] != 0 || map.arr[indY2][indX2] != 0||map.arr[indY1][indX2] != 0|| map.arr[indY2][indX1] != 0) {
	        collide = true;
	    } else {
	        collide = false;
	    }
	}


}
