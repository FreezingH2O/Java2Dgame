package entity.character;

import javafx.scene.image.Image;
import world.map;

public class HighMonster extends BaseMonster{
	
	public HighMonster(MonsterType type, double posX, double posY, int speed,int health, int attack,int size,map map) {
        super(type,posX, posY, health, speed, attack,size, map);
        
        pic = new Image("monster/"+monsterType+"monL.png");
        
    }

	public MonsterType getMonsterType() {
		return monsterType;
	}

	
		
	
}
