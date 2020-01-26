import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
//        Weather weather = new Weather("dc73c7c1d5b77d6a5e247211f2cc7e1d", 45040);
//        System.out.println(weather.findCurrentTemperature());

        //new TextAnalyzer("Hello my name is roy").pronounce();

        JFrame gui = new JFrame();
        gui.setSize(800,600);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.add(new RenderPanel(0,0,50,50));

        gui.setVisible(true);
    }

}
