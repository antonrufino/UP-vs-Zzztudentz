package avs.models;

import avs.ui.PickerButton;

public class Game {
    private static Game instance = new Game();
    private int energy;
    private Grid grid;
    private boolean selectedPlant; //temporary
    private int pendingCost;
    private PickerButton pendingButton;

    private Game() { }

    public static Game getInstance() {
        return Game.instance;
    }

    public void init() {
        this.energy = 999;
        this.grid = new Grid();
        this.selectedPlant = false;
        this.pendingCost = 0;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void selectPlant(boolean plant) {
        this.selectedPlant = plant;
    }

    public boolean getSelectedPlant() {
        return this.selectedPlant;
    }

    public synchronized void addEnergy(int amount) {
        this.energy += amount;
    }

    public synchronized void reduceEnergy() {
        this.energy -= this.pendingCost;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setPendingCost(int cost) {
        this.pendingCost = cost;
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
