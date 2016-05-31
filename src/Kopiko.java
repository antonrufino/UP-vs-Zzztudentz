package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Kopiko extends Plant {
	private Energy e;
	private ArrayList<Energy> lightnings = new ArrayList<Energy>();

    public Kopiko(Textures texx) {
		super(41, 102, 50, texx);
        this.animation = texx.getKopikoArray();

		this.anim = new Animator(5,animation);
	}

	public void tick(){

		this.anim.runAnimation();
	}

	public void render (Graphics g){
		this.anim.drawAnimation(g,getX(),getY(),width,height,0);
	}
}
