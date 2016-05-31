package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tower extends Plant implements Runnable{
	private BufferedImage[] animation;

	private Animator anim;

    public Tower(Textures texx){
		super(69,150,100,texx);
		this.animation = texx.getTowerArray();
		anim = new Animator(5,animation);
	}

	public void tick(){
		anim.runAnimation();
	}

	public void run(){
		try{
			while(true){
				if(isShooting() && this.isAlive()){
					Game.getInstance().createEggWaffle(this);
                    Thread.sleep(3*1000);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public boolean isShooting(){
		CopyOnWriteArrayList<Zombie> zombieList = Game.getInstance().getZombieList();
        Iterator<Zombie> iter = zombieList.iterator();
        while(iter.hasNext()){
			if(this.getRow() == iter.next().getRow()){
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
