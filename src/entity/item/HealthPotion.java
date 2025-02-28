package entity.item;

import entity.character.Player;

public class HealthPotion extends BaseItem {

	private int healthIncrease;

	public HealthPotion(int healthIncrease) {
		super("Health Potion");
		this.setHealthIncrease(healthIncrease);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
		player.setHealth(player.getHealth() + healthIncrease);
		System.out.println(player.getName() + " used " + getName() + " and increased health by " + healthIncrease);
	}

	public int getHealthIncrease() {
		return healthIncrease;
	}

	public void setHealthIncrease(int healthIncrease) {
		this.healthIncrease = Math.max(0, healthIncrease);
	}

}
