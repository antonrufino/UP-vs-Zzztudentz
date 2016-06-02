package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Energy extends Entity implements Runnable {
	private BufferedImage energyImg;
    private double targetY;	//where will it land
    private int amount;
    private boolean alive;
    private final int LIFE_SPAN = 9000;
    private Thread thread;

	public Energy(double x, double y, double targetY, int amount, Textures texx){
		super(x,y,48,75,texx);
        this.targetY = targetY;
        this.amount = amount;
		energyImg = texx.getEnergy();
        this.alive = true;
	}

	public void tick(){
		if (this.getY() < this.targetY) setY(getY()+2);
	}

	public void render(Graphics g){
		g.drawImage(energyImg, (int)this.getX(), (int)this.getY(), width, height,null);
	}

    public int getAmount() {
        return this.amount;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void start() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    private void stop() {
        this.thread.interrupt();
    }

    public void kill() {
        this.alive = false;
        this.stop();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(LIFE_SPAN);
            this.alive = false;
        } catch (InterruptedException e) {
            return;
        }
    }
}
