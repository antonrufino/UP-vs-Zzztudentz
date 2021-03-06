// This is where the game will be played

package avs.ui;

import avs.models.Grid;
import avs.models.Game;
import avs.models.Energy;
import avs.models.Bus;
import avs.models.Zombie;
import avs.models.EggWaffle;
import avs.utils.BufferedImageLoader;
import avs.utils.Textures;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.Iterator;

public class GamePanel extends JPanel implements Runnable {
    private Game game; // Instance of game singleton class
    private boolean running;
    private Thread thread; // Game thread
    private ProgressBarPanel progressBarPanel;
    private EnergyBar energyBar;
    private PlantPickerPanel pickerPanel; // Panel containing buttons where players can choose plants.
    private JPanel topPanel;
    private JButton inviBtn;
    private int renderFlag;
    private int score;

    private static BufferedImage bg;
    private static BufferedImage spriteSheet;
    private Textures tex;

    public GamePanel() {
        this.game = Game.getInstance(); // Get an instance of the Game singleton class.
        this.tex = new Textures(this);
        this.running = false;
        this.game.setTextures(tex);
        this.renderFlag = 0;
        this.score = 0;
        this.inviBtn  = new JButton();
        createPanelUI();
        addListeners();
    }

    private void createPanelUI() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        topPanel = new JPanel(new BorderLayout());
        JPanel westPanel = new JPanel(new BorderLayout());
        JPanel eastPanel = new JPanel(new BorderLayout());
        progressBarPanel = new ProgressBarPanel();
        energyBar = new EnergyBar();

        pickerPanel = new PlantPickerPanel(this.tex);
        westPanel.setOpaque(false);
        westPanel.add(energyBar, BorderLayout.WEST);
        westPanel.add(pickerPanel, BorderLayout.EAST);

