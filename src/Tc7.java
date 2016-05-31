package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tc7 extends Plant {
	Animator anim;

    public Tc7(Textures texx){
		super(100, 114, 50, texx);
		this.animation = texx.getTc7Array();
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
