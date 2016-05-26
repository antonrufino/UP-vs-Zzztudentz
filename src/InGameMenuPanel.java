package avs.ui;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
public class InGameMenuPanel extends JPanel{
    public InGameMenuPanel(){
        super();
        this.setOpaque(false);
        this.addBtn(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/menu/menu_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/menu/menu_hover.png"))
        );
        this.setPreferredSize(new Dimension(65,30));
    }
    private ImageIcon scaleImageIcon(ImageIcon icon) {
        Image scaledImg = icon.getImage().getScaledInstance(49, 30, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void addBtn(final ImageIcon defaultIcon,
        final ImageIcon hoverIcon) {
        final JButton btn = new JButton(defaultIcon);

        btn.setBorder(null);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(49,30));

        btn.addMouseListener(new MouseListener(){
            public void mouseEntered(MouseEvent me){
                btn.setIcon(hoverIcon);
            }
            public void mouseExited(MouseEvent me){
                btn.setIcon(defaultIcon);
            }
            public void mouseReleased(MouseEvent me){}
            public void mousePressed(MouseEvent me){}
            public void mouseClicked(MouseEvent me){}
        });

        this.add(btn);
    }
}
