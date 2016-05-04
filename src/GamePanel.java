package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    public GamePanel() {
        JButton mainMenuButton = new JButton("Back to Main Menu");
        JButton settingsButton = new JButton("Settings");
        
        this.add(mainMenuButton);
        this.add(settingsButton);
        this.setBackground(Color.RED);
    }
}
