import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSprite extends CollideableSprite {
    private BufferedImage frostBreath;
    private BufferedImage idle1;
    private BufferedImage idle2;
    private BufferedImage walkLeftFoot;
    private BufferedImage walkRightFoot;
    private BufferedImage attack;
    private BufferedImage faceUp;
    private BufferedImage upAttack;
    private BufferedImage downAttack;
    private BufferedImage faceDown;
    private BufferedImage leftAttack;
    private BufferedImage leftWalkLeftFoot;
    private BufferedImage leftWalkRightFoot;
    private BufferedImage umbrellaImage;
    private BufferedImage umbrellaUp;
    private BufferedImage umbrellaDown;
    private BufferedImage umbrellaRight;

    private DamageSprite umbrella;


    public PlayerSprite(){
        super();
    }
    public PlayerSprite(BufferedImage image, int x, int y){
        super(image, x, y);
    }

    public PlayerSprite(int x, int y, BufferedImage idle1, BufferedImage idle2, BufferedImage frostBreath,
                        BufferedImage umbrella, BufferedImage walkLeftFoot, BufferedImage walkRightFoot,
                        BufferedImage attack, BufferedImage faceUp, BufferedImage upAttack,
                        BufferedImage downAttack, BufferedImage faceDown, BufferedImage leftAttack,
                        BufferedImage leftWalkLeftFoot, BufferedImage leftWalkRightFoot, BufferedImage umbrellaUp,
                        BufferedImage umbrellaDown, BufferedImage umbrellaRight){
        super(idle1, x, y);
        this.frostBreath = frostBreath;
        this.idle1 = idle1;
        this.idle2 = idle2;
        this.umbrellaImage = umbrella;
        this.walkLeftFoot = walkLeftFoot;
        this.walkRightFoot = walkRightFoot;
        this.attack = attack;
        this.faceUp = faceUp;
        this.upAttack = upAttack;
        this.downAttack = downAttack;
        this.faceDown = faceDown;
        this.leftAttack = leftAttack;
        this.leftWalkLeftFoot = leftWalkLeftFoot;
        this.leftWalkRightFoot = leftWalkRightFoot;
        this.umbrella = new DamageSprite(umbrellaImage, 0, 0, 15);
        this.umbrellaRight = umbrellaRight;
        this.umbrellaDown = umbrellaDown;
        this.umbrellaUp = umbrellaUp;
    }


    public void attack(int x, int y){
        umbrella.setX(this.x);
        umbrella.setY(this.y);
        umbrella.move(x, y);
        umbrella.setVisible(true);
        if (x > 0)
            umbrella.setImage(umbrellaRight);
        if (x < 0)
            umbrella.setImage(umbrellaImage);
        if (y > 0)
            umbrella.setImage(umbrellaDown);
        if (y < 0)
            umbrella.setImage(umbrellaUp);
    }

    public void draw(Graphics g, RenderPanel r){
        super.draw(g, r);
        umbrella.draw(g, r);
    }


    public BufferedImage getFrostBreath() {
        return frostBreath;
    }

    public void setFrostBreath(BufferedImage frostBreath) {
        this.frostBreath = frostBreath;
    }

    public BufferedImage getIdle1() {
        return idle1;
    }

    public void setIdle1(BufferedImage idle1) {
        this.idle1 = idle1;
    }

    public BufferedImage getIdle2() {
        return idle2;
    }

    public void setIdle2(BufferedImage idle2) {
        this.idle2 = idle2;
    }

    public BufferedImage getUmbrellaImage() {
        return umbrellaImage;
    }

    public void setUmbrellaImage(BufferedImage umbrellaImage) {
        this.umbrellaImage = umbrellaImage;
    }

    public DamageSprite getUmbrella() {
        return umbrella;
    }

    public void setUmbrella(DamageSprite umbrella) {
        this.umbrella = umbrella;
    }

    public BufferedImage getWalkLeftFoot() {
        return walkLeftFoot;
    }

    public void setWalkLeftFoot(BufferedImage walkLeftFoot) {
        this.walkLeftFoot = walkLeftFoot;
    }

    public BufferedImage getWalkRightFoot() {
        return walkRightFoot;
    }

    public void setWalkRightFoot(BufferedImage walkRightFoot) {
        this.walkRightFoot = walkRightFoot;
    }

    public BufferedImage getAttack() {
        return attack;
    }

    public void setAttack(BufferedImage attack) {
        this.attack = attack;
    }

    public BufferedImage getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(BufferedImage faceUp) {
        this.faceUp = faceUp;
    }

    public BufferedImage getUpAttack() {
        return upAttack;
    }

    public void setUpAttack(BufferedImage upAttack) {
        this.upAttack = upAttack;
    }

    public BufferedImage getDownAttack() {
        return downAttack;
    }

    public void setDownAttack(BufferedImage downAttack) {
        this.downAttack = downAttack;
    }

    public BufferedImage getFaceDown() {
        return faceDown;
    }

    public void setFaceDown(BufferedImage faceDown) {
        this.faceDown = faceDown;
    }

    public BufferedImage getLeftAttack() {
        return leftAttack;
    }

    public void setLeftAttack(BufferedImage leftAttack) {
        this.leftAttack = leftAttack;
    }

    public BufferedImage getLeftWalkLeftFoot() {
        return leftWalkLeftFoot;
    }

    public void setLeftWalkLeftFoot(BufferedImage leftWalkLeftFoot) {
        this.leftWalkLeftFoot = leftWalkLeftFoot;
    }

    public BufferedImage getLeftWalkRightFoot() {
        return leftWalkRightFoot;
    }

    public void setLeftWalkRightFoot(BufferedImage leftWalkRightFoot) {
        this.leftWalkRightFoot = leftWalkRightFoot;
    }
}
