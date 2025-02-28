package entity.item;

import entity.character.Player;
import entity.weapon.BaseWeapon;

public class Talisman extends BaseItem {

	private int effectValue;

	public Talisman(String name, int effectValue) {
		super("Talisman");
		this.setEffectValue(effectValue);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
		//player.increaseWeaponDamage(25);
		BaseWeapon weapon = player.getHeldWeapon();
		weapon.setDamage(weapon.getDamage()*(1+25/100));
		System.out.println(player.getName() + " equipped " + getName() + " and increased weapon damage by 25%");

	}

	public int getEffectValue() {
		return effectValue;
	}

	public void setEffectValue(int effectValue) {
		this.effectValue = Math.max(0, effectValue);
	}
}
