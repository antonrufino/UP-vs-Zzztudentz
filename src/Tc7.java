package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tc7 extends Plant {
	private Animator anim;
	private BufferedImage notEaten;
	private BufferedImage halfEaten;
	private BufferedImage badlyEaten;
    private final int MAX_HP;

    public Tc7(Textures texx){
		super(100, 114, 50, texx);
		this.hp = this.MAX_HP = 1000;

		this.notEaten = texx.getTc7Frame(0);
		this.halfEaten = texx.getTc7Frame(1);
		this.badlyEaten = texx.getTc7Frame(2);
	}

	public void tick(){

	}

    public void run() { }

	public void render(Graphics g){
		if(this.hp >= (this.MAX_HP * 2) / 3) {
			g.drawImage(notEaten, (int)this.getX(), (int)this.getY(),width,height, null);
		}
		else if(this.hp < (this.MAX_HP * 2) / 3 && this.hp >= this.MAX_HP / 3){
			g.drawImage(halfEaten, (int)this.getX(), (int)this.getY(),width,height, null);
		}
		else if(this.hp < this.MAX_HP / 3 && this.hp > 0){
			g.drawImage(badlyEaten, (int)this.getX(), (int)this.getY(),width,height, null);
		}
	}
}
