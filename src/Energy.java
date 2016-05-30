package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Energy extends Entity{
	private BufferedImage energyImg;

	public Energy(double x, double y, Textures texx){
		super(x,y,48,75,texx);
		energyImg = texx.getEnergy();
	}

	public void tick(){
		setY(getY()+2);
	}

	public void render(Graphics g){
		g.drawImage(energyImg, (int)this.getX(), (int)this.getY(), width, height,null);
	}

	public Rectangle getBounds(){
		return new Rectangle((int) this.getX(), (int) this.getY(), Textures.ENERGY_WIDTH, Textures.ENERGY_HEIGHT); 
	}
}
