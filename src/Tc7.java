package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tc7 extends Plant {
	Animator anim;
	BufferedImage notEaten;
	BufferedImage halfEaten;
	BufferedImage badlyEaten;

    public Tc7(Textures texx){
		super(100, 114, 50, texx);
		this.hp = 600;

		this.notEaten = texx.getTc7Frame(0);
		this.halfEaten = texx.getTc7Frame(1);
		this.badlyEaten = texx.getTc7Frame(2);
	}

	public void tick(){

	}

    public void run() { }

	public void render(Graphics g){
		if(this.hp >= 400){
			g.drawImage(notEaten, (int)this.getX(), (int)this.getY(),width,height, null);
		}
		else if(this.hp < 400 && this.hp >= 200){
			g.drawImage(halfEaten, (int)this.getX(), (int)this.getY(),width,height, null);
		}
		else if(this.hp < 200 && this.hp > 0){
			g.drawImage(badlyEaten, (int)this.getX(), (int)this.getY(),width,height, null);
		}
	}
}
