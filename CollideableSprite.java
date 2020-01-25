import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class CollideableSprite extends Sprite{
    public static ArrayList<Sprite> collideables = new ArrayList<>();
    
    public CollideableSprite(){
        super();
        collideables.add(this);
    }
    public CollideableSprite(BufferedImage image, int x, int y){
        super(image, x, y);
        collideables.add(this);
    }

    public static void addColliadble(Sprite sprite){
        collideables.add(sprite);
    }

    public static ArrayList<Sprite> getCollideables() {
        return collideables;
    }

    public static void setCollideables(ArrayList<Sprite> collideables) {
        CollideableSprite.collideables = collideables;
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
        if (getCollisions().size() > 0) {
            this.x -= x;
            this.y -= y;
        }
    }

    public ArrayList<Sprite> getCollisions(){
        ArrayList<Sprite> collisions = new ArrayList<>();

        for (Sprite sprite : collideables){
            if (sprite.equals(this))
                continue;
            if (sprite.getX() == getX() && sprite.getY() == getY()){
                collisions.add(sprite);
            }
        }

        return collisions;
    }
    
}
