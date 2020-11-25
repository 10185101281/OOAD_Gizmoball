package component;

import javafx.scene.layout.Border;
import main.BouncingBall;
import javax.swing.*;
import java.awt.*;
import main.Board;

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

    public XComponent(Integer x, Integer y, Board board){
        this.x = x; this.y = y;
        length = base;
        rate = 1;
        angle = 0;
        isSelected = false;
        this.board = board;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    public abstract void paint(Graphics g);
    /**
     * @Author LiXiang, BaoLiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 放大控件
     */
    public void enlarge() {
        if(x+length >= board.getBoardWidth() || y+length >= board.getBoardHeight()) return ;
        
        rate++;
        for(int i=1; i<=rate; i++){
            int tx =  x+(rate-1)*base; int ty = y+(i-1)*base;
            if(!board.componentMapIsEmpty(tx, ty)) return ;
        }
        for(int i=1; i<=rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            if(!board.componentMapIsEmpty(tx, ty)) return ;
        }
        for(int i=1; i<=rate; i++){
            int tx =  x+(rate-1)*base; int ty = y+(i-1)*base;
            board.updateComponentMap(tx, ty, this);
        }
        for(int i=1; i<=rate; i++){
            int tx = x+(i-1)*base; int ty = y+(rate-1)*base;
            board.updateComponentMap(tx, ty, this);
        }
        length = length+base;
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
        board.getComponentlist().remove(this);
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
     * 判断小球是否与自己碰撞
     */
    public void judge(BouncingBall ball){

    }


    /**
     * @param ball 小球
     * @Author LiXiang
     * @Date 2020/11/20 10:00
     * @Version 1.0
     * 碰撞后作出反应，改变小球状态
     */
    public void react(BouncingBall ball){

    }

}
