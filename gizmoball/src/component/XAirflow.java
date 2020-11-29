package component;

import javax.swing.*;
import main.Board;

public class XAirflow extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/airflow.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);

    public XAirflow(Integer x,Integer y,Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }
}
