package avs;

import avs.models.*;

public class AVS {
    public static void main(String[] args) {
        // Demo goes here
        Energy energy = new Energy(0, 0);
        Zombie zombie = new Zombie(0, 0, 10);
        Thread energyThread = new Thread(energy);
        Thread zombiethread = new Thread(zombie);

        energyThread.start();
        zombiethread.start();
    }
}
