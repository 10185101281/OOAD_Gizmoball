package main;

import javax.swing.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/23 16:15
 * @Version 1.0
 */
public class Character {
    private String name;
    private JRadioButton button;
    private JLabel component;

    public Character(String name, JRadioButton button, JLabel component){
        this.name = name;
        this.button = button;
        this.component = component;
    }
    public String getName() {
        return name;
    }
    public JRadioButton getButton(){
        return button;
    }
    public JLabel getComponent(){
        return component;
    }
}
