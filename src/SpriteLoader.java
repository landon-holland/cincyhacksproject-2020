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
    private static BufferedImage walkLeftFoot;
    private static BufferedImage walkRightFoot;
    private static BufferedImage asphaltBlank;
    private static BufferedImage asphaltYellowLine;
    private static BufferedImage attack;
    private static BufferedImage faceUp;
    private static BufferedImage lightBall;
    private static BufferedImage lightningMan;
    public static BufferedImage spider;
    public static BufferedImage leftSpider;
    public static BufferedImage deadSpider;
    public static BufferedImage spiderWeb;
    private static BufferedImage upAttack;
    private static BufferedImage downAttack;
    private static BufferedImage faceDown;
    private static BufferedImage cloudOverlay;
    private static BufferedImage window;
    private static BufferedImage doorBottom;
    private static BufferedImage doorTop;
    private static BufferedImage leftAttack;
    private static BufferedImage leftWalkLeftFoot;
    private static BufferedImage leftWalkRightFoot;
    private static BufferedImage umbrellaUp;
    private static BufferedImage umbrellaDown;
    private static BufferedImage umbrellaRight;
    private static BufferedImage umbrellaLeft;
    private static BufferedImage wood;
    private static BufferedImage medKit;
    private static BufferedImage snowGrass;
    private static BufferedImage rainGrass;


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
            downAttack = ImageIO.read(new File("Sprites\\attack down.png"));
            faceDown = ImageIO.read(new File("Sprites\\face down.png"));
            cloudOverlay = ImageIO.read(new File("Sprites\\cloudyOverlay.png"));
            window = ImageIO.read(new File("Sprites\\window.png"));
            doorBottom = ImageIO.read(new File("Sprites\\doorbottom.png"));
            doorTop = ImageIO.read(new File("Sprites\\doortop.png"));
            leftAttack = ImageIO.read(new File("Sprites\\left attack.png"));
            leftWalkLeftFoot = ImageIO.read(new File("Sprites\\left walk left foot.png"));
            leftWalkRightFoot = ImageIO.read(new File("Sprites\\left walk right foot.png"));
            umbrellaLeft = ImageIO.read(new File("Sprites\\umbrella.png"));
            umbrellaRight = ImageIO.read(new File("Sprites\\right umbrella.png"));
            umbrellaDown = ImageIO.read(new File("Sprites\\down umbrella.png"));
            umbrellaUp = ImageIO.read(new File("Sprites\\up umbrella.png"));
            wood = ImageIO.read(new File("Sprites\\wood.png"));
            medKit = ImageIO.read(new File("Sprites\\medkit.png"));
            leftSpider = ImageIO.read(new File("Sprites\\leftspider.png"));
            deadSpider = ImageIO.read(new File("Sprites\\deadspider.png"));
            snowGrass = ImageIO.read(new File("Sprites\\snow.png"));
            rainGrass = ImageIO.read(new File("Sprites\\rain.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Sprite> load(String filename, int spriteWidth, int spiteHeight, int weather){
        ArrayList<Sprite> sprites = new ArrayList<>();
        CollideableSprite.setCollideables(new ArrayList<>());

        if (weather == 4)
            grass = snowGrass;
        else if (weather == 2)
            grass = rainGrass;


        String temp;
        try {
            Scanner scanner = new Scanner(new File(filename));
            int j = 0;

            while (scanner.hasNext()) {


                temp = scanner.nextLine();
                for (int i = 0; i < temp.length()/2; i++){
                    switch (temp.charAt(i*2)){
                        case 'a':
                            sprites.add(new Wall(brick, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'b':
                            sprites.add(new Background(asphaltBlank, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'c':
                            sprites.add(new Background(asphaltYellowLine, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'd':
                            sprites.add(new Background(doorBottom, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'e':
                            sprites.add(new Background(doorTop, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'f':
                            sprites.add(new Wall(window, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'l':
                            sprites.add(new Background(wood, i*spriteWidth, j*spiteHeight));
                            break;
                        case '.':
                            sprites.add(new Background(grass, i*spriteWidth, j*spiteHeight));
                            break;
                    }
                    switch (temp.charAt(i*2 + 1)){
                        case 'g':
                            sprites.add(new Enemy(fireman, i*spriteWidth, j*spiteHeight, fireball, fireAttack));
                            break;
                        case 'h':
                            sprites.add(new Enemy(snowman, i*spriteWidth, j*spiteHeight, snowball, snowSound));
                            break;
                        case 'i':
                            sprites.add(new Enemy(raindrop, i*spriteWidth, j*spiteHeight, rainball, waterAttack));
                            break;
                        case 'j':
                            sprites.add(new Enemy(lightningMan, i*spriteWidth, j*spiteHeight, lightBall, zapAttack));
                            break;
                        case 'k' :
                            sprites.add(new Wall(spiderWeb, i*spriteWidth, j*spiteHeight));
                            break;
                        case 'p':
                            sprites.add(new PlayerSprite(i*spriteWidth, j*spiteHeight, idle1, idle2, frostBreath,
                                    umbrellaLeft, walkLeftFoot, walkRightFoot, attack, faceUp, upAttack, downAttack,
                                    faceDown, leftAttack, leftWalkLeftFoot, leftWalkRightFoot, umbrellaUp, umbrellaDown,
                                    umbrellaRight));
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
