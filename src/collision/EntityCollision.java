package collision;

import entity.character.BaseCharacter;
import entity.character.Player;

import java.awt.Rectangle;
import java.util.List;

public class EntityCollision {

	private List<BaseCharacter> entities;
	private boolean playerCollide;
	private BaseCharacter target;

	public EntityCollision(List<BaseCharacter> entities) {
		this.entities = entities;
		this.playerCollide = false;

	}

	public boolean isColliding(BaseCharacter entity, double newX, double newY) {
		Rectangle entityBounds = new Rectangle((int) newX+8, (int) newY, entity.getSize()-20, entity.getSize());
		BaseCharacter closestEnemy = null;
		float minDistance = Float.MAX_VALUE;
		
		for (BaseCharacter other : entities) {
			if (other != entity) {
				Rectangle otherBounds = new Rectangle((int) other.getPosX()+8, (int) other.getPosY(), other.getSize()-20,
						other.getSize());

				if (entityBounds.intersects(otherBounds)) {
					target = other;
					if (other instanceof Player) {
						this.playerCollide = true;
					} else {
						playerCollide = false;
					}
					return true;
				}
				
				float distance = (float) Math.sqrt(
					    Math.pow(entity.getPosX() - other.getPosX(), 2) + Math.pow(entity.getPosY() - other.getPosY(), 2)
						);
			    if (distance < minDistance) {
			        minDistance = distance;
			        closestEnemy = other;
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

}
