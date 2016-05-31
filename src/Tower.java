package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tower extends Plant implements Runnable{
	private BufferedImage[] animation;
	private Thread thread;

	private Animator anim;

    public Tower(Textures texx){
		super(69,150,100,texx);
		this.animation = texx.getTowerArray();
		this.thread = new Thread(this);
		anim = new Animator(5,animation);

		thread.start();
	}

	public Tower(double x, double y, Textures texx){
		super(69,150,100,texx);
		animation = texx.getTowerArray();
		anim = new Animator(10,animation);
		this.thread = new Thread(this);

		thread.start();
	}

	public void tick(){
		anim.runAnimation();
	}

	public void run(){		
		try{
			while(true){
				if(isShooting()){
					thread.sleep(3*1000);
					Game.getInstance().createEggWaffle(this);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}	
	}
	
	public boolean isShooting(){
		ArrayList<Zombie> zombieList = Game.getInstance().getZombieList();
		for(int i = 0; i<zombieList.size(); i++){
			System.out.println("Plant row:"+this.getRow()+"Zombie row"+zombieList.get(i).getRow());
			if(this.getRow() == zombieList.get(i).getRow()){
				return true;
			}
		}
		return false;
	}

	public void render(Graphics g){
		anim.drawAnimation(g,getX(),getY(),width,height,0);
	}

    //temporary
    @Override
    public void setY(double y) {
        super.setY(y - 26);
    }
}
