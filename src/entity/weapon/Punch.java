package entity.weapon;

import entity.character.BaseMonster;
import entity.character.Player;

public class Punch extends BaseWeapon {

	public Punch(String name, int attackRange, int damage, int durability) {
		super(name, 1, 2, Integer.MAX_VALUE);
	}

	@Override
	public void attack(Player player, BaseMonster monster) {
		// TODO Auto-generated method stub
		monster.setHealth(monster.getHealth() - this.getDamage());
		this.reduceDurability();
		System.out.println(player.getName() + " punches " + monster.getName() + " for " + getDamage() + " damage.");
	}

}
