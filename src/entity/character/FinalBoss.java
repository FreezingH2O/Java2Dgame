package entity.character;

import components.StatusDisplay;
import javafx.scene.image.Image;
import world.Map;

public class FinalBoss extends HighMonster {

	private static FinalBoss instant ;
	public FinalBoss(double posX, double posY, int speed, int health, int attack, Map map) {

		super(MonsterType.FINAL_BOSS, posX, posY, speed ,health, attack, 96, map);
		pic  = new Image("boss/boss.png");
		
		System.out.println(getElementType());
		
	}

	public void setHealth(int health) {
		super.setHealth(health);
		if (isDeath()) {
			StatusDisplay.getInstant().gainExperience(200);
		}
	}
	

	public static FinalBoss getInstant() {
		return instant;
	}

	public static void setInstant(FinalBoss boss) {
		instant = boss;
	}
	
	


}
