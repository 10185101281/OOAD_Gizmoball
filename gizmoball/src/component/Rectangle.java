package component;

import main.BouncingBall;

public class Rectangle extends Component{
    private Integer boucing_edge;

    public void Rectangle(Integer x,Integer y){

        this.x = x;
        this.y = y;
        length = 20;
        angle = 0;
    }


    @Override
    public void judge(BouncingBall ball) {
        super.judge(ball);
        //小球圆心的坐标
        Integer center_x = ball.getX();
        Integer center_y = ball.getY();
        //小球半径
        Integer radius = ball.getRadius();
        if(center_x+radius>=x)//左边
        {
            boucing_edge = 1;
            ball.setX(center_x-(center_x+radius-x));
        }
        else if(center_x-radius<=x)//右边
        {
            boucing_edge = 1;
            ball.setX(center_x+(x-(center_x-radius)));
        }

        else if(center_y+radius>=y)//上边
        {
            boucing_edge = 2;
            ball.setY(center_y-(center_y+radius-x));
        }
        else if(center_y-radius<=y)
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
