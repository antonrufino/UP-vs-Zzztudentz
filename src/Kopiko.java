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
		super(Textures.KOPIKO_WIDTH, Textures.KOPIKO_HEIGHT, 50, texx);
        this.animation = texx.getKopikoArray();

		this.anim = new Animator(5,animation);

		for(int i=0; i<10; i++){
			lightnings.add(new Energy(200, 200, texx));
		}
	}

	public Kopiko(double x, double y, Textures texx){
		super(x, y, 41, 102, 50, texx);
		animation = texx.getKopikoArray();

		this.anim = new Animator(5,animation);

		for(int i=0; i<10; i++){
			lightnings.add(new Energy(200, 200, texx));
		}
	}

	public void tick(){

		this.anim.runAnimation();
		for(int i=0; i<lightnings.size(); i++){
			e = lightnings.get(i);

			e.tick();
		}
	}

	public void render (Graphics g){
		this.anim.drawAnimation(g,getX(),getY(),width,height,0);

		for(int i=0; i<lightnings.size(); i++){
			e = lightnings.get(i);
			e.render(g);
		}
	}
}
