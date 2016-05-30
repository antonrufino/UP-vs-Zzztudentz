package avs.models;

import avs.ui.PickerButton;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.*;

public class Game{
    private static Game instance = new Game();
    private ArrayList<Zombie> zombieList;
    private ArrayList<Energy> energyList;
    private int energy;
    private Grid grid;
    private Plant pendingPlant;
    private PickerButton pendingButton;
    private Random rand;
    private ZombieSummoner necro;
    private EnergyMaker em;
    private Textures tex;

    private Game() { }

    public static Game getInstance() {
        return Game.instance;
    }

    public void init() {
        this.energy = 999;
        this.grid = new Grid();
        this.pendingPlant = null;
        this.pendingButton = null;
        this.rand = new Random();
        this.necro = new ZombieSummoner();
        this.em = new EnergyMaker();
        zombieList = new ArrayList<Zombie>();
        energyList = new ArrayList<Energy>();

        necro.start();
        em.start();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public void selectPlant(Plant plant) {
        this.pendingPlant = plant;
    }

    public Plant getSelectedPlant() {
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

   public synchronized void createZombie(int enemyCount, int spawnDelay){
        for(int i = 0; i < enemyCount; i++){
            int y = rand.nextInt(5) * 109;
            addZombie(new Zombie(1280,y,tex));
        }
    }

    public synchronized void createEnergy(){
        for(int i = 0; i < 5; i++){
            int x = rand.nextInt(5) * 109;
            addEnergy(new Energy(x,-1,tex));
        }
    }

    public void addZombie(Zombie z){
        zombieList.add(z);
    }

    public void removeZombie(Zombie z){
        zombieList.remove(z);
    }

    public void addEnergy(Energy e){
        energyList.add(e);
    }

    public void removeEnergy(Energy e){
        energyList.remove(e);
    }

    public void setTextures(Textures tex){
        this.tex = tex;
    }

    public ArrayList<Zombie> getZombieList(){
        return this.zombieList;
    }

    public ArrayList<Energy> getEnergyList(){
        return this.energyList;
    }

    public void tick() {
        for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                if (grid.hasPlant(i, j)) grid.getPlant(i, j).tick();
            }
        }

        for (int i = 0; i<zombieList.size(); i++){
            zombieList.get(i).tick();
        }

        for (int i = 0; i<energyList.size(); i++){
            energyList.get(i).tick();
        }
    }
}
