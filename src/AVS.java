package avs;

import avs.models.*;
import avs.ui.MainFrame;
import avs.ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AVS {
    public static void main(String[] args) {
        // Demo goes here
        try {
            Thread bgThread = new Thread(new GamePanel.AssetLoader());
            bgThread.start();
            bgThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        new MainFrame();
    }
}
