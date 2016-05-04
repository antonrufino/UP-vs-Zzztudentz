package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;

    public MainFrame() {
        super("Acads vs. Students");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.setVisible(true);
    }
}
