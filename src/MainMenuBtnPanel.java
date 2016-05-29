package avs.ui;
import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

// import javax.imageio.*;
// import java.awt.image.*;
// import java.io.*;

public class MainMenuBtnPanel extends JPanel{
    public static final int OPTIONAL_OFFSET = 43;
    public MainMenuBtnPanel(int width, int height){
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(4,1,4,4));
        this.setOpaque(false);
        this.add(new MainMenuBtn(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/play-default.png"), width, 45),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/play-hover.png"), width, 45),
            width, 45
        ));
        this.add(createSubBtn(1, width, 30));
        this.add(createSubBtn(2, width, 29));
        this.add(createSubBtn(3, width, 29));

    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image scaledImg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private JPanel createSubBtn(int type, int width, int height){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setOpaque(false);
        if(type == 1){
            panel.add(new MainMenuBtn(
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/help-default.png"), width-OPTIONAL_OFFSET, height),
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/help-hover.png"), width-OPTIONAL_OFFSET, height),
                width-OPTIONAL_OFFSET, height), BorderLayout.EAST
            );
        }
        else if(type == 2){
            panel.add(new MainMenuBtn(
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/credits-default.png"), width-OPTIONAL_OFFSET, height),
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/credits-hover.png"), width-OPTIONAL_OFFSET, height),
                width-OPTIONAL_OFFSET, height), BorderLayout.EAST
            );
        }
        else if(type == 3){
            panel.add(new MainMenuBtn(
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/quit-default.png"), width-OPTIONAL_OFFSET, height),
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/quit-hover.png"), width-OPTIONAL_OFFSET, height),
                width-OPTIONAL_OFFSET, height), BorderLayout.EAST
            );
        }

        return panel;
    }
}
