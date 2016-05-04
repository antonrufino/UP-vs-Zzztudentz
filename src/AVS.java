package avs;

import avs.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AVS extends JFrame {
    public static void main(String[] args) {
        // Demo goes here
        new AVS();

        Energy energy = new Energy(0, 0);
        Zombie zombie = new Zombie(0, 0, 10);
        Thread energyThread = new Thread(energy);
        Thread zombiethread = new Thread(zombie);

        energyThread.start();
        zombiethread.start();
    }

    public AVS() {
        super("Acads vs. Students");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
