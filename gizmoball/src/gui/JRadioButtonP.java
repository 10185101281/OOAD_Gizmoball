package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * @Author BaoLiang
 * @Date 2020/11/24 20:15
 * @Version 1.0
 */
public class JRadioButtonP {
    private JRadioButton jRadioButton;
    private JLabel jLabel;
    public JRadioButtonP(String s){
        jRadioButton = new JRadioButton();
        jRadioButton.setActionCommand(s);
        jLabel = new JLabel(s);
        jLabel.setPreferredSize(new Dimension(70,70));
    }
    public JRadioButtonP(String s1, String s2){
        jRadioButton = new JRadioButton();
        jRadioButton.setActionCommand(s1);
        jLabel = new JLabel(s2);
        jLabel.setPreferredSize(new Dimension(70,70));
    }
    public JRadioButtonP(String s, ImageIcon imageIcon){
        jRadioButton = new JRadioButton();
        jRadioButton.setActionCommand(s);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        jLabel = new JLabel(imageIcon);
        jLabel.setPreferredSize(new Dimension(70,70));
    }
    public JRadioButtonP(String s, ImageIcon imageIcon, Integer width, Integer height){
        jRadioButton = new JRadioButton();
        jRadioButton.setActionCommand(s);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        jLabel = new JLabel(imageIcon);
        jLabel.setPreferredSize(new Dimension(width,height));
    }
    public JRadioButton getjRadioButton(){
        return this.jRadioButton;
    }
    public JLabel getjLabel(){
        return this.jLabel;
    }
    public void addActionListener(ActionListener listener){
        jRadioButton.addActionListener(listener);
    }
}
