import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * @Author
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class ControlSystem {
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 14:30
     * @Version 1.0
     * 创建一个UserPanel
     * @param width UserPanel的宽度
     * @param height UserPanel的高度
     * @return
     */
    private static  void createUserPanel(int width,int height){
        UserPanel userPanel = new UserPanel("Gizmobll");
        userPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userPanel.setSize(width, height);
        userPanel.setVisible(true);
    }
    public static void main(String[] args){
        createUserPanel(1200, 800);
    }
}
