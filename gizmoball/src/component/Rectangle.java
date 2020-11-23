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

        Integer center_x = ball.getX() + ball.getRadius();
        Integer center_y = ball.getY() - ball.getRadius();
        Integer radius = ball.getRadius();
        if(center_x+radius>=x||center_x-radius<=center_x+length)
            boucing_edge = 1;
        else if(center_y+radius>=y-length||center_y-radius<=y)
            boucing_edge = 2;

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
