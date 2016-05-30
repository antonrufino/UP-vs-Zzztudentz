package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class Plant {
	private boolean isAlive;
    protected int cost;

    public Plant(Textures texx) {
        super(texx);
        this.isAlive = true;
    }

	public Plant(double x, double y, Textures texx){
		super(x, y, texx);
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
