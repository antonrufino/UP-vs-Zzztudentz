package avs.ui;

import avs.models.Game;

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
    private int progress;

    public ProgressBarPanel() {
        //this.setOpaque(false);
        this.setPreferredSize(new Dimension(290, 45));
        this.setBackground(new Color(0,0,0,0));
    }

    public void update() {
        if(Game.getInstance().getZombieSummoner().getIsHugeWave())
            return;

        int zombieKilled = (Game.getInstance().getZombieKilled()%10) + 1;
        while(this.progress <= (220/10) * zombieKilled){
            this.progress += 1;
        }

        if(isDone()){
            this.progress = 0;
        }
    }

    public boolean isDone() {
        return this.progress >= 220;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        try {
            g.drawImage(ProgressBarPanel.progressBar, 0, 10,
                280, 46, null);
            g.drawImage(ProgressBarPanel.zombieHead, 215 - progress, 0,
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
