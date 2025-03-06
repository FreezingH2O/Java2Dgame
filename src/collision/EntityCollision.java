package collision;

import entity.character.BaseCharacter;
import entity.character.BaseMonster;
import entity.character.Player;
import world.Map;

import java.awt.Rectangle;
import java.rmi.server.RemoteRef;
import java.util.List;

public class EntityCollision {

	private boolean playerCollide;
	private BaseCharacter target;

	public EntityCollision() {
		this.playerCollide = false;

	}

	public boolean isColliding(BaseCharacter entity, double newX, double newY) {
		Rectangle entityBounds = new Rectangle((int) newX + 8, (int) newY, entity.getSize() - 20, entity.getSize());
		BaseCharacter closestEnemy = null;
		float minDistance = Float.MAX_VALUE;

		for (BaseCharacter other : Map.getEntities()) {
			if (other != entity) {
				Rectangle otherBounds = new Rectangle((int) other.getPosX() + 8, (int) other.getPosY(),
						other.getSize() - 20, other.getSize());

				if (entityBounds.intersects(otherBounds)) {
					setTarget(other);
					return true;
				}

				float distance = (float) Math.sqrt(Math.pow(entity.getPosX() - other.getPosX(), 2)
						+ Math.pow(entity.getPosY() - other.getPosY(), 2));

				if (entity instanceof BaseMonster && other instanceof Player) {
					System.out.println(entity.getName() + " " + distance);
					System.out.println(minDistance);
					if (distance < minDistance) {
						if (distance <=200) {
							System.out.println(entity.getName() + "target the player");
							playerCollide = true;
							minDistance = distance;
							closestEnemy = other;
							setTarget(closestEnemy);
						}
					}
				}

				else if (entity instanceof Player) {
					if (((Player) entity).getHeldWeapon() != null)// System.out.println("punch");
						if (distance <= ((Player) entity).getHeldWeapon().getAttackRange()) {

							playerCollide = false;
							minDistance = distance;
							closestEnemy = other;
							setTarget(closestEnemy);
						}

				}
			}

		}

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
