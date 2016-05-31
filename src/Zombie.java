package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;
import avs.ui.MainFrame;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Zombie extends Entity{
	private BufferedImage[] animation;
	private int speed;
	private int hp;
	private int damage;
    private int row;

    public Zombie(Textures texx){
        super(117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		this.hp = 100;
		this.damage = 20;
		anim = new Animator(5,animation);
	}

	public void tick(){

		this.x -= speed;

		anim.runAnimation();
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

	public int getDamage(){
		return this.damage;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public synchronized void attack(Plant a){
		a.setHp(a.getHp() - this.damage);
	}

    public void setRow(int row) {
        this.row = row;
    }

    public Rectangle getBounds() {
        int y = Grid.SIDEWALK_OFFSET + this.row * Grid.TILE_HEIGHT;
        return new Rectangle((int) this.getX(), y, this.getWidth(), Grid.TILE_HEIGHT);
    }
}
