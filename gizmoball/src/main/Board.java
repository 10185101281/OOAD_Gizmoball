package main;

import component.XRectangle;
import component.XTriangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @Author BaoLiang
 * @Date 2020/11/18 11:30
 * @Version 1.0
 */
public class Board extends JPanel{
    private Integer boardWidth;
    private Integer boardHeight;
    private Color[] boardBackground = new Color[]{
            new Color(0xF0FFFF),//天空之城
            new Color(0x1C1C1C),//幽邃深渊
            new Color(0x00CED1),//碧蓝航线
            new Color(0x2E8B57),//旷野之息
            new Color(0xBEBEBE),//灰烬之海
    };
    private BouncingBall ball;
    private ArrayList<Component> componentList =  new ArrayList<Component>();
    private String nowComponent;

    public void setNowComponent(String nowComponent) {
        this.nowComponent = nowComponent;
    }

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println(nowComponent);
            if(nowComponent == null) return ;
            Point positon = getMousePosition();
            int x = positon.x/20 * 40;
            int y = positon.y/20 * 40;
            if(nowComponent.equals("rectangle")){
                XRectangle rectangle = new XRectangle(x,y);
                componentList.add(rectangle);
                refresh();
            } else if(nowComponent.equals("triangle")){

            } else if(nowComponent.equals("circle")){

            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * 直接创建一个大小800*800的Board
     */
    public Board(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800,800));
        this.setBackground(boardBackground[0]);
        boardWidth = 800;
        boardHeight = 800;
        this.addMouseListener(mouseListener);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * @param width 宽度
     * @param height 高度
     * 创建一个大小 width*height 的Board
     */
    public Board(int width,int height){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(boardBackground[0]);
        boardWidth = width;
        boardHeight = height;
        this.addMouseListener(mouseListener);
    }


    public void setBall(BouncingBall ball){
        this.ball = ball;
    }
    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.paint(g);
        for(Component component: componentList) component.paint(g);
    }
    public void refresh() {
        ball.move();
        repaint(0, 0, boardWidth, boardHeight);
    }
}
