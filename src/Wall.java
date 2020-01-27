import java.awt.image.BufferedImage;

public class Wall extends Sprite {
    public Wall(){
        super();
        CollideableSprite.addColliadble(this);
    }
    public Wall(BufferedImage image, int x, int y){
        super(image, x, y);
        CollideableSprite.addColliadble(this);
    }

}
