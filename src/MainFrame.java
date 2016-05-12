package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private Container container;

    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;

    public MainFrame() {
        super("Acads vs. Students");

        cardLayout = new CardLayout();
        container = this.getContentPane();

        JPanel cardPanel = new JPanel(cardLayout);

        cardPanel.add("menu", new MainMenuPanel());
        cardPanel.add("game", new GamePanel());
        cardPanel.add("settings", new SettingsPanel());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setPreferredSize(
            new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);

        this.container.add(cardPanel);

        this.setVisible(true);
    }

    private class SwitchPanelAction implements ActionListener {
        private Container parent;
        private String tag;

        public SwitchPanelAction(Container parent, String tag) {
            this.parent = parent;
            this.tag = tag;
        }

        public void actionPerformed(ActionEvent e) {
            cardLayout.show(parent, tag);
        }
    }
}
