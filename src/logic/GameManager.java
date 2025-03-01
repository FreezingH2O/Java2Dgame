package logic;


	import world.Map;
import world.MapType;
import entity.character.BaseCharacter;

import javafx.scene.canvas.GraphicsContext;

	public class  GameManager {

	    private Map map;
	

	    public GameManager() {
	        map = new Map(MapType.ISLAND);
	
	    }

	    public void update(GraphicsContext gc) {
	        gc.clearRect(0, 0, map.getMapWidth(), map.getMapHeight());
	        map.update(gc);
	        for(BaseCharacter e: map.getEntities()) {
	        	e.update(gc);
	        }

	    
	        
	    }
	}
