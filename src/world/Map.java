package world;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import entity.character.BaseCharacter;
import entity.character.HighMonster;
import entity.character.MonsterType;
import entity.character.Player;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Main;

public class Map {
	private Image grass = new Image("background/grass.png");
	private Image brick = new Image("background/Brick.png");
	private Image water = new Image("background/water.png");
	private Image tree = new Image("background/tree.png");
	private Image floor = new Image("background/floor.png");
	private Image bigStone = new Image("background/Bigstone.png");
	private Image smallStone = new Image("background/smallstone.png");
	private Image dungeonBg = new Image("background/dungeonBg.png");
	private Image grayBrick = new Image("background/grayBrick.png");
	private Image dgfloor = new Image("background/dgfloor.png");
	private Image house = new Image("house.png");
	
	private MapType mapType;

	public int[][] arr;
	private int tileSize = 48;
	private int mapWidth, mapHeight;
	private ArrayList<BaseCharacter> entities;

	public Map(MapType type) {
		mapType = type;
		entities = new ArrayList<BaseCharacter>();
		arr = new int[Main.getMapSize()][Main.getMapSize()];
		setArr(type);

		this.mapHeight = arr.length * tileSize;
		this.mapWidth = arr[0].length * tileSize;

	}

	public void update(GraphicsContext gc) {
		for (int y = 0; y < arr.length; y++) {
			for (int x = 0; x < arr[0].length; x++) {
if(mapType== mapType.ISLAND) {
				if (arr[y][x] == 0) {
					gc.drawImage(water, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 1) {
					gc.drawImage(grass, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 2) {
					gc.drawImage(grass, x * tileSize, y * tileSize);
					gc.drawImage(tree, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 3) {
					gc.drawImage(smallStone, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 4) {
					gc.drawImage(bigStone, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 5) {
					gc.drawImage(floor, x * tileSize, y * tileSize);
				} else if (arr[y][x] == 88) {
					gc.drawImage(brick, x * tileSize, y * tileSize);
				}
				else {
					gc.drawImage(grass, x * tileSize, y * tileSize);
				}
}
else if(mapType== mapType.DUNGEON) {
	if (arr[y][x] == 0) {
		gc.drawImage(dungeonBg, x * tileSize, y * tileSize);
	} else if (arr[y][x] == 1) {
		gc.drawImage(dgfloor, x * tileSize, y * tileSize);
	} else if (arr[y][x] == 2) {
		gc.drawImage(grayBrick, x * tileSize, y * tileSize);
	}
	else {
		gc.drawImage(grass, x * tileSize, y * tileSize);
	}
}
				
				
				if (arr[y][x] == 99) {
					entities.add(new Player(x * tileSize, y * tileSize, 3, 200, 100, 20, 48, this));
					arr[y][x] = 1;

				} else if (arr[y][x] == 91) {
					entities.add(new HighMonster(MonsterType.FIRE, x * tileSize, y * tileSize, 6, 100, 20, 96, this));
					arr[y][x] = 1;
				} else if (arr[y][x] == 92) {
					entities.add(new HighMonster(MonsterType.WATER, x * tileSize, y * tileSize, 6, 100, 20, 96, this));
					arr[y][x] = 1;

				} else if (arr[y][x] == 55) {
					gc.drawImage(house, (x + 1) * tileSize - 160, (y + 1) * tileSize - 224);

				}


			}
		}

	}

	public void changeMap(MapType mapType) {
		setMapType(mapType);
		setArr(mapType);
		entitiesClear();
		//Platform.runLater(()->{getEntities().clear();}) ;
		
	}
	public void entitiesClear() {
		Platform.runLater(()->{entities.clear();}) ;
	}
	public ArrayList<BaseCharacter> getEntities() {
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

	public int[][] getArr() {
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
	}

}
