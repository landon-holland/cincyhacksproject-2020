import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class RenderPanel extends JPanel implements KeyListener {
    private int x = 0;
    private int y = 0;
    private int widthOfSprites = 50;
    private int heightOfSprites = 50;
    private boolean[] keyCodes = new boolean[1000];
    private long[] keyCodesTimes = new long[1000];
    private ArrayList<Sprite>  sprites;

    public RenderPanel(){
        this(0,0,50,50);
    }

    public RenderPanel(int x, int y, int widthOfSprites, int heightOfSprites){
        this.x = x;
        this.y = y;
        this.widthOfSprites = widthOfSprites;
        this.heightOfSprites = heightOfSprites;

        sprites = SpriteLoader.load("sprites.txt", widthOfSprites, heightOfSprites);

        setFocusable(true);
        addKeyListener(this);

        new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Sprite sprite : sprites){
                    if (sprite instanceof Player){
                        if (keyCodes[KeyEvent.VK_W])
                            sprite.move(0, -heightOfSprites);
                        if (keyCodes[KeyEvent.VK_S])
                            sprite.move(0, heightOfSprites);
                        if (keyCodes[KeyEvent.VK_A])
                            sprite.move(-widthOfSprites, 0);
                        if (keyCodes[KeyEvent.VK_D])
                            sprite.move(widthOfSprites, 0);
                        repaint();
                    }
                }

            }
        }).start();
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Sprite sprite : sprites){
            sprite.draw(g, this);
        }
    }


    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidthOfSprites() {
        return widthOfSprites;
    }

    public void setWidthOfSprites(int widthOfSprites) {
        this.widthOfSprites = widthOfSprites;
    }

    public int getHeightOfSprites() {
        return heightOfSprites;
    }

    public void setHeightOfSprites(int heightOfSprites) {
        this.heightOfSprites = heightOfSprites;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCodes[e.getKeyCode()] = true;

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (System.currentTimeMillis() - keyCodesTimes[e.getKeyCode()] < 200){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    keyCodes[e.getKeyCode()] = false;
                }
            }).start();
        } else
            keyCodes[e.getKeyCode()] = false;
    }
}
