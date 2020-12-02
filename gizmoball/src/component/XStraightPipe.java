package component;

import main.Board;

import javax.swing.*;

public class XStraightPipe extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/straightpipe.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    public XStraightPipe(Integer x,Integer y,Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }
    @Override
    public Integer is_collision(BouncingBall ball){
        return 0;
    }
}
