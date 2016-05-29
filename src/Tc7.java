package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tc7 extends AllyEntity{
	private BufferedImage[] animation;
	Animator anim;

    public Tc7(Textures texx){
		super(texx);
		this.animation = texx.getTc7Array();
        this.cost = 50;
        this.width = 100;
        this.height = 114;
		anim = new Animator(5,animation);
	}

	public Tc7(double x, double y, Textures texx){
		super(x,y,texx);
		this.animation = texx.getTc7Array();
        this.cost = 50;
		anim = new Animator(5,animation);
	}

	public void tick(){
		anim.runAnimation();
	}

	public void render(Graphics g){
		//g.drawImage(busImage, (int)this.getX(), (int)this.getY(), null);
		anim.drawAnimation(g,getX(),getY(),width,height,0);
	}
}
