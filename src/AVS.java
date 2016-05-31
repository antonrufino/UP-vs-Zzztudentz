//Program starting point

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
            // Thread for loading different imagesn needed by game.
            Thread bgThread2 = new Thread(new MainMenuPanel.AssetLoader());
            bgThread2.start();

            Thread bgThread3 = new Thread(new CreditsPanel.AssetLoader());
            bgThread3.start();

            Thread bgThread1 = new Thread(new GamePanel.AssetLoader());
            bgThread1.start();

            Thread creditBG = new Thread(new HelpPanel.AssetLoader());
            creditBG.start();

            Thread progressBarThread = new Thread(new ProgressBarPanel.AssetLoader());
            progressBarThread.start();

            Thread energyBarThread = new Thread(new EnergyBar.AssetLoader());
            energyBarThread.start();

            Thread bangaThread = new Thread(new Banga.AssetLoader());
            bangaThread.start();

            bgThread1.join();
            bgThread2.join();
            bgThread3.join();
            creditBG.join();
            progressBarThread.join();
            energyBarThread.join();
            bangaThread.join();
        } catch (InterruptedException e) {
            return;
        }

        new MainFrame();
    }
}
