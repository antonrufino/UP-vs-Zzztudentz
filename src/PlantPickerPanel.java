package avs.ui;

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
    private PickerButton kopiko;
    private PickerButton tower;
    private PickerButton banga;
    private PickerButton tc7;

    public PlantPickerPanel(Textures tex) {
        super();
        this.setOpaque(false);

        this.kopiko = new PickerButton(
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/kopiko_picker/kopiko_picker_default.png"))),
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/kopiko_picker/kopiko_picker_hover.png"))),
            new PlantFactory(PlantFactory.KOPIKO, tex), 40
        );
        this.add(this.kopiko);

        this.tower = new PickerButton(
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/kwekkwek_picker/kwekkwek_picker_default.png"))),
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/kwekkwek_picker/kwekkwek_picker_hover.png"))),
            new PlantFactory(PlantFactory.TOWER, tex), 60
        );
        this.add(this.tower);

        this.banga = new PickerButton(
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/banga_picker/banga_picker_default.png"))),
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/banga_picker/banga_picker_hover.png"))),
            new PlantFactory(PlantFactory.BANGA, tex), 200
        );
        this.add(this.banga);

        this.tc7 = new PickerButton(
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/tc7_picker/tc7_picker_default.png"))),
            scaleImageIcon(new ImageIcon(getClass().getResource("/assets/img/buttons/tc7_picker/tc7_picker_hover.png"))),
            new PlantFactory(PlantFactory.TC7, tex), 75
        );
        this.add(this.tc7);
    }

    private ImageIcon scaleImageIcon(ImageIcon icon) {
        Image scaledImg = icon.getImage().getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    void reset() {
        this.kopiko.reset();
        this.tower.reset();
        this.banga.reset();
        this.tc7.reset();
    }
}
