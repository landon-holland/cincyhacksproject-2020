import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpriteLoader {
    private static BufferedImage idle1;
    private static BufferedImage idle2;
    private static BufferedImage brick;
    private static BufferedImage fireball;
    private static BufferedImage fireman;
    private static BufferedImage frostBreath;
    private static BufferedImage grass;
    private static BufferedImage rainball;
    private static BufferedImage raindrop;
    private static BufferedImage snowball;
    private static BufferedImage snowman;
    private static BufferedImage umbrella;
    private static BufferedImage walkLeftFoot;
    private static BufferedImage walkRightFoot;
    private static BufferedImage asphaltBlank;
    private static BufferedImage asphaltYellowLine;
    private static BufferedImage attack;
    private static BufferedImage faceUp;
    private static BufferedImage lightBall;
    private static BufferedImage lightningMan;
    private static BufferedImage spider;
    private static BufferedImage spiderWeb;
    private static BufferedImage upAttack;


    private static String fireAttack = "audio\\fire attack.wav";
    private static String snowSound = "audio\\snow attack.wav";
    private static String waterAttack = "audio\\water attack.wav";
    private static String zapAttack = "audio\\zap attack.wav";
    private static String hitSound = "audio\\hit sound.wav";
    private static String swingSound = "audio\\swing sound.wav";
    private static String walkSound = "audio\\walk sound.wav";

    static {
        try {
            idle1 = ImageIO.read(new File("Sprites\\idleframe1.png"));
            idle2 = ImageIO.read(new File("Sprites\\idleframe2.png"));
            brick = ImageIO.read(new File("Sprites\\brick.png"));
            fireball = ImageIO.read(new File("Sprites\\fireball.png"));
            fireman = ImageIO.read(new File("Sprites\\fireman.png"));
            frostBreath = ImageIO.read(new File("Sprites\\frost breath.png"));
            grass = ImageIO.read(new File("Sprites\\grass.png"));
            rainball = ImageIO.read(new File("Sprites\\rainball.png"));
            raindrop = ImageIO.read(new File("Sprites\\raindrop.png"));
            snowball = ImageIO.read(new File("Sprites\\snowball.png"));
            snowman = ImageIO.read(new File("Sprites\\snowman.png"));
            umbrella = ImageIO.read(new File("Sprites\\umbrella.png"));
            walkLeftFoot = ImageIO.read(new File("Sprites\\walk left foot.png"));
            walkRightFoot = ImageIO.read(new File("Sprites\\walk right foot.png"));

            asphaltBlank = ImageIO.read(new File("Sprites\\asphalt blank.png"));
            asphaltYellowLine = ImageIO.read(new File("Sprites\\asphalt yellow line.png"));
            attack = ImageIO.read(new File("Sprites\\attack.png"));
            faceUp = ImageIO.read(new File("Sprites\\faceUp.png"));
            lightBall = ImageIO.read(new File("Sprites\\lightball.png"));
            lightningMan = ImageIO.read(new File("Sprites\\lightningman.png"));
            spider = ImageIO.read(new File("Sprites\\spider.png"));
            spiderWeb = ImageIO.read(new File("Sprites\\spiderweb.png"));
            upAttack = ImageIO.read(new File("Sprites\\upAttack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Sprite> load(String filename, int spriteWidth, int spiteHeight){
        ArrayList<Sprite> sprites = new ArrayList<>();

        String temp;
        try {
            Scanner scanner = new Scanner(new File(filename));
            int j = 0;

            while (scanner.hasNext()) {


                temp = scanner.nextLine();
                for (int i = 0; i < temp.length(); i++){
                    switch (temp.charAt(i)){
                        case 'a':
                            sprites.add(new Wall(brick, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'b':
                            sprites.add(new Enemy(fireman, i*spriteWidth, j*spiteHeight, fireball, fireAttack));
                            break;
                        case 'c':
                            sprites.add(new Enemy(snowman, i*spriteWidth, j*spiteHeight, snowball, snowSound));
                            break;
                        case 'd':
                            sprites.add(new Enemy(raindrop, i*spriteWidth, j*spiteHeight, rainball, waterAttack));
                            break;
                        case '.':
                            sprites.add(new Background(grass, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'f':
                            sprites.add(new PlayerSprite(i*spriteWidth, j*spiteHeight, idle1, idle2, frostBreath,
                                    umbrella, walkLeftFoot, walkRightFoot));
                            break;


                    }
                }
                j++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return sprites;
    }
}
