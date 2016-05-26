package avs.ui;

import avs.models.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(final GamePanel gamePanel) {
        JButton startBtn = new JButton("Start Game");
        JButton creditsBtn = new JButton("Credits");

        this.setBackground(Color.GREEN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));

        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        startBtn.addActionListener(
            new MainFrame.SwitchPanelAction(MainFrame.GAME));

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.start();
                Game.getInstance().init();
            }
        });

        creditsBtn.addActionListener(
            new MainFrame.SwitchPanelAction(MainFrame.CREDITS));

        this.add(startBtn);
        this.add(creditsBtn);
    }
}
