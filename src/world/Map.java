// Map.java  
package world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import entity.character.BaseCharacter;
import entity.character.HighMonster;
import entity.character.LowMonster;
import entity.character.MonsterType;
import entity.character.Player;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Main;

public class Map {
	private static HashMap<Integer, Image> islandImages = new HashMap<>();
	private MapType mapType;
	public static int[][] arr;
	private int tileSize = 48;
	private int mapWidth, mapHeight;
	private static ArrayList<BaseCharacter> entities;
	private final static Image house = new Image("house.png");
	private final static Image door = new Image("background/door.png");
	private Player player;

	public Map(MapType type) {
		mapType = type;
		entities = new ArrayList<BaseCharacter>();
		arr = new int[Main.getMapSize()][Main.getMapSize()];
		setArr(type);

		this.mapHeight = arr.length * tileSize;
		this.mapWidth = arr[0].length * tileSize;

		createEntitiesFromMapData();
		setImage();

	}

	public void update(GraphicsContext gc) {
		for (int y = 0; y < arr.length; y++) {
			for (int x = 0; x < arr[0].length; x++) {

				Image tileImage = islandImages.get(arr[y][x]);
				if (tileImage == null)
					gc.drawImage(islandImages.get(1), x * tileSize, y * tileSize);
				else
					gc.drawImage(tileImage, x * tileSize, y * tileSize);

				if (arr[y][x] == 55) {
					gc.drawImage(house, (x + 1) * tileSize - 160, (y + 1) * tileSize - 224);
				} else if (arr[y][x] == 89) {
					gc.drawImage(door, (x + 1) * tileSize - 96, (y + 1) * tileSize - 96);

				}
			}
		}
	
	}

	private void createEntitiesFromMapData() {
		for (int y = 0; y < arr.length; y++) {
			for (int x = 0; x < arr[0].length; x++) {
				if (arr[y][x] == 99) {
					Player.setInstant(new Player(x * tileSize, y * tileSize, 5, 200, 100, 20, 48, this));
					entities.add(Player.getInstant());
					setPlayer(Player.getInstant());
					arr[y][x] = 1;

				} else if (arr[y][x] == 91 && HighMonster.getHighBossLi().contains(MonsterType.FIRE + "")) {
					entities.add(new HighMonster(MonsterType.FIRE, x * tileSize, y * tileSize, 6, 300, 10, 96, this));
					arr[y][x] = 1;
				} else if (arr[y][x] == 92 && HighMonster.getHighBossLi().contains(MonsterType.WATER + "")) {
					entities.add(new HighMonster(MonsterType.WATER, x * tileSize, y * tileSize, 6,300, 10, 96, this));
					arr[y][x] = 1;
				} else if (arr[y][x] == 93 && HighMonster.getHighBossLi().contains(MonsterType.WIND + "")) {
					entities.add(new HighMonster(MonsterType.WIND, x * tileSize, y * tileSize, 6, 300, 10, 96, this));
					arr[y][x] = 1;
				} else if (arr[y][x] == 94 && HighMonster.getHighBossLi().contains(MonsterType.DARK + "")) {
					entities.add(new HighMonster(MonsterType.DARK, x * tileSize, y * tileSize, 6, 300, 10, 96, this));
					arr[y][x] = 1;
				}
				else if(arr[y][x]==1) {
					if(Math.random() < 0.05) {
					entities.add(new LowMonster( x * tileSize, y * tileSize, 3, 100, 10, this));
					 
					}
				}
			}

		}
		
		System.out.println(Map.getEntities().size());
	}

	public void entitiesClear() {
		Platform.runLater(() -> {
			entities.clear();
		}) ;
	}

	public void changeMap(MapType mapType) {
		setMapType(mapType);
		System.out.println(mapType);
		setArr(mapType);
		entitiesClear();
		Platform.runLater(() -> {
			createEntitiesFromMapData();
		});
	}

