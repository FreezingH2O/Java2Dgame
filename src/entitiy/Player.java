package entitiy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.KeyboardController;

public class Player {
	private int posX = 96, posY = 96;
	private int speed = 3;
	private Image pic ;
	private static Image up,down1,down2,left1,left2,right1,right2,still;
	private boolean  xCheck ,yCheck;
	private KeyboardController keyboard;
	
	public Player() {
		up = new Image("up.png");
		down1 = new Image("down1.png");
		down2 = new Image("down2.png");
		left1 = new Image("left1.png");
		left2 = new Image("left2.png");
		right1 = new Image("right1.png");
		right2 = new  Image("right2.png");
		still = new Image("still.png");
		
		
		xCheck = true;
		yCheck  = true;
		this.pic = still;
		keyboard = new KeyboardController();
	}
	
	

	 public void update(GraphicsContext gc) {
		xCheck = (posX%48==0)? !xCheck: xCheck;
		yCheck = (posY%48==0)? !yCheck: yCheck;
		// System.out.println(posY);
	        if (keyboard.isUpPressed()) { 
	        	posY -= speed;
	        	this.pic =up;
	        }
	        else if (keyboard.isDownPressed()) {
	        	posY += speed;
	        	this.pic = (yCheck)?down1:down2;
	        }
	        else if (keyboard.isLeftPressed()) {
	        	posX -=speed;
	        	this.pic = (xCheck)? left1:left2;
	        
	        }
	        else if (keyboard.isRightPressed()) {
	        	posX += speed;
	        	this.pic = (xCheck)? right1:right2;
	        
	        }
	        else {
	        	this.pic = still;
	        }
	        
	        gc.drawImage(pic, posX, posY);
	    }
	
	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}
	
	
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	
}
