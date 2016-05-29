package avs.models;

import avs.ui.PickerButton;

public class Game {
    private static Game instance = new Game();
    private int energy;
    private Grid grid;
    private AllyEntity pendingPlant;
    private PickerButton pendingButton;

    private Game() { }

    public static Game getInstance() {
        return Game.instance;
    }

    public void init() {
        this.energy = 999;
        this.grid = new Grid();
        this.pendingPlant = null;
        this.pendingButton = null;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void selectPlant(AllyEntity plant) {
        this.pendingPlant = plant;
    }

    public AllyEntity getSelectedPlant() {
        return this.pendingPlant;
    }

    public synchronized void addEnergy(int amount) {
        this.energy += amount;
    }

    public synchronized void reduceEnergy() {
        this.energy -= this.pendingPlant.getCost();
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setPendingButton(PickerButton pickerButton) {
        if (this.pendingButton != null) {
            this.pendingButton.stopPending();
        }

        this.pendingButton = pickerButton;

        if (pendingButton != null) this.pendingButton.startPending();
    }

    public void startButtonCoolDown() {
        this.pendingButton.startCoolDown();
    }

    public boolean hasPendingButton() {
        return this.pendingButton != null;
    }
}
