package entity.weapon;

import entity.ElementType;
import entity.character.BaseMonster;
import entity.character.Player;

public class Sword extends BaseWeapon {

	private ElementType elementType;

	public Sword(String name, int attackRange, int damage, int durability, ElementType elementType) {
		super(elementType + " Sword", 2, 5, 8);
		this.setElementType(elementType);
	}

	@Override
	public void attack(Player player, BaseMonster monster) {
		// TODO Auto-generated method stub
		int damage = getDamage();
		if (monster.getElementType() == elementType) {
			damage *= 2; 
		}
		monster.setHealth(monster.getHealth() - damage);
		System.out.println(player.getName() + " slashes " + monster.getName() + " for " + damage + " damage.");

	}

	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

}
