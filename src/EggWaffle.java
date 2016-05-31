package avs.models;

import avs.utils.Textures;
import avs.utils.CollisionChecker;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

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

        for(int i = 0; i < Game.getInstance().getZombieList().size(); i++){
        	this.z = Game.getInstance().getZombieList().get(i);

			if(CollisionChecker.isColliding(z,this)){
				this.alive = false;
				z.damageRec(50);
			}
		}
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
