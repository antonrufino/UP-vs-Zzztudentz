package avs.models;

import avs.ui.MainFrame;
import avs.utils.Animator;
import avs.utils.Textures;
import avs.utils.CollisionChecker;
import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Bus extends Entity{
	private BufferedImage[] animation;
	private int speed;
	private boolean isUsed;
	private Zombie zombie;
	Animator anim;
    private int row;

	public Bus(double x, double y, Textures texx){
		super(x,y,418,160,texx);
		this.animation = texx.getBusArray();
		this.speed = 5;
		this.isUsed = false;
		anim = new Animator(5,animation);
	}

	public void tick(){

		for(int i = 0; i<Game.getInstance().getZombieList().size(); i++){
			this.zombie = Game.getInstance().getZombieList().get(i);

			if(CollisionChecker.isColliding(zombie, this)){
				this.isUsed = true;
			}
		}

		if(isUsed){
			setX(getX()+speed);
			for(int i = 0; i<Game.getInstance().getZombieList().size(); i++){
				this.zombie = Game.getInstance().getZombieList().get(i);

				if(CollisionChecker.isColliding(zombie, this)){
					this.zombie.kill();
				}
			}
		}
		anim.runAnimation();
	}

	public void render(Graphics g){
		anim.drawAnimation(g,getX(),getY(),width,height,0);
	}

    public void setRow(int row) {
        this.row = row;
    }

    public Rectangle getBounds() {
        int y = Grid.SIDEWALK_OFFSET + this.row * Grid.TILE_HEIGHT;
        return new Rectangle((int) this.getX(), y + 1, this.getWidth(), Grid.TILE_HEIGHT - 2);
    }

    public boolean isAlive() {
        return getX() <= MainFrame.WIDTH;
    }
}
