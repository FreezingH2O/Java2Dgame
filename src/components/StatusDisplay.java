package components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StatusDisplay extends Pane {
    private static Bar healthBar;
    private static Bar manaBar;
    private static int level;
    private static int experience;
    private static int experienceToNextLevel;

    public StatusDisplay(int maxHealth, int maxMana) {
        healthBar = new Bar(maxHealth, maxHealth,20,Color.RED);
        manaBar = new Bar(maxMana,maxMana,20,Color.CYAN);
        level = 1;
       experience = 0;
        experienceToNextLevel = 100; 
        
        healthBar.setTranslateX(10);
        healthBar.setTranslateY(10);

        manaBar.setTranslateX(10);
        manaBar.setTranslateY(40);
        
        this.getChildren().addAll(healthBar, manaBar);
       this.setTranslateX(100);
       this.setTranslateY(20);

    }

    public static void takeDamage(int damage) {
        healthBar.updateBar(healthBar.getCurrentStat()-damage);
    }

    public static void heal(int amount) {
        healthBar.updateBar(healthBar.getCurrentStat()+amount);
    }

    public static void useMana(int amount) {
    	 healthBar.updateBar(healthBar.getCurrentStat()-amount);
    }

    public static void regainMana(int amount) {
         healthBar.updateBar(healthBar.getCurrentStat()+amount);
    }

    public static  void gainExperience(int amount) {
       experience += amount;
        while (experience >= experienceToNextLevel) {
            levelUp();
        }
    }

    private static  void levelUp() {
        experience -= experienceToNextLevel;
        level++;
        experienceToNextLevel += 50; 
        
        healthBar.setMaxStat(healthBar.getMaxStat()+10);
        healthBar.setCurrentStat(healthBar.getMaxStat());
        
        manaBar.setMaxStat(healthBar.getMaxStat()+5);
        manaBar.setCurrentStat(healthBar.getMaxStat());
        System.out.println("Level Up! New Level: " + level);
    }

    public static  void displayStatus(GraphicsContext gc, double x, double y) {
        System.out.println("Level: " + level);
        System.out.println("XP: " + experience + "/" + experienceToNextLevel);
    }
}
