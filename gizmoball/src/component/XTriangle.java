package component;

import main.BouncingBall;

import javax.swing.*;
import java.awt.*;
import main.Board;

public class XTriangle extends XComponent {
    public static final String picturePath = "gizmoball/src/picture/component/triangle.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    
    public XTriangle(Integer x, Integer y,Board board){
        super(x,y,board);
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/24 21:00
     * @Version 1.0
     * @param g
     */
    @Override
    public void paint(Graphics g){
        Image image;
        try {
            image = Toolkit.getDefaultToolkit().getImage(picturePath);
            g.drawImage(image, x, y,length,length,board);
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }
    @Override
    public void rotate(Integer angle) {

    }

    @Override
    public void judge(BouncingBall ball) {
        super.judge(ball);
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
    }
}
