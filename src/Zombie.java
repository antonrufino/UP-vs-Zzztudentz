package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;
import avs.utils.CollisionChecker;
import avs.ui.MainFrame;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Zombie extends Entity implements Runnable{
	private BufferedImage[] animation;
	private int speed;
	private int hp;
	private int damage;
	private Plant target;
	private int targetRow;
	private int targetCol;

    public Zombie(Textures texx){
        super(117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		this.hp = 100;
		this.damage = 20;
		anim = new Animator(5,animation);
		this.target = null;
	}

	public Zombie(double x, double y, Textures texx){
        super(x,y,117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		this.hp = 100;
		this.damage = 20;
		anim = new Animator(5,animation);
		this.target = null;
	}

	public void tick(){

		this.x -= speed;
		if (this.target == null) checkIfHitPlant();

		anim.setImages(animation);
		anim.runAnimation();
	}

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
				this.target.reduceHp(this.damage);
				Thread.sleep(3*1000);
			}
			Game.getInstance().getGrid().setPlant(targetRow, targetCol, null);
			this.target = null;
			this.speed = 1;
			animation = texx.getZombieWalkingArray();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void render(Graphics g){
		anim.drawAnimation(g,x,y,117,217,0);
	}

	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 150, 150);
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

	public int getDamage(){
		return this.damage;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}
}
