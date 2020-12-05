package component;

import java.awt.*;
import java.util.*;
import component.*;
import main.Board;

import javax.swing.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/18 9:00
 * @Version 1.0
 */
public class BouncingBall extends XComponent{
    public static String picturePath = "gizmoball/src/picture/component/ball.png";
    public static ImageIcon picture = new ImageIcon(picturePath);
    private Integer vx, vy, ax, ay;
    private Integer radius;
    private Integer g_count;
    private Integer f_count;
    public Integer getX() {
        return x+radius;
    }
    public Integer getY(){
        return y+radius;
    }
    public Integer getRadius(){
        return radius;
    }
    public Integer getVx(){
        return vx;
    }
    public Integer getVy() {
        return vy;
    }

    public void setVx(Integer vx) {
        this.vx = vx;
    }

    public void setVy(Integer vy) {
        this.vy = vy;
    }
    public void setX(Integer x) {
        this.x = x-radius;
    }

    public void setY(Integer y) {
        this.y = y-radius;
    }

    private void randomAttributes(){
        Random random = new Random();
        vx = 0; vy = 0;
        while(vx == 0 || vy == 0){
            vx = random.nextInt(10)-5;
            vy = random.nextInt(10)-5;
        }
    }
    public BouncingBall(Board board){
        super(400,400,board);
        this.radius = 10;
        this.board = board;
        randomAttributes();
        g_count = f_count = 0;
    }
    public BouncingBall(Integer x,Integer y,Board board){
        super(x, y,  board);
        this.radius = 10;
        this.board = board;
        randomAttributes();
        g_count = f_count = 0;
    }
    public BouncingBall(Integer x,Integer y,Integer vx,Integer vy,Integer ax,Integer ay,Board board){
        super(x,y,board);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.radius = 10;
        this.board = board;
        g_count = f_count = 0;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/19 19:30
     * @Version 1.0
     * 模拟小球运动
     */
    public void move(){
        ArrayList<XComponent> componentslist = board.getComponentlist();
        for(XComponent component : componentslist){
            component.react(this);
        }
       // System.out.println("Component finish");
        Integer boardWidth = board.getPreferredSize().width;
        Integer boardHeight = board.getPreferredSize().height;

        x += vx;
        if(x <= 0){
            x = 0;
            vx = -vx;
        } else if(x + 2*radius >=  boardWidth){
            x = boardWidth-2*radius;
            vx = -vx;
        }

        y += vy;
        if(y <= 0){
            y = 0;
            vy = -vy;
        } else if(y + 2*radius >= boardHeight){
            y = boardHeight-2*radius;
            vy = -vy;
        }
        g_count ++;
        f_count ++;
        if(g_count == 10) {
            vy++;
            g_count = 0;
        }
        
        if(f_count == 500){
            if(Math.abs(vy) >= Math.abs(vx)){
                if(vy > 0) vy--;
                else if(vy < 0) vy++;
            } else {
                if(vx > 0) vx--;
                else if(vx < 0) vx++;
            }
            f_count = 0;
        }
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/19 19:30
     * @Version 1.0
     */
    public Rectangle boundingBox(){
        return new Rectangle(x - radius - 1, y - radius - 1, radius + radius + 2,radius + radius + 2);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/19 19:30
     * @Version 1.0
     * @param g
     */
    @Override
    public void paint(Graphics g){
        Image image;
        image = Toolkit.getDefaultToolkit().getImage(picturePath);
        g.drawImage(image, x, y,radius+radius,radius+radius,board);
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/19 19:30
     * @Version 1.0
     * 删除球
     */
    public void delete(){
        board.setBall(null);
    }

    @Override
    public Integer is_collision(BouncingBall ball) {
        return 0;
    }
}
