package logic;


	import world.map;
import world.mapType;
import entity.character.BaseCharacter;

import javafx.scene.canvas.GraphicsContext;

	public class  GameManager {

	    private map map;
	

	    public GameManager() {
	        map = new map(mapType.ISLAND);
	
	    }

	    public void update(GraphicsContext gc) {
	        gc.clearRect(0, 0, map.getMapWidth(), map.getMapHeight());
	        map.update(gc);
	        for(BaseCharacter e: map.getEntities()) {
	        	e.update(gc);
	        }

	    
	        
	    }
	}
