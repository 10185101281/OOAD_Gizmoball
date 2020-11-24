package component;

import com.sun.xml.internal.rngom.parse.host.GrammarHost;
import main.BouncingBall;

import javax.swing.*;
import java.math.*;

public class XCircle extends Component{
    public static final ImageIcon picture = new ImageIcon("gizmoball/src/picture/component/circle.png");

    public XCircle(Integer x,Integer y){
        super(x,y);
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
    public void rotate(Integer angle) {
        super.rotate(angle);
    }

    @Override
    public void judge(BouncingBall ball) {
        super.judge(ball);
        //控件圆心坐标
        Integer center_x1 = x + length/2;
        Integer center_y1 = y - length/2;
        //小球圆心坐标
        Integer center_x2 = ball.getX();
        Integer center_y2 = ball.getY();
        //圆心间距离
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(distance<=length/2+ball.getRadius())//如果圆心距离小于半径和，则相交
        {
            react(ball);
        }
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        //控件圆心坐标
        double center_x1 = x.doubleValue() + length.doubleValue()/2;
        double center_y1 = y.doubleValue() - length.doubleValue()/2;
        //小球圆心坐标
        double center_x2 = ball.getX().doubleValue();
        double center_y2 = ball.getY().doubleValue();

        double k = (Math.abs(center_y2-center_y1)/Math.abs(center_x2-center_x1));
        //切线与水平方向夹角的sin和cos
        double sin_a = 1/Math.sqrt(1+k*k);
        double cos_a = k/Math.sqrt(1+k*k);
        //获取小球当前x方向和y方向分速度
        double vx = ball.getVx();
        double vy = ball.getVy();

        //x方向速度在碰撞面切向和法向做分解
        double vxt = vx*cos_a;
        double vxn = vx*sin_a;

        //切向速度和法向速度再沿xy轴分解
        double vxtx = vxt*cos_a;
        double vxty = vxt*sin_a;

        double vxnx = vxn*sin_a;
        double vxny = vxn*cos_a;

        //法向速度反向
        vxnx = -vxnx;
        vxny = -vxny;

        double Xspeed1 = vxtx + vxnx;
        double Yspeed1 = vxty + vxny;

        //y方向速度在碰撞面切向和法向做分解
        double vyt = vy*cos_a;
        double vyn = vy*sin_a;

        //切向速度和法向速度再沿xy轴分解
        double vytx = vyt*cos_a;
        double vyty = vyt*sin_a;

        double vynx = vyn*sin_a;
        double vyny = vyn*cos_a;

        //法向速度反向
        vynx = -vynx;
        vyny = -vyny;

        double Xspeed2 = vytx + vynx;
        double Yspeed2 = vyty + vyny;


        Double new_vx = Xspeed1 + Xspeed2;
        Double new_vy = Yspeed1 + Yspeed2;

        ball.setVx(new_vx.intValue());
        ball.setVy(new_vy.intValue());

    }
}
