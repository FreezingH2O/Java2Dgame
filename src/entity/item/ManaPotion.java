package entity.item;

import entity.character.Player;

public class ManaPotion extends BaseItem {

	private int manaIncrease;
	private int quntity;
	public ManaPotion(int manaIncrease) {
		super("manaPotion");
		this.setManaIncrease(manaIncrease);
		
	}

	public void use(Player player) {
		player.setMana(player.getMana() + manaIncrease);
		System.out.println(player.getName() + " used " + getName() + " and increased mana by " + manaIncrease);
	}

	public int getManaIncrease() {
		return manaIncrease;
	}

	public void setManaIncrease(int manaIncrease) {
		this.manaIncrease = Math.max(0, manaIncrease);
	}
}
