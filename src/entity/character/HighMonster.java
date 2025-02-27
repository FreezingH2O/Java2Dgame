package entity.character;

import javafx.scene.image.Image;
import world.map;

public class HighMonster extends BaseMonster{
	
	private MonsterType monsterType;
	
	public HighMonster(MonsterType type, double posX, double posY, int speed,int health, int attack,int size,map map) {
        super(posX, posY, health, speed, attack,size, map);
        this.monsterType = type;
        
        pic = new Image("monster/"+monsterType+"monL.png");
        
    }
	
	public void attackTarget(BaseCharacter target) {
	       target.takeDamage(getAttack());
	       System.out.println(monsterType+" attack "+ target.getClass());
	         
	    }

	public MonsterType getMonsterType() {
		return monsterType;
	}

	
		
	
}
