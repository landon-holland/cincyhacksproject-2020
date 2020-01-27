import javax.swing.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws Exception {

        int zip = Integer.parseInt(JOptionPane.showInputDialog("Enter your zip code:"));
        boolean skipCutscenes;

        Weather weather = new Weather("dc73c7c1d5b77d6a5e247211f2cc7e1d", zip);
        //13315
        //12345
        //new TextAnalyzer("Hello my name is roy").pronounce();


        JFrame gui = new JFrame("game");
        gui.setSize(1280,720);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        CutscenePanel cutscenePanel;
        long time;

        if (JOptionPane.showConfirmDialog(null, "Skip cutscenes:") == JOptionPane.YES_OPTION) {
            skipCutscenes = true;
        } else{
            skipCutscenes = false;
        }

        if (!skipCutscenes){
            cutscenePanel = new CutscenePanel(1, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
            gui.add(cutscenePanel);
            gui.setVisible(true);

            time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 30000) ;

            gui.setVisible(false);
            gui.remove(cutscenePanel);
            cutscenePanel = new CutscenePanel(2, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
            gui.add(cutscenePanel);
            gui.setVisible(true);

            time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 17000) ;
            gui.setVisible(false);
            gui.remove(cutscenePanel);
        }
        RenderPanel renderPanel = new RenderPanel(50,50, 0, weather.findCurrentDescription());
        gui.add(renderPanel);
        gui.setVisible(true);
        JOptionPane.showMessageDialog(null, "Roy: I should go home now. Press tab to start.");

        while (true){
            if (renderPanel.getGameState() == 5)
                break;
            if (renderPanel.getGameState() >= 9){
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
        if (!skipCutscenes) {
            cutscenePanel = new CutscenePanel(3, weather.findCurrentCityName(), getWeather(weather.findCurrentDescription()));
            gui.add(cutscenePanel);
            gui.setVisible(true);

            time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 60000) ;
            gui.setVisible(false);
            gui.remove(cutscenePanel);
        }
        renderPanel = new RenderPanel(50,50, 1, weather.findCurrentDescription());
        gui.add(renderPanel);
        gui.setVisible(true);

        while (true){
            if (renderPanel.getGameState() >= 9)
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
