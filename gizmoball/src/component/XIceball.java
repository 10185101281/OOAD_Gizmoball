package component;

import main.Board;

import javax.swing.*;

public class XIceball extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/iceball.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);

    public XIceball(Integer x, Integer y, Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }
}
