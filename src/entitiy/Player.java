package entitiy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.KeyboardController;
import main.main;

public class Player {
	private  double posX = 10*48;
	private  double posY = 7*48;
	private int speed = 1;
	private Image pic ;
	private static Image up,down1,down2,left1,left2,right1,right2,still;
	private boolean  xCheck ,yCheck;
	private KeyboardController keyboard;
	
	public Player() {
		up = new Image("player/up.png");
		down1 = new Image("player/down1.png");
		down2 = new Image("player/down2.png");
		left1 = new Image("player/left1.png");
		left2 = new Image("player/left2.png");
		right1 = new Image("player/right1.png");
		right2 = new  Image("player/right2.png");
		still = new Image("player/still.png");
		
		
		xCheck = true;
		yCheck  = true;
		this.pic = still;
		keyboard = new KeyboardController();
	}
	
	

	 public void update(GraphicsContext gc) {
		xCheck = (posX%72==0)? !xCheck: xCheck;
		yCheck = (posY%72==0)? !yCheck: yCheck;
		// System.out.println(posY);
	        if (keyboard.isUpPressed()) { 
	        	
	        	setPosY(getPosY()-speed);
	        	this.pic =up;
	        }
	        else if (keyboard.isDownPressed()) {
	        
	        	setPosY(getPosY()+speed);
	        	this.pic = (yCheck)?down1:down2;
	        }
	        else if (keyboard.isLeftPressed()) {
	  
	        	setPosX(getPosX()-speed);
	        	this.pic = (xCheck)? left1:left2;
	        
	        }
	        else if (keyboard.isRightPressed()) {
	        	setPosX(getPosX()+speed);
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
	
	
	
	public  double getPosX() {
		return posX;
	}

	public void setPosX(double d) {
		if(d<0)posX=0;
		else if(d>30*48) {
			posX = 30*48;
		}
		else
		this.posX = d;
	}

	public  double getPosY() {
		return posY;
	}

	public void setPosY(double d) {
		if(d<0) {
			posY = 0;
		}
		else if(d>30*48) {
			posY = 30*48;
		}
		else {
			posY = d;
		}
		
	}
	
	
	
}
