package avs.models;

import avs.utils.Textures;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class EggWaffle extends Entity{
	private BufferedImage waffleImg;


	public EggWaffle(double x, double y, Textures texx){
		super(x,y,Textures.TOWER_WIDTH,Textures.TOWER_HEIGHT,texx);
		this.waffleImg = texx.getEggWaffle();
		this.width = Textures.TOWER_WIDTH;
		this.height = Textures.TOWER_HEIGHT;
	}

	public void tick(){
		setX(getX()+5);
	}

	public void render(Graphics g){
		g.drawImage(waffleImg, (int)this.getX(), (int)this.getY(), width, height, null);
	}
}
