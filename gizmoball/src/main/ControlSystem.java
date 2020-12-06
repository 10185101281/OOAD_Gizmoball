package main;

import component.BouncingBall;

import javax.swing.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class ControlSystem {

  //  private static BouncingBall ball;
    private static Board board;
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * 获取Board
     */
    public static Board getBoard(){
        return board;
    }

//    public static void createBall(){
//        ball = new BouncingBall(board);
//        board.setBall(ball);
//    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * 创建一个新的Board
     */
    public static void createBoard(){
        board = new Board();
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 14:30
     * @Version 1.0
     * 创建一个UserPanel
     * @param width UserPanel的宽度
     * @param height UserPanel的高度
     * @return
     */
    private static void createUserPanel(int width,int height){
        UserPanel userPanel = new UserPanel("Gizmobll");
        userPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userPanel.setSize(width, height);
        userPanel.setVisible(true);
    }

    public static void main(String[] args){
        createUserPanel(1150, 830);
    }
}
