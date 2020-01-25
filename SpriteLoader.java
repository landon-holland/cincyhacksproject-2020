import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpriteLoader {
    public static ArrayList<Sprite> load(String filename){
        ArrayList<Sprite> sprites = new ArrayList<>();

        String[] temp;
        try {
            Scanner scanner = new Scanner(new File(filename));

            while (scanner.hasNext()) {
                temp = scanner.nextLine().split(" ");

                if (temp[0].equals("player")) {
                    sprites.add(new Player(ImageIO.read(new File(temp[1])), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
                } else if (temp[0].equals("enemy")){
                    sprites.add(new Enemy(ImageIO.read(new File(temp[1])), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
                } else if (temp[0].equals("background")){
                    sprites.add(new Background(ImageIO.read(new File(temp[1])), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
                } else if (temp[0].equals("wall")){
                    sprites.add(new Wall(ImageIO.read(new File(temp[1])), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return sprites;
    }
}
