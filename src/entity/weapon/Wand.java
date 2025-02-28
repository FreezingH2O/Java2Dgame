package entity.weapon;

import entity.ElementType;
import entity.character.BaseMonster;
import entity.character.Player;

public class Wand extends BaseWeapon {
    private int manaCost;  

    public Wand(int manaCost) {  
        super("Wand", 5, 10, 10);  
        this.setManaCost(manaCost); 
    }  
	@Override
	public void attack(Player player, BaseMonster monster) {
		// TODO Auto-generated method stub
		if (monster.getElementType() != ElementType.FIRE) {
            System.out.println(monster.getName() + " is frozen for 3 seconds!");  
			monster.setFrozen(true);
			new Thread(() -> {  
	            try {  
	                Thread.sleep(3000); 
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	            monster.setFrozen(false); 
	            System.out.println(monster.getName() + " is no longer frozen.");  
	        }).start(); 
        } else {  
            System.out.println(monster.getName() + " is immune to the wand's effect!");  
        }  
    
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = Math.max(0, manaCost);
	}

}
