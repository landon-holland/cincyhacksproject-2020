// CutscenePanel.java
// Landon Holland - 1/25/2020
// Extends JPanel to play cutscenes for the game

import javax.swing.*;
import javax.media.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CutscenePanel extends JPanel {
    // only constructor
    CutscenePanel(int cutsceneNumber, String cityname, String currentweather) throws IOException, CannotRealizeException, NoPlayerException {
        // Time to make the player depending on the cutscene
        if (cutsceneNumber == 1) {
            Player mediaPlayer = Manager.createRealizedPlayer(new File("C:\\Users\\multi\\Documents\\Git\\cincyhacksproject-2020\\Cutscenes\\1.mov").toURI().toURL());
            Component video = mediaPlayer.getVisualComponent();
            add(video);
            mediaPlayer.start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    new TextAnalyzer("Hi my name is Roy. The weather in " + cityname + " is currently " + currentweather).pronounce();
                }
            }).start();
        }
        else if (cutsceneNumber == 2) {
            Player mediaPlayer = Manager.createRealizedPlayer(new File("C:\\Users\\multi\\Documents\\Git\\cincyhacksproject-2020\\Cutscenes\\2.mov").toURI().toURL());
            Component video = mediaPlayer.getVisualComponent();
            add(video);
            mediaPlayer.start();
        }
        else if (cutsceneNumber == 3) {
            Player mediaPlayer = Manager.createRealizedPlayer(new File("C:\\Users\\multi\\Documents\\Git\\cincyhacksproject-2020\\Cutscenes\\3.mov").toURI().toURL());
            Component video = mediaPlayer.getVisualComponent();
            add(video);
            mediaPlayer.start();
            new Thread(() -> {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new TextAnalyzer("Why did you do this? I am going to end you. You loser").pronounce();
                try {
                    Thread.sleep(14000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new TextAnalyzer("No. You must die now").pronounce();

            }).start();

        }
        else {
            //error
            System.out.println("error");
        }
    }
}
