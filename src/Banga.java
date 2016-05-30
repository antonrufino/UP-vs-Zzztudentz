package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Banga extends Plant{
	Animator anim;

    public Banga(Textures texx){
		super(95, 159, 150, texx);
		this.animation = texx.getBangaArray();
		anim = new Animator(5,animation);
	}

	public Banga(double x, double y, Textures texx){
		super(x,y,95,159,150,texx);
		this.animation = texx.getBangaArray();
		anim = new Animator(5,animation);
	}

	public void tick(){
		anim.runAnimation();
	}

	public void render(Graphics g){
		//g.drawImage(busImage, (int)this.getX(), (int)this.getY(), null);
		anim.drawAnimation(g,getX(),getY(),width,height,0);
	}

    //temporary
    @Override
    public void setY(double y) {
        super.setY(y - 26);
    }
}
