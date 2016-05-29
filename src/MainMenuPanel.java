package avs.ui;

import avs.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class MainMenuPanel extends JPanel {
    public static final int LEFT_OFFSET = 906;
    public static final int RIGHT_OFFSET = 206;
    public static final int TOP_OFFSET = 197;
    public static final int BOTTOM_OFFSET = 385;
    public static final int BTN_PANEL_WIDTH = 175;
    public static final int BTN_PANEL_HEIGHT = 140;
    private static BufferedImage bg;

    public MainMenuPanel(final GamePanel gamePanel) {
        super();
        MainMenuBtnPanel btnPanel = new MainMenuBtnPanel(BTN_PANEL_WIDTH, BTN_PANEL_HEIGHT);

        this.setBackground(Color.GREEN);
        this.setOpaque(false);
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(TOP_OFFSET,LEFT_OFFSET,BOTTOM_OFFSET,RIGHT_OFFSET));
        this.add(btnPanel);

        // startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        // startBtn.setPreferredSize(new Dimension(BTN_PANEL_WIDTH, 75));
        // creditsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        // creditsBtn.setPreferredSize(new Dimension(197, 40));




        // startBtn.addActionListener(
        //     new MainFrame.SwitchPanelAction(MainFrame.GAME));
        //
        // startBtn.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         gamePanel.start();
        //         Game.getInstance().init();
        //     }
        // });
        //
        // creditsBtn.addActionListener(
        //     new MainFrame.SwitchPanelAction(MainFrame.CREDITS));


        // buttons.setOpaque(false);

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
                    new File("../assets/img/main_menu/main-menu.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }


}
