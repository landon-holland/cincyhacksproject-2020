import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        int zip = Integer.parseInt(JOptionPane.showInputDialog("Enter your zip code:"));

        Weather weather = new Weather("dc73c7c1d5b77d6a5e247211f2cc7e1d", zip);

        //new TextAnalyzer("Hello my name is roy").pronounce();

        JFrame gui = new JFrame();
        gui.setSize(1280,720);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*CutscenePanel cutscenePanel = new CutscenePanel(1, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
        gui.add(cutscenePanel);
        gui.setVisible(true);

        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 30000);

        gui.setVisible(false);
        gui.remove(cutscenePanel);
        cutscenePanel = new CutscenePanel(2, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
        gui.add(cutscenePanel);
        gui.setVisible(true);

        time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 17000);
        gui.setVisible(false);
        gui.remove(cutscenePanel);*/
        gui.add(new RenderPanel(50,50));
        gui.setVisible(true);
    }

    public static String getWeather(int w){
        switch (w){
            case 0:
                return "Thunderstorm";
            default:
                return "Clear";
        }
    }
}
