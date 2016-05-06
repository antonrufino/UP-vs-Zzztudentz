package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class LoadingScreenPanel extends JPanel {
    public LoadingScreenPanel() {
        this.setBackground(Color.YELLOW);
        this.add(new JLabel("Loading..."));
    }
}
