package entity.character;

import components.StatusDisplay;

public class LowMonster extends BaseMonster{

	public LowMonster(double posX, double posY, int speed,int health, int attack,world.Map map) {
		super(MonsterType.LOW_CLASS,posX,posY,health, speed,attack,48,map);
		
	}
	
	
	public void setHealth(int health) {
		super.setHealth(health);
		if(isDeath()) {
			StatusDisplay.getInstant().gainExperience(20);
		}
	}
	

}
