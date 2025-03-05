package entity.item;

import entity.ElementType;
import entity.character.Player;
import entity.weapon.BaseWeapon;

public class KeyItem extends BaseItem {

	private ElementType elementType;
	private int effectValue;
	
	public KeyItem(String name, ElementType elementType, int effectValue) {
		super(elementType + " KeyItem"); 
		this.setElementType(elementType);
		this.setEffectValue(effectValue);
	}

	public void equip(Player player) {
		player.addKeyItem(this);
		BaseWeapon weapon = player.getHeldWeapon();
		weapon.setDamage(weapon.getDamage()*(1+effectValue/100));
		System.out.println(player.getName() + " equipped " + getName() + " and increased weapon damage by "+effectValue+"%");
	}
	
	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}
	
	public int getEffectValue() {
		return effectValue;
	}

	public void setEffectValue(int effectValue) {
		this.effectValue = Math.max(0, effectValue);
	}

}
