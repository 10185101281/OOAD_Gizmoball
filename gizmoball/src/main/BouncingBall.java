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
    public BouncingBall(Board board){
        this.x = 400;
        this.y = 400;
        Random random = new Random();
        this.vx = random.nextInt(5)-2;
        this.vy = random.nextInt(5)-2;
        this.radius = 10;
        this.board = board;
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

        x += vx;
        if(x <= radius){
            x = radius;
            vx = -vx;
        } else if(x + radius >=  boardWidth){
            x = boardWidth-radius;
            vx = -vx;
        }

        y += vy;
        if(y <= radius){
            y = radius;
            vy = -vy;
        } else if(y + radius >= boardHeight){
            vy = -vy;
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
    public void paint(Graphics g){
        Rectangle clipRect = g.getClipBounds();
        if (clipRect.intersects(this.boundingBox())) {
            g.setColor(color);
            g.fillOval(x - radius, y - radius, radius + radius, radius + radius);
        }
    }
}
