import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class RenderPanel extends JPanel implements KeyListener {
    private int widthOfSprites = 50;
    private int heightOfSprites = 50;
    private boolean[] keyCodes = new boolean[1000];
    private long[] keyCodesTimes = new long[1000];
    private ArrayList<Sprite>  sprites;
    private int gameState = 0;

    public RenderPanel(){
        this(50,50);
    }

    public RenderPanel(int widthOfSprites, int heightOfSprites){
        this.widthOfSprites = widthOfSprites;
        this.heightOfSprites = heightOfSprites;

        sprites = SpriteLoader.load("levels\\sprites2.txt", widthOfSprites, heightOfSprites);

        setFocusable(true);
        requestFocus();
        addKeyListener(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                boolean isIdle1 = true;
                Random random = new Random();

                while (true) {
                    if (System.currentTimeMillis() - time > 500){
                        time = System.currentTimeMillis();
                        if (isIdle1)
                            isIdle1 = false;
                        else
                            isIdle1 = true;
                        for (Sprite sprite : sprites) {
                            if (sprite instanceof Enemy) {
                                if (((Enemy) sprite).getDamageSprite().isVisible()){
                                    ((Enemy) sprite).updateAttack();
                                }
                                else {
                                    if (random.nextDouble() > .66) {
                                        ((Enemy) sprite).attack(0, 50);
                                        for (Sprite sprite2 : sprites) {
                                            if (sprite2 instanceof PlayerSprite) {
                                                if (Math.abs(sprite.getX() - sprite2.getX()) <
                                                        Math.abs(sprite.getY() - sprite2.getY())) {
                                                    if (sprite.getY() - sprite2.getY() > 0)
                                                        ((Enemy) sprite).attack(0, -50);
                                                    else
                                                        ((Enemy) sprite).attack(0, 50);
                                                } else {
                                                    if (sprite.getX() - sprite2.getX() > 0)
                                                        ((Enemy) sprite).attack(-50, 0);
                                                    else
                                                        ((Enemy) sprite).attack(50, 0);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (random.nextDouble() > .66){
                                    double rand = random.nextDouble();
                                    if (rand < .25)
                                        sprite.move(widthOfSprites, 0);
                                    else if (rand >= .25 && rand < .5)
                                        sprite.move(-widthOfSprites, 0);
                                    else if (rand > .75)
                                        sprite.move(0, heightOfSprites);
                                    else
                                        sprite.move(0, -heightOfSprites);
                                }


                            }
                        }
                    }

                    for (Sprite sprite : sprites){
                        if (sprite instanceof PlayerSprite){
                            //-------------------------------------------------------------------------------
                            //map loading
                            //------------------------------------------------------------------------------
                            if (gameState == 0){
                                if (sprite.getX() > 1200){
                                    gameState = 1;
                                    sprites = SpriteLoader.load("Levels\\sprites3.txt", 50, 50);
                                    continue;
                                }
                            }
                            else if (gameState == 1){
                                if (sprite.getX() > 400 && sprite.getX() < 800 && sprite.getY() > 600) {
                                    gameState = 2;
                                    sprites = SpriteLoader.load("Levels\\sprites2.txt", 50, 50);
                                    continue;
                                }
                            }


                            if (keyCodes[KeyEvent.VK_W] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_W] > 500) {
                                sprite.move(0, -heightOfSprites);
                                keyCodesTimes[KeyEvent.VK_W] = System.currentTimeMillis();
                                sprite.setImage(((PlayerSprite) sprite).getFaceUp());
                            }
                            if (keyCodes[KeyEvent.VK_S] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_S] > 500) {
                                sprite.move(0, heightOfSprites);
                                keyCodesTimes[KeyEvent.VK_S] = System.currentTimeMillis();
                                sprite.setImage(((PlayerSprite) sprite).getFaceDown());
                            }
                            if (keyCodes[KeyEvent.VK_A] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_A] > 500) {
                                sprite.move(-widthOfSprites, 0);
                                keyCodesTimes[KeyEvent.VK_A] = System.currentTimeMillis();
                                if (isIdle1)
                                    sprite.setImage(((PlayerSprite) sprite).getIdle1());
                                else
                                    sprite.setImage(((PlayerSprite) sprite).getIdle2());

                            }
                            if (keyCodes[KeyEvent.VK_D] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_D] > 500) {
                                sprite.move(widthOfSprites, 0);
                                keyCodesTimes[KeyEvent.VK_D] = System.currentTimeMillis();

                                if (isIdle1)
                                    sprite.setImage(((PlayerSprite) sprite).getIdle1());
                                else
                                    sprite.setImage(((PlayerSprite) sprite).getIdle2());
                            }
                            if (keyCodes[KeyEvent.VK_SPACE] && System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_SPACE] > 500){

                                if (keyCodes[KeyEvent.VK_W]) {
                                    sprite.setImage(((PlayerSprite) sprite).getUpAttack());
                                    ((PlayerSprite) sprite).attack(0, -heightOfSprites);
                                }
                                else if (keyCodes[KeyEvent.VK_S]) {
                                    sprite.setImage(((PlayerSprite) sprite).getDownAttack());
                                    ((PlayerSprite) sprite).attack(0, heightOfSprites);
                                }
                                else if (keyCodes[KeyEvent.VK_A]) {
                                    sprite.setImage(((PlayerSprite) sprite).getLeftAttack());
                                    ((PlayerSprite) sprite).attack(-widthOfSprites, 0);
                                }
                                else {
                                    sprite.setImage(((PlayerSprite) sprite).getAttack());
                                    ((PlayerSprite) sprite).attack(widthOfSprites, 0);
                                }

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
                                if (keyCodes[KeyEvent.VK_W])
                                    sprite.setImage(((PlayerSprite) sprite).getFaceUp());
                                else if (keyCodes[KeyEvent.VK_S])
                                    sprite.setImage(((PlayerSprite) sprite).getFaceDown());
                                else if (keyCodes[KeyEvent.VK_D]){
                                    if (isIdle1)
                                        sprite.setImage(((PlayerSprite) sprite).getWalkLeftFoot());
                                    else
                                        sprite.setImage(((PlayerSprite) sprite).getWalkRightFoot());
                                }
                                else if (keyCodes[KeyEvent.VK_A]){
                                    if (isIdle1)
                                        sprite.setImage(((PlayerSprite) sprite).getLeftWalkLeftFoot());
                                    else
                                        sprite.setImage(((PlayerSprite) sprite).getLeftWalkRightFoot());
                                }
                                else{
                                    if (isIdle1)
                                        sprite.setImage(((PlayerSprite) sprite).getIdle1());
                                    else
                                        sprite.setImage(((PlayerSprite) sprite).getIdle2());
                                }
                                if (System.currentTimeMillis() - keyCodesTimes[KeyEvent.VK_SPACE] > 500){
                                    ((PlayerSprite) sprite).getUmbrella().setVisible(false);
                                    ((PlayerSprite) sprite).getUmbrella().setX(sprite.getX());
                                    ((PlayerSprite) sprite).getUmbrella().setY(sprite.getY());
                                }
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

        for (Sprite sprite : sprites){
            if (sprite instanceof Enemy)
                continue;
            if (sprite instanceof PlayerSprite)
                continue;
            sprite.draw(g, this);
        }
        for (Sprite sprite : sprites){
            if (sprite instanceof Enemy)
                sprite.draw(g, this);
            if (sprite instanceof PlayerSprite)
                sprite.draw(g, this);
        }
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
