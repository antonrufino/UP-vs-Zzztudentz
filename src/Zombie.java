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
	private Thread thread;
	private boolean isEating;

	public Zombie(double x, double y, Textures texx){
        super(x,y,117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		this.hp = 100;
		this.damage = 20;
		this.thread = new Thread(this);
		anim = new Animator(5,animation);
	}

	public void tick(){

		this.x -= speed;

		for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                if(Game.getInstance().getGrid().hasPlant(i, j)){
                	if(CollisionChecker.isColliding(this,Game.getInstance().getGrid().getPlant(i,j))){
                		if(Game.getInstance().getGrid().getPlant(i,j).getHp() != 0){
                			animation = texx.getZombieEatingArray();
                			Game.getInstance().getGrid().getPlant(i,j).setHp(Game.getInstance().getGrid().getPlant(i,j).getHp() - this.damage);
                			this.speed = 0;
                		}else{
                			Game.getInstance().getGrid().setPlant(i,j,null);
                			animation = texx.getZombieWalkingArray();
                			this.speed = 1;
                		}
                	}
                }
            }
        }

		anim.setImages(animation);
		anim.runAnimation();
	}

	public void run(){

	}

	public void render(Graphics g){
		anim.drawAnimation(g,x,y,117,217,0);
	}

	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 113, 109); 
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
	
	public synchronized void attack(Plant a){
		a.setHp(a.getHp() - this.damage);
	}
}
