import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/17 9:15
 * @Version 1.0
 */
public class Board extends JPanel{
    private BouncingBall ball;
    public Board(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800,800));
        this.setBackground(Color.BLACK);
    }
    public Board(int width, int height, Color background){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(background);
    }
}
