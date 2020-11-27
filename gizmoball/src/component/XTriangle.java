package component;

import javax.swing.*;
import java.awt.*;
import main.Board;

public class XTriangle extends XComponent {
    public static final String picturePath = "gizmoball/src/picture/component/triangle.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    
    public XTriangle(Integer x, Integer y,Board board){
        super(x,y,board);
        paintPicturePath = picturePath;
    }


    public void rotate() {

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
