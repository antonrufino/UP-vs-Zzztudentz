package avs.ui;

import avs.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class MainMenuPanel extends JPanel {
    // public static final int LEFT_OFFSET = 906;
    // public static final int RIGHT_OFFSET = 206;
    // public static final int TOP_OFFSET = 197;
    // public static final int BOTTOM_OFFSET = 385;
    public static final int BTN_PANEL_WIDTH = 175;
    public static final int BTN_PANEL_HEIGHT = 140;

    public static final int LEFT_OFFSET = 720;
    public static final int RIGHT_OFFSET = 206;
    public static final int TOP_OFFSET = 102;
    public static final int BOTTOM_OFFSET = 380;
    private static BufferedImage bg;

    public MainMenuPanel(final GamePanel gamePanel) {
        super();
        JPanel mainMenuComponents = new JPanel();
        JPanel playerName = new JPanel();
        PlayerNameTextField player = new PlayerNameTextField();
        MainMenuBtnPanel btnPanel = new MainMenuBtnPanel(gamePanel, BTN_PANEL_WIDTH, BTN_PANEL_HEIGHT);



        playerName.setOpaque(false);
        playerName.add(player);


        mainMenuComponents.setPreferredSize(new Dimension(356, 240));
        mainMenuComponents.setOpaque(false);
        mainMenuComponents.setLayout(new BorderLayout());
        mainMenuComponents.add(playerName, BorderLayout.NORTH);
        mainMenuComponents.add(btnPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(TOP_OFFSET,LEFT_OFFSET,BOTTOM_OFFSET,RIGHT_OFFSET));
        this.add(mainMenuComponents);

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
