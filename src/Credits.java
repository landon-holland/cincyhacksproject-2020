import javax.swing.*;
import java.awt.*;

public class Credits extends JPanel {
    int offset = 0;

    public Credits(){
        setBackground(Color.BLACK);
        long time = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    offset = (int) ((System.currentTimeMillis() - time) / 10);
                    repaint();
                }
            }
        }).start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("FIN", 500, 1500 - offset);
        g.drawString("WEATHERMAN - CREDITS", 500, 2000 - offset);
        g.drawString("Gameplay and TTS - Drew Smith", 500, 2400 - offset);
        g.drawString("Sprites and Concept - Daniel Wahl", 500, 2600 - offset);
        g.drawString("Weather, Cutscenes and Voice Acting - Landon Holland (RIP)", 500, 2800 - offset);
        g.drawString("Thanks for Playing!", 500, 3600 - offset);
    }
}
