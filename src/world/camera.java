package world;

import main.main;

public class camera {

	private double mapWidth;
	private double mapHeight;
	private double screenWidth;
	private double screenHeight;
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;

	public camera() {
		// root = main.getRoot();
		mapWidth = map.getMapWidth();
		mapHeight = map.getMapHeight();
		screenWidth = main.getWidth();
		screenHeight = main.getHeight();
		minX = -(mapWidth - screenWidth) / 2;
		minY = -(mapHeight - screenHeight) / 2;
		maxX = (mapWidth - screenWidth) / 2;
		maxY = (mapHeight - screenHeight) / 2;
	}

	public void cameraMove(double posX, double posY) {
		// System.out.println("Root: " + root);

		double camX = Math.max(minX, Math.min(maxX, mapWidth / 2 - posX));
		double camY = Math.max(minY, Math.min(maxY, mapHeight / 2 - posY));

		main.getRoot().setTranslateX(camX);
		main.getRoot().setTranslateY(camY);
	}

}
