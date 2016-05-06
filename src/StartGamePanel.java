package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class StartGamePanel extends JPanel {
    private CardLayout cardLayout;
    private BufferedImage bg;

    public StartGamePanel() {
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        GamePanel gamePanel = new GamePanel();

        this.add("loading", new LoadingScreenPanel());
        this.add("game", gamePanel);

        Thread loadBgThread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Loading...");
                    bg = ImageIO.read(
                        new File("assets/img/background.png"));
                    gamePanel.setBackgroundImage(bg);
                    System.out.println("Done.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        };
        loadBgThread.start();

        cardLayout.show(this, "loading");

        try {
            loadBgThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        cardLayout.show(this, "game");
    }
}
