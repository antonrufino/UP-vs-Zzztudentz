package avs.models;

import avs.utils.Textures;
import avs.models.Grid;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class Plant extends Entity {
    protected BufferedImage[] animation;
    protected int cost;
    private int hp;

    private int row;

    public Plant(int w, int h, int cost, Textures texx) {
        super(w,h,texx);
        this.cost = cost;
        this.hp = 100;
    }

    public void setRow(int row) {
        this.row = row;
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

    public boolean isAlive() {
        return this.hp > 0;
    }

    public Rectangle getBounds() {
        int y = Grid.SIDEWALK_OFFSET + this.row * Grid.TILE_HEIGHT;
        return new Rectangle((int) this.getX(), y, this.getWidth(), Grid.TILE_HEIGHT);
    }
}
