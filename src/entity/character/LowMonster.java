package entity.character;

import components.StatusDisplay;
import javafx.scene.image.Image;

public class LowMonster extends BaseMonster{

	public LowMonster(double posX, double posY, int speed,int health, int attack,world.Map map) {
		super(MonsterType.LOW_CLASS,posX,posY,health, speed,attack,48,map);
		
		pic = new Image("monster/ZombieL.png");
	}
	
	
	public void setHealth(int health) {
		super.setHealth(health);
		if(isDeath()) {
			StatusDisplay.getInstant().gainExperience(20);
		}
	}
	

}
