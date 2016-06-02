package avs.models;

import avs.utils.Textures;
import avs.utils.CollisionChecker;
import avs.ui.MainFrame;

import java.awt.image.*;
import java.awt.*;
import javax.swing.*;

import java.util.Iterator;

public class EggWaffle extends Entity{
	private BufferedImage waffleImg;
	private Zombie z;
    private boolean alive;
    private int row;

	public EggWaffle(double x, double y, Textures texx){
		super(x,y,69,150,texx);
		this.waffleImg = texx.getEggWaffle();
        this.alive = true;
    }

	public void tick(){
		setX(getX()+5);

        Iterator<Zombie> iter = Game.getInstance().getZombieList().iterator();
        while (iter.hasNext()) {
        	this.z = iter.next();

			if(CollisionChecker.isColliding(z,this)){
				this.alive = false;
				z.damageRec(70);
                return;
			}
		}

        if (this.x > MainFrame.WIDTH) this.alive = false;
	}

	public void render(Graphics g){
		g.drawImage(waffleImg, (int)this.getX(), (int)this.getY(), width, height, null);
	}

    public boolean isAlive() {
        return this.alive;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Rectangle getBounds() {
        int y = Grid.SIDEWALK_OFFSET + this.row * Grid.TILE_HEIGHT;
        return new Rectangle((int) this.getX(), y + 1, this.getWidth(), Grid.TILE_HEIGHT - 2);
    }
}
