import java.awt.image.BufferedImage;

public class DamageSprite extends CollideableSprite {
    public double damage;

    public DamageSprite(){
        super();
    }
    public DamageSprite(BufferedImage image, int x, int y, double damage){
        super(image, x, y);
        this.damage = damage;
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
        if (getCollisions().size() > 0) {
            for (Sprite sprite : getCollisions()){
                if (sprite instanceof CollideableSprite){
                    //sprite.damage(damage)
                }
            }
            this.x -= x;
            this.y -= y;
        }
    }

}
