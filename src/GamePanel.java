package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    private BufferedImage bg;

    public GamePanel() {
        this.bg = bg;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(createPlantsPanel(), BorderLayout.WEST);
        topPanel.add(createProgressPanel(), BorderLayout.EAST);

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

        progressPanel.setOpaque(false);

        progressPanel.add(new JLabel("Progress"));
        progressPanel.add(new JButton("Menu"));

        return progressPanel;
    }

    private JPanel createCanvasPanel() {
        JPanel canvasPanel = new JPanel();
        canvasPanel.setBackground(null);
        canvasPanel.setOpaque(false);
        canvasPanel.add(new JLabel("Kunwari may shits dito."));
        return canvasPanel;
    }

    public void setBackgroundImage(BufferedImage bg) {
        this.bg = bg;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        try {
            g.drawImage(this.bg, 0, 0, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
