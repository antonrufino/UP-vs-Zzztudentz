package avs.ui;
import javax.swing.*;
import java.awt.*;
import java.io.*;



public class PlayerNameTextField extends JTextField{
    public PlayerNameTextField(){
        super();
        this.setText("Enter Your Name");
        this.setPreferredSize(new Dimension(356, 37));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setForeground(Color.BLACK);
        this.setFont(this.loadFont());
        this.setBorder(null);
        this.setOpaque(false);
    }
    public Font loadFont(){
        try {
            //Returned font is of pt size 1
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("../assets/fonts/FREESCPT.TTF"));
            //Derive and return a 12 pt version:
            //Need to use float otherwise
            //it would be interpreted as style
            return font.deriveFont(30f);
        } catch (IOException|FontFormatException e) {
            return null;// Handle exception
        }
    }
}
