package avs.models;

import avs.utils.Textures;
import avs.models.Grid;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class Plant extends Entity implements Runnable {
    protected BufferedImage[] animation;
    protected int cost;
    protected int hp;

    private int row;
    private int col;

    private Thread thread;

    public Plant(int w, int h, int cost, Textures texx) {
        super(w,h,texx);
        this.cost = cost;
        this.hp = 300;
    }

    public abstract void run();

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread.stop();
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getCost() {
        return this.cost;
    }

    public int getHp(){
    	return this.hp;
    }

    public synchronized void reduceHp(int hp){
        this.hp -= hp;
    }

    synchronized void kill() {
        this.hp = 0;
        stop();
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public Rectangle getBounds() {
        int y = Grid.SIDEWALK_OFFSET + this.row * Grid.TILE_HEIGHT;
        return new Rectangle((int) this.getX(), y + 1, this.getWidth(), Grid.TILE_HEIGHT - 2);
    }
}
