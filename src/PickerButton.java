package avs.ui;

import avs.models.Game;
import avs.models.Plant;
import avs.models.PlantFactory;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class PickerButton extends JButton implements Runnable {
    private boolean isClickable;
    private int coolDown;
    private PlantFactory factory;
    private int progress;
    private static final int SPEED = 100;
    private boolean isPending;

    private final ImageIcon defaultIcon;
    private final ImageIcon hoverIcon;

    public PickerButton(final ImageIcon defaultIcon,
        final ImageIcon hoverIcon, PlantFactory factory, int coolDown) {
        super(defaultIcon);

        this.defaultIcon = defaultIcon;
        this.hoverIcon = hoverIcon;
        this.factory = factory;
        this.coolDown = coolDown * PickerButton.SPEED;

        this.isClickable = true;
        this.progress = 0;
        this.isPending = false;

        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(100,60));

        addListeners();
    }

    public void addListeners() {
        this.addMouseListener(new MouseListener(){
            public void mouseEntered(MouseEvent me){
                if (isClickable && !isPending) setIcon(hoverIcon);
            }
            public void mouseExited(MouseEvent me){
                if (!isPending) setIcon(defaultIcon);
            }
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){}
        });

        this.addActionListener(new PickPlantAction(this));
    }

    public void run() {
        this.progress = 0;
        try {
            while (this.progress < this.coolDown) {
                this.progress += PickerButton.SPEED;
                this.repaint();
                Thread.sleep(PickerButton.SPEED);
            }
            this.isClickable = true;
            this.stopPending();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void startCoolDown() {
        this.isClickable = false;
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        try {
            if (!this.isClickable) {
                float newFloatHeight = (float) this.getHeight() *
                    (float) (this.coolDown - this.progress) / (float) this.coolDown;
                int newHeight = (int) newFloatHeight;

                g2d.setColor(new Color(123, 17, 19, 128));
                g2d.fillRect(0, 0, this.getWidth(), newHeight);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void startPending() {
        this.isPending = true;
        this.setIcon(this.hoverIcon);
    }

    public void stopPending() {
        this.isPending = false;
        this.setIcon(this.defaultIcon);
    }

    private class PickPlantAction implements ActionListener {
        private PickerButton button;

        public PickPlantAction(PickerButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            Game game = Game.getInstance();
            Plant plant = button.factory.makePlant();
            if (button.isClickable && game.getEnergy() >= plant.getCost()) {
                button.setIcon(button.hoverIcon);

                game.selectPlant(plant);
                game.setPendingButton(button);
            }
        }
    }
}
