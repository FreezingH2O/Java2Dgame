package entity.effect;

import entity.ElementType;
import entity.character.BaseCharacter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class arrow extends GameEffect {

	public arrow(ElementType type, double startX, double startY, double targetX, double targetY, double speed,
			BaseCharacter target) {
		super(type, startX, startY, targetX, targetY, speed, target);
		pic = new Image("skill/arrow.png");
	}

	public void render(GraphicsContext gc) {
		if (!isActive)
			return;

		double scaleFactor = 1.2;

		double resizedWidth = pic.getWidth() * scaleFactor;
		double resizedHeight = pic.getHeight() * scaleFactor;

		gc.save();
		gc.translate(x, y);

		double angle = Math.atan2(speedY, speedX);
		gc.rotate(Math.toDegrees(angle));

		gc.drawImage(pic, -resizedWidth / 2, 0, resizedWidth, resizedHeight);
		gc.restore();
	}
	
	 public boolean checkCollision(BaseCharacter player) {
	    	if (player == null)return false;
//	    	System.out.println(player.getPosX()+ " "+player.getPosY());
//	    	System.out.println(x+" "+y);
	        double distance = Math.sqrt(Math.pow(player.getPosX() - x, 2) + Math.pow(player.getPosY() - y, 2));
	        return distance < 2; 
	    }

}
