import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Enemy extends CollideableSprite{
    private String attackSound;
    private BufferedImage attack;
    private DamageSprite damageSprite;
    private int moveX;
    private int moveY;

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
        damageSprite = new DamageSprite(attack, 0, 0, 5);
    }

    public int attackCounter;
    public void attack(int x, int y){
        if (getHealth() <= 0)
            return;
        damageSprite.setX(getX());
        damageSprite.setY(getY());
        moveX = x;
        moveY = y;
        damageSprite.move(moveX, moveY);
        damageSprite.setVisible(true);
        attackCounter = 0;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(attackSound));

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void updateAttack(){
        damageSprite.move(moveX, moveY);
        attackCounter++;
        if (damageSprite.getCollisions().size() > 0 || attackCounter >= 20){
            moveX = 0;
            moveY = 0;
            damageSprite.setX(x);
            damageSprite.setY(y);
            damageSprite.setVisible(false);
        }
    }

    public void draw(Graphics g, RenderPanel r){
        super.draw(g,r);
        damageSprite.draw(g,r);
    }

    public void damage(int damage){
        super.damage(damage);
        if (getHealth() <= 0){
            try {
                CollideableSprite.getCollideables().remove(damageSprite);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

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

    public DamageSprite getDamageSprite() {
        return damageSprite;
    }

    public void setDamageSprite(DamageSprite damageSprite) {
        this.damageSprite = damageSprite;
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }
}
