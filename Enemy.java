import java.awt.image.BufferedImage;

public class Enemy extends CollideableSprite{
    private String attackSound;
    private BufferedImage attack;

    public Enemy(){
        super();
    }
    public Enemy(BufferedImage image, int x, int y){
        super(image, x, y);
    }

    public Enemy(BufferedImage image, int x, int y, BufferedImage attack, String attackSound){
        this(image, x, y);
        this.attack = attack;
        this.attackSound = attackSound;
    }

    public String getAttackSound() {
        return attackSound;
    }

    public void setAttackSound(String attackSound) {
        this.attackSound = attackSound;
    }

    public BufferedImage getAttack() {
        return attack;
    }

    public void setAttack(BufferedImage attack) {
        this.attack = attack;
    }
}
