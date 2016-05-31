package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;
import avs.models.Grid;

import java.util.ArrayList;
import java.io.File;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;
import javax.imageio.ImageIO;

public class Banga extends Plant implements Runnable {
	private Animator anim; //animates this plant
    private Rectangle deathZone; //all the zombies that will come near this area will die
    private static BufferedImage deathZoneImg; //image for the death zone

    public Banga(Textures texx){
		super(95, 159, 150, texx);
		this.animation = texx.getBangaStaticArray();
		anim = new Animator(5,animation);
        this.deathZone = null;
	}

	public void tick(){
        if (this.deathZone != null) killZombies();

        anim.setImages(this.animation);
		anim.runAnimation();
	}

	public void render(Graphics g){
        if (this.deathZone != null) {
            g.drawImage(deathZoneImg, (int)deathZone.getX(), (int)deathZone.getY(),
                (int)deathZone.getWidth(), (int)deathZone.getHeight(), null);
        }

		anim.drawAnimation(g,getX(),getY(),width,height,0);
    }

    public void run() {
        try {
            int x = Grid.BUS_OFFSET + (this.getCol() - 1) * Grid.TILE_WIDTH;
            int y = Grid.SIDEWALK_OFFSET + (this.getRow() - 1) * Grid.TILE_HEIGHT;

            Thread.sleep(3000);

            this.deathZone = new Rectangle(x + 1, y + 1, Grid.TILE_WIDTH * 3 - 2, Grid.TILE_HEIGHT * 3 - 2);
            this.animation = texx.getBangaSpinningArray();
            Thread.sleep(2000);
            this.deathZone = null;

            this.animation = texx.getBangaDyingArray();
            Thread.sleep(580);

            this.kill();
            Game.getInstance().getGrid().setPlant(this.getRow(), this.getCol(), null);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //iterates through the Zombie list and if intersects or it is contained in the bounds of the Banga, it will die
    public void killZombies() {
        for (int i = 0; i < Game.getInstance().getZombieList().size(); ++i) {
            Zombie z = Game.getInstance().getZombieList().get(i);
            if (deathZone.intersects(z.getBounds()) || deathZone.contains(z.getBounds())) {
                z.kill();
            }
        }
    }

    //temporary
    @Override
    public void setY(double y) {
        super.setY(y - 26);
    }

    public static class AssetLoader implements Runnable {
        public void run() {
            try {
                Banga.deathZoneImg = ImageIO.read(new File("../assets/img/area-of-death.png"));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
