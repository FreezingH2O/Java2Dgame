package entity.weapon;

import entity.ElementType;
import entity.character.BaseMonster;
import entity.character.Player;
import entity.effect.EffectManager;
import entity.effect.Fireball;

public class Wand extends BaseWeapon {
    private int manaCost;  

    public Wand(int manaCost) {  
        super("Wand", 4*48, 10, 5);  
        this.setManaCost(manaCost); 
    }  
    
    private void launchFireball(Player player, BaseMonster monster) {
        double startX = player.getPosX();
        double startY = player.getPosY();
        double targetX = monster.getPosX();
        double targetY = monster.getPosY();

        double speed = 200; // pixels per second
        double angle = Math.atan2(targetY - startY, targetX - startX);
        double speedX = speed * Math.cos(angle);
        double speedY = speed * Math.sin(angle);

        Fireball fireball = new Fireball(startX, startY, speedX, speedY);
        EffectManager.getInstance().addEffect(fireball);
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
		
		launchFireball(player, monster);
    
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = Math.max(0, manaCost);
	}

}
