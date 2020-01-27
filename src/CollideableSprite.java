import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class CollideableSprite extends Sprite{
    public static ArrayList<Sprite> collideables = new ArrayList<>();
    private int health = 25;
    public CollideableSprite(){
        super();
        collideables.add(this);
    }
    public CollideableSprite(BufferedImage image, int x, int y){
        super(image, x, y);
        collideables.add(this);
    }

    @Override
    public void draw(Graphics g, RenderPanel r){
        if (health > 0) {
            super.draw(g, r);
            g.setColor(Color.RED);
            g.fillRect((x - r.getX() + (r.getWidthOfSprites() / 4)), y - r.getY() - 15, health, 7);
            g.setColor(Color.RED);
        }
    }

    public void setHealth(int hp){
        health = hp;
    }

    public void damage(int damage){
        health -= damage;
        if (health <= 0) {
            try {
                CollideableSprite.getCollideables().remove(this);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public int getHealth(){
        return health;
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
        if (this.x < 0)
            this.x = 0;
        if (this.y < 0)
            this.y = 0;
        if (this.y > 650)
            this.y = 650;
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