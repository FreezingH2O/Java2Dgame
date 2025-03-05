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
import entity.character.MonsterType;  
import entity.character.Player;  
import javafx.application.Platform;  
import javafx.scene.canvas.GraphicsContext;  
import javafx.scene.image.Image;  
import main.Main;  

public class Map {  
    private HashMap<Integer, Image> islandImages = new HashMap<>();  
    private MapType mapType;  
    public int[][] arr;  
    private int tileSize = 48;  
    private int mapWidth, mapHeight;  
    private ArrayList<BaseCharacter> entities;  
    private Player player; // Store the player directly in the map.  

    public Map(MapType type) {  
        mapType = type;  
        entities = new ArrayList<BaseCharacter>();  
        arr = new int[Main.getMapSize()][Main.getMapSize()];  
        setArr(type); //Load the array *first*  

        this.mapHeight = arr.length * tileSize;  
        this.mapWidth = arr[0].length * tileSize;  

        // Load images into the HashMap  
        islandImages.put(0, new Image("island_bg/0.png"));  
        islandImages.put(1, new Image("island_bg/1.png"));  
        islandImages.put(2, new Image("island_bg/2.png"));  
        islandImages.put(3, new Image("island_bg/3.png"));  
        islandImages.put(4, new Image("island_bg/4.png"));  
        islandImages.put(5, new Image("island_bg/5.png"));  

        islandImages.put(5252, new Image("island_bg/5252.png"));  
        islandImages.put(5253, new Image("island_bg/5253.png"));  
        islandImages.put(5254, new Image("island_bg/5254.png"));  
        islandImages.put(5255, new Image("island_bg/5255.png"));  
        islandImages.put(5256, new Image("island_bg/5256.png"));  
        islandImages.put(5257, new Image("island_bg/5257.png"));  
        islandImages.put(5258, new Image("island_bg/5258.png"));  
        islandImages.put(5259, new Image("island_bg/5259.png"));  
        islandImages.put(5260, new Image("island_bg/5260.png"));  
        islandImages.put(5261, new Image("island_bg/5261.png"));  

        //Now find and create the player and monsters *immediately* after loading the array.  
        createEntitiesFromMapData();  


    }  

    private void createEntitiesFromMapData() {  
        for (int y = 0; y < arr.length; y++) {  
            for (int x = 0; x < arr[0].length; x++) {  
                if (arr[y][x] == 99) {  
                    this.player = new Player(x * tileSize, y * tileSize, 3, 200, 100, 20, 48, this);  
                    entities.add(this.player);  // Also add to entities list.  
                    arr[y][x] = 1;  // Replace the '99' with a ground tile.  

                } else if (arr[y][x] == 91) {  
                    HighMonster monster = new HighMonster(MonsterType.FIRE, x * tileSize, y * tileSize, 6, 100, 20, 96, this);  
                    entities.add(monster);  
                    arr[y][x] = 1;  
                } else if (arr[y][x] == 92) {  
                    HighMonster monster = new HighMonster(MonsterType.WATER, x * tileSize, y * tileSize, 6, 100, 20, 96, this);  
                    entities.add(monster);  
                    arr[y][x] = 1;  
                }  
            }  
        }  
    }  

    public void update(GraphicsContext gc) {  
        for (int y = 0; y < arr.length; y++) {  
            for (int x = 0; x < arr[0].length; x++) {  
                if (mapType == mapType.ISLAND) {  
                    Image tileImage = islandImages.get(arr[y][x]);  
                    gc.drawImage(tileImage, x * tileSize, y * tileSize);  

                }  
            }  
        }  
    }  

    public void changeMap(MapType mapType) {  
        setMapType(mapType);  
        setArr(mapType);  
        entitiesClear();  
    }  

    public void entitiesClear() {  
        Platform.runLater(() -> {  
            entities.clear();  
            this.player = null; // Important!  Clear the player reference.  
        });  
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


    public Player getPlayer() {  
        return player;  
    }  

    public void setPlayer(Player player) {  
        this.player = player;  
    }  
}  