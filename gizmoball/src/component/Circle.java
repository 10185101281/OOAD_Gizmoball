package component;

import com.sun.xml.internal.rngom.parse.host.GrammarHost;
import main.BouncingBall;
import java.math.*;

public class Circle extends Component{


    @Override
    public void enlarge(Integer scaler) {
        super.enlarge(scaler);
    }

    @Override
    public void shrink(Integer scaler) {
        super.shrink(scaler);
    }

    @Override
    public void rotate(Integer angle) {
        super.rotate(angle);
    }

    @Override
    public void judge(BouncingBall ball) {
        super.judge(ball);
        Integer center_x1 = x + length/2;
        Integer center_y1 = y - length/2;
        Integer center_x2 = ball.getX() + ball.getRadius();
        Integer center_y2 = ball.getY() - ball.getRadius();
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(distance<=length/2+ball.getRadius())
        {
            react(ball);
        }
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        double center_x1 = x.doubleValue() + length.doubleValue()/2;
        double center_y1 = y.doubleValue() - length.doubleValue()/2;
        double center_x2 = ball.getX().doubleValue() + ball.getRadius().doubleValue();
        double center_y2 = ball.getY().doubleValue() - ball.getRadius().doubleValue();
        double k = (Math.abs(center_y2-center_y1)/Math.abs(center_x2-center_x1));
        double cos_a = 1/Math.sqrt(1+k*k);
        double sin_a = k/Math.sqrt(1+k*k);
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
