package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private Container container;

    public MainFrame() {
        super("Acads vs. Students");

        cardLayout = new CardLayout();
        container = this.getContentPane();

        JButton menuButton = new JButton("Main Menu");
        JButton gameButton = new JButton("Start Game");
        JButton settingsButton = new JButton("Settings");


        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(menuButton);
        buttonPanel.add(gameButton);
        buttonPanel.add(settingsButton);

        JPanel cardPanel = new JPanel(cardLayout);

        cardPanel.add("menu", new MainMenuPanel());
        cardPanel.add("game", new GamePanel());
        cardPanel.add("settings", new SettingsPanel());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setLocationRelativeTo(null);

        this.container.setLayout(new BorderLayout());
        this.container.add(buttonPanel, BorderLayout.NORTH);
        this.container.add(cardPanel, BorderLayout.CENTER);

        menuButton.addActionListener(new SwitchPanelAction(cardPanel, "menu"));
        gameButton.addActionListener(new SwitchPanelAction(cardPanel, "game"));
        settingsButton.addActionListener(
            new SwitchPanelAction(cardPanel, "settings")
        );

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
