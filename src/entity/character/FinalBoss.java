package entity.character;

import world.map;

public class FinalBoss extends HighMonster {

    public FinalBoss(double posX, double posY, int speed,int health, int attack, map map) {
    	
        super(MonsterType.FINAL_BOSS,posX, posY, health, speed, attack,96,map);
    }



    public void ultimateAbility() {
       
    	
    	
    }
}
