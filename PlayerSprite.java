import java.awt.image.BufferedImage;

public class PlayerSprite extends CollideableSprite {
    private BufferedImage frostBreath;
    private BufferedImage idle1;
    private BufferedImage idle2;
    private BufferedImage umbrella;
    private BufferedImage walkLeftFoot;
    private BufferedImage walkRightFoot;
    private BufferedImage attack;
    private BufferedImage faceUp;
    private BufferedImage upAttack;
    

    public PlayerSprite(){
        super();
    }
    public PlayerSprite(BufferedImage image, int x, int y){
        super(image, x, y);
    }

    public PlayerSprite(int x, int y, BufferedImage idle1, BufferedImage idle2,
                        BufferedImage frostBreath, BufferedImage umbrella, BufferedImage walkLeftFoot, BufferedImage walkRightFoot){
        super(idle1, x, y);
        this.frostBreath = frostBreath;
        this.idle1 = idle1;
        this.idle2 = idle2;
        this.umbrella = umbrella;
        this.walkLeftFoot = walkLeftFoot;
        this.walkRightFoot = walkRightFoot;
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

    public BufferedImage getUmbrella() {
        return umbrella;
    }

    public void setUmbrella(BufferedImage umbrella) {
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
}
