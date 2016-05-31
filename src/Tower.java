package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tower extends Plant {
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

	public void render(Graphics g){
		anim.drawAnimation(g,getX(),getY(),width,height,0);
	}

    //temporary
    @Override
    public void setY(double y) {
        super.setY(y - 26);
    }
}
