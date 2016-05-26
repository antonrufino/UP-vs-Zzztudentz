package avs.models;

public class Game {
    private int energy;
    private Grid grid;
    private String currSelectedPlant;

    public Game() {
        this.energy = 0;
        this.grid = new Grid();
        this.currSelectedPlant = null;
    }

    public selectPlant(Plant plant) {
        this.currSelectedPlant = plant;
    }
}
