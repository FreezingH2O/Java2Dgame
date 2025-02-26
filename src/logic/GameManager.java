package logic;


	import world.map;
import entity.character.Player;
import javafx.scene.canvas.GraphicsContext;

	public class  GameManager {
	    private Player player;
	    private map map1;

	    public GameManager() {
	        map1 = new map();
	        player = new Player(map1);
	    }

	    public void update(GraphicsContext gc) {
	        gc.clearRect(0, 0, map.getMapWidth(), map.getMapHeight());
	        map1.update(gc);
	        player.update(gc);
	    }
	}
