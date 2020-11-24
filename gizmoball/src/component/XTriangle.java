package component;

import main.BouncingBall;

import javax.swing.*;

public class XTriangle extends Component{

    public static final ImageIcon picture = new ImageIcon("gizmoball/src/picture/component/triangle.png");
    
    public XTriangle(Integer x, Integer y){
        super(x,y);
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
