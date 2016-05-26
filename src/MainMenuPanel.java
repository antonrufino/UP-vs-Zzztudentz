package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
public class MainMenuPanel extends JPanel {
    private static BufferedImage bg;
    public MainMenuPanel() {
        JButton startBtn = new JButton("Play Game");
        JButton creditsBtn = new JButton("Credits");
        JPanel buttons = new JPanel();

        this.setBackground(Color.GREEN);
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));

        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setPreferredSize(new Dimension(197, 50));
        creditsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsBtn.setPreferredSize(new Dimension(197, 35));

        startBtn.addActionListener(
            new MainFrame.SwitchPanelAction(MainFrame.GAME));

        creditsBtn.addActionListener(
            new MainFrame.SwitchPanelAction(MainFrame.CREDITS));

        this.setBorder(BorderFactory.createEmptyBorder(250,893,300,190));

        buttons.setOpaque(false);
        buttons.add(startBtn);
        buttons.add(creditsBtn);
        this.add(buttons);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.paintComponent(g);

        try {
            g2d.drawImage(MainMenuPanel.bg, 0, 0,
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
                MainMenuPanel.bg = ImageIO.read(
                    new File("../assets/img/main-menu.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
