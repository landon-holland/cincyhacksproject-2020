import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class RenderPanel extends JPanel implements KeyListener {
    private int x;
    private int y;
    private int widthOfSprites;
    private int heightOfSprites;
//    private Enemy enemy = new Enemy();
//    private Wall wall = new Wall();
    private boolean[] keyCodes = new boolean[1000];
    private long[] keyCodesTimes = new long[1000];
    private ArrayList<Sprite> sprites = SpriteLoader.load("sprites.txt");

    public RenderPanel(){
        this(0,0,0,0);
    }

    public RenderPanel(int x, int y, int widthOfSprites, int heightOfSprites){
        this.x = x;
        this.y = y;
        this.widthOfSprites = widthOfSprites;
        this.heightOfSprites = heightOfSprites;

        setFocusable(true);
        addKeyListener(this);

//        try {
//            enemy.setImage(ImageIO.read(new File("Sprites\\fireman.png")));
//            wall.setImage(ImageIO.read(new File("Sprites\\brick.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        wall.setX(100);

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
//        enemy.draw(g, this);
//        wall.draw(g, this);
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
        keyCodesTimes[e.getKeyCode()] = System.currentTimeMillis();
//        if (e.getKeyChar() == 'w')
//            y += heightOfSprites;
//        if (e.getKeyChar() == 's')
//            y -= heightOfSprites;
//        if (e.getKeyChar() == 'a')
//            x += widthOfSprites;
//        if (e.getKeyChar() == 'd')
//            x -= widthOfSprites;

        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;

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
