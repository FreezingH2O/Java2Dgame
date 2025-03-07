package entity.weapon;



import entity.character.BaseMonster;
import entity.character.Player;
import entity.item.BaseItem;
import javafx.scene.image.Image;

public abstract class BaseWeapon extends BaseItem{
	protected String name;  
	protected int attackRange;  
	protected int damage;  
	protected int durability;
	
	public BaseWeapon(String name, int attackRange, int damage, int durability) {
		super(name);
		System.out.println(name);
		this.setAttackRange(attackRange);
		this.setDamage(damage);
		this.setDurability(durability);
	}
	
    public abstract void attack(Player player, BaseMonster monster);  
    
	public boolean isUsable(Player player, BaseMonster monster) {  
        return durability > 0;  
    }  
	
	protected void reduceDurability() {  
        if (durability > 0) { durability--; }  
    }  
	
	public int getAttackRange() {
		return attackRange;
	}
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = Math.max(0, damage);
	}
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	} 
	
	
}
