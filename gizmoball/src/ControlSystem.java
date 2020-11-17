import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * @Author
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class ControlSystem {
    private static  void createUserPanel(){
        UserPanel userPanel = new UserPanel("Gizmobll");
        userPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userPanel.setSize(1200, 800);
        userPanel.setVisible(true);
    }
    public static void main(String[] args){
        createUserPanel();
    }
}
