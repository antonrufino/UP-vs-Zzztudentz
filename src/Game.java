package avs.models;

public class Game {
    private static Game instance = new Game();
    private int energy;
    private Grid grid;
    private boolean selectedPlant; //temporary

    private Game() { }

    public static Game getInstance() {
        return Game.instance;
    }

    public void init() {
        this.energy = 0;
        this.grid = new Grid();
        this.selectedPlant = false;
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
}
