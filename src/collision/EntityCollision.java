
package collision;

import entity.character.BaseCharacter;
import entity.character.BaseMonster;
import entity.character.Player;
import world.Map;

import java.awt.Rectangle;


public class EntityCollision {

 
    private static final int OFFSET_X = 8;
    private static final int SIZE_REDUCTION = 20;
    private static final int MONSTER_COLLISION_DISTANCE = 200;
    private static final int PLAYER_COLLISION_DISTANCE = 100;
    
    private boolean playerCollide;
    private BaseCharacter target;
    
    public EntityCollision() {
        this.playerCollide = false;
        target = null;
    }

    private Rectangle getBounds(BaseCharacter entity, double posX, double posY) {
        return new Rectangle((int) posX + OFFSET_X, (int) posY, entity.getSize() - SIZE_REDUCTION, entity.getSize());
    }

    public boolean isColliding(BaseCharacter entity, double newX, double newY) {
    
        Rectangle entityBounds = getBounds(entity, newX, newY);
                
        BaseCharacter closestEnemy = null;
        float minDistance = Float.MAX_VALUE;

        for (BaseCharacter other : Map.getEntities()) {
            if (other != entity) {
    
                Rectangle otherBounds = getBounds(other, other.getPosX(), other.getPosY());

                if (entityBounds.intersects(otherBounds)) {
                    return true;
                }

                float distance = (float) Math.sqrt(Math.pow(entity.getPosX() - other.getPosX(), 2)
                        + Math.pow(entity.getPosY() - other.getPosY(), 2));

                if (entity instanceof BaseMonster && other instanceof Player) {
                    if (distance < minDistance) {
                        if (distance <= MONSTER_COLLISION_DISTANCE) {
                            playerCollide = true;
                            minDistance = distance;
                            closestEnemy = other;
                        }
                    }
                } else if (entity instanceof Player) { 
                    if (((Player) entity).getHeldWeapon() != null) {
                        if (distance <= ((Player) entity).getHeldWeapon().getAttackRange()) {
                            playerCollide = false;
                            minDistance = distance;
                            closestEnemy = other;
                        }
                    } else if (distance <= PLAYER_COLLISION_DISTANCE) {
                        playerCollide = false;
                        minDistance = distance;
                        closestEnemy = other;
                    }
                }
            }
        }
        
        setTarget(closestEnemy);
        return false;
    }

    public boolean isPlayerCollide() {
        return playerCollide;
    }

    public void setTarget(BaseCharacter target) {
        this.target = target;
    }

    public BaseCharacter getTarget() {
        return target;
    }
    

    public boolean getPlayerCollide() {
        return playerCollide;
    }
    
    public void setPlayerCollide(boolean playerCollide) {
        this.playerCollide = playerCollide;
    }
    
    public float computeDistance(BaseCharacter a, BaseCharacter b) {
        return (float) Math.sqrt(Math.pow(a.getPosX() - b.getPosX(), 2) + Math.pow(a.getPosY() - b.getPosY(), 2));
    }
}

