package avs.ui;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class PlantPickerPanel extends JPanel{
    private BufferedImageLoader loader = new BufferedImageLoader();
    private ImageIcon kopiko_default;
    private ImageIcon kopiko_hover;
    private ImageIcon kwekkwek_default;
    private ImageIcon kwekkwek_hover;
    private ImageIcon banga_default;
    private ImageIcon banga_hover;
    private ImageIcon tc7_default;
    private ImageIcon tc7_hover;

    private Image scaled_img;
    private final JButton kopikoBtn;
    private final JButton kkBtn;
    private final JButton bangaBtn;
    private final JButton tc7Btn;

    public PlantPickerPanel(){
            super();
            this.setOpaque(false);

            kopiko_default = new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_default.png");
            kopiko_hover = new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_hover.png");
            scaled_img = kopiko_default.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            kopiko_default = new ImageIcon(scaled_img);
            scaled_img = kopiko_hover.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            kopiko_hover = new ImageIcon(scaled_img);
            kopikoBtn = new JButton(kopiko_default);
            kopikoBtn.setPreferredSize(new Dimension(100,60));
            kopikoBtn.setBorder(null);
            kopikoBtn.setContentAreaFilled(false);
            kopikoBtn.setOpaque(false);
            kopikoBtn.setBorderPainted(false);
            kopikoBtn.setPreferredSize(new Dimension(100,60));



            kwekkwek_default = new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_default.png");
            kwekkwek_hover = new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_hover.png");
            scaled_img = kwekkwek_default.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            kwekkwek_default = new ImageIcon(scaled_img);
            scaled_img = kwekkwek_hover.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            kwekkwek_hover = new ImageIcon(scaled_img);
            kkBtn = new JButton(kwekkwek_default);
            kkBtn.setPreferredSize(new Dimension(100,60));
            kkBtn.setBorder(null);
            kkBtn.setContentAreaFilled(false);
            kkBtn.setOpaque(false);
            kkBtn.setBorderPainted(false);
            kkBtn.setPreferredSize(new Dimension(100,60));



            banga_default = new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_default.png");
            banga_hover = new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_hover.png");
            scaled_img = banga_default.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            banga_default = new ImageIcon(scaled_img);
            scaled_img = banga_hover.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            banga_hover = new ImageIcon(scaled_img);
            bangaBtn = new JButton(banga_default);
            bangaBtn.setPreferredSize(new Dimension(100,60));
            bangaBtn.setBorder(null);
            bangaBtn.setContentAreaFilled(false);
            bangaBtn.setOpaque(false);
            bangaBtn.setBorderPainted(false);
            bangaBtn.setPreferredSize(new Dimension(100,60));


            tc7_default = new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_default.png");
            tc7_hover = new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_hover.png");
            scaled_img = tc7_default.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            tc7_default = new ImageIcon(scaled_img);
            scaled_img = tc7_hover.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
            tc7_hover = new ImageIcon(scaled_img);
            tc7Btn = new JButton(tc7_default);
            tc7Btn.setPreferredSize(new Dimension(100,60));
            tc7Btn.setBorder(null);
            tc7Btn.setContentAreaFilled(false);
            tc7Btn.setOpaque(false);
            tc7Btn.setBorderPainted(false);
            tc7Btn.setPreferredSize(new Dimension(100,60));

            this.add(kopikoBtn);
            this.add(kkBtn);
            this.add(bangaBtn);
            this.add(tc7Btn);
            kopikoBtn.addMouseListener(new MouseListener(){
                public void mouseEntered(MouseEvent me){
                    kopikoBtn.setIcon(kopiko_hover);
                }
                public void mouseExited(MouseEvent me){
                    kopikoBtn.setIcon(kopiko_default);
                }
                public void mouseReleased(MouseEvent me){}
                public void mousePressed(MouseEvent me){}
                public void mouseClicked(MouseEvent me){}
            });

            kkBtn.addMouseListener(new MouseListener(){
                public void mouseEntered(MouseEvent me){
                    kkBtn.setIcon(kwekkwek_hover);
                }
                public void mouseExited(MouseEvent me){
                    kkBtn.setIcon(kwekkwek_default);
                }
                public void mouseReleased(MouseEvent me){}
                public void mousePressed(MouseEvent me){}
                public void mouseClicked(MouseEvent me){}
            });

            bangaBtn.addMouseListener(new MouseListener(){
                public void mouseEntered(MouseEvent me){
                    bangaBtn.setIcon(banga_hover);
                }
                public void mouseExited(MouseEvent me){
                    bangaBtn.setIcon(banga_default);
                }
                public void mouseReleased(MouseEvent me){}
                public void mousePressed(MouseEvent me){}
                public void mouseClicked(MouseEvent me){}
            });

            tc7Btn.addMouseListener(new MouseListener(){
                public void mouseEntered(MouseEvent me){
                    tc7Btn.setIcon(tc7_hover);
                }
                public void mouseExited(MouseEvent me){
                    tc7Btn.setIcon(tc7_default);
                }
                public void mouseReleased(MouseEvent me){}
                public void mousePressed(MouseEvent me){}
                public void mouseClicked(MouseEvent me){}
            });
    }
}
