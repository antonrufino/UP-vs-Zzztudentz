package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;
import avs.utils.CollisionChecker;
import avs.ui.MainFrame;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Zombie extends Entity implements Runnable{
	private BufferedImage[] animation;
	private int speed;
	private int hp;
	private int damage;
    private int row;
	private Plant target;
	private int targetRow;
	private int targetCol;

    public Zombie(Textures texx){
        super(117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		this.hp = 350;
		this.damage = 60;
		anim = new Animator(5,animation);
		this.target = null;
	}
	public void tick(){
		this.x -= speed;
		if (this.target == null) checkIfHitPlant();
		anim.setImages(animation);
		anim.runAnimation();
	}


	//if it collides with a plant, it starts the thread that will reduce the hp of the plant it is collided with
	private void checkIfHitPlant() {
		for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
            	Plant p = Game.getInstance().getGrid().getPlant(i, j);
                if(p != null){
                	if(CollisionChecker.isColliding(this,p)){
        				this.target = p;
        				this.targetRow = i;
        				this.targetCol = j;
            			animation = texx.getZombieEatingArray();
            			this.speed = 0;
            			new Thread(this).start();
                	}
                }
            }
        }
	}

	public void run(){
		try{
			while (this.target.isAlive()) {
				if(!this.isAlive()){
					break;
				}
				this.target.reduceHp(this.damage);
				Thread.sleep(1000);
			}

			this.target = null;
			this.speed = 1;
			animation = texx.getZombieWalkingArray();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void render(Graphics g){
		anim.drawAnimation(g,x,y,width,height,0);
	}

	public double getX(){
		return x;
	}

	public void setX(double x){
		this.x = x;
	}

	public double getY(){
		return y;
	}

	public void damageRec(int x){
		this.hp = hp - x;
	}

	public int getHp(){
		return this.hp;
	}

    synchronized void kill() {
        this.hp = 0;
    }

    boolean isAlive() {
        return this.hp > 0;
    }

	public int getDamage(){
		return this.damage;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow(){
    	return this.row;
    }

    public Rectangle getBounds() {
        int y = Grid.SIDEWALK_OFFSET + this.row * Grid.TILE_HEIGHT;
        return new Rectangle((int) this.getX(), y + 1, this.getWidth(), Grid.TILE_HEIGHT - 2);
    }
}
