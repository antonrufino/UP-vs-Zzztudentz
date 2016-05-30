package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public abstract class Entity{

	private double x;
	private double y;
	private Textures texx;
    protected int width;
    protected int height;
    protected Animator anim;

    public Entity(int w, int h, Textures texx) {
        this.width = w;
        this.height = h;
        this.texx = texx;
        this.isAlive = true;
    }

	public Entity(double x, double y, int w, int h, Textures texx){
		this.x = x;
		this.y = y;
        this.width = w;
        this.height = h;
		this.texx = texx;
		this.isAlive = true;
	}

	public abstract void tick();
	public abstract void render(Graphics g);

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

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}