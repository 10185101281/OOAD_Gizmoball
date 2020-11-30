package component;

import java.awt.*;
import main.Board;

import javax.swing.*;

/**
 * @Author LiXiang
 * @Date 2020/11/19 15:30
 * @Version 1.0
 */
public abstract class XComponent{
    Integer x;  //左上角坐标
    Integer y;
    Integer length;
    Integer angle;
    Integer base = 40;//一个格子40像素
    Integer rate;
    Board board;
    boolean isSelected;
    boolean isDeleting;
    protected String paintPicturePath;

    public boolean getIsDeleting(){return isDeleting;}

    public XComponent(Integer x, Integer y, Board board){
        this.x = x; this.y = y;
        length = base;
        rate = 1;
        angle = 0;
        isSelected = false;
        isDeleting = false;
        this.board = board;
    }
    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    public void paint(Graphics g){
        try {
            Image image;
            image = Toolkit.getDefaultToolkit().getImage(paintPicturePath);
            g.drawImage(image, x, y,length,length,board);
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
    /**
     * @Author LiXiang, BaoLiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 放大控件
     */
    public void enlarge() {
        if(x+length >= board.getBoardWidth() || y+length >= board.getBoardHeight()) return ;
        length = length+base;
        if(is_collision(board.getBall()) > 0){
            length = length - base;
            return ;
        }
        rate++;
        for(int i=1; i<=rate; i++){
            int tx =  x+(rate-1)*base; int ty = y+(i-1)*base;
            if(!board.componentMapIsEmpty(tx, ty)) {
                length = length - base;
                rate--;
                return ;
            }
        }
        for(int i=1; i<=rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            if(!board.componentMapIsEmpty(tx, ty)) {
                length = length - base;
                rate--;
                return ;
            }
        }
        for(int i=1; i<=rate; i++){
            int tx =  x+(rate-1)*base; int ty = y+(i-1)*base;
            board.updateComponentMap(tx, ty, this);
        }
        for(int i=1; i<=rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            board.updateComponentMap(tx, ty, this);
        }
    }


    /**
     * @Author LiXiang, BaoLiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 缩小控件
     */
    public void shrink() {
        if(length.equals(base)) return ;
        for(int i=1; i<=rate; i++){
            int tx =  x+(rate-1)*base; int ty = y+(i-1)*base;
            board.updateComponentMap(tx, ty, null);
        }
        for(int i=1; i<=rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            board.updateComponentMap(tx, ty, null);
        }
        rate--;
        length = length-base;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/25 22:30
     * @Version 1.0
     * 删除控件
     */
    public void delete() {
        for(int i=1; i<=rate; i++){
            for(int j=1; j<=rate; j++){
                int tx = x+(i-1)*base, ty = y+(j-1)*base;
                board.updateComponentMap(tx, ty, null);
            }
        }
        isDeleting = true;
        //board.getComponentlist().remove(this);
    }

    /**
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 旋转控件
     */
    public void rotate() { }



    /**
     * @param ball 小球
     * @Author LiXiang
     * @Date 2020/11/20 10:00
     * @Version 1.0
     * 碰撞后作出反应，改变小球状态
     */
    public void react(BouncingBall ball){
        if(ball==null)
            return;

    }

    public abstract  Integer is_collision(BouncingBall ball);

}
