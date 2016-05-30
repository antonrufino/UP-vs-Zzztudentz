package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class Plant extends Entity {
    protected BufferedImage[] animation;
	private boolean isAlive;
    protected int cost;

    public Plant(int w, int h, int cost, Textures texx) {
        super(texx);
        this.cost = cost;
        this.isAlive = true;
    }

	public Plant(double x, double y, int w, int h, int cost, Textures texx){
		super(x, y, w, h, texx);
        this.cost = cost;
		this.isAlive = true;
	}

	public boolean isAlive(){
		return this.isAlive;
	}

	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}

    public int getCost() {
        return this.cost;
    }
}