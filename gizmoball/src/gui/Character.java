package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/23 16:15
 * @Version 1.0
 */
public class Character {
    private String name;
    private ImageIcon characterAvatar;
    private JRadioButtonP characterComponent;
    /**
     * @Author BaoLiang
     * @Date 2020/11/13 16:00
     * @Version 1.0
     * 生成指定头像。
     * @param width 头像的宽度
     * @param height 头像的高度
     * @param s 角色名称
     * @return 根据角色名称生成的头像
     */
    private ImageIcon getAvatar(int width,int height,String s){
        ImageIcon imageIcon = new ImageIcon("src/picture/avatar/"+s+".png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
        return imageIcon;
    }
    public Character(String name, JRadioButtonP buttonP){
        this.name = name;
        this.characterAvatar = getAvatar(70,70,name);
        this.characterComponent = buttonP;
    }
    public String getName() {
        return name;
    }
    public ImageIcon getCharacterAvatar(){return characterAvatar;}
    public JRadioButton getButton(){return characterComponent.getjRadioButton(); }
    public JLabel getComponent(){return characterComponent.getjLabel(); }
}