	public static ArrayList<BaseCharacter> getEntities() {
		return entities;
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public static int[][] getArr() {
		return arr;
	}

	public void setArr(MapType type) {

		String filename = "map/" + type + ".txt";
		int[][] newArr = new int[Main.getMapSize()][Main.getMapSize()];

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename)) {
			if (inputStream == null) {
				throw new FileNotFoundException("File not found in resources: " + filename);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			int row = 0;

			while ((line = br.readLine()) != null && row < Main.getMapSize()) {
				String[] values = line.split(" ");
				for (int col = 0; col < Main.getMapSize(); col++) {
					newArr[row][col] = Integer.parseInt(values[col]);
				}
				row++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.arr = newArr;

		setImage();
	}

	public void setImage() {
		islandImages.clear();
		
		tryLoadImage(getMapType() + "/0.png", 0);
		tryLoadImage(getMapType() + "/1.png", 1);
		tryLoadImage(getMapType() + "/2.png", 2);
		tryLoadImage(getMapType() + "/3.png", 3);
		tryLoadImage(getMapType() + "/4.png", 4);
		tryLoadImage(getMapType() + "/5.png", 5);
		tryLoadImage(getMapType() + "/6.png", 6);
		tryLoadImage(getMapType() + "/7.png", 7);
		tryLoadImage(getMapType() + "/9.png", 9);
		
		tryLoadImage(getMapType() + "/5252.png", 5252);
		tryLoadImage(getMapType() + "/5253.png", 5253);
		tryLoadImage(getMapType() + "/5254.png", 5254);
		tryLoadImage(getMapType() + "/5255.png", 5255);
		tryLoadImage(getMapType() + "/5256.png", 5256);
		tryLoadImage(getMapType() + "/5257.png", 5257);
		tryLoadImage(getMapType() + "/5258.png", 5258);
		tryLoadImage(getMapType() + "/5259.png", 5259);
		tryLoadImage(getMapType() + "/5260.png", 5260);
		tryLoadImage(getMapType() + "/5261.png", 5261);
		
		tryLoadImage(getMapType() + "/5270.png", 5270);
        tryLoadImage(getMapType() + "/5271.png", 5271);
        tryLoadImage(getMapType() + "/5272.png", 5272);
        tryLoadImage(getMapType() + "/5273.png", 5273);
        tryLoadImage(getMapType() + "/5274.png", 5274);
        tryLoadImage(getMapType() + "/5275.png", 5275);
        tryLoadImage(getMapType() + "/5276.png", 5276);
        tryLoadImage(getMapType() + "/5277.png", 5277);
        tryLoadImage(getMapType() + "/5278.png", 5278);
        tryLoadImage(getMapType() + "/5279.png", 5279);
        tryLoadImage(getMapType() + "/5280.png", 5280);
        tryLoadImage(getMapType() + "/5281.png", 5281);
        tryLoadImage(getMapType() + "/5282.png", 5282);
        tryLoadImage(getMapType() + "/5283.png", 5283);
        
        tryLoadImage(getMapType() + "/5300.png", 5300);
        tryLoadImage(getMapType() + "/5301.png", 5301);
        tryLoadImage(getMapType() + "/5302.png", 5302);
        tryLoadImage(getMapType() + "/5303.png", 5303);
        tryLoadImage(getMapType() + "/5304.png", 5304);
        tryLoadImage(getMapType() + "/5305.png", 5305);
        tryLoadImage(getMapType() + "/5306.png", 5306);
        tryLoadImage(getMapType() + "/5307.png", 5307);
        tryLoadImage(getMapType() + "/5308.png", 5308);
        tryLoadImage(getMapType() + "/5309.png", 5309);
        tryLoadImage(getMapType() + "/5310.png", 5310);

        
	}

	private void tryLoadImage(String path, int key) {
		try {
			islandImages.put(key, new Image(path));
		} catch (IllegalArgumentException e) {
			System.err.println("Could not load image: " + path);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	

}
