package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Banga extends AllyEntity{
	private BufferedImage[] animation;
	Animator anim;

	public Banga(double x, double y, Textures texx){
		super(x,y,texx);
		this.animation = texx.getBangaArray();
        this.cost = 150;
		anim = new Animator(5,animation);
	}

	public void tick(){
		anim.runAnimation();
	}

	public void render(Graphics g){
		//g.drawImage(busImage, (int)this.getX(), (int)this.getY(), null);
		anim.drawAnimation(g,getX(),getY(),95,159,0);
	}
}
