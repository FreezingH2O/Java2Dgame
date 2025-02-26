package entity.character;

public abstract class BaseMonster extends BaseCharacter{
	
	public BaseMonster(double posX, double posY, int health, int speed, int attack) {
		super(posX,posY, 100, 4, 10);
	}
}
