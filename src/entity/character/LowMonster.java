package entity.character;

public class LowMonster extends BaseMonster{

	public LowMonster(double posX, double posY, int speed,int health, int attack,world.map map) {
		super(posX,posY,health, speed,attack,48,map);
		
	}
	
	public void attackTarget(BaseCharacter target) {
       target.takeDamage(getAttack());
         
    }
	
	

}
