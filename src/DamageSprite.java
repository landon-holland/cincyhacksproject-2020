import java.awt.*;
import java.awt.image.BufferedImage;

public class DamageSprite extends CollideableSprite {
    private int damage;
    private boolean isVisible = false;

    public DamageSprite(){
        super();
    }
    public DamageSprite(BufferedImage image, int x, int y, int damage){
        super(image, x, y);
        this.damage = damage;
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
        if (getCollisions().size() > 0) {
            for (Sprite sprite : getCollisions()){
                if (sprite instanceof CollideableSprite){
                    ((CollideableSprite) sprite).damage(damage);
                }
            }
        }
    }

    public void draw(Graphics g, RenderPanel r){
        if (isVisible){
            g.drawImage(image, x - r.getX(), y - r.getY(),
                    r.getWidthOfSprites(), r.getHeightOfSprites(), null);
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
