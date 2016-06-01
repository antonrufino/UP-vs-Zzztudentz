package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private static CardLayout cardLayout; // Layout for window;
    private static JPanel cardPanel; // This is where the cardlayout will be placed..
    private Container container;

    // Dimensions of the window.
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;

    // Tags to be used for card layout.
    public final static String MENU = "menu";
    public final static String GAME = "game";
    public final static String CREDITS = "credits";
    public final static String HELP = "help";

    public MainFrame() {
        super("UP vs. Zzztudentz");

        cardLayout = new CardLayout();
        container = this.getContentPane();

        cardPanel = new JPanel(cardLayout);

        GamePanel gamePanel = new GamePanel();

        cardPanel.add(MENU, new MainMenuPanel(gamePanel));
        cardPanel.add(GAME, gamePanel);
        cardPanel.add(CREDITS, new CreditsPanel());
        cardPanel.add(HELP, new HelpPanel());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFrameIcon();
        container.setPreferredSize(
            new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);

        this.container.add(cardPanel);

        this.setVisible(true);
        this.setResizable(false);
    }

    // Set icon for window.
    public void setFrameIcon(){
        try{
            Image image = new ImageIcon(getClass().getResource("/assets/img/icon.png")).getImage();
            this.setIconImage(image);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // ActionListener that handles switching cards in the game.
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
