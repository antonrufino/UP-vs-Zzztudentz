package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProgressBarPanel extends JPanel {
    private static BufferedImage progressBar;
    private static BufferedImage zombieHead;

    public ProgressBarPanel() {
        //this.setOpaque(false);
        this.setPreferredSize(new Dimension(265, 45));
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        try {
            g.drawImage(ProgressBarPanel.progressBar, 0, 10,
                280, 46, null);
            g.drawImage(ProgressBarPanel.zombieHead, 215, 0,
                60, 60, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static class AssetLoader implements Runnable {
        @Override
        public void run() {
            try {
                ProgressBarPanel.progressBar = ImageIO.read(
                    new File("../assets/img/counters/wave_counter/wave_counter_empty.png"));

                ProgressBarPanel.zombieHead = ImageIO.read(
                    new File("../assets/img/counters/wave_counter/wave_counter_seek.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
