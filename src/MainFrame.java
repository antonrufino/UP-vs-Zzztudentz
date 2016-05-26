package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private Container container;

    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;

    public final static String MENU = "menu";
    public final static String GAME = "game";
    public final static String CREDITS = "credits";

    public MainFrame() {
        super("Acads vs. Students");

        cardLayout = new CardLayout();
        container = this.getContentPane();

        cardPanel = new JPanel(cardLayout);

        GamePanel gamePanel = new GamePanel();

        cardPanel.add(MENU, new MainMenuPanel(gamePanel));
        cardPanel.add(GAME, gamePanel);
        cardPanel.add(CREDITS, new CreditsPanel());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setPreferredSize(
            new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);

        this.container.add(cardPanel);

        this.setVisible(true);
        this.setResizable(false);
    }

    public static class SwitchPanelAction implements ActionListener {
        private String tag;

        public SwitchPanelAction(String tag) {
            this.tag = tag;
        }

        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, tag);
        }
    }
}
