package avs.ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class EnergyBar extends JPanel{
    private JLabel value;
    private ImageIcon icon;
    private static BufferedImage bg;
    private Font font;

    public EnergyBar(){
        super();
        icon = new ImageIcon("../assets/energy_counter/energy_counter_notext.png");
        this.setPreferredSize(new Dimension(121, 60));
        value = new JLabel("0");
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(8,48,2,20));
        this.setLayout(new BorderLayout());
        value.setFont(this.loadFont());
        value.setForeground(Color.WHITE);
        value.setMaximumSize(new Dimension(20,20));
        value.setHorizontalAlignment(SwingConstants.CENTER);
        value.setVerticalAlignment(SwingConstants.CENTER);
        this.add(value, BorderLayout.CENTER);

    }
    public Font loadFont(){
        try {
            //Returned font is of pt size 1
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("../assets/fonts/comixloud.ttf"));
            return font.deriveFont(9.5f);
        } catch (IOException|FontFormatException e) {
            return null;// Handle exception
        }
    }
    public static Font loadFont(Float size){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("../assets/fonts/comixloud.ttf"));
            return font.deriveFont(size);
        } catch (IOException|FontFormatException e) {
            return null;
        }
    }

    public void setValue(int value) {
        this.value.setText(Integer.toString(value));
    }

    public int getValue() {
        return Integer.parseInt(this.value.getText());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        super.paintComponent(g);

        try {
            g.drawImage(EnergyBar.bg, 10, 10,
                101, 60, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static class AssetLoader implements Runnable {
        @Override
        public void run() {
            try {
                EnergyBar.bg = ImageIO.read(
                    new File("../assets/img/counters/energy_counter/energy_counter_notext.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
