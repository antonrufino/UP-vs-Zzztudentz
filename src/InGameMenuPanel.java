package avs.ui;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;


public class InGameMenuPanel extends JPanel{
    private JButton menuBtn;

    public InGameMenuPanel(int type){
        super();
        this.setOpaque(false);
        this.addBtn(type,
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/menu/menu_default.png"))),
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/menu/menu_hover.png")))
        );
        this.setPreferredSize(new Dimension(65,40));
    }
    private ImageIcon scaleImageIcon(ImageIcon icon) { //Scales the button
        Image scaledImg = icon.getImage().getScaledInstance(49, 30, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void addBtn(int type, final ImageIcon defaultIcon,
        final ImageIcon hoverIcon) {
        final JButton btn = new JButton(defaultIcon);
        this.menuBtn = btn;

        btn.setBorder(null);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(49,30));

        btn.addMouseListener(new MouseListener(){ //Set the hovering of the button
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

        if(type == 0){
            btn.addActionListener(
                new MainFrame.SwitchPanelAction(MainFrame.MENU));
        }
        else if(type == 1){
            btn.addActionListener(
                new MainFrame.SwitchPanelAction(MainFrame.MENU));
        }
    }

    public void addAdditionalActionListerner(ActionListener al) {
        menuBtn.addActionListener(al);
    }
}
