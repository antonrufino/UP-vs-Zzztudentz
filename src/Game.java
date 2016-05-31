package avs.models;

import avs.ui.PickerButton;
import avs.ui.MainFrame;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.*;

public class Game{
    private static Game instance = new Game();
    private ArrayList<Zombie> zombieList;
    private ArrayList<Energy> energyList;
    private ArrayList<EggWaffle> eggWaffleList;

    private int energy;

    private Grid grid;
    private Plant pendingPlant;
    private PickerButton pendingButton;

    private Random rand;

    private ZombieSummoner zombieThread;
    private EnergyMaker energyThread;

    private Textures tex;
    private Tower tower;

    private Game() { }

    public static Game getInstance() {
        return Game.instance;
    }

    public void init() {
        this.energy = 5000;
        this.grid = new Grid();
        this.pendingPlant = null;
        this.pendingButton = null;
        this.rand = new Random();

        this.zombieThread = new ZombieSummoner();
        this.energyThread = new EnergyMaker();

        zombieList = new ArrayList<Zombie>();
        energyList = new ArrayList<Energy>();
        eggWaffleList = new ArrayList<EggWaffle>();

        zombieThread.start();
        energyThread.start();
        //eggWaffleThread.start();
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

    public synchronized void increaseEnergy(int amount) {
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

    public void createEggWaffle(Tower tower){
      
        addEggWaffle(new EggWaffle(tower.getX(), tower.getY(), tex));
    }

    public synchronized void createZombie() {
        Zombie zombie = new Zombie(tex);

        int row = rand.nextInt(5);
        int y = Grid.SIDEWALK_OFFSET + row * Grid.TILE_HEIGHT;
        y -= zombie.getHeight()/2;
        y -= 18;

        zombie.setX(MainFrame.WIDTH);
        zombie.setY(y);
        zombie.setRow(row);

        addZombie(zombie);
    }

    public synchronized void createEnergy(){
        int x = new Random().nextInt(Grid.WIDTH - Grid.TILE_WIDTH) +
            Grid.TILE_WIDTH / 2 + Grid.BUS_OFFSET;
        int targetY = new Random().nextInt(Grid.HEIGHT - Grid.TILE_HEIGHT) +
            Grid.TILE_HEIGHT / 2 + Grid.SIDEWALK_OFFSET;
        addEnergy(new Energy(x, 0, targetY, 50, tex));
    }

    public synchronized void createEnergy(Kopiko kopiko){
        addEnergy(new Energy(kopiko.getX()+15, kopiko.getY()-20, kopiko.getY() + kopiko.getHeight() -50, 25, tex));
    }

    public void addZombie(Zombie z){
        zombieList.add(z);
    }

    public synchronized void removeZombie(Zombie z){
        zombieList.remove(z);
    }

    public synchronized void addEnergy(Energy e){
        energyList.add(e);
    }

    public synchronized void removeEnergy(Energy e){
        energyList.remove(e);
    }

    public synchronized void addEggWaffle(EggWaffle ew){
        eggWaffleList.add(ew);
    }

    public synchronized void removeEggWaffle(EggWaffle ew){
        eggWaffleList.remove(ew);
    }

    public void setTextures(Textures tex){
        this.tex = tex;
    }

    public void setTower(Tower tower){
        this.tower = tower;
    }

    public Tower getTower(){
        return this.tower;
    }

    public synchronized ArrayList<Zombie> getZombieList(){
        return this.zombieList;
    }

    public synchronized ArrayList<Energy> getEnergyList(){
        return this.energyList;
    }

    public synchronized ArrayList<EggWaffle> getEggWaffleList(){
        return this.eggWaffleList;
    }

    public void tick() {
        for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                if (grid.hasPlant(i, j)) {
                    Plant p = grid.getPlant(i, j);
                    if (p.isAlive()) p.tick();
                    else grid.setPlant(i, j, null);
                }
            }
        }

        Iterator<Zombie> iter = zombieList.iterator();
        while (iter.hasNext()) {
            Zombie z = iter.next();
            if (!z.isAlive()) iter.remove();
            else z.tick();
        }

        for (int i = 0; i<energyList.size(); i++){
            energyList.get(i).tick();
        }

        for (int i = 0; i<eggWaffleList.size();i++){
            eggWaffleList.get(i).tick();
        }
    }
}
