package entity.character;

import components.StatusDisplay;
import world.Map;

public class FinalBoss extends HighMonster {

    public FinalBoss(double posX, double posY, int speed,int health, int attack, Map map) {
    	
        super(MonsterType.FINAL_BOSS,posX, posY, health, speed, attack,96,map);
    }

	public void setHealth(int health) {
		super.setHealth(health);
		if(isDeath()) {
			StatusDisplay.getInstant().gainExperience(200);
		}
	}

    public void ultimateAbility() {
       
    	
    	
    }
}
