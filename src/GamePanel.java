package avs.ui;

import avs.models.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

public class GamePanel extends JPanel {
    private static BufferedImage bg;
    Grid grid;

    public GamePanel() {
        grid = new Grid();

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel westPanel = new JPanel(new BorderLayout());
        final ProgressBarPanel progressBarPanel = new ProgressBarPanel();

        westPanel.setOpaque(false);
        westPanel.add(createEnergyBar(), BorderLayout.WEST);
        westPanel.add(createPlantsPanel(), BorderLayout.EAST);

        topPanel.setOpaque(false);
        topPanel.add(westPanel, BorderLayout.WEST);
        topPanel.add(progressBarPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(createCanvasPanel(), BorderLayout.CENTER);

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        if (!progressBarPanel.isDone()) {
                            progressBarPanel.update();
                        }

                        repaint();
                        progressBarPanel.repaint();
                        Thread.sleep(100);
                    } catch(Exception e) { }
                }
            }
        }).start();
    }

    private JPanel createEnergyBar(){
        EnergyBar energyBar = new EnergyBar();
        return energyBar;
    }
    private JPanel createPlantsPanel() {
        PlantPickerPanel plantsPanel = new PlantPickerPanel();
        return plantsPanel;
    }

    private JPanel createCanvasPanel() {
        JPanel canvasPanel = new JPanel();
        canvasPanel.setBackground(null);
        canvasPanel.setOpaque(false);

        return canvasPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.paintComponent(g);

        try {
            g2d.drawImage(GamePanel.bg, 0, 0,
                this.getWidth(), this.getHeight(), null);

            g2d.setColor(new Color(1, 68, 33));

            Point p = this.getMousePosition();
            if (p != null) {
                createHiglightThread(p, g2d).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private Thread createHiglightThread(final Point p, final Graphics2D g2d) {
        return new Thread() {
            public void run() {
                for (int i = 0; i < Grid.ROWS; ++i) {
                    for (int j = 0; j < Grid.COLS; ++j) {
                        Rectangle rect = grid.getRectangle(i, j);
                        if (rect.contains(p)) {
                            g2d.draw(rect);
                        };
                    }
                }
            }
        };
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
