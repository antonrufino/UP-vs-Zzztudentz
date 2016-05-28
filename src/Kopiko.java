package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Kopiko extends AllyEntity{
	private BufferedImage[] animation;
	private Energy e;
	private ArrayList<Energy> lightnings = new ArrayList<Energy>();

	Animator anim;

	public Kopiko(double x, double y, Textures texx){
		super(x,y,texx);
		animation = texx.getKopikoArray();

		anim = new Animator(5,animation);

		for(int i=0; i<10; i++){
			lightnings.add(new Energy(200, 200, texx));
		}
	}

	public void tick(){

		anim.runAnimation();
		for(int i=0; i<lightnings.size(); i++){
			e = lightnings.get(i);

			e.tick();
		}
	}

	public void render (Graphics g){
		anim.drawAnimation(g,getX(),getY(),41,102,0);

		for(int i=0; i<lightnings.size(); i++){
			e = lightnings.get(i);
			e.render(g);
		}
	}
}
