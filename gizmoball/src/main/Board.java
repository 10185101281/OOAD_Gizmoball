package main;

import component.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private XBarrier boardBarrier;
    private ArrayList<XComponent> componentList;
    private XComponent[][] componentMap;
    private String nowComponent;
    private XComponent selectedComponent;
    private boolean forbid;

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
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        boardWidth = 800;
        boardHeight = 800;
        componentList = new ArrayList<>();
        componentMap = new XComponent[810][810];
        selectedComponent = null;
        forbid = false;
        this.addMouseListener(mouseListener);
        this.addKeyListener(keyListener);
        requestFocus();
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
        componentList = new ArrayList<>();
        componentMap = new XComponent[width+10][height+10];
        selectedComponent = null;
        forbid = false;
        this.addMouseListener(mouseListener);
        this.addKeyListener(keyListener);
        requestFocus();
    }
    public void setForbid(boolean forbid){this.forbid =  forbid;}
    public Integer getBoardWidth(){return boardWidth;}
    public Integer getBoardHeight(){return boardHeight;}
    private Board getThisBoard(){
        return this;
    }
    public final ArrayList<XComponent> getComponentlist(){
        return componentList;
    }
    public final XComponent[][] getComponentMap(){return componentMap; }
    public boolean componentMapIsEmpty(int x,int y){return componentMap[x][y] == null;}

    public BouncingBall getBall() { return ball; }
    public void updateComponentMap(int x, int y, XComponent xComponent){componentMap[x][y]=xComponent;}
    public void addComponentToList(XComponent component){componentList.add(component);}
    public void setNowComponent(String nowComponent) {
        this.nowComponent = nowComponent;
    }

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            System.out.println("Key pressed..."+code);
            //left 37, up 38, right 39, down 40
            if(boardBarrier == null) return ;
            if(code == 37) boardBarrier.move_left();
            else if(code == 38) boardBarrier.move_up();
            else if(code == 39) boardBarrier.move_right();
            else if(code == 40) boardBarrier.move_down();
            refresh(false);
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            requestFocus();
            if(forbid) return ;
            if(selectedComponent != null){
                selectedComponent.setSelected(false);
                selectedComponent = null;
            }

            if(nowComponent == null) return ;
            Point positon = getMousePosition();
            int x = positon.x/40 * 40;
            int y = positon.y/40 * 40;

            //System.out.println(nowComponent+" "+x+","+y);
            if(nowComponent.equals("placement")){
                if(componentMap[x][y] != null){
                    selectedComponent = componentMap[x][y];
                    selectedComponent.setSelected(true);
                }
            } else {
                if(componentMap[x][y] != null) return ;
                if(nowComponent.equals("ball")){
                    if(ball != null) return ;
                    ball = new BouncingBall(x,y,getThisBoard());
                } else if(nowComponent.equals("rectangle")){
                    XComponent rectangle = new XRectangle(x,y,getThisBoard());
                    if(rectangle.is_collision(ball) > 0){
                        rectangle.delete();
                        return ;
                    }
                } else if(nowComponent.equals("triangle")){
                    XComponent triangle = new XTriangle(x,y,getThisBoard());
                    if(triangle.is_collision(ball) > 0){
                        triangle.delete();
                        return ;
                    }
                } else if(nowComponent.equals("circle")){
                    XComponent circle = new XCircle(x,y,getThisBoard());
                    if(circle.is_collision(ball) > 0){
                        circle.delete();
                        return ;
                    }
                } else if(nowComponent.equals("blackhole")){
                    XComponent blackhole = new XBlackHole(x,y,getThisBoard());
                    if(blackhole.is_collision(ball) > 0){
                        blackhole.delete();
                        return ;
                    }
                } else if(nowComponent.equals("boom")){
                    XComponent boom = new XBoom(x,y,getThisBoard());
                    if(boom.is_collision(ball)>0){
                        boom.delete();
                        return ;
                    }
                } else if(nowComponent.equals("airflow")){
                    XComponent airflow = new XAirflow(x,y,getThisBoard());
                    if(airflow.is_collision(ball)>0){
                        airflow.delete();
                        return ;
                    }
                } else if(nowComponent.equals("iceball")) {
                    XComponent iceball = new XIceball(x, y, getThisBoard());
                    if (iceball.is_collision(ball) > 0) {
                        iceball.delete();
                        return;
                    }
                } else if(nowComponent.equals("barrier")){
                    if(boardBarrier != null) return ;
                    if(componentMap[x+XComponent.base][y] != null) return ;
                    if(x+XComponent.base >= getBoardWidth()) return ;
                    XComponent barrier = new XBarrier(x, y, getThisBoard());
                    boardBarrier = (XBarrier)barrier;
                    if(barrier.is_collision(ball) > 0){
                        barrier.delete();
                        return ;
                    }
                } else if(nowComponent.equals("straightPipe")){
                    XComponent straightPipe = new XStraightPipe(x, y, getThisBoard());
                    if(straightPipe.is_collision(ball) > 0){
                        straightPipe.delete();
                        return ;
                    }
                } else if(nowComponent.equals("curvedPipe")){
                    XComponent curvedPipe = new XCurvedPipe(x, y, getThisBoard());
                    if(curvedPipe.is_collision(ball) > 0){
                        curvedPipe.delete();
                        return ;
                    }
                }
            }
            refresh(false);
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

    public void setSelectedComponent(XComponent selectedComponent){
        this.selectedComponent = selectedComponent;
    }
    public XComponent getSelectedComponent(){
        return selectedComponent;
    }
    public void setBall(BouncingBall ball){
        this.ball = ball;
    }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<XComponent> deleteList = new ArrayList<>();
        for(XComponent component: componentList){
            if(component.getIsDeleting()) deleteList.add(component);
        }
        for(XComponent component: deleteList){
            componentList.remove(component);
        }
        for(XComponent component: componentList) {
            if(component == null) continue;
            if(component.getIsDeleting()) continue;
            component.paint(g);
        }
        if(ball != null) ball.paint(g);
    }
    public void refresh(boolean hasMove) {
        if(hasMove&&ball!=null) ball.move();
        repaint(0, 0, boardWidth, boardHeight);
    }
}
