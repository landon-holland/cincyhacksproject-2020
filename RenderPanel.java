import javax.sound.sampled.AudioFormat;
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
    private volatile int gameState = 0;
    ArrayList<Enemy> spiderwebs = new ArrayList<>();

    public RenderPanel(){
        this(50,50, 0);
    }

    public RenderPanel(int widthOfSprites, int heightOfSprites, int state){
        this.widthOfSprites = widthOfSprites;
        this.heightOfSprites = heightOfSprites;


        //sprites = SpriteLoader.load("levels\\StartingArea.txt", widthOfSprites, heightOfSprites);
        if (state == 0)
            sprites = SpriteLoader.load("levels\\StartingArea.txt", widthOfSprites, heightOfSprites);
        else {
            sprites = SpriteLoader.load("levels\\Boss.txt", widthOfSprites, heightOfSprites);
            gameState = 6;
        }
        setFocusable(true);
        requestFocus();
        addKeyListener(this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                boolean isIdle1 = true;
                Random random = new Random();

                if (gameState == 6){
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 200, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 250, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 300, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 350, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 150, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 400, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 450, SpriteLoader.spiderWeb, null));
                    spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 500, SpriteLoader.spiderWeb, null));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("audio\\bossbattle.wav"));

                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioIn);
                                    clip.start();

                                    AudioFormat format = audioIn.getFormat();
                                    long frames = audioIn.getFrameLength();
                                    double durationInSeconds = (frames+0.0) / format.getFrameRate();
                                    long time = System.currentTimeMillis();
                                    while (System.currentTimeMillis() - time < durationInSeconds * 1000){
                                        if (gameState == 9) {
                                            clip.stop();
                                            return;
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }

                while (true) {
                    if (System.currentTimeMillis() - time > 500){
                        time = System.currentTimeMillis();
                        if (isIdle1)
                            isIdle1 = false;
                        else
                            isIdle1 = true;

                        if (gameState >= 6){
                            for (Sprite sprite : spiderwebs){
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
                            }
                        }

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
                                    sprites = SpriteLoader.load("Levels\\SecondArea.txt", 50, 50);
                                    continue;
                                }
                            }
                            else if (gameState == 1){
                                if (sprite.getX() > 400 && sprite.getX() < 800 && sprite.getY() > 600) {
                                    gameState = 2;
                                    sprites = SpriteLoader.load("Levels\\HealthRoom.txt", 50, 50);
                                    continue;
                                }
                                if (sprite.getX() > 1200){
                                    gameState = 3;
                                    sprites = SpriteLoader.load("Levels\\Maze.txt", 50, 50);
                                    continue;
                                }
                            }
                            else if (gameState == 2){
                                if (sprite.getY() == 50){
                                    gameState = 1;
                                    sprites = SpriteLoader.load("Levels\\SecondAreaBottom.txt", 50, 50);
                                    continue;
                                }
                            }
                            else if (gameState == 3){
                                if (sprite.getX() > 1200 && sprite.getY() > 150){
                                    gameState = 4;
                                    sprites = SpriteLoader.load("Levels\\AreaBeforeBoss.txt", 50, 50);
                                    continue;
                                }
                            }
                            else if (gameState == 4){
                                if (sprite.getX() > 1200){
                                    gameState = 5;
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

                                if (gameState == 6){
                                    if (sprite.getX() >= 1000 && sprite.getY() >= 300 && sprite.getY() <= 400) {
                                        gameState = 7;
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 200, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 250, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 300, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 350, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 150, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 400, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 450, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 100, 500, SpriteLoader.spiderWeb, null));
                                    }
                                }
                                else if (gameState == 7){
                                    if (sprite.getX() <= 250 && sprite.getY() >= 300 && sprite.getY() <= 400) {
                                        gameState = 8;
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 200, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 250, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 300, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 350, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 150, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 400, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 450, SpriteLoader.spiderWeb, null));
                                        spiderwebs.add(new Enemy(SpriteLoader.spiderWeb, 1300, 500, SpriteLoader.spiderWeb, null));
                                    }
                                }
                                else if (gameState == 8) {
                                    if (sprite.getX() >= 1000 && sprite.getY() >= 300 && sprite.getY() <= 400) {
                                        spiderwebs = new ArrayList<>();
                                        gameState = 9;
                                    }
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
        if (gameState == 6)
            g.drawImage(SpriteLoader.spider, 1100, 200, 250,250, null);
        if (gameState == 7)
            g.drawImage(SpriteLoader.leftSpider, 0, 200, 250,250, null);
        if (gameState == 8)
            g.drawImage(SpriteLoader.spider, 1100, 200, 250,250, null);
        if (gameState == 9){
            g.drawImage(SpriteLoader.deadSpider, 500, 200, 250,250, null);
        }

        for (Enemy sprite : spiderwebs){
            sprite.getDamageSprite().draw(g, this);
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

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public boolean[] getKeyCodes() {
        return keyCodes;
    }

    public void setKeyCodes(boolean[] keyCodes) {
        this.keyCodes = keyCodes;
    }

    public long[] getKeyCodesTimes() {
        return keyCodesTimes;
    }

    public void setKeyCodesTimes(long[] keyCodesTimes) {
        this.keyCodesTimes = keyCodesTimes;
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }
}
