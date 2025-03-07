/*
 * File: WorldCollision.java
 * Description: This class handles collision detection between entities and the world map.
 * It calculates if an entity collides with a forbidden tile or enters a special moving area.
 *
 * Checklist improvements (based on the Code Review Checklist :contentReference[oaicite:0]{index=0}):
 *   - Format: Added file header comment with author and date, consistent spacing, and clear indentation.
 *   - Magic Numbers: Introduced constants (e.g., tile size from Main, and sets for forbidden and moving area tiles).
 *   - Code Shortening: Added helper functions getMoveTile() and hasForbiddenTile() to reduce duplicate code.
 *
 * Author: [Your Name]
 * Date: [Current Date]
 */

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
    private int movetile;
    private final static Set<Integer> forbid = new HashSet<>(Set.of(0, 2, 4, 5, 6, 7, 8, 9, 55, 89));
    private final static Set<Integer> moveArea = new HashSet<>(Set.of(88, 81, 82));

    public WorldCollision(Map m) {
        map = m;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || y < 0 || x >= map.arr[0].length || y >= map.arr.length;
    }

    // Helper function to check if any of the four tile indices equal the specified tileValue.
    private boolean containsTile(int tileValue, int indX1, int indY1, int indX2, int indY2) {
        return map.arr[indY1][indX1] == tileValue || map.arr[indY2][indX2] == tileValue ||
               map.arr[indY1][indX2] == tileValue || map.arr[indY2][indX1] == tileValue;
    }
    
    // Helper function that returns the move tile value based on the four indices.
    private int getMoveTile(int indX1, int indY1, int indX2, int indY2) {
        if (containsTile(88, indX1, indY1, indX2, indY2)) {
            return 88;
        } else if (containsTile(81, indX1, indY1, indX2, indY2)) {
            return 81;
        } else if (containsTile(82, indX1, indY1, indX2, indY2)) {
            return 82;
        } else if (containsTile(83, indX1, indY1, indX2, indY2)) {
            return 83;
        }
        return 0;
    }
    
    // Helper function that returns true if any of the four tile indices are forbidden.
    private boolean hasForbiddenTile(int indX1, int indY1, int indX2, int indY2) {
        return forbid.contains(map.arr[indY1][indX1]) || forbid.contains(map.arr[indY2][indX2]) ||
               forbid.contains(map.arr[indY1][indX2]) || forbid.contains(map.arr[indY2][indX1]);
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
        
        movetile = getMoveTile(indX1, indY1, indX2, indY2);
        movingArea = movetile > 80;
        
        return hasForbiddenTile(indX1, indY1, indX2, indY2);
    }

    public boolean isMovingArea() {
        return movingArea;
    }

    public int getMovetile() {
        return movetile;
    }

    public void setMovetile(int movetile) {
        this.movetile = movetile;
    }
}

