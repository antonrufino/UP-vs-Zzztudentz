package avs.ui;

import avs.models.AllyEntity;
import avs.models.Game;
import avs.models.PlantFactory;
import avs.utils.Textures;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class PlantPickerPanel extends JPanel{

    public PlantPickerPanel(Textures tex) {
        super();
        this.setOpaque(false);
        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kopiko_picker/kopiko_picker_hover.png")),
            new PlantFactory(PlantFactory.KOPIKO, tex), 40
        ));

        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/kwekkwek_picker/kwekkwek_picker_hover.png")),
            new PlantFactory(PlantFactory.TOWER, tex), 60
        ));

        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/banga_picker/banga_picker_hover.png")),
            new PlantFactory(PlantFactory.BANGA, tex), 100
        ));

        this.add(new PickerButton(
            scaleImageIcon(new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_default.png")),
            scaleImageIcon(new ImageIcon("../assets/img/buttons/tc7_picker/tc7_picker_hover.png")),
            new PlantFactory(PlantFactory.TC7, tex), 75
        ));
    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        Image scaledImg = icon.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}
