package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Zombie extends Entity{
	private BufferedImage[] animation;
	private int speed;

	public Zombie(double x, double y, Textures texx){
        super(x,y,117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		anim = new Animator(5,animation);
	}

	public void tick(){

		this.x -= speed;

		

		anim.runAnimation();
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
	
	public synchronized void attack(AllyEntity a){
		a.setHp(a.getHp() - this.damage);
	}
}
