package avs.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;




public class PlayerNameTextField extends JTextField{
    protected static String name;
    public PlayerNameTextField(){
        super();
        this.name = "";
        this.setText("Enter Your Name");
        this.setPreferredSize(new Dimension(356, 37));
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setForeground(Color.BLACK);
        this.setFont(this.loadFont());
        this.setBorder(null);
        this.setOpaque(false);
        addListeners();
    }
    public Font loadFont(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/assets/fonts/FREESCPT.TTF")); //loads the font
            return font.deriveFont(30f); //returns the font with the size of 30f;
        } catch (IOException|FontFormatException e) {
            return null;
        }
    }
    public void addListeners(){
        this.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                setText("");
            }
            public void focusLost(FocusEvent e){
                if(getText().equals("")){
                    name = "";
                    setText("Enter Your Name");
                }
                else{
                    name = getText();
                    setText(name);
                }
            }
        });
    }
}
