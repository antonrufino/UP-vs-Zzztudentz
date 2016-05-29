package avs.models;

import avs.utils.Textures;

import java.util.*;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Controller{

	private ArrayList<AllyEntity> allyList;
	private ArrayList<Zombie> zombieList;
	private AllyEntity ally;
	private Zombie zombie;
	private Textures tex;

	private Random rand;

	public Controller(Textures tex){
		this.tex = tex;
		this.allyList = new ArrayList<AllyEntity>();
		this.zombieList = new ArrayList<Zombie>();
		this.rand = new Random();
	}

	public void tick(){
		for(int i = 0; i<allyList.size(); i++){
			ally = allyList.get(i);
			ally.tick();
		}

		for(int i = 0; i<zombieList.size(); i++){
			zombie = zombieList.get(i);
			zombie.tick();
		}
	}

	public void render(Graphics g){
		for(int i = 0; i<allyList.size(); i++){
			ally = allyList.get(i);
			ally.render(g);
		}

		for(int i = 0; i<zombieList.size(); i++){
			zombie = zombieList.get(i);
			zombie.render(g);
		}
	}

	public void createZombie(int enemyCount){
		for(int i = 0; i < enemyCount; i++){
			addZombie(new Zombie(100,100,tex));
		}
	}

	public void addZombie(Zombie z){
		zombieList.add(z);
	}

	public void removeZombie(Zombie z){
		zombieList.remove(z);
	}

	public void addAlly(AllyEntity a){
		allyList.add(a);
	}

	public void removeAlly(AllyEntity a){
		allyList.add(a);
	}
}
