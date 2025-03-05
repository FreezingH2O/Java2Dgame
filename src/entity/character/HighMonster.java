package entity.character;

import javafx.scene.image.Image;
import world.Map;

public class HighMonster extends BaseMonster{
	
	public HighMonster(MonsterType type, double posX, double posY, int speed,int health, int attack,int size,Map map) {
        super(type,posX, posY, health, speed, attack,size, map);
        
        pic = new Image("monster/"+monsterType+"monL.png");
        
    }


	
		
	
}
