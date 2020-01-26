import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
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
        requestFocus();
        addKeyListener(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Sprite sprite : sprites){
                        if (sprite instanceof PlayerSprite){
                            if (keyCodes[KeyEvent.VK_W] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_W] > 500) {
                                sprite.move(0, -heightOfSprites);
                                keyCodesTimes[KeyEvent.VK_W] = System.currentTimeMillis();
                            }
                            if (keyCodes[KeyEvent.VK_S] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_S] > 500) {
                                sprite.move(0, heightOfSprites);
                                keyCodesTimes[KeyEvent.VK_S] = System.currentTimeMillis();
                            }
                            if (keyCodes[KeyEvent.VK_A] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_A] > 500) {
                                sprite.move(-widthOfSprites, 0);
                                keyCodesTimes[KeyEvent.VK_A] = System.currentTimeMillis();
                            }
                            if (keyCodes[KeyEvent.VK_D] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_D] > 500) {
                                sprite.move(widthOfSprites, 0);
                                keyCodesTimes[KeyEvent.VK_D] = System.currentTimeMillis();
                            }
                            if (keyCodes[KeyEvent.VK_SPACE] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_SPACE] > 500){
                                sprite.setImage(((PlayerSprite) sprite).getFrostBreath());
                                keyCodesTimes[KeyEvent.VK_SPACE] = System.currentTimeMillis();
                                try {
                                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("audio\\swing sound.wav"));

                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioIn);
                                    clip.start();
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (!keyCodes[KeyEvent.VK_SPACE]) {
                                sprite.setImage(((PlayerSprite) sprite).getIdle1());
                            }

                            repaint();
                        }
                    }
                }
            }
        }).start();
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Sprite player = null;
        for (Sprite sprite : sprites){
            sprite.draw(g, this);
            if (sprite instanceof PlayerSprite)
                player = sprite;
        }
        if (player != null)
            player.draw(g, this);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCodes[e.getKeyCode()] = false;
    }
}
