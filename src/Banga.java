package avs.models;

import avs.utils.Animator;
import avs.utils.Textures;
import avs.models.Grid;

import java.util.ArrayList;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class Banga extends Plant implements Runnable {
	private Animator anim;
    private Rectangle deathZone;

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
		//g.drawImage(busImage, (int)this.getX(), (int)this.getY(), null);
        if (this.deathZone != null) {
            g.setColor(new Color(123, 17, 19, 128));
            ((Graphics2D) g).fill(this.deathZone);
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
            Thread.sleep(650);

            this.kill();
            Game.getInstance().getGrid().setPlant(this.getRow(), this.getCol(), null);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

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
}
