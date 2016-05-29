package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class EggWaffle extends AllyEntity{
	private BufferedImage waffleImg;

	public EggWaffle(double x, double y, Textures texx){
		super(x,y,texx);
        this.width = 69;
        this.width = 150;
		this.waffleImg = texx.getEggWaffle();
	}

	public void tick(){
		setX(getX()+5);
	}

	public void render(Graphics g){
		g.drawImage(waffleImg, (int)this.getX(), (int)this.getY(), width, height, null);
	}
}
