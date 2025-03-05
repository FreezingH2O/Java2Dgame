package collision;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.shape.Rectangle;
import main.Main;
import world.Map;

public class WorldCollision {
	private int tile = Main.getTilesize();
	private Map map;
	private boolean movingArea;
	private final static Set<Integer> forbid = new HashSet<>(Set.of(0, 2, 4,5));

	public WorldCollision(Map m) {
		map = m;

	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || y < 0 || x >= map.arr[0].length || y >= map.arr.length;
	}

	public boolean isCollide(double x, double y, Rectangle box) {
		
		   int entityLeftWorldX = (int) (x + box.getX());
		    int entityRightWorldX = (int) (x + box.getX() + box.getWidth());
		    int entityTopWorldY = (int) (y + box.getY());
		    int entityBottomWorldY = (int) (y + box.getY() + box.getHeight());

		    int indX1 = (int) Math.floor(entityLeftWorldX / tile); 
		    int indY1 = (int) Math.floor(entityTopWorldY / tile); 

		    int indX2 = (int) Math.ceil(entityRightWorldX / tile); 
		    int indY2 = (int) Math.ceil(entityBottomWorldY / tile); 



		if (isOutOfBounds(indX1, indY1) || isOutOfBounds(indX2, indY2)) {
			return true;
		}
		movingArea = (map.arr[indY1][indX1] == 88 || map.arr[indY2][indX2] == 88 || map.arr[indY1][indX2] == 88
				|| map.arr[indY2][indX1] == 88);

		if (forbid.contains(map.arr[indY1][indX1]) || forbid.contains(map.arr[indY2][indX2])
				|| forbid.contains(map.arr[indY1][indX2]) || forbid.contains(map.arr[indY2][indX1])) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMovingArea() {
		return movingArea;
	}

}
