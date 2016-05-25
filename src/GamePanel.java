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
                while (!progressBarPanel.isDone()) {
                    try {
                        progressBarPanel.update();
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
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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
