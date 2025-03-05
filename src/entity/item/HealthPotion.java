package entity.item;

import entity.character.Player;

public class HealthPotion extends BaseItem {

	private int healthIncrease;
	private int quntity;
	
	public HealthPotion(int healthIncrease) {
		super("healthPotion");
		this.setHealthIncrease(healthIncrease);
		setQuntity(3);
	}

	public void use(Player player) {
		player.setHealth(player.getHealth() + healthIncrease);
		System.out.println(player.getName() + " used " + getName() + " and increased health by " + healthIncrease);
	}

	public int getHealthIncrease() {
		return healthIncrease;
	}

	public void setHealthIncrease(int healthIncrease) {
		this.healthIncrease = Math.max(0, healthIncrease);
	}

	public int getQuntity() {
		return quntity;
	}

	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}
	
	

}
