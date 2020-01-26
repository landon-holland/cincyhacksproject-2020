import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        int zip = Integer.parseInt(JOptionPane.showInputDialog("Enter your zip code:"));

        Weather weather = new Weather("dc73c7c1d5b77d6a5e247211f2cc7e1d", zip);

        //new TextAnalyzer("Hello my name is roy").pronounce();

        JFrame gui = new JFrame();
        gui.setSize(1280,720);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CutscenePanel cutscenePanel = new CutscenePanel(1, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
        gui.add(cutscenePanel);
        gui.setVisible(true);

        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 3000);

        gui.setVisible(false);
        gui.remove(cutscenePanel);
        cutscenePanel = new CutscenePanel(2, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
        gui.add(cutscenePanel);
        gui.setVisible(true);

        time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 1700);
        gui.setVisible(false);
        gui.remove(cutscenePanel);
        RenderPanel renderPanel = new RenderPanel(50,50, 0);
        gui.add(renderPanel);
        gui.setVisible(true);
        JOptionPane.showMessageDialog(null, "Roy: I should go home now. Press tab to start.");

        while (true){
            if (renderPanel.getGameState() == 5)
                break;
            if (renderPanel.getGameState() == 9){
                gui.setVisible(false);
                gui.remove(renderPanel);
                Credits credits = new Credits();
                gui.add(credits);
                gui.setVisible(true);
                while (true);
            }
        }
        gui.setVisible(false);
        gui.remove(renderPanel);
        renderPanel.setSprites(new ArrayList<>());
        cutscenePanel = new CutscenePanel(3, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
        gui.add(cutscenePanel);
        gui.setVisible(true);

        time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 60000);
        gui.setVisible(false);
        gui.remove(cutscenePanel);
        renderPanel = new RenderPanel(50,50, 1);
        gui.add(renderPanel);
        gui.setVisible(true);

        while (true){
            if (renderPanel.getGameState() == 9)
                break;
            //System.out.println(renderPanel.getGameState());
        }

        Thread.sleep(5000);
        gui.setVisible(false);
        gui.remove(renderPanel);
        Credits credits = new Credits();
        gui.add(credits);
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
