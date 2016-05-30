package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Zombie extends Entity{
	private BufferedImage[] animation;
	private int speed;

	public Zombie(double x, double y, Textures texx){
        super(x,y,117,217,texx);
		this.animation = texx.getZombieWalkingArray();
		this.speed = 1;
		anim = new Animator(5,animation);
	}

	public void tick(){

		this.x -= speed;
		anim.runAnimation();
	}

	public void render(Graphics g){
		anim.drawAnimation(g,x,y,117,217,0);
	}
}
