package avs.models;

import avs.utils.Textures;
import avs.utils.CollisionChecker;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class EggWaffle extends Entity{
	private BufferedImage waffleImg;
	private Zombie z;

	public EggWaffle(double x, double y, Textures texx){
		super(x,y,69,150,texx);
		this.waffleImg = texx.getEggWaffle();
	}

	public void tick(){
		setX(getX()+5);

        for(int i = 0; i < Game.getInstance().getZombieList().size(); i++){
        	this.z = Game.getInstance().getZombieList().get(i);

			if(CollisionChecker.isColliding(z,this)){
				Game.getInstance().removeEggWaffle(this);
				z.damageRec(25);
				if(z.getHp() == 0)
					Game.getInstance().removeZombie(z);
			}
		}
	}

	public void render(Graphics g){
		g.drawImage(waffleImg, (int)this.getX(), (int)this.getY(), width, height, null);
	}
}
