package entity.item;

import components.StatusDisplay;
import entity.character.Player;
import javafx.scene.media.AudioClip;
import logic.Sound;

public class ManaPotion extends BaseItem {

	private int manaIncrease;
	private int quntity;
	public ManaPotion(int manaIncrease) {
		super("manaPotion");
		this.setManaIncrease(manaIncrease);
		
	}

	public void use(Player player) {
		StatusDisplay.regainMana(manaIncrease);
		player.setMana(player.getMana() + manaIncrease);
		System.out.println(player.getName() + " used " + getName() + " and increased mana by " + manaIncrease);
		AudioClip powerup_sound = Sound.getpowerup_sound();
		powerup_sound.setVolume(1.0);
		powerup_sound.play();
	}

	public int getManaIncrease() {
		return manaIncrease;
	}

	public void setManaIncrease(int manaIncrease) {
		this.manaIncrease = Math.max(0, manaIncrease);
	}
	
	public int getQuntity() {
		return quntity;
	}

	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}
}
