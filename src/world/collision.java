package world;


import main.main;

public class collision {

	private map map;

	private int tile = main.getTilesize();


	public collision(map m) {
		map = m;


	}

	public boolean isCollide(double x, double y) {
		int indX1 = (int) Math.floor(x / tile);
		int indY1 = (int) Math.floor(y / tile);

		int indX2 = (int) Math.ceil(x / tile);
		int indY2 = (int) Math.ceil(y / tile);

		// System.out.println("Checking: (" + indX1 + ", " + indY1 + ") and (" + indX2 +
		// ", " + indY2 + ")");

		if (map.arr[indY1][indX1] % 8 != 0 || map.arr[indY2][indX2] % 8 != 0 || map.arr[indY1][indX2] % 8 != 0
				|| map.arr[indY2][indX1] % 8 != 0) {
			return true;
		} else {
			return false;
		}
	}

}
