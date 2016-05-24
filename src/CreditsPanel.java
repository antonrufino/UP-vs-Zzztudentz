package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreditsPanel extends JPanel {
    public CreditsPanel() {
        JButton menuBtn = new JButton("Menu");

        menuBtn.addActionListener(
            new MainFrame.SwitchPanelAction(MainFrame.MENU));
        this.setBackground(Color.BLUE);
    }
}
