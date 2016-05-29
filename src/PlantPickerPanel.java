package avs.ui;

import avs.models.AllyEntity;
import avs.models.Game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class PlantPickerPanel extends JPanel{

    public PlantPickerPanel() {
        super();
        this.setOpaque(false);
        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_hover.png")),
            40, 50
        ));

        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_hover.png")),
            60, 100
        ));

        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_hover.png")),
            100, 150
        ));

        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_hover.png")),
            75, 50
        ));
    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        Image scaledImg = icon.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
