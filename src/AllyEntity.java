package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class AllyEntity{

	private double x;
	private double y;
	private boolean isAlive;
	private Textures texx;
    protected int cost;

    public AllyEntity(Textures texx) {
        this.texx = texx;
        this.isAlive = true;
    }

	public AllyEntity(double x, double y, Textures texx){
		this.x = x;
		this.y = y;
		this.texx = texx;
		this.isAlive = true;
	}

	public abstract void tick();
	public abstract void render(Graphics g);

	public boolean isAlive(){
		return this.isAlive;
	}

	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

    public int getCost() {
        return this.cost;
    }
}
