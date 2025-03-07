package entity.character;

import java.util.Arrays;
import java.util.List;

import components.StatusDisplay;
import javafx.scene.image.Image;
import world.Map;

public class HighMonster extends BaseMonster {
	private static List<String> highBossLi = Arrays.asList("FIRE", "WIND", "WATER", "DARK");

	public HighMonster(MonsterType type, double posX, double posY, int speed, int health, int attack, int size,
			Map map) {
		super(type, posX, posY, health, 10, attack, size, map);

		pic = new Image("monster/" + monsterType + "monL.png");

	}

	public static List<String> getHighBossLi() {
		return highBossLi;
	}

	public static void setHighBossLi(List<String> li) {
		highBossLi = li;
	}
	
	public void setHealth(int health) {
		super.setHealth(health);
		if(isDeath()) {
			StatusDisplay.getInstant().gainExperience(50);
		}
	}

}
