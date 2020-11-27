package component;

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
        /*
        g.setColor(Color.BLACK);
        g.fill3DRect(x,y,length,length,true);
        */
        try {
            Image image;
            image = Toolkit.getDefaultToolkit().getImage(picturePath);
            g.drawImage(image, x, y,length,length,board);
            if(isSelected){
                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(Color.RED);
                //BasicStroke s = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{5.0f}, 0.0f);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(x,y,length,length);
            }
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }
    public void rotate() {

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
