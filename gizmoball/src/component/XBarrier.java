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
     * @Date 2020/12/5 9:00
     * @Version 1.0
     */
    public void move_left(){
        if(is_collision(board.getBall()) > 0) return ;
        if(x == 0) return ;
        x -= base;
        for(int j=1; j<=rate; j++){
            int tx = x;
            int ty = y + (j-1)*base;
            if(!board.componentMapIsEmpty(tx,ty)){
                x += base;
                return ;
            }
        }
        for(int j=1; j<=rate; j++){
            int tx1 = x, tx2 = x+(2*rate)*base;
            int ty = y + (j-1)*base;
            board.updateComponentMap(tx1,ty,this);
            board.updateComponentMap(tx2,ty,null);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/12/5 9:00
     * @Version 1.0
     */
    public void move_up(){
        if(is_collision(board.getBall()) > 0) return ;
        if(y == 0) return ;
        y -= base;
        for(int i=1; i<=2*rate; i++){
            int tx = x + (i-1)*base;
            int ty = y;
            if(!board.componentMapIsEmpty(tx,ty)){
                y += base;
                return ;
            }
        }
        for(int i=1; i<=2*rate; i++){
            int tx = x + (i-1)*base;
            int ty1 = y, ty2 = y + rate*base;
            board.updateComponentMap(tx,ty1,this);
            board.updateComponentMap(tx,ty2,null);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/12/5 9:00
     * @Version 1.0
     */
    public void move_right(){
        if(is_collision(board.getBall()) > 0) return ;
        if(x+2*length == board.getBoardWidth()) return ;
        x += base;
        for(int j=1; j<=rate; j++){
            int tx = x+(2*rate-1)*base;
            int ty = y + (j-1)*base;
            if(!board.componentMapIsEmpty(tx,ty)){
                x -= base;
                return ;
            }
        }
        for(int j=1; j<=rate; j++){
            int tx1 = x+(2*rate-1)*base, tx2 = x-base;
            int ty = y + (j-1)*base;
            board.updateComponentMap(tx1,ty,this);
            board.updateComponentMap(tx2,ty,null);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/12/5 9:00
     * @Version 1.0
     */
    public void move_down(){
        if(is_collision(board.getBall()) > 0) return ;
        if(y+length == board.getBoardHeight()) return ;
        y += base;
        for(int i=1; i<=2*rate; i++){
            int tx = x + (i-1)*base;
            int ty = y + (rate-1)*base;
            if(!board.componentMapIsEmpty(tx,ty)){
                y -= base;
                return ;
            }
        }
        //System.out.println(x+","+y);
        //System.out.println((y + (rate-1)*base)+" "+(y-base));
        for(int i=1; i<=2*rate; i++){
            int tx = x + (i-1)*base;
            int ty1 = y + (rate-1)*base, ty2 = y-base;
            board.updateComponentMap(tx,ty1,this);
            board.updateComponentMap(tx,ty2,null);
        }
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
                g2.drawRect(x,y,2*length,length);
            }
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }
}