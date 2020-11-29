package component;

import main.Board;

import javax.swing.*;

public class XBarrier extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/barrier.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);

    public XBarrier(Integer x, Integer y, Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }
}
