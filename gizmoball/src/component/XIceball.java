package component;

import main.Board;

import javax.swing.*;

public class XIceball extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/iceball.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    private boolean braking = true;

    public XIceball(Integer x, Integer y, Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        int status = is_collision(ball);
        if(is_collision(ball)==0)
        {
            braking = true;
        }
        if(status!=0&&braking)
        {
            if(Math.abs(ball.getVx())>=2&&Math.abs(ball.getVy())>=2)
            ball.setVx((int) Math.round(0.5 * ball.getVx()));
            ball.setVy((int) Math.round(0.5 * ball.getVy()));
            braking = false;
            System.out.println("braked:");
            System.out.println("x:"+ball.getVx()+"y:"+ball.getVy());
        }
    }

    @Override
    public Integer is_collision(BouncingBall ball) {
        //控件圆心坐标
        Integer center_x1 = x + length/2;
        Integer center_y1 = y + length/2;
        //小球圆心坐标
        Integer center_x2 = ball.getX();
        Integer center_y2 = ball.getY();

        Integer radius = ball.getRadius();
        //圆心间距离
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(distance<length/2+radius){
            if(center_x2>center_x1&&center_y2<center_y1)//第一象限，右上方
            {
                return 1;
            }
            else if(center_x2<center_x1&&center_y2<center_y1)//第二象限，左上方
            {
                return 2;
            }
            else if(center_x2<center_x1&&center_y2>center_y1)//第三象限，左下方
            {
                return 3;
            }
            else if(center_x2>center_x1&&center_y2>center_y1)//第四象限，右下方
            {
                return 4;
            }
        }
        return 0;
    }
}
