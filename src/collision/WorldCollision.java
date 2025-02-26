package collision;

import java.util.HashSet;
import java.util.Set;

import main.main;
import world.map;

public class WorldCollision {

	private map map;

	private int tile = main.getTilesize();
	private final static Set<Integer> forbid = new HashSet<>(Set.of( 0,2,3,4));

	public WorldCollision(map m) {
		map = m;


	}

	public boolean isCollide(double x, double y) {
		int indX1 = (int) Math.floor(x / tile);
		int indY1 = (int) Math.floor(y / tile);

		int indX2 = (int) Math.ceil(x / tile);
		int indY2 = (int) Math.ceil(y / tile);

		// System.out.println("Checking: (" + indX1 + ", " + indY1 + ") and (" + indX2 +
		// ", " + indY2 + ")");

		if (forbid.contains(map.arr[indY1][indX1])  || forbid.contains(map.arr[indY2][indX2]) || forbid.contains(map.arr[indY1][indX2])
				|| forbid.contains(map.arr[indY2][indX1])) {
			return true;
		} else {
			return false;
		}
	}

}
