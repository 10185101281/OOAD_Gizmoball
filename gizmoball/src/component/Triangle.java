package component;

import main.BouncingBall;

public class Triangle extends Component{

    
    public Triangle(Integer x,Integer y){
        this.x = x;
        this.y = y;
        length = 20;
        angle = 0;
    }


    @Override
    public void enlarge(Integer scaler) {
        length = length*scaler;

    }

    @Override
    public void shrink(Integer scaler) {
        length = length/scaler;

    }

    @Override
    public void rotate(Integer angle) {

    }

    @Override
    public void judge(BouncingBall ball) {
        super.judge(ball);
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
    }
}
