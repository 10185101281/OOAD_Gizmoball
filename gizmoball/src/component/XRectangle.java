package component;

import main.BouncingBall;
import main.Board;

import javax.swing.*;
import java.awt.*;

public class XRectangle extends XComponent {
    private static final String picturePath = "gizmoball/src/picture/component/rectangle.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);

    private Integer boucing_edge;
    public XRectangle(Integer x,Integer y,Board board){
        super(x,y,board);
        boucing_edge = 0;
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
    public void judge(BouncingBall ball) {
        super.judge(ball);
        //小球圆心的坐标
        Integer center_x = ball.getX();
        Integer center_y = ball.getY();
        //小球半径
        Integer radius = ball.getRadius();
        if(center_x+radius>=x&&y-radius<=center_y&&center_y<=y+length+radius)//左边
        {
            boucing_edge = 1;
            ball.setX(center_x-(center_x+radius-x));
        }
        else if(center_x-radius<=x&&y-radius<=center_y&&center_y<=y+length+radius)//右边
        {
            boucing_edge = 1;
            ball.setX(center_x+(x-(center_x-radius)));
        }

        else if(center_y+radius>=y&&x<=center_x&&center_x<=x+length)//上边
        {
            boucing_edge = 2;
            ball.setY(center_y-(center_y+radius-x));
        }
        else if(center_y-radius<=y&&x<=center_x&&center_x<=x+length)//下边
        {
            boucing_edge = 2;
            ball.setY(center_y+(y-(center_y-radius)));
        }

        react(ball);
        boucing_edge = 0;

    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        //碰到左右的边
        if(boucing_edge==1)
        {
            ball.setVy(-ball.getVy());
        }
        //碰到上下的边
        else if(boucing_edge==2)
        {
            ball.setVx(-ball.getVx());
        }

    }
}
