package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Tower extends AllyEntity{
	private BufferedImage[] animation;
	private EggWaffle ew;
	private Textures texx;
	private ArrayList<EggWaffle> waffles = new ArrayList<EggWaffle>();

	private Animator anim;

    public Tower(Textures texx){
		super(texx);
		this.animation = texx.getTowerArray();
        this.cost = 100;
        this.width = 69;
        this.height = 150;

		anim = new Animator(5,animation);

		for(int i=0; i<10; i++){
			waffles.add(new EggWaffle(100, 100, texx));
		}
	}

	public Tower(double x, double y, Textures texx){
		super(x,y,texx);
		animation = texx.getTowerArray();
        cost = 100;

		anim = new Animator(5,animation);

		for(int i=0; i<10; i++){
			waffles.add(new EggWaffle(100, 100, texx));
		}
	}

	public void tick(){
		anim.runAnimation();

		for(int i=0; i<waffles.size(); i++){
			ew = waffles.get(i);

			ew.tick();
		}
	}

	public void render(Graphics g){

		anim.drawAnimation(g,getX(),getY(),width,height,0);

		for(int i=0; i<waffles.size(); i++){
			ew = waffles.get(i);

			ew.render(g);
		}
	}

    //temporary
    @Override
    public void setY(double y) {
        super.setY(y - 26);
    }
}
