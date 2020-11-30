package component;

import main.Board;

import javax.swing.*;
import java.awt.*;

public class XBarrier extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/barrier.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);

    public XBarrier(Integer x, Integer y, Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
        this.board.updateComponentMap(x+base,y,this);
    }

    @Override
    public Integer is_collision(BouncingBall ball) {
        return 0;
    }


    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     * 放大控件
     */
    @Override
    public void enlarge() {
        if(x+length >= board.getBoardWidth() || y+2*length >= board.getBoardHeight()) return ;

        length = length+base;
        if(is_collision(board.getBall()) > 0){
            length = length - base;
            return ;
        }

        rate++;
        for(int i=1; i<=rate; i++){
            int tx =  x+(2*rate-1)*base; int ty = y+(i-1)*base;
            if(!board.componentMapIsEmpty(tx, ty)) {
                length = length - base;
                rate--;
                return ;
            }
        }

        for(int i=1; i<=2*rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            if(!board.componentMapIsEmpty(tx, ty)) {
                length = length - base;
                rate--;
                return ;
            }
        }

        for(int i=1; i<=rate; i++){
            int tx =  x+(2*rate-1)*base; int ty = y+(i-1)*base;
            board.updateComponentMap(tx, ty, this);
        }

        for(int i=1; i<=2*rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            board.updateComponentMap(tx, ty, this);
        }
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     * 缩小控件
     */
    @Override
    public void shrink() {
        if(length.equals(base)) return ;
        for(int i=1; i<=rate; i++){
            int tx =  x+(2*rate-1)*base; int ty = y+(i-1)*base;
            board.updateComponentMap(tx, ty, null);
        }
        for(int i=1; i<=2*rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            board.updateComponentMap(tx, ty, null);
        }
        rate--;
        length = length-base;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     * 绘画控件
     */
    @Override
    public void paint(Graphics g){
        try {
            Image image;
            image = Toolkit.getDefaultToolkit().getImage(paintPicturePath);
            g.drawImage(image, x, y,2*length,length,board);
            if(isSelected){
                Graphics2D g2 = (Graphics2D)g;
                g2.setColor(Color.RED);
                //BasicStroke s = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{5.0f}, 0.0f);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(x,y,length,length);
            }
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }
}