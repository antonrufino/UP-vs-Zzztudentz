package avs.ui;

import avs.models.Game;
import avs.models.Grid;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.io.*;

class GameCanvas extends Canvas implements Runnable {
    private Game game;
    private boolean running;
    private GamePanel parent;

    public GameCanvas(GamePanel parent) {
        super();

        this.game = Game.getInstance();
        this.running = false;
        this.parent = parent;

        this.addListener();
    }

    public addListener() {
        this.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){
                if (!game.getSelectedPlant()) return;

                for (int i = 0; i < Grid.ROWS; ++i) {
                    for (int j = 0; j < Grid.COLS; ++j) {
                        Rectangle rect = game.getGrid().getRectangle(i, j);
                        if (rect.contains(me.getPoint())) {
                            if (!game.getGrid().hasPlant(i, j)) {
                                game.getGrid().setPlant(i, j, true);
                                game.reduceEnergy();
                                game.startButtonCoolDown();
                                game.setPendingButton(null);
                                game.selectPlant(false);
                            }
                            return;
                        }
                    }
                }
            }
        });
    }

    public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if(delta >= 1){
				tick();
				delta--;
			}
			render();
		}
		stop();
	}

    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
			return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(1, 68, 33));
        for (int i = 0; i < Grid.ROWS; ++i) {
            for int j = 0; i < Grid.COLS; ++j) {
                g2d.draw(game.getGrid().getRectangle(i, j));
            }
        }
    }

    public void start() {

    }
}
