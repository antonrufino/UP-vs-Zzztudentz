package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Energy extends AllyEntity{
	private BufferedImage energyImg;

	public Energy(double x, double y, Textures texx){
		super(x,y,texx);
        this.width = 48;
        this.height = 75;
		energyImg = texx.getEnergy();
	}

	public void tick(){
		setY(getY()+2);
	}

	public void render(Graphics g){
		g.drawImage(energyImg, (int)this.getX(), (int)this.getY(), width, height,null);
	}
}
