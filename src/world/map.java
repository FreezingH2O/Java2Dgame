package world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class map {
	private Image grass = new Image("background/grass.png");
	private Image brick = new Image("background/Brick.png");
	private Image water = new Image("background/water.png");
	private Image tree = new Image("background/tree.png");
	private Image floor = new Image("background/floor.png");
	// private Image grassborder = new Image("background/grassborder.png");
	public int[][] arr;
	private int tileSize = 48;
	private static int mapWidth, mapHeight;
	public boolean collision;

	public map() {

		String filename = "map/worldmap.txt"; // Path inside `resources/`
        arr = new int[60][60];

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found in resources: " + filename);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < 60) {
                String[] values = line.split(" ");
                for (int col = 0; col < 60; col++) {
                    arr[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mapHeight = arr.length * tileSize;
        this.mapWidth = arr[0].length * tileSize;

	}

	public void update(GraphicsContext gc) {

		for (int y = 0; y < arr.length; y++) {
			for (int x = 0; x < arr[0].length; x++) {

				if (arr[y][x] == 18) {
					gc.drawImage(grass, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 12) {
					gc.drawImage(brick, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 0) {
					gc.drawImage(water, x * tileSize, y * tileSize);
				//	gc.drawImage(floor, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 3) {
					gc.drawImage(floor, x * tileSize, y * tileSize);

				} else if (arr[y][x] == 16) {
					gc.drawImage(grass, x * tileSize, y * tileSize);
					gc.drawImage(tree, x * tileSize, y * tileSize);

				}else {
					gc.drawImage(grass, x * tileSize, y * tileSize);
				}

			}
		}

	}

	public static int getMapWidth() {
		return mapWidth;
	}

	public static int getMapHeight() {
		return mapHeight;
	}

	public int[][] getArr() {
		return arr;
	}

}
