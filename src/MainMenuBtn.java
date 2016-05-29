package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainMenuBtn extends JButton {
    private final ImageIcon defaultIcon;
    private final ImageIcon hoverIcon;

    public MainMenuBtn(final ImageIcon defaultIcon, final ImageIcon hoverIcon,
        int width, int height){
        super(defaultIcon);

        this.defaultIcon = defaultIcon;
        this.hoverIcon = hoverIcon;
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(width,height));
        addListeners();
    }
    public void addListeners() {
        this.addMouseListener(new MouseListener(){
            public void mouseEntered(MouseEvent me){
                setIcon(hoverIcon);
            }
            public void mouseExited(MouseEvent me){
                setIcon(defaultIcon);
            }
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){}
        });

    }
}
