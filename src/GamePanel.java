package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    private static BufferedImage bg;

    public GamePanel() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(createPlantsPanel(), BorderLayout.WEST);
        topPanel.add(new ProgressBarPanel(), BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(createCanvasPanel(), BorderLayout.CENTER);
    }

    private JPanel createPlantsPanel() {
        JPanel plantsPanel = new JPanel();

        plantsPanel.setOpaque(false);

        plantsPanel.add(new JLabel("Energy: 150"));
        plantsPanel.add(new JButton("Kopiko"));
        plantsPanel.add(new JButton("Kwek Kwek"));
        plantsPanel.add(new JButton("Banga"));
        plantsPanel.add(new JButton("TC7"));

        return plantsPanel;
    }

    private JPanel createProgressPanel() {
        JPanel progressPanel = new JPanel();
        JButton menuBtn = new JButton("Menu");

        menuBtn.addActionListener(
            new MainFrame.SwitchPanelAction(MainFrame.MENU));

        //progressPanel.setOpaque(false);

        progressPanel.add(new JLabel("Progress"));
        progressPanel.add(menuBtn);

        return progressPanel;
    }

    private JPanel createCanvasPanel() {
        JPanel canvasPanel = new JPanel();
        canvasPanel.setBackground(null);
        canvasPanel.setOpaque(false);
        canvasPanel.add(new JLabel("Kunwari may shits dito."));
        return canvasPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        try {
            g.drawImage(GamePanel.bg, 0, 0,
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
                GamePanel.bg = ImageIO.read(
                    new File("../assets/img/background.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
