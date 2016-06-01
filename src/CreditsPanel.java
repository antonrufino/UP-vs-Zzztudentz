// Panel for credits.

package avs.ui;

import avs.utils.BufferedImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class CreditsPanel extends JPanel {
    private static BufferedImage bg;
    public CreditsPanel() {
        InGameMenuPanel menuBtn = new InGameMenuPanel(1);
        this.setPreferredSize(new Dimension(65,30));
        this.setLayout(new BorderLayout());
        this.add(menuBtn, BorderLayout.EAST);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.paintComponent(g);

        try {
            g2d.drawImage(CreditsPanel.bg, 0, 0,
                this.getWidth(), this.getHeight(), null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Asynchronously loads assets.
    public static class AssetLoader implements Runnable {
        @Override
        public void run() {
            try {
                CreditsPanel.bg = new BufferedImageLoader().loadImage("/assets/img/credits.png");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
