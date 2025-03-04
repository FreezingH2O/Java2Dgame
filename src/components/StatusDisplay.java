package components;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StatusDisplay extends Pane {
    private static Bar healthBar;
    private static Bar manaBar;
    private static int level;
    private static int experience;
    private static int experienceToNextLevel;
    private static Label levelUI;
    

    public StatusDisplay(int maxHealth, int maxMana) {
        healthBar = new Bar(maxHealth, maxHealth,20,Color.RED);
        manaBar = new Bar(maxMana,maxMana,20,Color.CYAN);
        level = 1;
        experience = 0;
        experienceToNextLevel = 100;
        
        levelUI = new Label("00");
        levelUI.setTranslateX(-60);
        levelUI.setTranslateY(8);
        levelUI.setFont(Font.font("tahoma",FontWeight.BOLD,45));
        

        
        Circle outerBorder = new Circle(50);
        outerBorder.setFill(Color.rgb(220, 117, 22)); // Outer border color
        outerBorder.setStroke(Color.rgb(150, 70, 15)); // Darker stroke
        outerBorder.setStrokeWidth(5);
        
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(100, 50, 10, 0.7)); // Dark brown shadow with some transparency
        shadow.setRadius(10); // Spread of the shadow
        shadow.setOffsetX(5); // Horizontal shadow offset
        shadow.setOffsetY(5); // Vertical shadow offset

        
        // Apply the shadow to the circle
        outerBorder.setEffect(shadow);
        outerBorder.setTranslateX(-30);
        outerBorder.setTranslateY(35);

        Circle innerCircle = new Circle(45);
        innerCircle.setFill(Color.rgb(255, 208, 126));

        innerCircle.setTranslateX(-30);
        innerCircle.setTranslateY(35);
        
    
        
        healthBar.setTranslateX(10);
        healthBar.setTranslateY(10);

        manaBar.setTranslateX(10);
        manaBar.setTranslateY(40);
        
        this.getChildren().addAll(healthBar, manaBar,outerBorder,innerCircle,levelUI);
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
        
        levelUI.setText("LV. "+level);
        
        System.out.println("Level Up! New Level: " + level);
    }

    public static  void displayStatus(GraphicsContext gc, double x, double y) {
        System.out.println("Level: " + level);
        System.out.println("XP: " + experience + "/" + experienceToNextLevel);
    }
}
