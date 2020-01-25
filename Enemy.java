import java.awt.image.BufferedImage;

public class Enemy extends CollideableSprite{
    private String attackSound;

    public Enemy(){
        super();
    }
    public Enemy(BufferedImage image, int x, int y){
        super(image, x, y);
    }



}
