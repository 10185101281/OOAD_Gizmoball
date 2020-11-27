package component;

import main.Board;

import javax.swing.*;
import java.awt.*;

public class XCircle extends XComponent {
    public static String picturePath = "gizmoball/src/picture/component/circle.png";
    public static ImageIcon picture = new ImageIcon(picturePath);

    public XCircle(Integer x,Integer y,Board board){
        super(x,y,board);
        paintPicturePath = picturePath;
    }

    @Override
    public void enlarge() {
        super.enlarge();
    }

    @Override
    public void shrink() {
        super.shrink();
    }
    
    @Override
    public void rotate() {

    }


    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        //控件圆心坐标
        double center_x1 = x.doubleValue() + length.doubleValue()/2;
        double center_y1 = y.doubleValue() + length.doubleValue()/2;
        //小球圆心坐标
        double center_x2 = ball.getX().doubleValue();
        double center_y2 = ball.getY().doubleValue();
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        Integer status = is_collision(ball);
        if(status!=0) {
            Double new_x2 = center_x1 + (length / 2 + ball.getRadius()) / distance * (center_x2 - center_x1);
            Double new_y2 = center_y1 + (length / 2 + ball.getRadius()) / distance * (center_y2 - center_y1);
            ball.setX(new_x2.intValue());
            ball.setY(new_y2.intValue());

            double k = (Math.abs(center_y2 - center_y1) / Math.abs(center_x2 - center_x1));
            //切线与水平方向夹角的sin和cos
            double sin_a = 1 / Math.sqrt(1 + k * k);
            double cos_a = k / Math.sqrt(1 + k * k);
            System.out.println("正余弦：" + sin_a + " " + cos_a);
            //获取小球当前x方向和y方向分速度
            double vx = ball.getVx();
            double vy = ball.getVy();

            //x方向速度在碰撞面切向和法向做分解
            double vxt = vx * cos_a;
            double vxn = vx * sin_a;
            System.out.println("x方向速度在碰撞面切向和法向做分解:切向：" + vxt + "法向：" + vxn);

            //切向速度和法向速度再沿xy轴分解
            double vxtx = 0;//切向速度在x轴投影
            double vxty = 0;//切向速度在y轴投影
            double vxnx = 0;//法向速度在x轴投影
            double vxny = 0;//法向速度在y轴投影
            if (status == 1)//第一象限，右上方
            {
                vxtx = vxt * cos_a;//切向速度*cos
                vxty = vxt * sin_a;//切向速度在*sin
                vxnx = vxn * sin_a;//法向速度*sin
                vxny = -vxn * cos_a;//法向速度*cos
            } else if (status == 2)//第二象限，左上方
            {
                vxtx = vxt * cos_a;//切向速度*cos
                vxty = -vxt * sin_a;//切向速度在*sin
                vxnx = vxn * sin_a;//法向速度*sin
                vxny = vxn * cos_a;//法向速度*cos
            } else if (status == 3)//第三象限，左下方
            {
                vxtx = vxt * cos_a;//切向速度*cos
                vxty = vxt * sin_a;//切向速度在*sin
                vxnx = vxn * sin_a;//法向速度*sin
                vxny = -vxn * cos_a;//法向速度*cos
            } else if (status == 4)//第四象限，右下方
            {
                vxtx = vxt * cos_a;//切向速度*cos
                vxty = -vxt * sin_a;//切向速度在*sin
                vxnx = vxn * sin_a;//法向速度*sin
                vxny = vxn * cos_a;//法向速度*cos
            }

            System.out.println("X切向分解，x轴：" + vxtx + "y轴：" + vxty);


            System.out.println("X法向分解，x轴：" + vxnx + "y轴：" + vxny);

            //法向速度反向
            vxnx = -vxnx;
            vxny = -vxny;

            double Xspeed1 = vxtx + vxnx;
            double Yspeed1 = vxty + vxny;


            System.out.println("XSpeed1:" + Xspeed1 + " YSpeed1:" + Yspeed1);
            //y方向速度在碰撞面切向和法向做分解
            double vyt = vy * sin_a;
            double vyn = vy * cos_a;
            System.out.println("y方向速度在碰撞面切向和法向做分解:切向：" + vyt + "法向：" + vyn);

            //切向速度和法向速度再沿xy轴分解
            double vytx = 0;//切向速度在x轴投影
            double vyty = 0;//切向速度在y轴投影
            double vynx = 0;//法向速度在x轴投影
            double vyny = 0;//法向速度在y轴投影
            if (status == 1 || status == 3)//第一、三象限
            {
                vytx = vyt * cos_a;
                vyty = vyt * sin_a;
                vynx = -vyn * sin_a;
                vyny = vyn * cos_a;
            } else if (status == 2 || status == 4) {
                vytx = -vyt * cos_a;
                vyty = vyt * sin_a;
                vynx = vyn * sin_a;
                vyny = vyn * cos_a;
            }

            System.out.println("Y切向分解，x轴：" + vytx + "y轴：" + vyty);


            System.out.println("Y法向分解，x轴：" + vynx + "y轴：" + vyny);
            //法向速度反向
            vynx = -vynx;
            vyny = -vyny;

            double Xspeed2 = vytx + vynx;
            double Yspeed2 = vyty + vyny;

            System.out.println("XSpeed2:" + Xspeed2 + " YSpeed2:" + Yspeed2);

            Double new_vx = Xspeed1 + Xspeed2;
            Double new_vy = Yspeed1 + Yspeed2;

            ball.setVx((int) Math.round(new_vx));
            ball.setVy((int) Math.round(new_vy));
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
