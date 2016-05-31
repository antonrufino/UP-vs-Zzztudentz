package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Kopiko extends Plant implements Runnable{
	private Energy e;

    public Kopiko(Textures texx) {
		super(41, 102, 50, texx);
        this.animation = texx.getKopikoStaticArray();
		this.anim = new Animator(5, animation);
	}

	public void tick(){
        this.anim.setImages(animation);
		this.anim.runAnimation();
	}

	public void run(){
		try{
            while (this.isAlive()) {
                Thread.sleep(10*1000);
                animation = texx.getKopikoEmittingArray();
                Thread.sleep(500);
                Game.getInstance().createEnergy(this);
                animation = texx.getKopikoStaticArray();
            }
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void render (Graphics g){
		this.anim.drawAnimation(g,getX(),getY(),width,height,0);
	}

	public int getHeight(){
		return this.height;
	}
}
