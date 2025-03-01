package world;

import main.Main;
public class Camera {

    private double mapWidth;
    private double mapHeight;
    private double screenWidth;
    private double screenHeight;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    public Camera(Map map) {
        mapWidth = map.getMapWidth();
        mapHeight = map.getMapHeight();
        screenWidth = Main.getWidth();
        screenHeight = Main.getHeight();
        minX = -(mapWidth - screenWidth) / 2;
        minY = -(mapHeight - screenHeight) / 2;
        maxX = (mapWidth - screenWidth) / 2;
        maxY = (mapHeight - screenHeight) / 2;
    }

    public void cameraMove(double posX, double posY) {
        // Calculate the camera's position, clamping it within bounds
        double camX = Math.max(minX, Math.min(maxX, mapWidth / 2 - posX));
        double camY = Math.max(minY, Math.min(maxY, mapHeight / 2 - posY));


        Main.getRoot().getChildren().get(0).setTranslateX(camX); 
        Main.getRoot().getChildren().get(0).setTranslateY(camY); 
    }
}
