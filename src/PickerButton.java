package avs.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

class PickerButton extends JButton implements Runnable {
    private boolean isClickable;
    private int coolDown;
    private int progress;
    private static final int SPEED = 100;

    public PickerButton(final ImageIcon defaultIcon,
        final ImageIcon hoverIcon, int coolDown) {
        super(defaultIcon);

        this.isClickable = true;
        this.coolDown = coolDown * PickerButton.SPEED;
        this.progress = 0;

        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(100,60));

        this.addMouseListener(new MouseListener(){
            public void mouseEntered(MouseEvent me){
                if (isClickable) setIcon(hoverIcon);
            }
            public void mouseExited(MouseEvent me){
                setIcon(defaultIcon);
            }
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){}
        });

        final PickerButton self = this;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isClickable) {
                    new Thread(self).start();
                }
            }
        });
    }

    public void run() {
        this.progress = 0;
        try {
            this.isClickable = false;
            while (this.progress < this.coolDown) {
                this.progress += PickerButton.SPEED;
                this.repaint();
                Thread.sleep(PickerButton.SPEED);
            }
            this.isClickable = true;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
}
