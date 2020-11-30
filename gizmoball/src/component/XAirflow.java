package component;

import javax.swing.*;
import main.Board;

public class XAirflow extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/airflow.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    private boolean acceleting = true;

    public XAirflow(Integer x,Integer y,Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        Integer center_x = ball.getX();
        Integer center_y = ball.getY();
        //小球半径
        Integer radius = ball.getRadius();
        Integer status = is_collision(ball);
        if(is_collision(ball)==0)
        {
            acceleting = true;
        }
        if(status==1&&acceleting)
        {
            if(Math.abs(ball.getVx())<=5&&Math.abs(ball.getVy())<=5)
            {
                ball.setVx((int) Math.round(1.2 * ball.getVx()));
                ball.setVy((int) Math.round(1.2 * ball.getVy()));
                acceleting = false;
                System.out.println("accelerated:");
                System.out.println("x:"+ball.getVx()+"y:"+ball.getVy());
            }
        }
    }

    @Override
    public Integer is_collision(BouncingBall ball) {
        //小球圆心的坐标
        Integer center_x = ball.getX();
        Integer center_y = ball.getY();
        //小球半径
        Integer radius = ball.getRadius();
        if(center_x>=x-radius&&center_x<=x&&y-radius<=center_y&&center_y<=y+length+radius)//左边
        {
            return 1;
        }
        else if(center_x>=x+length&&center_x<=x+length+radius&&y-radius<=center_y&&center_y<=y+length+radius)//右边
        {
            return 2;
        }

        else if(center_y<=y&&center_y>=y-radius&&x<=center_x&&center_x<=x+length)//上边
        {
            return 3;
        }
        else if(center_y>=y+length&&center_y<=y+length+radius&&x<=center_x&&center_x<=x+length)//下边
        {
            return 4;
        }
        return 0;

    }


}
