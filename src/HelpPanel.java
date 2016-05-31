package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class HelpPanel extends JPanel {
    private static BufferedImage bg;
    public HelpPanel() {
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
            g2d.drawImage(HelpPanel.bg, 0, 0,
                this.getWidth(), this.getHeight(), null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static class AssetLoader implements Runnable {
        @Override
        public void run() {
            try {
                HelpPanel.bg = ImageIO.read(
                    new File("../assets/img/help.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
