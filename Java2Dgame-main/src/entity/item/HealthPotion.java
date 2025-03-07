package entity.item;

import components.StatusDisplay;
import entity.character.Player;
import javafx.scene.media.AudioClip;
import logic.Sound;

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
		StatusDisplay.heal(healthIncrease);
		System.out.println(player.getName() + " used " + getName() + " and increased health by " + healthIncrease);
		AudioClip powerup_sound = Sound.getpowerup_sound();
		powerup_sound.setVolume(1.0);
		powerup_sound.play();
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
