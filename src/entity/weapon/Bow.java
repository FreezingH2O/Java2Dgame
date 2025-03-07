package entity.weapon;

import entity.character.BaseMonster;
import entity.character.Player;

public class Bow extends BaseWeapon {

	public Bow() {
		super("bow", 250, 3, 15);
	}

	@Override
	public void attack(Player player, BaseMonster monster) {
		// TODO Auto-generated method stub
		monster.takeDamage(damage);
		System.out.println(player.getName() + " attacks " + monster.getName() + " for " + damage + " damage.");
	}

}
