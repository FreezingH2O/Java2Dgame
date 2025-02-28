package entity.weapon;

import entity.character.BaseMonster;
import entity.character.Player;

public abstract class BaseWeapon {
	protected String name;  
	protected int attackRange;  
	protected int damage;  
	protected int durability;
	
	public BaseWeapon(String name, int attackRange, int damage, int durability) {
		super();
		this.setName(name);
		this.setAttackRange(attackRange);
		this.setDamage(damage);
		this.setDurability(durability);
	}
	
    public abstract void attack(Player player, BaseMonster monster);  
    
    private double calculateDistance(Player player, BaseMonster monster) {  
        double deltaX = monster.getPosX() - player.getPosX();  
        double deltaY = monster.getPosY() - player.getPosY();  
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);  
    } 
	
	public boolean isUsable(Player player, BaseMonster monster) {  
        double distance = calculateDistance(player, monster);  
        return durability > 0 && distance <= attackRange;  
    }  
	
	protected void reduceDurability() {  
        if (durability > 0) { durability--; }  
    }  
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
