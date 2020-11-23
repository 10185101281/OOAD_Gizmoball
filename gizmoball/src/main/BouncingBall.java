package main;

import java.awt.*;
import java.util.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/18 9:00
 * @Version 1.0
 */
public class BouncingBall {
    private Integer x, y, vx, vy, ax, ay;
    private Integer radius;
    private Board board;
    private Color color = new Color(0x00000);

    public Integer getX() {
        return x;
    }
    public Integer getY(){
        return y;
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
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    private void randomAttributes(){
        Random random = new Random();
        vx = random.nextInt(5)-2;
        vy = random.nextInt(5)-2;
    }
    public BouncingBall(Board board){
        this.x = 400;
        this.y = 400;
        this.radius = 10;
        this.board = board;
        randomAttributes();
    }
    public BouncingBall(int x,int y,int vx,int vy,int ax,int ay,Board board){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
        this.radius = 10;
        this.board = board;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/19 19:30
     * @Version 1.0
     * 模拟小球运动
     */
    public void move(){
        Integer boardWidth = board.getPreferredSize().width;
        Integer boardHeight = board.getPreferredSize().height;

        boolean is = false;
        x += vx;
        if(x <= radius){
            x = radius;
            vx = -vx;
            is = true;
        } else if(x + radius >=  boardWidth){
            x = boardWidth-radius;
            vx = -vx;
            is = true;
        }

        y += vy;
        if(y <= radius){
            y = radius;
            vy = -vy;
            is = true;
        } else if(y + radius >= boardHeight){
            vy = -vy;
            is = true;
        }
        if(is) randomAttributes();
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
    public void paint(Graphics g){
        Rectangle clipRect = g.getClipBounds();
        if (clipRect.intersects(this.boundingBox())) {
            g.setColor(color);
            g.fillOval(x - radius, y - radius, radius + radius, radius + radius);
        }
    }
}
