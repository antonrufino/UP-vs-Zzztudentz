package avs.utils; 

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Animator{
	private BufferedImage[] images;
	private BufferedImage current;
	private int speed;
	private int frames;
	private int index;
	private int count;

	public Animator(int speed, BufferedImage[] images){
		this.speed = speed;
		this.images = images;
		this.index = 0;
		this.count = 0;
		this.frames = images.length-1;
	}

	public void runAnimation(){
		index += 1;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}

	public void nextFrame(){
		current = images[count];
		count += 1;
		if(count > frames)	count = 0;
	}

	public void drawAnimation(Graphics g, double x, double y, int width, int height,int offset){
		g.drawImage(current,(int) x - offset, (int) y, width, height, null);
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getSpeed(){
		return this.speed;
	}

	public int getCount(){
		return this.count;
	}

	public void setImages(BufferedImage[] images){
		this.images = images;
	}
}
