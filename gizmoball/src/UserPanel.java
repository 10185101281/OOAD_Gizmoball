import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class UserPanel extends JFrame{
    private Container contentPane;
    private JPanel boardPanel, sideBarPanel, titlePanel, componentPanel, toolPanel;
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 9:30
     * @Version 1.0
     * 初始化BoardPanel，
     */
    private void initBoardPanel(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 9:30
     * @Version 1.0
     * 初始化BoardPanel
     */
    private void initTitlePanel(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 9:30
     * @Version 1.0
     * 初始化BoardPanel
     */
    private void initComponentPanel(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 9:30
     * @Version 1.0
     * 初始化BoardPanel
     */
    private void initToolPanel(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 9:30
     * @Version 1.0
     * 初始化BoardPanel
     */
    private void initSideBarPanel(){

    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化ContentPane，即初始化整个UserPanel。
     */
    private void initContentPane(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:00
     * @Version 1.0
     * 设置整体UserPanel在屏幕居中
     */
    private void setCenter(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth/4, screenHeight/10);

    }
    public UserPanel(String title){
        super(title);
        setCenter();
        initContentPane();
    }
}
