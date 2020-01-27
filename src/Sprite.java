import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Sprite {
    protected BufferedImage image;
    protected int x;
    protected int y;

    public Sprite(){
        this(null, 0, 0);
    }

    public Sprite(BufferedImage image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, RenderPanel renderPanel){
        g.drawImage(image, x, y,
                renderPanel.getWidthOfSprites(), renderPanel.getHeightOfSprites(), null);
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
