package logic;


	import world.map;
import entity.character.BaseCharacter;

import javafx.scene.canvas.GraphicsContext;

	public class  GameManager {

	    private map map1;
	

	    public GameManager() {
	        map1 = new map();
	
	    }

	    public void update(GraphicsContext gc) {
	        gc.clearRect(0, 0, map1.getMapWidth(), map1.getMapHeight());
	        map1.update(gc);
	        for(BaseCharacter e: map1.getEntities()) {
	        	e.update(gc);
	        }

	    
	        
	    }
	}
