package avs.utils;

import avs.ui.GamePanel;

import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Textures{

	public static final int BUS_WIDTH = 1742;
	public static final int BUS_HEIGHT = 667;
	public static final int BANGA_WIDTH = 396;
	public static final int BANGA_HEIGHT = 660;
	public static final int KOPIKO_WIDTH = 167;
	public static final int KOPIKO_HEIGHT = 422;
	public static final int ENERGY_WIDTH = 218;
	public static final int ENERGY_HEIGHT = 314;
	public static final int TOWER_WIDTH = 288;
	public static final int TOWER_HEIGHT = 622;
	public static final int ZOMBIE_WIDTH = 489;
	public static final int ZOMBIE_HEIGHT = 901;
	public static final int TC7_WIDTH = 417;
	public static final int TC7_HEIGHT = 476;
	// public static final int EGG_WAFFLE_WIDTH = 90;
	// public static final int EGG_WAFFLE_HEIGHT = 90;

	private BufferedImage[] zombieWalking;
	private BufferedImage[] zombieEating;
	private BufferedImage[] tc7;
	private BufferedImage[] bus;
	private BufferedImage[] bangaStatic;
    private BufferedImage[] bangaSpinning;
    private BufferedImage[] bangaDying;
	private BufferedImage[] kopikoStatic;
    private BufferedImage[] kopikoEmitting;
	private BufferedImage[] tower;

	private BufferedImage energy;
	private BufferedImage eggWaffle;

	private SpriteSheet ss;

	public Textures(GamePanel game){
		ss = new SpriteSheet(game.getSpriteSheet());

		this.zombieWalking = new BufferedImage[2];
		this.zombieEating = new BufferedImage[4];
		this.tc7 = new BufferedImage[3];
		this.bangaStatic = new BufferedImage[1];
        this.bangaSpinning = new BufferedImage[2];
        this.bangaDying = new BufferedImage[6];
        this.bus = new BufferedImage[6];
		this.tower = new BufferedImage[4];
		this.kopikoStatic = new BufferedImage[1];
        this.kopikoEmitting = new BufferedImage[5];

		getTextures();
	}

	public void getTextures(){
		int index = 0;
		for(int i = 0; i < 6; i++){
			if(i==0 || i==5){
				zombieWalking[index] = ss.grabImage(i+18,2,ZOMBIE_WIDTH,ZOMBIE_HEIGHT);
				index += 1;
			}
			else{
				zombieEating[i-1] = ss.grabImage(i+18,2,ZOMBIE_WIDTH,ZOMBIE_HEIGHT);
			}
		}

        this.bangaStatic[0] = ss.grabImage(1, 2, BANGA_WIDTH, BANGA_HEIGHT);
        for (int i = 0; i < 2; ++i) this.bangaSpinning[i] = ss.grabImage(i+2, 2, BANGA_WIDTH, BANGA_HEIGHT);
        for (int i = 0; i < 6; ++i) this.bangaDying[i] = ss.grabImage(i+4, 2, BANGA_WIDTH, BANGA_HEIGHT);

		for(int i = 0; i < 3; i++)	tc7[i] = ss.grabImage(i+10,2,TC7_WIDTH,TC7_HEIGHT);
		for(int i = 0; i < 4; i++)	tower[i] = ss.grabImage(i+13, 2, TOWER_WIDTH, TOWER_HEIGHT);
		this.eggWaffle = ss.grabImage(17,2,KOPIKO_WIDTH,KOPIKO_HEIGHT);

		for(int i = 0; i < 6; i++)	bus[i] = ss.grabImage(i+1, 1, BUS_WIDTH, BUS_HEIGHT);

		//for(int i = 0; i < 6; i++)	kopiko[i] = ss.grabImage(i+1,3,KOPIKO_WIDTH,KOPIKO_HEIGHT);
        kopikoStatic[0] = ss.grabImage(1, 3, KOPIKO_WIDTH, KOPIKO_HEIGHT);
        for (int i = 0; i < 5; i++) kopikoEmitting[i] = ss.grabImage(i+2,3,KOPIKO_WIDTH,KOPIKO_HEIGHT);

		this.energy = ss.grabImage(7,3,ENERGY_WIDTH,ENERGY_HEIGHT);

	}

	public BufferedImage[] getZombieEatingArray(){
		return this.zombieEating;
	}

	public BufferedImage[] getZombieWalkingArray(){
		return this.zombieWalking;
	}

	public BufferedImage[] getTc7Array(){
		return this.tc7;
	}

	public BufferedImage[] getBangaStaticArray(){
		return this.bangaStatic;
	}

    public BufferedImage[] getBangaSpinningArray(){
		return this.bangaSpinning;
	}

    public BufferedImage[] getBangaDyingArray(){
		return this.bangaDying;
	}

	public BufferedImage[] getBusArray(){
		return this.bus;
	}

	public BufferedImage[] getKopikoStaticArray(){
		return this.kopikoStatic;
	}

    public BufferedImage[] getKopikoEmittingArray(){
        return this.kopikoEmitting;
    }

	public BufferedImage[] getTowerArray(){
		return this.tower;
	}

	public BufferedImage getEnergy(){
		return this.energy;
	}

	public BufferedImage getEggWaffle(){
		return this.eggWaffle;
	}

	public BufferedImage getTc7Frame(int f){
		return this.tc7[f];
	}
}
