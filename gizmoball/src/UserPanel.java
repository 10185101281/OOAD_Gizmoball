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
    private JPanel specialComponentPanel, componentPanel, toolPanel;
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

    private String[] characterNames = new String[]{
            "Qiqi","Jean","Amber","Lisa","Barbara","Venti",
            "Klee","Noelle","Fischl","Sucrose","Mona","Xiao",
            "Ningguang","Beidou","Keqing"
    };
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
        Integer randomInteger = new Random().nextInt(characterNames.length);
        ImageIcon imageIcon = new ImageIcon("gizmoball/src/picture/avatar/"+characterNames[randomInteger]+".png");
        //System.out.println("gizmoball/src/picture/avatar/"+characterNames[randomInteger]+".png");
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
        titlePanel.setPreferredSize(new Dimension(300,95));
        titlePanel.setBackground(new Color(0xF5F5F5));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        JLabel titleText = new JLabel("GIZMO BALL");
        titleText.setPreferredSize(new Dimension(300,90));
        titleText.setFont(new Font("Times New Roman",Font.BOLD,40));
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setVerticalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleText);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 20:30
     * @Version 1.0
     * 初始化modeControlPanel。
     * 设置为流布局。
     * 设置两个按钮，layoutMode和playMode，控制两个模式。
     */
    private void initModeControlPanel(){
        modeControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        modeControlPanel.setPreferredSize(new Dimension(300, 95));
        modeControlPanel.setBackground(new Color(0xF5F5F5));
        modeControlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        JButton layoutMode = new JButton("Layout Mode");
        layoutMode.setPreferredSize(new Dimension(100, 60));
        JButton playMode = new JButton("Play Mode");
        playMode.setPreferredSize(new Dimension(100,60));
        modeControlPanel.add(layoutMode);
        modeControlPanel.add(playMode);
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化specialComponentPanel
     */
    private void initSpecialComponentPanel(){
        specialComponentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        specialComponentPanel.setPreferredSize(new Dimension(300,95));
        specialComponentPanel.setBackground(new Color(0xE6E6FA));
        specialComponentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        JLabel avatar = new JLabel(getRandomAvatar(80,80));
        avatar.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        specialComponentPanel.add(avatar);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化componentPanel
     */
    private void initComponentPanel(){
        componentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        componentPanel.setPreferredSize(new Dimension(300,295));
        componentPanel.setBackground(new Color(0xE6E6FA));
        componentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        JRadioButton[] jrs = new JRadioButton[]{
                new JRadioButton(), new JRadioButton(),
                new JRadioButton(), new JRadioButton(),
                new JRadioButton(), new JRadioButton(),
        };
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i=0; i<3; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(componentPanel.getPreferredSize().width-5,componentPanel.getPreferredSize().height/3-5));
            tJPanel.setBackground(new Color(0x54FF9F));
            for(int j=0; j<2; j++){
                buttonGroup.add(jrs[i*2+j]);
                tJPanel.add(jrs[i*2+j]);
                tJPanel.add(new JLabel(getRandomAvatar(80,80)));
            }
            componentPanel.add(tJPanel);
        }
        jrs[0].setSelected(true);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化toolPanel
     */
    private void initToolPanel(){
        toolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toolPanel.setPreferredSize(new Dimension(300,195));
        toolPanel.setBackground(new Color(0xE6E6FA));
        toolPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        for(int i=0; i<2; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(toolPanel.getPreferredSize().width-5,toolPanel.getPreferredSize().height/2-5));
            tJPanel.setBackground(new Color(0x54FF9F));
            for(int j=0; j<2; j++){
                JButton tJButton = new JButton(getRandomAvatar(80,80));
                tJButton.setPreferredSize(new Dimension(80,80));
                tJButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                tJPanel.add(tJButton);
            }
            toolPanel.add(tJPanel);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化LayoutConsolePanel。
     * 设置整体布局为Border布局，其中speceialComponentPanel居上，componentPanel居中，toolPanel居下。
     */
    private void initLayoutConsolePanel(){
        layoutConsolePanel = new JPanel(new BorderLayout());
        layoutConsolePanel.setPreferredSize(new Dimension(300, 595));
        layoutConsolePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        initSpecialComponentPanel();
        initComponentPanel();
        initToolPanel();
        layoutConsolePanel.add(specialComponentPanel,BorderLayout.NORTH);
        layoutConsolePanel.add(componentPanel,BorderLayout.CENTER);
        layoutConsolePanel.add(toolPanel,BorderLayout.SOUTH);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:45
     * @Version 1.0
     * 初始化SideBarPanel。
     * 设置整体布局为Border布局，其中titlePanel居上，modeControlPanel居中，layoutConsolePanel居下。
     */
    private void initSideBarPanel(){
        sideBarPanel = new JPanel(new BorderLayout());
        sideBarPanel.setPreferredSize(new Dimension(305, 800));
        sideBarPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY,4));

        initTitlePanel();
        initModeControlPanel();
        initLayoutConsolePanel();
        sideBarPanel.add(titlePanel,BorderLayout.NORTH);
        sideBarPanel.add(modeControlPanel,BorderLayout.CENTER);
        sideBarPanel.add(layoutConsolePanel,BorderLayout.SOUTH);
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
        contentPane.setPreferredSize(new Dimension(1105,800));

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
