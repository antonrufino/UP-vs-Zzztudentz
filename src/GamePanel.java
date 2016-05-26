package avs.ui;

import avs.models.Grid;
import avs.models.Game;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    private static BufferedImage bg;
    private Game game;
    private boolean running;
    private Thread thread;
    private final ProgressBarPanel progressBarPanel;

    public GamePanel() {
        game = Game.getInstance();

        running = false;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel westPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new BorderLayout());
        progressBarPanel = new ProgressBarPanel();

        westPanel.setOpaque(false);
        westPanel.add(createEnergyBar(), BorderLayout.WEST);
        westPanel.add(createPlantsPanel(), BorderLayout.EAST);

        eastPanel.setOpaque(false);
        eastPanel.add(progressBarPanel, BorderLayout.WEST);
        eastPanel.add(createInGameMenuPanel(), BorderLayout.EAST);
        eastPanel.setPreferredSize(new Dimension(350, 45));

        topPanel.setOpaque(false);
        topPanel.add(westPanel, BorderLayout.WEST);
        topPanel.add(eastPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(createCanvasPanel(), BorderLayout.CENTER);

        this.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){
                if (!game.getSelectedPlant()) return;
                System.out.println("hello");

                for (int i = 0; i < Grid.ROWS; ++i) {
                    for (int j = 0; j < Grid.COLS; ++j) {
                        Rectangle rect = game.getGrid().getRectangle(i, j);
                        if (rect.contains(me.getPoint())) {
                            if (!game.getGrid().hasPlant(i, j)) {
                                game.getGrid().setPlant(i, j, true);
                            }

                            game.selectPlant(false);
                            return;
                        }
                    }
                }
            }
        });
    }

    public void run() {
        while (running) {
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

    public synchronized void start() {
        if (running) return;

        running = true;
        thread = new Thread(this);

        game.init();
        thread.start();

    }

    public synchronized void stop() {
        if (!running) return;

        running = false;
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

    private JPanel createInGameMenuPanel(){
        InGameMenuPanel menuPanel = new InGameMenuPanel();
        return menuPanel;
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
            if (p != null && game.getSelectedPlant()) {
                createHiglightThread(p, g2d).start();
            }

            createPrintPlantThread(g2d).start();
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
                        Rectangle rect = game.getGrid().getRectangle(i, j);
                        if (rect.contains(p)) {
                            g2d.draw(rect);
                        };
                    }
                }
            }
        };
    }

    private Thread createPrintPlantThread(final Graphics2D g2d) {
        return new Thread() {
            public void run() {
                for (int i = 0; i < Grid.ROWS; ++i) {
                    for (int j = 0; j < Grid.COLS; ++j) {
                        Rectangle rect = game.getGrid().getRectangle(i, j);
                        if (game.getGrid().hasPlant(i, j)) {
                            g2d.fill(rect);
                        }
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
