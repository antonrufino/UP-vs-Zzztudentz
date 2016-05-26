package avs.ui;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class PlantPickerPanel extends JPanel{
    public PlantPickerPanel(){
        super();
        this.setOpaque(false);

        addPickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_hover.png"))
        );

        addPickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_hover.png"))
        );

        addPickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_hover.png"))
        );

        addPickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_hover.png"))
        );
    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        Image scaledImg = icon.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void addPickerButton(final ImageIcon defaultIcon,
        final ImageIcon hoverIcon) {
        final JButton btn = new JButton(defaultIcon);

        btn.setBorder(null);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(100,60));

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