        // Stops game.
        InGameMenuPanel igmp = new InGameMenuPanel(0);
        igmp.addAdditionalActionListerner(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.stop();
                stop();
                pickerPanel.reset();
            }
        });

        eastPanel.setOpaque(false);
        eastPanel.add(progressBarPanel, BorderLayout.WEST);
        eastPanel.add(igmp, BorderLayout.EAST);
        eastPanel.setPreferredSize(new Dimension(350, 45));

        topPanel.setOpaque(false);
        topPanel.add(westPanel, BorderLayout.WEST);
        topPanel.add(eastPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);
    }

    private void addListeners() {
        this.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent me){}
            public void mouseExited(MouseEvent me){}
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){
                // A mouse click can collect energy or place a plant on the grid.
                Point p = me.getPoint();

                if (p == null) return;

                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (game.getSelectedPlant() != null)game.placePlant(p);
                    else game.collectEnergy(p);
                } else if (SwingUtilities.isRightMouseButton(me)) {
                    if (game.getSelectedPlant() != null) game.undoSelectPlant();
                    else game.removePlant(p);
                }
            }
        });
    }

    public void run(){
		Long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		while(running){
			Long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if(delta >= 1){
				tick();
                game.tick();
				delta--;
			}
            repaint();
            progressBarPanel.repaint();
            energyBar.setValue(game.getEnergy());
		}
		stop();
	}

    public void tick() {
        if (!progressBarPanel.isDone()) {
            progressBarPanel.update();
        }
    }

    public synchronized void start() {
        if (running) return;

        running = true;
        thread = new Thread(this);

        game.init();
        this.remove(inviBtn);
        this.topPanel.setVisible(true);
        thread.start();

    }

    public synchronized void stop() {
        if (!running) return;

        running = false;
    }

    // Rendering functions.

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.paintComponent(g);

        try {
            g2d.drawImage(GamePanel.bg, 0, 0,
                this.getWidth(), this.getHeight(), null);

            g2d.setColor(new Color(1, 68, 33, 128));

            Point p = this.getMousePosition();
            if (p != null && game.getSelectedPlant() != null) {
                highlightTile(p, g2d);
            }

            renderPlants(g);
            renderZombies(g);
            renderEnergies(g);
            renderBus(g);
            renderEggWaffles(g);
            renderHighScore(g2d);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void highlightTile(Point p, Graphics2D g2d) {
        for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                Rectangle rect = game.getGrid().getRectangle(i, j);
                if (rect.contains(p)) {
                    g2d.fill(rect);
                };
            }
        }
    }

    private void renderHighScore(Graphics2D g2d){ //Checks if a Zombie goes beyond the size of the frame
        if(game.getGameState()){
            if(this.renderFlag == 0){
                this.score = game.getZombieKilled() -1;
                this.renderFlag += 1;
            }
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Smoothens the edges of the g2d

            g2d.setColor(new Color(0,0,0,128));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setFont(EnergyBar.loadFont(40f, getClass().getResourceAsStream("/assets/fonts/Adventure.otf")));
            g2d.setColor(Color.WHITE);
            g2d.drawString("YOUR SCORE", MainFrame.WIDTH/2 -80, 90);

            g2d.setFont(EnergyBar.loadFont(120f, getClass().getResourceAsStream("/assets/fonts/Adventure.otf")));
            g2d.setColor(Color.WHITE);
            g2d.drawString(Integer.toString(score), MainFrame.WIDTH/2 -10, 200);

            g2d.setFont(EnergyBar.loadFont(30f, getClass().getResourceAsStream("/assets/fonts/Adventure.otf")));
            g2d.setColor(Color.YELLOW);

            if(PlayerNameTextField.name == ""){
                g2d.drawString("NO NAME", MainFrame.WIDTH/2 -30, 250);
            }
            else{
                g2d.drawString(PlayerNameTextField.name.toUpperCase(), MainFrame.WIDTH/2 -30, 250);
            }

            g2d.setFont(EnergyBar.loadFont(20f, getClass().getResourceAsStream("/assets/fonts/Adventure.otf")));
            g2d.setColor(Color.WHITE);
            g2d.drawString("CLICK ANYWHERE TO CONTINUE", MainFrame.WIDTH/2 -110, 600);
            topPanel.setVisible(false);


            inviBtn.setBorder(null);
            inviBtn.setContentAreaFilled(false);
            inviBtn.setOpaque(false);
            inviBtn.setBorderPainted(false);
            inviBtn.addActionListener(new MainFrame.SwitchPanelAction(MainFrame.MENU)); //Go back to the main menu
            inviBtn.addMouseListener(new MouseListener(){ //Ends the game and resets the state of in-game buttons
                public void mouseEntered(MouseEvent e){}
                public void mouseExited(MouseEvent e){}
                public void mouseClicked(MouseEvent e){}
                public void mousePressed(MouseEvent e){}
                public void mouseReleased(MouseEvent e){
                    stop();
                    game.stop();
                    pickerPanel.reset();
                }
            });
            this.add(inviBtn, BorderLayout.CENTER);

        }
    }

    private void renderPlants(Graphics g) {
        for (int i = 0; i < Grid.ROWS; ++i) {
            for (int j = 0; j < Grid.COLS; ++j) {
                if (game.getGrid().hasPlant(i, j)) {
                    game.getGrid().getPlant(i, j).render(g);
                }
            }
        }
    }

    private void renderZombies(Graphics g) {
        Iterator<Zombie> iter = game.getZombieList().iterator();
        while(iter.hasNext()){
              iter.next().render(g);
        }
    }

    private void renderEggWaffles(final Graphics g){
        Iterator<EggWaffle> iter = game.getEggWaffleList().iterator();
        while(iter.hasNext()){
              iter.next().render(g);
        }
    }

    private void renderEnergies(Graphics g) {
        Iterator<Energy> iter = game.getEnergyList().iterator();
        while(iter.hasNext()){
              iter.next().render(g);
        }
    }

    private void renderBus(Graphics g){
        Iterator<Bus> iter = game.getBusList().iterator();
        while(iter.hasNext()){
              iter.next().render(g);
        }
    }

    public static BufferedImage getSpriteSheet() {
        return GamePanel.spriteSheet;
    }

    // Asynchronously loads assets.
    public static class AssetLoader implements Runnable {
        @Override
        public void run() {
            BufferedImageLoader loader = new BufferedImageLoader();

            try {
                GamePanel.bg = loader.loadImage("/assets/img/background.png");
                GamePanel.spriteSheet = loader.loadImage("/assets/img/spritesheets/spritesheet-fullres.png");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
