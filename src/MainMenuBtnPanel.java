package avs.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import avs.models.Game;


public class MainMenuBtnPanel extends JPanel{
    public static final int OPTIONAL_OFFSET = 43;
    public MainMenuBtnPanel(final GamePanel gamePanel, int width, int height){
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(4,1,4,4));
        this.setOpaque(false);

        this.add(createBtn(gamePanel, width, 45));
        this.add(createSubBtn(1, width, 30));
        this.add(createSubBtn(2, width, 29));
        this.add(createSubBtn(3, width, 29));

    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image scaledImg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private JPanel createBtn(final GamePanel gamePanel, int width, int height){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setOpaque(false);

        MainMenuBtn button = new MainMenuBtn(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/play-default.png"), width, height),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/play-hover.png"), width, height),
            width, 45
        );

        button.addActionListener(new MainFrame.SwitchPanelAction(MainFrame.GAME));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.start();
                Game.getInstance().init();
            }
        });

        panel.add(button);
        return panel;
    }

    private JPanel createSubBtn(int type, int width, int height){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setOpaque(false);
        if(type == 1){
            MainMenuBtn button = new MainMenuBtn(
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/help-default.png"), width-OPTIONAL_OFFSET, height),
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/help-hover.png"), width-OPTIONAL_OFFSET, height),
                width-OPTIONAL_OFFSET, height
            );

            panel.add(button, BorderLayout.EAST);
        }
        else if(type == 2){
            MainMenuBtn button = new MainMenuBtn(
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/credits-default.png"), width-OPTIONAL_OFFSET, height),
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/credits-hover.png"), width-OPTIONAL_OFFSET, height),
                width-OPTIONAL_OFFSET, height
            );

            panel.add(button, BorderLayout.EAST);
        }
        else if(type == 3){
            MainMenuBtn button = new MainMenuBtn(
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/quit-default.png"), width-OPTIONAL_OFFSET, height),
                scaleImageIcon(new ImageIcon("../assets/img/buttons/main_menu_btns/quit-hover.png"), width-OPTIONAL_OFFSET, height),
                width-OPTIONAL_OFFSET, height
            );

            panel.add(button, BorderLayout.EAST);
        }

        return panel;
    }
}
