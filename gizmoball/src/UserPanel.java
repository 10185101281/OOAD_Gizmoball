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
    private JPanel boardPanel, sideBarPanel;
    private JPanel titlePanel, modeControlPanel, layoutConsolePanel;
    private JPanel componentPanel, toolPanel;
    private JButton bordBackgroundButton;
    private Color[] boardBackground = new Color[]{
            new Color(0x1C1C1C),
            new Color(0x00CED1),
            new Color(0x2E8B57),
            new Color(0xF0FFFF)
    };
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化BoardPanel。
     * 设置BordPanel为绝对布局，为其渲染背景。
     */
    private void initBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setLayout(null);
        boardPanel.setPreferredSize(new Dimension(800,800));
        boardPanel.setBackground(boardBackground[0]);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 16:00
     * @Version 1.0
     * 随机生成头像。
     * @param width 头像的宽度
     * @param height 头像的高度
     * @return 随机生成的头像
     */
    private ImageIcon getRandomAvatar(int width,int height){
        Integer randomInteger = new Random().nextInt(16);
        ImageIcon imageIcon = new ImageIcon("src/picture/avatar/"+randomInteger+".png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return imageIcon;
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 16:00
     * @Version 1.0
     * 初始化titlePanel。
     * 设置为流布局。
     * 添加随机头像，并设置标题。
     */
    private void initTitlePanel(){
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.setPreferredSize(new Dimension(300,100));
        titlePanel.setBackground(new Color(0xF5F5F5));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JLabel avatar = new JLabel(getRandomAvatar(80,80));
        avatar.setBorder(BorderFactory.createLineBorder(new Color(0x696969),4,true));
        JLabel titleText = new JLabel("GIZMO BALL");
        titleText.setPreferredSize(new Dimension(200,90));
        titleText.setFont(new Font("Dialog",Font.BOLD,25));
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setVerticalAlignment(SwingConstants.CENTER);
        titleText.setOpaque(true);
        titleText.setBackground(new Color(0xF0F8FF));
        titleText.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));

        titlePanel.add(avatar);
        titlePanel.add(titleText);
    }
    private void initModeControlPanel(){

    }
    private void initComponentPanel(){

    }
    private void initToolPanel(){

    }
    private void initLayoutConsolePanel(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:45
     * @Version 1.0
     * 初始化SideBar。
     * 设置整体布局为Border布局，其中titlePanel居上，modeControlPanel居中，layoutConsolePanel居下。
     */
    private void initSideBarPanel(){
        sideBarPanel = new JPanel(new BorderLayout());
        sideBarPanel.setPreferredSize(new Dimension(300, 800));
        sideBarPanel.setBorder(BorderFactory.createLineBorder(new Color(0xF5FFFA),2));

        initTitlePanel();
        initModeControlPanel();
        initLayoutConsolePanel();
        sideBarPanel.add(titlePanel,BorderLayout.NORTH);
        //sideBarPanel.add(modeControlPanel,BorderLayout.CENTER);
        //sideBarPanel.add(layoutConsolePanel,BorderLayout.SOUTH);
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化ContentPane，即初始化整个UserPanel。
     * 设置ContentPane为Border布局，其中boardPanel居中部，sideBarPanel居右部。
     */
    private void initContentPane(){
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1100,800));

        initBoardPanel();
        initSideBarPanel();
        contentPane.add(boardPanel,BorderLayout.CENTER);
        contentPane.add(sideBarPanel,BorderLayout.EAST);
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
        setLocation(screenWidth/6, screenHeight/12);

    }
    public UserPanel(String title){
        super(title);
        setCenter();
        initContentPane();
    }
}
