package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class EggWaffle extends AllyEntity{
	private BufferedImage waffleImg;

	public EggWaffle(double x, double y, Textures texx){
		super(x,y,texx);
		this.waffleImg = texx.getEggWaffle();
	}

	public void tick(){
		setX(getX()+5);
	}

	public void render(Graphics g){
		g.drawImage(waffleImg, (int)this.getX(), (int)this.getY(), 69, 150, null);
	}
}
