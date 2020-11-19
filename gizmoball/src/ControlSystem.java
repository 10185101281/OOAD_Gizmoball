import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Timer;
/**
 * @Author
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class ControlSystem {

    private static BouncingBall bouncingBall;
    private static Board board;
    private static final int FRAMES_PER_SECOND = 100;
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * 获取Board
     */
    public static Board getBoard(){
        return board;
    }
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
        Timer timer = new Timer();
        timer.schedule(new Test1(),0,1000/FRAMES_PER_SECOND);
        createUserPanel(1105, 800);
    }
}

class Test1 extends TimerTask{
    private static int cnt = 0;
    @Override
    public void run(){
        cnt++;
        System.out.println("Text:"+cnt);
    }
}

