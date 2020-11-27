package component;

import main.Board;
import main.BouncingBall;


import javax.swing.*;
import java.awt.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/26 16:00
 * @Version 1.0
 */
public class XBlackHole extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/specialcomponent/blackhole.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    public XBlackHole(Integer x, Integer y, Board board){
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

    /**
     * @Author LiXiang
     * @Date 2020/11/27 14:00
     * @Version 1.0
     * @param ball
     */

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        //控件圆心坐标
        Integer center_x1 = x + length/2;
        Integer center_y1 = y + length/2;
        //小球圆心坐标
        Integer center_x2 = ball.getX();
        Integer center_y2 = ball.getY();
        //圆心间距离
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        boolean isattracted = is_attracted(ball);
        if(reach_center(ball)){
            ball.setVx(0);
            ball.setVy(0);
            System.out.println("vanish");
        }

        if(isattracted)
        {
            Integer vx = ball.getVx();
            Integer vy = ball.getVy();
            Double v = Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2));
            Double new_v = v/2;
            Double new_vx = new_v*(center_x1-center_x2)/distance;
            Double new_vy = new_v*(center_y1-center_y2)/distance;
            ball.setVx((int)Math.round(new_vx));
            ball.setVy((int)Math.round(new_vy));
        }

<<<<<<< HEAD

=======
        
>>>>>>> 26896f217f6bd2aa2cd327e1c1c77daf6e12201a
    }

    /**
     * @Author LiXiang
     * @Date 2020/11/27 14:00
     * @Version 1.0
     * @param ball
     */
    private boolean is_attracted(BouncingBall ball){
        //控件圆心坐标
        Integer center_x1 = x + length/2;
        Integer center_y1 = y + length/2;
        //小球圆心坐标
        Integer center_x2 = ball.getX();
        Integer center_y2 = ball.getY();
        Integer radius = ball.getRadius();//小球半径
        //圆心间距离
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(distance<3*radius){
            return true;
        }
        return false;
    }

    /**
     * @Author LiXiang
     * @Date 2020/11/27 14:00
     * @Version 1.0
     * @param ball
     */
    private boolean reach_center(BouncingBall ball){
        //控件圆心坐标
        Integer center_x1 = x + length/2;
        Integer center_y1 = y + length/2;
        //小球圆心坐标
        Integer center_x2 = ball.getX();
        Integer center_y2 = ball.getY();
        Integer radius = ball.getRadius();//小球半径
        //圆心间距离
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(distance<10)
        {
            return true;
        }
        return false;
    }
}

