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
    private ArrayList<Bus> busList;

    private int energy;

    private Grid grid;
    private Plant pendingPlant;
    private PickerButton pendingButton;

    private Random rand;

    private ZombieSummoner zombieThread;
    private EnergyMaker energyThread;

    private Textures tex;
    private Tower tower;

    private boolean hasLost;

    private Game() { }

    public static Game getInstance() {
        return Game.instance;
    }

    public void init() {
        this.energy = 0;
        this.grid = new Grid();
        this.pendingPlant = null;
        this.pendingButton = null;
        this.rand = new Random();
        this.hasLost = false;

        this.zombieThread = new ZombieSummoner();
        this.energyThread = new EnergyMaker();

        zombieList = new ArrayList<Zombie>();
        energyList = new ArrayList<Energy>();
        eggWaffleList = new ArrayList<EggWaffle>();
        busList = new ArrayList<Bus>();

        createBus();

        zombieThread.start();
        energyThread.start();
        //eggWaffleThread.start();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public boolean getGameState(){
        return this.hasLost;
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
        EggWaffle e = new EggWaffle(tower.getX(), tower.getY(), tex);
        e.setRow(tower.getRow());
        addEggWaffle(e);
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

    public synchronized void createBus(){
       int j = Grid.TILE_HEIGHT;
       for(int i = 0; i < 5; i++){
            Bus b = new Bus(-300, j, tex);
            b.setRow(i);
            addBus(b);
            j += Grid.TILE_HEIGHT;
       }
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

    public void addBus(Bus z){
        busList.add(z);
    }

    public void removeBus(Bus z){
        busList.remove(z);
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

    public ArrayList<Bus> getBusList(){
        return this.busList;
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

        Iterator<Zombie> zIter = zombieList.iterator();
        while (zIter.hasNext()) {
            Zombie z = zIter.next();
            if (!z.isAlive()) zIter.remove();
            else {
                if(z.getX() == 0 -z.getWidth()){
                    this.hasLost = true;
                    // this.removeZombie(z);
                    // System.out.println("remove");
                }
                z.tick();
            }
        }

        for (int i = 0; i<energyList.size(); i++){
            energyList.get(i).tick();
        }

        Iterator<EggWaffle> eIter = eggWaffleList.iterator();
        while (eIter.hasNext()) {
            EggWaffle e = eIter.next();
            if (!e.isAlive()) eIter.remove();
            else e.tick();
        }

        Iterator<Bus> bIter = busList.iterator();
        while (bIter.hasNext()) {
            Bus b = bIter.next();
            if (!b.isAlive()) bIter.remove();
            else b.tick();
        }
    }
}
