package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class Plant extends Entity {
    protected BufferedImage[] animation;
    protected int cost;
    private int hp;

    public Plant(int w, int h, int cost, Textures texx) {
        super(w,h,texx);
        this.cost = cost;
        this.hp = 100;
    }

	public Plant(double x, double y, int w, int h, int cost, Textures texx){
		super(x, y, w, h, texx);
        this.cost = cost;
        this.hp = 100;
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
}
