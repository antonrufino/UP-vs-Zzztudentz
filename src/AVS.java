package avs;

import avs.models.*;
import avs.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AVS {
    public static void main(String[] args) {
        // Demo goes here
        try {
            Thread bgThread = new Thread(new GamePanel.AssetLoader());
            bgThread.start();

            Thread progressBarThread = new Thread(new ProgressBarPanel.AssetLoader());
            progressBarThread.start();

            Thread energyBarThread = new Thread(new EnergyBar.AssetLoader());
            energyBarThread.start();

            bgThread.join();
            progressBarThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        new MainFrame();
    }
}
