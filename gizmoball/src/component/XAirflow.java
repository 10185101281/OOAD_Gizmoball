package component;

import javax.swing.*;
import main.Board;

import java.awt.*;

public class XAirflow extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/airflow.png";
    private String paintPicturePath1 = "gizmoball/src/picture/component/airflow-1.png";
    private String paintPicturePath2 = "gizmoball/src/picture/component/airflow-2.png";
    private String paintPicturePath3 = "gizmoball/src/picture/component/airflow-3.png";
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


    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     */
    public void rotate() {
        rotPosition=(rotPosition+1)%4;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     */
    @Override
    public void paint(Graphics g){
        try {
            Image image;
            if(rotPosition == 0){
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath);
            } else if(rotPosition == 1){
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath1);
            } else if(rotPosition == 2){
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath2);
            } else {
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath3);
            }
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

}
