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
		Rectangle entityBounds = new Rectangle((int) newX, (int) newY, entity.getSize(), entity.getSize());

		for (BaseCharacter other : entities) {
			if (other != entity) {
				Rectangle otherBounds = new Rectangle((int) other.getPosX(), (int) other.getPosY(), other.getSize(),
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
			}
		}
		return false;
	}

	public boolean isPlayerCollide() {
		return playerCollide;
	}

	public BaseCharacter getTarget() {
		return target;
	}

}
