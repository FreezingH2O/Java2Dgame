package entity.effect;

import entity.ElementType;
import entity.character.BaseCharacter;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class GameEffect {
    protected double x;
	protected double y; 
    protected double speedX;
	protected double speedY; 
    protected boolean isActive; 
    private double speed; 
    protected Image pic;
    private ElementType type;
private BaseCharacter target;
    public GameEffect(ElementType type ,double startX, double startY, double targetX, double targetY, double speed,BaseCharacter target) {
        this.x = startX;
        this.y = startY;
        this.speed = 2; speed = 2;
        this.isActive = true;
        this.target = target;
       // System.out.println("skill/"+type+".gif");
        this.type =type;
        if(!(this instanceof arrow)) {
        this.pic = new Image("skill/"+type+".gif");}
        

        double dx = targetX - startX;
        double dy = targetY - startY;
        double distance = Math.sqrt(dx * dx + dy * dy);


        if (distance != 0) {
            this.speedX = (dx / distance) * speed;
            this.speedY = (dy / distance) * speed;
        } else {
            this.speedX = 0;
            this.speedY = 0;
        }
    }

    public void update() {
        if (!isActive) return;

        x += speedX;
        y += speedY;


        if (isTargetFarAway()) {
            isActive = false; 
        }
    }

    private boolean isTargetFarAway() {

        int targetX = (int) target.getPosX();
        int targetY = (int) target.getPosY();


        double distance = Math.sqrt(Math.pow(x - targetX, 2) + Math.pow(y - targetY, 2));


        final double MAX_DISTANCE = 500.0;

        return distance > MAX_DISTANCE; 
    }
    
    public void render(GraphicsContext gc) {
        if (!isActive) return;

        double scaleFactor = 0.8; 

  
        double resizedWidth = pic.getWidth() * scaleFactor;
        double resizedHeight = pic.getHeight() * scaleFactor;

        gc.save();
        gc.translate(x, y);
        if(type!=ElementType.WIND &&type!=ElementType.DARK ) {
            double angle = Math.atan2(speedY, speedX); 
            gc.rotate(Math.toDegrees(angle));}
        
        
        
        
        gc.drawImage(pic, -resizedWidth / 1.5, -resizedHeight / 1.5, resizedWidth, resizedHeight);
        gc.restore();
    }

    public boolean checkCollision(BaseCharacter player) {
    	if (player == null)return false;
//    	System.out.println(player.getPosX()+ " "+player.getPosY());
//    	System.out.println(x+" "+y);
        double distance = Math.sqrt(Math.pow(player.getPosX() - x, 2) + Math.pow(player.getPosY() - y, 2));
        return distance < 10; 
    }

    public void deactivate() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
